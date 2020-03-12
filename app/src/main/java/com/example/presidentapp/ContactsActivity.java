package com.example.presidentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

    }

    public void phoneapp(View view){
        Intent phoneIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("tel:9090900990"));
        startActivity(phoneIntent);
    }

    public void mailapp(View view){
        Intent mailIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:epartment@gmail.com"));
        startActivity(mailIntent);
    }
}