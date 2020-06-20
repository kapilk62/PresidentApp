package com.example.presidentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.presidentapp.Model.AddComplaintsModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_complaints extends AppCompatActivity{

    TextInputEditText complaintname, complaintdescription;
    Spinner complaintcategory, complaintpriority, complaintstatus;
    Button addcomplaint;
    String buildingId;
    DatabaseReference databaseReferenceAddComplaint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_complaints);
        GlobalClass globalClass = (GlobalClass) getApplicationContext();
        buildingId = globalClass.getBuildingId();
        complaintname = findViewById(R.id.complaint_name_txt_fld);
        complaintdescription=findViewById(R.id.complaint_description_txt_fld);
        complaintcategory=findViewById(R.id.complaint_category_spinner);
        complaintpriority=findViewById(R.id.complaint_priority_spinner);
        complaintstatus=findViewById(R.id.complaint_status_spinner);
        addcomplaint=findViewById(R.id.add_complaint_button);
        String currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReferenceAddComplaint = FirebaseDatabase.getInstance().getReference("Add Complaint").child(currentuserId).child(buildingId);
        addcomplaint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AddComplaints();
                finish();
            }
        });
    }
    private void AddComplaints(){
        String getcomplaintname = complaintname.getText().toString().trim();
        String getcomplaintdescription = complaintdescription.getText().toString().trim();
        String getcomplaintcategory =  complaintcategory.getSelectedItem().toString().trim();
        String getcomplaintpriority =  complaintpriority.getSelectedItem().toString().trim();
        String getcomplaintstatus =  complaintstatus.getSelectedItem().toString().trim();

        if(!TextUtils.isEmpty(getcomplaintname))
        {
            String complaintId=databaseReferenceAddComplaint.push().getKey();
            AddComplaintsModel addComplaintsModel = new AddComplaintsModel(complaintId, getcomplaintname,getcomplaintdescription,getcomplaintcategory,getcomplaintpriority,getcomplaintstatus);
            databaseReferenceAddComplaint.child(complaintId).setValue(addComplaintsModel);
            Toast.makeText(this,"New Complaint added",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "You should enter the complaint name", Toast.LENGTH_LONG).show();
        }
        }

    public void AddComplaints(View view) {
    }
}

