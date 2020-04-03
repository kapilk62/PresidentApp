package com.example.presidentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Join_Building_Page extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join__building__page);
    }
    public void CreateNewSociety(View view) {
        Intent intent = new Intent(getApplicationContext(), Create_New_Society.class);
        startActivity(intent);
    }
    public void JoinYourSociety(View view) {
        Intent intent = new Intent(getApplicationContext(), Join_Your_Building.class);
        startActivity(intent);
    }
}
