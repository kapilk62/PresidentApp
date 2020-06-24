package com.example.presidentapp.MyBuilding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.presidentapp.GlobalClass;
import com.example.presidentapp.Model.MyBuildingAddBankModel;
import com.example.presidentapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyBuildingEditBank extends AppCompatActivity {

    Button editBankDetailsButton;
    TextInputEditText bankname, bankupiid, bankaccountname, bankaccountnumber, bankifsccode, bankaddress;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    private static final String TAG = "1";
    final String currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    String buildingId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_building_edit_bank);
        bankname = findViewById(R.id.edit_bank_name_txt_fld);
        bankupiid = findViewById(R.id.edit_upi_id_txt_fld);
        bankaccountname = findViewById(R.id.edit_account_name_txt_fld);
        bankaccountnumber = findViewById(R.id.edit_account_number_txt_fld);
        bankifsccode = findViewById(R.id.edit_ifsc_code_txt_fld);
        bankaddress = findViewById(R.id.edit_bank_address_txt_fld);
        editBankDetailsButton = findViewById(R.id.edit_bank_button);

        GlobalClass globalClass = (GlobalClass) getApplicationContext();
        buildingId = globalClass.getBuildingId();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        Intent intentedit =getIntent();
        final String BankId = intentedit.getStringExtra("BankId");
        final String BankName = intentedit.getStringExtra("BANKNAMEEDIT");
        final String BankUpiId = intentedit.getStringExtra("BANKUPIIDEDIT");
        final String BankAccountName = intentedit.getStringExtra("BANKACCOUNTNAMEEDIT");
        final String BankAccountNumber = intentedit.getStringExtra("BANKACCOUNTNUMBEREDIT");
        final String BankIfscCode = intentedit.getStringExtra("BANKIFSCCODEEDIT");
        final String BankAddress = intentedit.getStringExtra("BANKADDRESSEDIT");
        Log.d(TAG, "onCreate: "+BankId);
        bankname.setText(BankName);
        bankupiid.setText(BankUpiId);
        bankaccountname.setText(BankAccountName);
        bankaccountnumber.setText(BankAccountNumber);
        bankifsccode.setText(BankIfscCode);
        bankaddress.setText(BankAddress);

        editBankDetailsButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String getbankname =  bankname.getText().toString().trim();
                String getupiid = bankupiid.getText().toString().trim();
                String getbankaccountname = bankaccountname.getText().toString().trim();
                String getbankaccountnumber = bankaccountnumber.getText().toString().trim();
                String getbankifsccode = bankifsccode.getText().toString().trim();
                String getbankaddress = bankaddress.getText().toString().trim();
                if(TextUtils.isEmpty(getbankname)){
                    bankname.setError("First Name Required");
                }
                if(TextUtils.isEmpty(getupiid)){
                    bankupiid.setError("Last Name Required");
                }
                if(TextUtils.isEmpty(getbankaccountname)){
                    bankaccountname.setError("Email Id Required");
                }
                if(TextUtils.isEmpty(getbankaccountnumber)){
                    bankaccountnumber.setError("Mobile Number Required");
                }
                if(TextUtils.isEmpty(getbankifsccode)){
                    bankifsccode.setError("Mobile Number Required");
                }
                if(TextUtils.isEmpty(getbankaddress)){
                    bankaddress.setError("Mobile Number Required");
                }
                updateBankDetails(BankId ,getbankname,getupiid,getbankaccountname,getbankaccountnumber,getbankifsccode,getbankaddress);
                Intent intent1 = new Intent(MyBuildingEditBank.this, MyBuildingBank.class);
                startActivity(intent1);
                finish();
            }
        });

    }


    private boolean updateBankDetails(String addbankId, String  bankName,String bankUpiId , String bankAccountName ,String bankAccountNumber ,String bankIfscCode , String bankAddress){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Add Bank").child(currentuserId).child(buildingId).child(addbankId);
        MyBuildingAddBankModel myBuildingAddBankModel = new MyBuildingAddBankModel(addbankId, bankName, bankUpiId , bankAccountName , bankAccountNumber, bankIfscCode , bankAddress);
        databaseReference.setValue(myBuildingAddBankModel);
        Toast.makeText(this, "Bank Details Updated", Toast.LENGTH_LONG).show();
        return true;
    }
}
