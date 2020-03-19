package com.example.presidentapp.MyBuilding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.presidentapp.Adapter.Bank_Details_adapter;
import com.example.presidentapp.Model.MyBuildingAddBankModel;
import com.example.presidentapp.MyBuildingEditBank;
import com.example.presidentapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyBuildingBank extends AppCompatActivity {
    private static final String TAG = "hello";

    ListView listViewBankDetails;
    DatabaseReference databaseReferencebankDetails;
    List<MyBuildingAddBankModel> bankdetailsmodellist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_building_bank);

        databaseReferencebankDetails = FirebaseDatabase.getInstance().getReference("Add Bank");

        listViewBankDetails = findViewById(R.id.listviewbankdetails);
        bankdetailsmodellist = new ArrayList<>();

        listViewBankDetails.setOnItemClickListener(new AdapterView.OnItemClickListener(){


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyBuildingAddBankModel myBuildingAddBankModel = bankdetailsmodellist.get(position);
                Intent intent = new Intent(getApplicationContext(), MyBuildingShowBankDetails.class);
                intent.putExtra("BankId",myBuildingAddBankModel.getAddbankId());
                intent.putExtra("BANKNAME",myBuildingAddBankModel.getAddbankname());
                intent.putExtra("BANKUPIID",myBuildingAddBankModel.getAddbankUPIId());
                intent.putExtra("BANKACCOUNTNAME",myBuildingAddBankModel.getAddbankaccountname());
                intent.putExtra("BANKACCOUNTNUMBER",myBuildingAddBankModel.getAddbankaccountnumber());
                intent.putExtra("BANKIFSCCODE",myBuildingAddBankModel.getAddbankIFSCcode());
                intent.putExtra("BANKADDRESS",myBuildingAddBankModel.getAddbankaddress());
                startActivity(intent);
                Log.d(TAG, "onItemClick: "+myBuildingAddBankModel.getAddbankId());
                openbankdetails(myBuildingAddBankModel.getAddbankname(),myBuildingAddBankModel.getAddbankUPIId(),myBuildingAddBankModel.getAddbankaccountname(),myBuildingAddBankModel.getAddbankaccountnumber(),myBuildingAddBankModel.getAddbankIFSCcode(),myBuildingAddBankModel.getAddbankaddress());


            }
        });

    }

    private void openbankdetails(String addbankname, String addbankUPIId, String addbankaccountname, String addbankaccountnumber, String addbankIFSCcode, String addbankaddress) {

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReferencebankDetails.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bankdetailsmodellist.clear();

                for(DataSnapshot bankdetailsSnapshot : dataSnapshot.getChildren()){
                    MyBuildingAddBankModel myBuildingAddBankMode = bankdetailsSnapshot.getValue(MyBuildingAddBankModel.class);
                    bankdetailsmodellist.add(myBuildingAddBankMode);

                }
                Bank_Details_adapter adapter = new Bank_Details_adapter( MyBuildingBank.this, bankdetailsmodellist);
                listViewBankDetails.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void AddBank(View view) {
        Intent intent = new Intent(getApplicationContext(), MybuildngAddBank.class);
        startActivity(intent);
    }
}
