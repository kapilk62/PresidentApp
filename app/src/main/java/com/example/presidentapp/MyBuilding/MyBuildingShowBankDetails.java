package com.example.presidentapp.MyBuilding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.presidentapp.Model.MyBuildingAddBankModel;
import com.example.presidentapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyBuildingShowBankDetails extends AppCompatActivity{

    TextView bankname, bankupiid, bankaccountname, bankaccountnumber, bankifsccode, bankaddress;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_building_show_bank_details);
        Intent intent = getIntent();

        final String BankName = intent.getStringExtra("BANKNAME");
        final String BankUpiId = intent.getStringExtra("BANKUPIID");
        final String BankAccountName = intent.getStringExtra("BANKACCOUNTNAME");
        final String BankAccountNumber = intent.getStringExtra("BANKACCOUNTNUMBER");
        final String BankIfscCode = intent.getStringExtra("BANKIFSCCODE");
        final String BankAddress = intent.getStringExtra("BANKADDRESS");

        bankname = findViewById(R.id.banknametextviewclick);
        bankupiid = findViewById(R.id.bankupiidtextviewclick);
        bankaccountname = findViewById(R.id.bankaccountnametextviewclick);
        bankaccountnumber = findViewById(R.id.bankaccountnumbertextviewclick);
        bankifsccode = findViewById(R.id.bankifsccodetextviewclick);
        bankaddress = findViewById(R.id.bankaddresstextviewclick);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReferencebankdetailsshow = firebaseDatabase.getReference("Add Bank");

        databaseReferencebankdetailsshow.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                MyBuildingAddBankModel myBuildingAddBankModel = dataSnapshot.getValue(MyBuildingAddBankModel.class);
                bankname.setText(BankName);
                bankupiid.setText(BankUpiId);
                bankaccountname.setText(BankAccountName);
                bankaccountnumber.setText(BankAccountNumber);
                bankifsccode.setText(BankIfscCode);
                bankaddress.setText(BankAddress);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MyBuildingShowBankDetails.this, databaseError.getCode(),Toast.LENGTH_LONG).show();

            }
        });


    }
}
