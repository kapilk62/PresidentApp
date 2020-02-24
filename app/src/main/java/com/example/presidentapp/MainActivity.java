package com.example.presidentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView billsCard,membersCard,societyfundCard,emergencynumberCard,mybuldingCard,complaintsCard,vehiclesCard,gatekeeperCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        billsCard =  findViewById(R.id.bills);
        membersCard = findViewById(R.id.members);
        societyfundCard =  findViewById(R.id.society_fund);
        emergencynumberCard =  findViewById(R.id.emergency_number);
        mybuldingCard = findViewById(R.id.my_building);
        complaintsCard =  findViewById(R.id.complaints);
        vehiclesCard =  findViewById(R.id.vehicles);
        gatekeeperCard =  findViewById(R.id.gatekeeper);

        billsCard.setOnClickListener(this);
        membersCard.setOnClickListener(this);
        societyfundCard.setOnClickListener(this);
        emergencynumberCard.setOnClickListener(this);
        mybuldingCard.setOnClickListener(this);
        complaintsCard.setOnClickListener(this);
        vehiclesCard.setOnClickListener(this);
        gatekeeperCard.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId())
        {
            case R.id.bills : i  = new Intent(this, Bills.class);startActivity(i); break;
            case R.id.members : i  = new Intent(this, Members.class);startActivity(i); break;
            case R.id.society_fund : i  = new Intent(this, Society_Fund.class); startActivity(i);break;
            case R.id.emergency_number : i  = new Intent(this, Emergency_Number.class);startActivity(i); break;
            case R.id.my_building : i  = new Intent(this, My_Building.class); startActivity(i);break;
            case R.id.complaints : i  = new Intent(this, Complaints.class); startActivity(i);break;
            case R.id.vehicles : i  = new Intent(this, Vehicles.class); startActivity(i);break;
            case R.id.gatekeeper : i  = new Intent(this, Gate_Keeper.class); startActivity(i);break;
            default:break;
        }

    }
}
