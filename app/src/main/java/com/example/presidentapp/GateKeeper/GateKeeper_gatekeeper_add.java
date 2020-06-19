package com.example.presidentapp.GateKeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.presidentapp.Model.GateKeeper_AddGateKeeperModel;
import com.example.presidentapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GateKeeper_gatekeeper_add extends AppCompatActivity{

    TextInputEditText gatekeeperfirstname, gatekeeperlastname, gatekeeperemail, gatekeepermobilenumber;
    Button AddGateKeeperbtn;
    DatabaseReference databaseReferenceAddGateKeeper;
    final String currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate_keeper_gatekeeper_add);

        gatekeeperfirstname = findViewById(R.id.gatekeeperfirstname_txt_fld);
        gatekeeperlastname = findViewById(R.id.gatekeeperlastname_txt_fld);
        gatekeeperemail = findViewById(R.id.gatekeeperemail_txt_fld);
        gatekeepermobilenumber = findViewById(R.id.gatekeepermob_no_txt_fld);
        AddGateKeeperbtn = findViewById(R.id.addgatekeeper_btn);

        databaseReferenceAddGateKeeper = FirebaseDatabase.getInstance().getReference("Add GateKeeper").child(currentuserId);
        AddGateKeeperbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AddGateKeeperDetails();
                finish();
            }
        });
    }

    private void AddGateKeeperDetails() {
        String getgatekeeperfirstname = gatekeeperfirstname.getText().toString().trim();
        String getgatekeeperlastname = gatekeeperlastname.getText().toString().trim();
        String getgatekeeperemail = gatekeeperemail.getText().toString().trim();
        String getgatekeepermobilenumber = gatekeepermobilenumber.getText().toString().trim();

        if(!TextUtils.isEmpty(getgatekeeperfirstname)){
            String gatekeeperId = databaseReferenceAddGateKeeper.push().getKey();
            GateKeeper_AddGateKeeperModel gateKeeper_addGateKeeperModel = new GateKeeper_AddGateKeeperModel(gatekeeperId,getgatekeeperfirstname,getgatekeeperlastname,getgatekeeperemail,getgatekeepermobilenumber);
            databaseReferenceAddGateKeeper.child(gatekeeperId).setValue(gateKeeper_addGateKeeperModel);
            Toast.makeText(this, "New Gate Keeper added", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "You should enter the details", Toast.LENGTH_LONG).show();
        }
    }
}
