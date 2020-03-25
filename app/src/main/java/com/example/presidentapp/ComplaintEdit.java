package com.example.presidentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.presidentapp.Model.AddComplaintsModel;
import com.example.presidentapp.Model.MyBuildingAddBankModel;
import com.example.presidentapp.MyBuilding.MyBuildingBank;
import com.example.presidentapp.MyBuilding.MyBuildingEditBank;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ComplaintEdit extends AppCompatActivity{
    Button editComplaintDetailsButton;
    TextInputEditText complaintname, complaintdescription;
    Spinner complaintcategory, complaintpriority, complaintstatus;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    final String currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_edit);

        complaintname = findViewById(R.id.edit_complaint_name_txt_fld);
        complaintdescription = findViewById(R.id.edit_complaint_description_txt_fld);
        complaintcategory = findViewById(R.id.edit_complaint_category_spinner);
        complaintpriority = findViewById(R.id.edit_complaint_priority_spinner);
        complaintstatus = findViewById(R.id.edit_complaint_status_spinner);
        editComplaintDetailsButton = findViewById(R.id.edit_complaint_button);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        Intent intentedit = getIntent();
        final String ComplaintId = intentedit.getStringExtra("ComplaintId");
        final String ComplaintName = intentedit.getStringExtra("Complaintname");
        final String ComplaintDescription = intentedit.getStringExtra("Complaintdescription");
        final String ComplaintCategory = intentedit.getStringExtra("Complaintcategory");
        final String ComplaintPriority = intentedit.getStringExtra("Complaintpriority");
        final String ComplaintStatus = intentedit.getStringExtra("Complaintstatus");

        complaintname.setText(ComplaintName);
        complaintdescription.setText(ComplaintDescription);
        //complaintcategory.(ComplaintCategory);
        //complaintname.setText(ComplaintName);
        //complaintname.setText(ComplaintName);
        //complaintname.setText(ComplaintName);

        editComplaintDetailsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String getcomplaintname = complaintname.getText().toString().trim();
                String getcomplaintdescription = complaintdescription.getText().toString().trim();
                String getcomplaintcategory = complaintcategory.getSelectedItem().toString().trim();
                String getcomplaintpriority = complaintpriority.getSelectedItem().toString().trim();
                String getcomplaintstatus = complaintstatus.getSelectedItem().toString().trim();
                if (TextUtils.isEmpty(getcomplaintname)) {
                    complaintname.setError("Complaint Required");
                }
                updateComaplintDetails(ComplaintId, getcomplaintname, getcomplaintdescription, getcomplaintcategory, getcomplaintpriority, getcomplaintstatus);
                Intent intent1 = new Intent(ComplaintEdit.this, Complaints.class);
                startActivity(intent1);
                finish();
            }
        });
    }

    private boolean updateComaplintDetails(String addcomplaintId, String complaintName, String complaintDescription, String complaintCategory, String complaintPriority, String complaintStatus) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Add Complaint").child(currentuserId).child(addcomplaintId);
        AddComplaintsModel addComplaintsModel = new AddComplaintsModel(addcomplaintId, complaintName, complaintDescription, complaintCategory, complaintPriority, complaintStatus);
        databaseReference.setValue(addComplaintsModel);
        Toast.makeText(this, "Complaint Details Updated", Toast.LENGTH_LONG).show();
        return true;
    }
}
