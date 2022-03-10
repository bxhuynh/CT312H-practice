package com.example.lab6_declarationofmedical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class DeclarationResultsActivity extends AppCompatActivity {
    DeclarationListViewAdapter listAdapter;
    ListView listViewDeclaration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration_results);
        ArrayList<Declaration> list = (ArrayList<Declaration>)getIntent().getSerializableExtra("listDeclaration");

        listAdapter = new DeclarationListViewAdapter(list);
        listViewDeclaration = findViewById(R.id.lv_results);
        listViewDeclaration.setAdapter(listAdapter);
    }

    public void onBack() {
        finish();
    }
}
