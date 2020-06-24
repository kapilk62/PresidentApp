package com.example.presidentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.presidentapp.Adapter.Complaints_Details_adapter;
import com.example.presidentapp.Model.AddComplaintsModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Complaints extends AppCompatActivity {

    ListView listViewComplaintDetails;
    String buildingId;
    DatabaseReference databaseReferencecomplaintDetails;
    List<AddComplaintsModel> complaintdetailsmodelllist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);
        GlobalClass globalClass = (GlobalClass) getApplicationContext();
        buildingId = globalClass.getBuildingId();
        String currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReferencecomplaintDetails = FirebaseDatabase.getInstance().getReference("Add Complaint").child(currentuserId).child(buildingId);

        listViewComplaintDetails = findViewById(R.id.listviewcomplaintsdetails);
        complaintdetailsmodelllist = new ArrayList<>();

        listViewComplaintDetails.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AddComplaintsModel addComplaintsModel =  complaintdetailsmodelllist.get(position);
                Intent intent = new Intent(getApplicationContext(), ShowComplaintDetails.class);
                intent.putExtra("ComplaintId",addComplaintsModel.getComplaintId());
                intent.putExtra("Complaintname",addComplaintsModel.getComplaintname());
                intent.putExtra("Complaintdescription",addComplaintsModel.getComplaintdescription());
                intent.putExtra("Complaintcategory",addComplaintsModel.getComplaintcategory());
                intent.putExtra("Complaintpriority",addComplaintsModel.getComplaintpriority());
                intent.putExtra("Complaintstatus",addComplaintsModel.getComplaintstatus());
                startActivity(intent);
                
                opencomplaintsdetails(addComplaintsModel.getComplaintname(),addComplaintsModel.getComplaintdescription(),addComplaintsModel.getComplaintcategory(),addComplaintsModel.getComplaintpriority(),addComplaintsModel.getComplaintstatus());
            }
        });
    }

    private void opencomplaintsdetails(String addcomplaintname, String addcomplaintdescription, String addcomplaintcategory, String addcomplaintpriority, String addcomplaintstatus) {
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReferencecomplaintDetails.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                complaintdetailsmodelllist.clear();

                for (DataSnapshot complaintdetailsSnapshot : dataSnapshot.getChildren()) {
                    AddComplaintsModel addComplaintsModel = complaintdetailsSnapshot.getValue(AddComplaintsModel.class);
                    complaintdetailsmodelllist.add(addComplaintsModel);
                }
                Complaints_Details_adapter adapter = new Complaints_Details_adapter(Complaints.this, complaintdetailsmodelllist);
                listViewComplaintDetails.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void AddComplaints(View view) {
        Intent intent = new Intent(getApplicationContext(), Add_complaints.class);
        startActivity(intent);
    }
}
