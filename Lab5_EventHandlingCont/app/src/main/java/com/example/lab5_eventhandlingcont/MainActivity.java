package com.example.lab5_eventhandlingcont;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public String gender = "";
    public boolean java, cpp, go, js;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.rd_female:
                if (checked){
                    gender="Female";
                    break;}
            case R.id.rd_male:
                if (checked){
                    gender="Male";
                    break;}
            default:
                gender="Other";

        }
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case  R.id.cb_CPP:
                cpp=checked;
                break;
            case  R.id.cb_Java:
                java=checked;
                break;
            case  R.id.cb_Golang:
               go=checked;
                break;
            case  R.id.cb_Javascript:
               js=checked;
                break;
            default:
                return;
        }
    }

    public void onClickButton(View view) {
        CharSequence text = "Gender: ";
        if (gender.isEmpty()) {
            text = text + "not choose yet.";
        } else {text = text + gender;}

                text = text + ". Fav Programming Lang(s): ";
        if (!js && !go && !java && !cpp)
            text = text + "None";
        if (js) text = text+"Javascript ";
        if (java) text = text+"Java ";
        if (go) text = text+"Golang ";
        if (cpp) text = text+"C++ ";

        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

}