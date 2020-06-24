package com.example.presidentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.presidentapp.Model.AddComplaintsModel;
import com.example.presidentapp.MyBuilding.MyBuildingEditBank;
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
    final String currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    String buildingId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_complaint_details);

        Intent intent = getIntent();
        final String ComplaintId = intent.getStringExtra("ComplaintId");
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

        GlobalClass globalClass = (GlobalClass) getApplicationContext();
        buildingId = globalClass.getBuildingId();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReferencecomplaintdetailsshow = firebaseDatabase.getReference("Add Complaint").child(currentuserId);

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.edititem:
                Intent intent = getIntent();
                final String ComplaintId = intent.getStringExtra("ComplaintId");
                final String ComplaintName = intent.getStringExtra("Complaintname");
                final String ComplaintDescription = intent.getStringExtra("Complaintdescription");
                final String ComplaintCategory = intent.getStringExtra("Complaintcategory");
                final String ComplaintPriority = intent.getStringExtra("Complaintpriority");
                final String ComplaintStatus = intent.getStringExtra("Complaintstatus");

                Intent intentedit = new Intent(ShowComplaintDetails.this, ComplaintEdit.class);
                intentedit.putExtra("ComplaintId",ComplaintId);
                intentedit.putExtra("Complaintname",ComplaintName);
                intentedit.putExtra("Complaintdescription",ComplaintDescription);
                intentedit.putExtra("Complaintcategory",ComplaintCategory);
                intentedit.putExtra("Complaintpriority",ComplaintPriority);
                intentedit.putExtra("Complaintstatus",ComplaintStatus);
                startActivity(intentedit);
                break;

            case  R.id.deleteitem:
                Intent intent1 = getIntent();
                final String ComplaintId1 = intent1.getStringExtra("ComplaintId");
                deleteComplaint(ComplaintId1);
                break;
            default:

                break;
        }
        return true;
    }
    private void deleteComplaint(String ComplaintId) {
        DatabaseReference databaseReferenceComplaint = FirebaseDatabase.getInstance().getReference("Add Complaint").child(currentuserId).child(buildingId).child(ComplaintId);
        databaseReferenceComplaint.removeValue();

        Toast.makeText(this, "Complaint is deleted", Toast.LENGTH_LONG).show();
    }
}
