package com.example.presidentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity{

    private FirebaseAuth currentUser;
    TextInputEditText profilenameedittext;
    Button updateprofilebutton;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        currentUser = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1);

        profilenameedittext = findViewById(R.id.profilename_txt_fld);
        updateprofilebutton = findViewById(R.id.updateprofile_btn);
        }
    }

