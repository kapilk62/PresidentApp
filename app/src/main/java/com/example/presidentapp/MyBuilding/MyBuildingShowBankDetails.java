package com.example.presidentapp.MyBuilding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.presidentapp.GlobalClass;
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
    String buildingId;
    String currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

    private static final String TAG = "1";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_building_show_bank_details);

        GlobalClass globalClass = (GlobalClass) getApplicationContext();
        buildingId = globalClass.getBuildingId();


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

        DatabaseReference databaseReferencebankdetailsshow = firebaseDatabase.getReference("Add Bank").child(currentuserId).child(buildingId);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.edititem:
                Intent intent = getIntent();
                final String BankId = intent.getStringExtra("BankId");
                final String BankName = intent.getStringExtra("BANKNAME");
                final String BankUpiId = intent.getStringExtra("BANKUPIID");
                final String BankAccountName = intent.getStringExtra("BANKACCOUNTNAME");
                final String BankAccountNumber = intent.getStringExtra("BANKACCOUNTNUMBER");
                final String BankIfscCode = intent.getStringExtra("BANKIFSCCODE");
                final String BankAddress = intent.getStringExtra("BANKADDRESS");
                Log.d(TAG, "onOptionsItemSelected: "+BankId);
                Intent intentedit = new Intent(MyBuildingShowBankDetails.this, MyBuildingEditBank.class);
                intentedit.putExtra("BankId",BankId);
                intentedit.putExtra("BANKNAMEEDIT",BankName);
                intentedit.putExtra("BANKUPIIDEDIT",BankUpiId);
                intentedit.putExtra("BANKACCOUNTNAMEEDIT",BankAccountName);
                intentedit.putExtra("BANKACCOUNTNUMBEREDIT",BankAccountNumber);
                intentedit.putExtra("BANKIFSCCODEEDIT",BankIfscCode);
                intentedit.putExtra("BANKADDRESSEDIT",BankAddress);
                startActivity(intentedit);
                break;

            case  R.id.deleteitem:
                Intent intent1 = getIntent();
                final String BankId1 = intent1.getStringExtra("BankId");
                deleteBank(BankId1);
                break;
            default:

                break;
        }
        return true;
    }

    private void deleteBank(String bankId) {
        DatabaseReference databaseReferencebank = FirebaseDatabase.getInstance().getReference("Add Bank").child(currentuserId).child(buildingId).child(bankId);
        databaseReferencebank.removeValue();

        Toast.makeText(this, "event is deleted", Toast.LENGTH_LONG).show();
    }

}
