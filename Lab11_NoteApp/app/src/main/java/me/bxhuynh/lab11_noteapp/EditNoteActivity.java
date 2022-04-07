package me.bxhuynh.lab11_noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditNoteActivity extends AppCompatActivity {
    EditText etTitle, etContent;
    Button cancel, edit;
    int id;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        dbHandler = new DBHandler(EditNoteActivity.this);

        etTitle = findViewById(R.id.et_editTitle);
        etContent = findViewById(R.id.et_editContent);
        Bundle extras = getIntent().getExtras();
        etTitle.setText(extras.getString("title"));
        etContent.setText(extras.getString("content"));
        id = extras.getInt("id");

        cancel = findViewById(R.id.btnCancelEdit);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        edit = findViewById(R.id.btnEdit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler.updateNote(id, etTitle.getText().toString(), etContent.getText().toString());
                Intent i = new Intent(EditNoteActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHandler.close();
    }
}