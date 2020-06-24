package com.example.presidentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.presidentapp.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserInfo extends AppCompatActivity {
    EditText firstname,lastname,email;
    Button saveBtn;
    FirebaseAuth firebaseAuth;
    String userID;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        email = findViewById(R.id.emailid);
        saveBtn = findViewById(R.id.saveuser_btn);
        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child("President").child(userID);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!firstname.getText().toString().isEmpty() && !lastname.getText().toString().isEmpty() && !email.getText().toString().isEmpty()){
                  String firstName = firstname.getText().toString();
                  String lastName = lastname.getText().toString();
                  String userEmail = email.getText().toString();
                  String userInfoId = databaseReference.getKey();

                  User user =new  User(firstName,lastName,userEmail);
                  databaseReference.setValue(user);

                  startActivity(new Intent(getApplicationContext(),multiple_building_page.class));
                  finish();

                }else {
                    Toast.makeText(UserInfo.this, "All Field Are Required!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}