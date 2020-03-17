package com.example.presidentapp.MyBuilding;

import androidx.appcompat.app.AppCompatActivity;

import com.example.presidentapp.Add_event;
import com.example.presidentapp.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MyBuildingBank extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_building_bank);
    }
    public void AddBank(View view) {
        Intent intent = new Intent(getApplicationContext(), MybuildngAddBank.class);
        startActivity(intent);
    }
}
