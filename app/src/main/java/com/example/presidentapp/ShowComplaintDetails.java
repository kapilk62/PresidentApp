package com.example.presidentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.presidentapp.Model.AddComplaintsModel;
import com.example.presidentapp.MyBuilding.MyBuildingShowBankDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowComplaintDetails extends AppCompatActivity{

    TextView complaintname,complaintdescription,complaintcategory,complaintpriority,complaintstatus;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_complaint_details);

        Intent intent = getIntent();
        final String ComplaintName = intent.getStringExtra("Complaintname");
        final String ComplaintDescription = intent.getStringExtra("Complaintdescription");
        final String ComplaintCategory = intent.getStringExtra("Complaintcategory");
        final String ComplaintPriority = intent.getStringExtra("Complaintpriority");
        final String ComplaintStatus = intent.getStringExtra("Complaintstatus");

        complaintname = findViewById(R.id.complaintnametextviewclick);
        complaintdescription = findViewById(R.id.complaintdescriptiontextviewclick);
        complaintcategory = findViewById(R.id.complaintcategorytextviewclick);
        complaintpriority = findViewById(R.id.complaintprioritytextviewclick);
        complaintstatus = findViewById(R.id.complaintstatustextviewclick);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReferencecomplaintdetailsshow = firebaseDatabase.getReference("Add Complaint");

        databaseReferencecomplaintdetailsshow.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                AddComplaintsModel addComplaintsModel = dataSnapshot.getValue(AddComplaintsModel.class);
                complaintname.setText(ComplaintName);
                complaintdescription.setText(ComplaintDescription);
                complaintcategory.setText(ComplaintCategory);
                complaintpriority.setText(ComplaintPriority);
                complaintstatus.setText(ComplaintStatus);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ShowComplaintDetails.this, databaseError.getCode(),Toast.LENGTH_LONG).show();

            }
        });

    }
}