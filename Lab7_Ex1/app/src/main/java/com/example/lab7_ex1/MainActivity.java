package com.example.lab7_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Product> listProduct;
    ProductListViewAdapter productListViewAdapter;
    ListView listViewProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init ListProduct, adding data
        listProduct = new ArrayList<>();
        listProduct.add(new Product(1, "Iphone 12", 600));
        listProduct.add(new Product(2, "Iphone 13", 700));
        listProduct.add(new Product(3, "Sony Abc", 800));
        listProduct.add(new Product(4, "Samsung XYZ", 900));
        listProduct.add(new Product(5, "SP 5", 500));
        listProduct.add(new Product(6, "SP 6", 700));
        listProduct.add(new Product(7, "SP 7", 800));
        listProduct.add(new Product(8, "SP 8", 900));


        productListViewAdapter = new ProductListViewAdapter(listProduct);
        listViewProduct = findViewById(R.id.productList);
        listViewProduct.setAdapter(productListViewAdapter);

        // on press product
        listViewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = (Product) productListViewAdapter.getItem(position);
                Toast.makeText(MainActivity.this, product.name, Toast.LENGTH_LONG).show();
            }
        });

        // on press button delete
        findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listProduct.size() > 0) {
                    int productpost = 0;
                    listProduct.remove(productpost);
                    productListViewAdapter.notifyDataSetChanged();
                }
            }
        });

    }
}