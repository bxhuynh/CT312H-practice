package com.example.lab4_eventhandling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText a, b, tv_result;
    Button btn_add, btn_minus, btn_mult, btn_div, btn_combine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         a = (EditText) findViewById(R.id.et_A);
         b = (EditText) findViewById(R.id.et_B);
            tv_result = (EditText) findViewById(R.id.tv_value);

        btn_add = (Button) findViewById(R.id.btn_plus);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc("add");
            }
        });

        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc("minus");
            }
        });

        btn_combine = (Button) findViewById(R.id.btn_combine);
        btn_combine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc("combine");
            }
        });

        btn_mult = (Button) findViewById(R.id.btn_mult);
        btn_mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc("multi");
            }
        });


        btn_div = (Button) findViewById(R.id.btn_div);
        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc("div");
            }
        });


    }

    public void calc(String operator) {
        try {
            if (a.getText().toString().isEmpty() || b.getText().toString().isEmpty()) {
                tv_result.setText("Please enter both a and b");
                return;
            }

            int result;
            int aVal = Integer.parseInt(a.getText().toString());
            int bVal =  Integer.parseInt(b.getText().toString());

            switch (operator) {
                case "add":
                    result = aVal + bVal;
                    break;
                case "minus":
                    result = aVal - bVal;
                    break;
                case "multi":
                    result = aVal * bVal;
                    break;
                case "div":
                    result = aVal / bVal;
                    break;
                case "combine":
                    result = aVal & bVal;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + operator);
            }
            tv_result.setText(Integer.toString(result));
        }
        catch (Exception e) {

        }
    }
}