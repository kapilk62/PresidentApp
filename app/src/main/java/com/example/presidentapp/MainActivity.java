package com.example.presidentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.presidentapp.Model.CreateNewSocietyModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private CardView share_code_Card,billsCard, membersCard, societyfundCard, emergencynumberCard, mybuldingCard, complaintsCard, vehiclesCard, gatekeeperCard;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private static String buildingId;
    private NavigationView mNavigationView;
    DatabaseReference databaseReference;
    String currentuserId;
    TextView society_code_tv,society_info_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        society_code_tv = findViewById(R.id.Society_code_share);
        society_info_tv = findViewById(R.id.society_name_share);
        share_code_Card = findViewById(R.id.share_card_view);
        billsCard = findViewById(R.id.bills);
        membersCard = findViewById(R.id.members);
        societyfundCard = findViewById(R.id.society_fund);
        emergencynumberCard = findViewById(R.id.emergency_number);
        mybuldingCard = findViewById(R.id.my_building);
        complaintsCard = findViewById(R.id.complaints);
        vehiclesCard = findViewById(R.id.vehicles);
        gatekeeperCard = findViewById(R.id.gatekeeper);
        mNavigationView = findViewById(R.id.navigationView);

        share_code_Card.setOnClickListener(this);
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

        mNavigationView.setNavigationItemSelectedListener(this);

        buildingId = getIntent().getExtras().get("BUILDINGID").toString();
        databaseReference = FirebaseDatabase.getInstance().getReference("New Building").child(currentuserId).child(buildingId);

        GlobalClass globalClass = (GlobalClass) getApplicationContext();
        globalClass.setBuildingId(buildingId);

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

    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        int id=item.getItemId();

        if(id==R.id.sprofile)
        {
            Intent i = new Intent(MainActivity.this,Profile.class);
            startActivity(i);
        }


        else if(id==R.id.smy_bills)
        {
            Intent i = new Intent(MainActivity.this,Join_Building_Page.class);
            startActivity(i);
        }

        else if(id==R.id.semergency_number)
        {
            Intent i = new Intent(MainActivity.this, Emergency_Number.class);
            startActivity(i);
        }

        else if(id==R.id.sevents)
        {
            Intent i = new Intent(MainActivity.this,Event.class);
            startActivity(i);
        }

        else if (id==R.id.scomplaints){
            Intent i = new Intent(MainActivity.this,Complaints.class);
            startActivity(i);
        }

        else if(id==R.id.swallet)
        {
            Intent i = new Intent(MainActivity.this,multiple_building_page.class);
            startActivity(i);
        }

        else if(id==R.id.smember)
        {

        }

        else if(id==R.id.svehicles)
        {

        }

        else if(id==R.id.sannouncements)
        {
            Intent i = new Intent(MainActivity.this,Announcements.class);
            startActivity(i);
        }

        else if(id==R.id.sbalance_sheet)
        {

        }

        else if(id==R.id.srules)
        {
            Intent i = new Intent(MainActivity.this,Rules.class);
            startActivity(i);
        }

        else if(id==R.id.snotification)
        {

        }

        else if(id==R.id.ssetting)
        {

        }

        else if (id==R.id.logoutbutton)
        {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),Register_user.class));
            finish();
        }

        else if(id==R.id.scontact_us)
        {
            Intent i = new Intent(MainActivity.this, ContactsActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer1 =findViewById(R.id.drawer);
        drawer1.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.share_card_view:
                i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT,"Society Code");
                String shareBody= "step 1:open an member app and login or sign-Up.\nStep 2: Enter Your Details if you are not already login.\nStep 3: Enter Following joining code\n"+ currentuserId +"\n4: Select your society";
                i.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(i,"Society Code"));
                break;

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

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CreateNewSocietyModel createNewSocietyModel = dataSnapshot.getValue(CreateNewSocietyModel.class);
                society_code_tv.setText(currentuserId);
                society_info_tv.setText("Share above code with building member to join "+createNewSocietyModel.getBuildingName()+".");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}