package com.example.presidentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.presidentapp.GateKeeper.GateKeeper_gatekeepers;
import com.example.presidentapp.GateKeeper.GateKeeper_gatepass;
import com.example.presidentapp.GateKeeper.GateKeeper_gates;
import com.example.presidentapp.GateKeeper.GateKeeper_visitorstatshistory;

public class Gate_Keeper extends AppCompatActivity implements View.OnClickListener{

    private CardView GateKeepersCard, GatesCard, GatePassCard, VisitorStatisticsHistoryCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate__keeper);

        GateKeepersCard=findViewById(R.id.GateKeeper_gatekeepers);
        GatesCard=findViewById(R.id.GateKeeper_gates);
        GatePassCard=findViewById(R.id.GateKeeper_gatepass);
        VisitorStatisticsHistoryCard=findViewById(R.id.GateKeeper_visitor_stats_history);

        GateKeepersCard.setOnClickListener(this);
        GatesCard.setOnClickListener(this);
        GatePassCard.setOnClickListener(this);
        VisitorStatisticsHistoryCard.setOnClickListener(this);
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

    public void onClick(View view){
        Intent i;
        switch (view.getId()) {
            case R.id.GateKeeper_gatekeepers:
                i = new Intent(this, GateKeeper_gatekeepers.class);
                startActivity(i);
                break; case R.id.GateKeeper_gates:

                i = new Intent(this, GateKeeper_gates.class);
                startActivity(i);
                break;
            case R.id.GateKeeper_gatepass:
                i = new Intent(this, GateKeeper_gatepass.class);
                startActivity(i);
                break;
            case R.id.GateKeeper_visitor_stats_history:
                i = new Intent(this, GateKeeper_visitorstatshistory.class);
                startActivity(i);
                break;
        }
    }
}
