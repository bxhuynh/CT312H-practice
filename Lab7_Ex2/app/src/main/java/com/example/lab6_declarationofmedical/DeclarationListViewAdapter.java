package com.example.lab6_declarationofmedical;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DeclarationListViewAdapter extends BaseAdapter {
        final ArrayList<Declaration> listDeclaration;
        DeclarationListViewAdapter(ArrayList<Declaration> listDeclaration) {
            this.listDeclaration = listDeclaration;
        }

    @Override
    public int getCount() {
        return listDeclaration.size();
    }

    @Override
    public Object getItem(int i) {
        return listDeclaration.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listDeclaration.get(i).id;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View viewDeclaration;
        if (convertView == null)
            viewDeclaration = View.inflate(parent.getContext(), R.layout.result_item_view, null);
        else viewDeclaration = convertView;

        Declaration declaration = (Declaration) getItem(i);
        ((TextView) viewDeclaration.findViewById(R.id.tv_title)).setText(declaration.title);
        ((TextView) viewDeclaration.findViewById(R.id.tv_value)).setText(declaration.value);
        return viewDeclaration;
    }
}
