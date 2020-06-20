package com.example.presidentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.presidentapp.Model.Emergency_Num_Model;
import com.example.presidentapp.Model.Vendor_Num_Model;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class VendorForm extends AppCompatActivity {
    TextInputEditText vendordetailsName , vendordetailsNumber,vendordetailsAddress;
    Button addVendorBtn;
    Spinner vendordetailsCategory;
    DatabaseReference databaseVendorDetails;
    String buildingId;

    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_form);

        vendordetailsName = findViewById(R.id.VendorName);
        vendordetailsNumber = findViewById(R.id.VendorNumber);
        vendordetailsAddress = findViewById(R.id.VendorAddress);
        vendordetailsCategory = findViewById(R.id.VendorSpinner);
        addVendorBtn = findViewById(R.id.VendorSubmitBtn);

        GlobalClass globalClass = (GlobalClass) getApplicationContext();
        buildingId = globalClass.getBuildingId();
        String currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseVendorDetails = FirebaseDatabase.getInstance().getReference("Vendor_details").child(buildingId).child(currentuserId);

        addVendorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addVendorNumber();
                finish();
            }
        });
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
       
        private void addVendorNumber() {

            String VendorDetailsName= vendordetailsName.getText().toString().trim();
            String VendorDetailsNumber = vendordetailsNumber.getText().toString().trim();
            String VendorDetailsAddress = vendordetailsAddress.getText().toString().trim();
            String VendorDetailsCategory = vendordetailsCategory.getSelectedItem().toString().trim();

            if (!TextUtils.isEmpty(VendorDetailsName)) {
                String vendorNumberId = databaseVendorDetails.push().getKey();
                Vendor_Num_Model vendor_num_model = new Vendor_Num_Model(vendorNumberId, VendorDetailsName , VendorDetailsNumber, VendorDetailsAddress,VendorDetailsCategory);
                databaseVendorDetails.child(vendorNumberId).setValue(vendor_num_model);
                Toast.makeText(this, "Emergency Number added", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "You should enter the Emergency Number name", Toast.LENGTH_LONG).show();
            }
        }




}
