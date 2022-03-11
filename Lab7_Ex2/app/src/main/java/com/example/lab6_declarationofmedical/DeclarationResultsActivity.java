package com.example.lab6_declarationofmedical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeclarationResultsActivity extends AppCompatActivity {
    DeclarationListViewAdapter listAdapter;
    ListView listViewDeclaration;
    ArrayList<Declaration> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration_results);
        list = (ArrayList<Declaration>)getIntent().getSerializableExtra("listDeclaration");
        listAdapter = new DeclarationListViewAdapter(list);
        listViewDeclaration = findViewById(R.id.lv_results);
        listViewDeclaration.setAdapter(listAdapter);

        //register
        registerForContextMenu(listViewDeclaration);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.lv_results) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_list, menu);
        }
    }

    // menu item select listener
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == R.id.menu_item_delete) {
            if ( info.position > -1) {
                list.remove(info.position);
                listAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Deleted",Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    public void onBack() {
        finish();
    }
}
