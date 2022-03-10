package com.example.lab7_ex1;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class ProductListViewAdapter  extends BaseAdapter {
    final ArrayList<Product> productList;

    ProductListViewAdapter(ArrayList<Product> productList) {
        this.productList = productList;
    }

    @Override
    public int getCount() {
        //return number of elements, invoked by ListView
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        // get product data by position of Adapter
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        //get product id by position
        return productList.get(position).productID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //convertView is View which is an element of ListVoew
        //if convertView != null: this view is recycle, only update data
        //if convertView == null: create new
        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.product_view, null);
        } else viewProduct = convertView;

        //Bind product data to view
        Product product = (Product) getItem(position);
        ((TextView) viewProduct.findViewById(R.id.productId)).setText(String.format("ID: %d", product.productID));
        ((TextView) viewProduct.findViewById(R.id.productName)).setText(String.format("Name : %s", product.name));
        ((TextView) viewProduct.findViewById(R.id.productPrice)).setText(String.format("Price: %d", product.price));

        return viewProduct;
    }

}
