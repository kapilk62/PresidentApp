package com.example.presidentapp.GateKeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.example.presidentapp.Adapter.GateKeeper_adapter;
import com.example.presidentapp.Model.GateKeeper_AddGateKeeperModel;
import com.example.presidentapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GateKeeper_gatekeepers extends AppCompatActivity{



    ListView listViewGateKeeper;
    DatabaseReference databaseReferenceGateKeeper;
    List<GateKeeper_AddGateKeeperModel> addGateKeeperModelList;
    final String currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate_keeper_gatekeepers);

        databaseReferenceGateKeeper = FirebaseDatabase.getInstance().getReference("Add GateKeeper").child(currentuserId);

        listViewGateKeeper = findViewById(R.id.listviewgatekeeperdetails);
        addGateKeeperModelList = new ArrayList<>();

        listViewGateKeeper.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                GateKeeper_AddGateKeeperModel gateKeeper_addGateKeeperModel = addGateKeeperModelList.get(position);
                showupdatedialog(gateKeeper_addGateKeeperModel.getGatekeeperId(),gateKeeper_addGateKeeperModel.getGatekeeperfirstname(),gateKeeper_addGateKeeperModel.getGatekeeperlastname(),gateKeeper_addGateKeeperModel.getGatekeeperemail(),gateKeeper_addGateKeeperModel.getGatekeepermobilenumber());
                return false;
            }
        });

    }

    private void showupdatedialog(final String gatekeeperId, String gatekeeperfirstname, String gatekeeperlastname, String gatekeeperemail, final String gatekeepermobilenumber) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_gatekeeper_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextGateKeeperFirstName = (EditText) dialogView.findViewById(R.id.update_gatekeeper_first_name_txt_fld);
        final EditText editTextGateKeeperLastName = (EditText) dialogView.findViewById(R.id.update_gatekeeper_last_name_txt_fld);
        final EditText editTextGateKeeperEmail = (EditText) dialogView.findViewById(R.id.update_gatekeeper_email_txt_fld);
        final EditText editTextGateKeeperMobileNumber = (EditText) dialogView.findViewById(R.id.update_gatekeeper_mobile_number_txt_fld);

        final Button buttonUpdate = dialogView.findViewById(R.id.update_gatekeeper_btn);
        final Button buttonDelete = dialogView.findViewById(R.id.delete_gatekeeper_btn);

        dialogBuilder.setTitle("Updating rule: " + gatekeeperfirstname);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String gatekeeperfirstname = editTextGateKeeperFirstName.getText().toString().trim();
                String gatekeeperlastname = editTextGateKeeperLastName.getText().toString().trim();
                String gatekeeperemail = editTextGateKeeperEmail.getText().toString().trim();
                String gatekeepermobilenumber = editTextGateKeeperMobileNumber.getText().toString().trim();

                if (TextUtils.isEmpty(gatekeeperfirstname)) {
                    editTextGateKeeperFirstName.setError("first name  is required");
                    return;
                }
                updategatekeeper(gatekeeperId,gatekeeperfirstname,gatekeeperlastname,gatekeeperemail,gatekeepermobilenumber);
                alertDialog.dismiss();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                deletegatekeeper(gatekeeperId);
                alertDialog.dismiss();
            }
        });
    }



    private void updategatekeeper(String gatekeeperId, String gatekeeperfirstname, String gatekeeperlastname, String gatekeeperemail, String gatekeepermobilenumber) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Add GateKeeper").child(currentuserId).child(gatekeeperId);
        GateKeeper_AddGateKeeperModel gateKeeper_addGateKeeperModel = new GateKeeper_AddGateKeeperModel(gatekeeperId,gatekeeperfirstname,gatekeeperlastname,gatekeeperemail,gatekeepermobilenumber);
        databaseReference.setValue(gateKeeper_addGateKeeperModel);
    }

    private void deletegatekeeper(String gatekeeperId) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Add GateKeeper").child(currentuserId).child(gatekeeperId);
        databaseReference.removeValue();

        Toast.makeText(this, "GateKeeper is deleted", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReferenceGateKeeper.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                addGateKeeperModelList.clear();
                for(DataSnapshot gatekeeperdataSnapshot : dataSnapshot.getChildren()){
                    GateKeeper_AddGateKeeperModel gateKeeper_addGateKeeperModel = gatekeeperdataSnapshot.getValue(GateKeeper_AddGateKeeperModel.class);
                    addGateKeeperModelList.add(gateKeeper_addGateKeeperModel);
                }
                GateKeeper_adapter adapter  = new GateKeeper_adapter(GateKeeper_gatekeepers.this, addGateKeeperModelList);
                listViewGateKeeper.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void AddGateKeeper(View view) {
        Intent intent = new Intent(getApplicationContext(), GateKeeper_gatekeeper_add.class);
        startActivity(intent);
    }
}
