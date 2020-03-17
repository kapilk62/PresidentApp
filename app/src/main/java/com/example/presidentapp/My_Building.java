package com.example.presidentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.presidentapp.MyBuilding.MyBuildingBank;
import com.example.presidentapp.MyBuilding.MyBuildingDocuments;
import com.example.presidentapp.MyBuilding.MyBuildingInfo;
import com.example.presidentapp.MyBuilding.MyBuildingStatistics;
import com.example.presidentapp.MyBuilding.MyBuildingWings;

public class My_Building extends AppCompatActivity implements View.OnClickListener {

    private CardView wingsCard, buildingInfoCard, rulesCard, statisticsCard, bankCard, documentsCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__building);

        wingsCard = findViewById(R.id.myBuildingWings);
        buildingInfoCard = findViewById(R.id.myBuildingBuildingInfo);
        rulesCard = findViewById(R.id.myBuildingRules);
        statisticsCard = findViewById(R.id.myBuildingStatistics);
        bankCard = findViewById(R.id.myBuildingBank);
        documentsCard = findViewById(R.id.myBuildingDocuments);

        wingsCard.setOnClickListener(this);
        buildingInfoCard.setOnClickListener( this);
        rulesCard.setOnClickListener(this);
        statisticsCard.setOnClickListener(this);
        bankCard.setOnClickListener(this);
        documentsCard.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.myBuildingWings:
                i = new Intent(this, MyBuildingWings.class);
                startActivity(i);
                break;
            case R.id.myBuildingBuildingInfo:
                i = new Intent(this, MyBuildingInfo.class);
                startActivity(i);
                break;
            case R.id.myBuildingRules:
                i = new Intent(this, Rules.class);
                startActivity(i);
                break;
            case R.id.myBuildingStatistics:
                i = new Intent(this, MyBuildingStatistics.class);
                startActivity(i);
                break;
            case R.id.myBuildingBank:
                i = new Intent(this, MyBuildingBank.class);
                startActivity(i);
                break;
            case R.id.myBuildingDocuments:
                i = new Intent(this, MyBuildingDocuments.class);
                startActivity(i);
                break;
            default:
                break;

        }

    }
}
