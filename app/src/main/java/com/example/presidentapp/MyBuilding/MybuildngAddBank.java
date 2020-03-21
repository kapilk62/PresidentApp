package com.example.presidentapp.MyBuilding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.presidentapp.Model.CreateNewSocietyModel;
import com.example.presidentapp.Model.MyBuildingAddBankModel;
import com.example.presidentapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MybuildngAddBank extends AppCompatActivity {

    TextInputEditText bankname, bankupiid, bankaccountname, bankaccountnumber, bankifsccode, bankaddress;
    DatabaseReference databaseReferenceAddBank;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    Button addbank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybuildng_add_bank);

        bankname = findViewById(R.id.bank_name_txt_fld);
        bankupiid = findViewById(R.id.upi_id_txt_fld);
        bankaccountname = findViewById(R.id.account_name_txt_fld);
        bankaccountnumber = findViewById(R.id.account_number_txt_fld);
        bankifsccode = findViewById(R.id.ifsc_code_txt_fld);
        bankaddress = findViewById(R.id.bank_address_txt_fld);
        addbank = findViewById(R.id.add_bank_button);
        String currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReferenceAddBank = FirebaseDatabase.getInstance().getReference("Add Bank").child(currentuserId);
        addbank.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AddBankDetails();
                finish();
            }
        });
    }

    private void AddBankDetails(){

        String getbankname =  bankname.getText().toString().trim();
        String getupiid = bankupiid.getText().toString().trim();
        String getbankaccountname = bankaccountname.getText().toString().trim();
        String getbankaccountnumber = bankaccountnumber.getText().toString().trim();
        String getbankifsccode = bankifsccode.getText().toString().trim();
        String getbankaddress = bankaddress.getText().toString().trim();

        if(!TextUtils.isEmpty(getbankname)){
            String bankId = databaseReferenceAddBank.push().getKey();
            MyBuildingAddBankModel myBuildingAddBankModel = new MyBuildingAddBankModel(bankId, getbankname,getupiid,getbankaccountname,getbankaccountnumber,getbankifsccode,getbankaddress);
            databaseReferenceAddBank.child(bankId).setValue(myBuildingAddBankModel);
            Toast.makeText(this,"New Bank added",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "You should enter the bank name", Toast.LENGTH_LONG).show();
        }

    }
}
