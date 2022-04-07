package me.bxhuynh.lab11_noteapp;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder>{
    //2. adapter var
    ArrayList<NoteModel> noteList;
    Context context;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    //3. constructor
    public NoteListAdapter(ArrayList<NoteModel> noteList, Context context) {
        this.noteList = noteList;
        this.context = context;
    }

    //6. get item count
    @Override
    public int getItemCount() {
        return noteList.size();
    }

    //4. onCreateViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new ViewHolder(v);
    }


    //5. onBind
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NoteModel note = noteList.get(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvContent.setText(note.getContent());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                setPosition(position);
                return false;
            }
        });
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    //1. create view holder
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle, tvContent;
        public ViewHolder(@NonNull View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tv_noteTitle);
            tvContent = view.findViewById(R.id.tv_noteContent);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    Intent i = new Intent(context, EditNoteActivity.class);
                    NoteModel note = noteList.get(pos);
                    i.putExtra("id", note.getId());
                    i.putExtra("title", note.getTitle());
                    i.putExtra("content", note.getContent());
                    context.startActivity(i);
                }
            });

            view.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                    contextMenu.add(Menu.NONE, R.id.ctx_edit, Menu.NONE, "Edit");
                    contextMenu.add(Menu.NONE, R.id.ctx_delete, Menu.NONE, "Delete");
                }
            });

        }


    }


    public void editNote() {
        NoteModel note = noteList.get(position);
        Intent i = new Intent(context, EditNoteActivity.class);
        i.putExtra("id", note.getId());
        i.putExtra("title", note.getTitle());
        i.putExtra("content", note.getContent());
        context.startActivity(i);
    }

    public void deleteNote() {
        DBHandler dbHandler;
        dbHandler =  new DBHandler(context);
        NoteModel note  = noteList.get(position);
        dbHandler.deleteNoteById(note.getId());
        notifyItemRemoved(position);
        Toast.makeText(context, "Deleted" + note.getId(), Toast.LENGTH_SHORT).show();
    }

}
