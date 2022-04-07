package me.bxhuynh.lab11_noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fabAddNewNote;
    ArrayList<NoteModel> noteList;
    DBHandler dbHandler;
    NoteListAdapter noteListAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabAddNewNote = findViewById(R.id.fabCreateNewNote);
        fabAddNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddNewNoteActivity.class);
                startActivity(i);
            }
        });

        dbHandler = new DBHandler(MainActivity.this);
        recyclerView = findViewById(R.id.recyclerView_noteList);
        noteList = new ArrayList<>();
        noteList = dbHandler.readNotes();
        noteListAdapter = new NoteListAdapter(noteList, MainActivity.this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(noteListAdapter);

        registerForContextMenu(recyclerView);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ctx_edit:
                noteListAdapter.editNote();
                break;
            case R.id.ctx_delete:
                noteListAdapter.deleteNote();
                break;
        }

        return super.onContextItemSelected(item);
    }
}