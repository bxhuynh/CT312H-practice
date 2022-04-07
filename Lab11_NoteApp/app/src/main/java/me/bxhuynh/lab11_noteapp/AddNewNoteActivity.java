package me.bxhuynh.lab11_noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewNoteActivity extends AppCompatActivity {
    Button btnCancel, btnAdd;
    EditText etTitle, etContent;
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);

        dbHandler = new DBHandler(AddNewNoteActivity.this);

        btnCancel = findViewById(R.id.btnCancelCreate);
        btnAdd = findViewById(R.id.btnCreate);
        etTitle = findViewById(R.id.et_editTitle);
        etContent = findViewById(R.id.et_editContent);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();
                if (title.equals("") || content.equals("")) {
                    Toast.makeText(AddNewNoteActivity.this, "Please enter both title and content.", Toast.LENGTH_SHORT).show();
                } else {
                    dbHandler.addNewNote(title, content);
                    Toast.makeText(AddNewNoteActivity.this, "Added new note.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AddNewNoteActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHandler.close();
    }
}