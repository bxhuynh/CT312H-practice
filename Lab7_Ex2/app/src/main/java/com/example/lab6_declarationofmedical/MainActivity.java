package com.example.lab6_declarationofmedical;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    CheckBox first, second, third;
    EditText fname, address, phone, date1, date2, date3;
    DatePickerDialog.OnDateSetListener setListenerDate1, setListenerDate2, setListenerDate3;
    String gender = "Unknown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fname = (EditText) findViewById(R.id.et_full_name);
        address = (EditText) findViewById(R.id.et_address);
        phone = (EditText) findViewById(R.id.et_phone);

        date1 = (EditText) findViewById(R.id.et_date1);
        date2 = (EditText) findViewById(R.id.et_date2);
        date3 = (EditText) findViewById(R.id.et_date3);

        first = (CheckBox) findViewById(R.id.cb_first);
        second = (CheckBox) findViewById(R.id.cb_second);
        third = (CheckBox) findViewById(R.id.cb_third);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (first.isChecked()) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this, android.R.style.Theme_Material_Dialog_MinWidth, setListenerDate1, year, month, day);
                datePickerDialog.getWindow();
                datePickerDialog.show(); } else {
                    Toast.makeText(getApplicationContext(), "Please check the box to enter date.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        setListenerDate1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = day + "/" + (month + 1) + "/" + year;
                date1.setText(date);
            }
        };

        date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (second.isChecked()) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this, android.R.style.Theme_Material_Dialog_MinWidth, setListenerDate2, year, month, day);
                datePickerDialog.getWindow();
                datePickerDialog.show();}else {
                    Toast.makeText(getApplicationContext(), "Please check the box to enter date.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        setListenerDate2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = day + "/" + (month + 1) + "/" + year;
                date2.setText(date);
            }
        };

        date3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (third.isChecked()) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this, android.R.style.Theme_Material_Dialog_MinWidth, setListenerDate3, year, month, day);
                datePickerDialog.getWindow();
                datePickerDialog.show();}else {
                    Toast.makeText(getApplicationContext(), "Please check the box to enter date.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        setListenerDate3 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = day + "/" + (month + 1) + "/" + year;
                date3.setText(date);
            }
        };
    }

    public void onCheckGender(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rb_male:
                if (checked){
                gender = "Male";
                break;}
            case R.id.rb_female:
                if(checked) {
                gender = "Female";
                break;}
            default:
                gender = "Unknown";
        }
    }

    // Collect values from all widgets and show it to dialog
    public void onOk(View view) {
        ArrayList <Declaration> listDeclaration;
        listDeclaration = new ArrayList<>();
        listDeclaration.add(new Declaration( 1, "Name:", fname.getText().toString()));
        listDeclaration.add(new Declaration(2,"Gender:", gender));
        listDeclaration.add(new Declaration(3,"Address:", address.getText().toString()));
        listDeclaration.add(new Declaration(4,"Phone number:", phone.getText().toString()));
        listDeclaration.add(new Declaration(5,"First dose:", date1.getText().toString()));
        listDeclaration.add(new Declaration(6,"Second dose:", date2.getText().toString()));
        listDeclaration.add(new Declaration(7,"Third dose:", date3.getText().toString()));

        Intent i = new Intent(this, DeclarationResultsActivity.class);
        i.putExtra("listDeclaration", listDeclaration);
        startActivity(i);
    }

//  Reset all values on cancel
    public void onCancel(View view) {
        fname.setText("");
        address.setText("");
        phone.setText("");
        RadioGroup rg = (RadioGroup)findViewById(R.id.rg_gender);
        rg.clearCheck();
        date1.setText("");
        date2.setText("");
        date3.setText("");
        first.setChecked(false);
        second.setChecked(false);
        third.setChecked(false);
    }
}