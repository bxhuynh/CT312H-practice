package me.bxhuynh.lab12_registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText fullName, userName, email, phoneNumber, password;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullName = findViewById(R.id.et_fname);
        userName = findViewById(R.id.et_username);
        email = findViewById(R.id.et_email);
        phoneNumber = findViewById(R.id.et_phone);
        password = findViewById(R.id.et_password);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullNameStr = fullName.getText().toString();
                String userNameStr = userName.getText().toString();
                String emailStr = email.getText().toString();
                String phoneStr = phoneNumber.getText().toString();
                String passwordStr = password.getText().toString();

                if (TextUtils.isEmpty(fullNameStr) || TextUtils.isEmpty(userNameStr) || TextUtils.isEmpty(emailStr) || TextUtils.isEmpty(phoneStr) || TextUtils.isEmpty(passwordStr) ){
                    Toast.makeText(MainActivity.this, "Please fill all the fields.", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference ref = database.getReference("users");
                    DatabaseReference pushedKey = ref.push();
                    User user = new User();
                    user.setFullname(fullNameStr);
                    user.setUsername(userNameStr);
                    user.setEmail(emailStr);
                    user.setPhone(phoneStr);
                    user.setPassword(passwordStr);
                    pushedKey.setValue(user);
                    Toast.makeText(MainActivity.this, "User registered. UID: " + pushedKey.getKey(), Toast.LENGTH_SHORT).show();
                    fullName.setText("");
                    userName.setText("");
                    email.setText("");
                    phoneNumber.setText("");
                    password.setText("");

                }

            }
        });
    }
}