package com.example.presidentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.presidentapp.Model.Emergency_Num_Model;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EmergencyForm extends AppCompatActivity {

    TextInputEditText emergencyNumberName , emergencyNumber;
    Button addEmergencyBtn;
    DatabaseReference databaseEmergencyNumbers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_form);
        emergencyNumberName = findViewById(R.id.EmerName);
        emergencyNumber = findViewById(R.id.EmerNumber);
        addEmergencyBtn = findViewById(R.id.EmerSubmitBtn);

        String currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseEmergencyNumbers = FirebaseDatabase.getInstance().getReference("Emergency_Numbers").child(currentuserId);

        addEmergencyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEmergencyNumber();
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

    private void addEmergencyNumber() {

        String EmergencyNumberName= emergencyNumberName.getText().toString().trim();
        String EmergencyNumber = emergencyNumber.getText().toString().trim();

        if (!TextUtils.isEmpty(EmergencyNumberName)) {
            String emergencyNumberId = databaseEmergencyNumbers.push().getKey();
             Emergency_Num_Model emergency_num_model = new Emergency_Num_Model(emergencyNumberId, EmergencyNumberName , EmergencyNumber);
            databaseEmergencyNumbers.child(emergencyNumberId).setValue(emergency_num_model);
            Toast.makeText(this, "Emergency Number added", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "You should enter the Emergency Number name", Toast.LENGTH_LONG).show();
        }
    }
}
