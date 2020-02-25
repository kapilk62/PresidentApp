package com.example.presidentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView billsCard, membersCard, societyfundCard, emergencynumberCard, mybuldingCard, complaintsCard, vehiclesCard, gatekeeperCard;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        billsCard = findViewById(R.id.bills);
        membersCard = findViewById(R.id.members);
        societyfundCard = findViewById(R.id.society_fund);
        emergencynumberCard = findViewById(R.id.emergency_number);
        mybuldingCard = findViewById(R.id.my_building);
        complaintsCard = findViewById(R.id.complaints);
        vehiclesCard = findViewById(R.id.vehicles);
        gatekeeperCard = findViewById(R.id.gatekeeper);

        billsCard.setOnClickListener(this);
        membersCard.setOnClickListener(this);
        societyfundCard.setOnClickListener(this);
        emergencynumberCard.setOnClickListener(this);
        mybuldingCard.setOnClickListener(this);
        complaintsCard.setOnClickListener(this);
        vehiclesCard.setOnClickListener(this);
        gatekeeperCard.setOnClickListener(this);
        mDrawerlayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }
    
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if (mToggle.onOptionsItemSelected(item)) {

            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }

    }

    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.bills:
                i = new Intent(this, Bills.class);
                startActivity(i);
                break;
            case R.id.members:
                i = new Intent(this, Members.class);
                startActivity(i);
                break;
            case R.id.society_fund:
                i = new Intent(this, Society_Fund.class);
                startActivity(i);
                break;
            case R.id.emergency_number:
                i = new Intent(this, Emergency_Number.class);
                startActivity(i);
                break;
            case R.id.my_building:
                i = new Intent(this, My_Building.class);
                startActivity(i);
                break;
            case R.id.complaints:
                i = new Intent(this, Complaints.class);
                startActivity(i);
                break;
            case R.id.vehicles:
                i = new Intent(this, Vehicles.class);
                startActivity(i);
                break;
            case R.id.gatekeeper:
                i = new Intent(this, Gate_Keeper.class);
                startActivity(i);
                break;
            default:
                break;

        }
    }






}