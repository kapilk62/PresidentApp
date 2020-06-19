package com.example.presidentapp.GateKeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.presidentapp.Model.GateKeeper_AddGatesModel;
import com.example.presidentapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GateKeeper_gates_add extends AppCompatActivity{

    TextInputEditText gatesnamenumber;
    Button AddGatesbtn;
    DatabaseReference databaseReferenceAddGates;
    final String currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate_keeper_gates_add);

        gatesnamenumber = findViewById(R.id.gatesnamenumber_txt_fld);
        AddGatesbtn = findViewById(R.id.addgates_btn);

        databaseReferenceAddGates = FirebaseDatabase.getInstance().getReference("Add Gates").child(currentuserId);
        AddGatesbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AddGatesDetails();
                finish();
            }
        });
    }

    private void AddGatesDetails() {

        String getgatesnamenumber = gatesnamenumber.getText().toString().trim();

        if(!TextUtils.isEmpty(getgatesnamenumber)){
            String gatesId = databaseReferenceAddGates.push().getKey();
            GateKeeper_AddGatesModel gateKeeper_addGatesModel = new GateKeeper_AddGatesModel(gatesId,getgatesnamenumber);
            databaseReferenceAddGates.child(gatesId).setValue(gateKeeper_addGatesModel);
            Toast.makeText(this, "New Gate added", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "You should enter the details", Toast.LENGTH_LONG).show();
        }
    }
}

