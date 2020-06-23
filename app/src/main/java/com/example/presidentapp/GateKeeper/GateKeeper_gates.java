package com.example.presidentapp.GateKeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.presidentapp.Adapter.Gates_adapter;
import com.example.presidentapp.Model.GateKeeper_AddGatesModel;
import com.example.presidentapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

import java.util.ArrayList;
import java.util.List;

public class GateKeeper_gates extends AppCompatActivity{

    ListView listViewGates;
    DatabaseReference databaseReferenceGates;
    List<GateKeeper_AddGatesModel> addGatesModelList;
    final String currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate_keeper_gates);

        databaseReferenceGates = FirebaseDatabase.getInstance().getReference("Add Gates").child(currentuserId);

        listViewGates = findViewById(R.id.listviewgatesdetails);
        addGatesModelList = new ArrayList<>();

        listViewGates.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                GateKeeper_AddGatesModel gateKeeper_addGatesModel = addGatesModelList.get(position);
                showupdatedialog(gateKeeper_addGatesModel.getGatesId(),gateKeeper_addGatesModel.getGatesName());
                return false;
            }
        });
    }

    private void showupdatedialog(final String gatesId, final String gatesName) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_gates_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextGates = (EditText) dialogView.findViewById(R.id.update_gates_edit_txt);
        final Button buttonUpdate = dialogView.findViewById(R.id.update_gates_btn);
        final Button buttonDelete = dialogView.findViewById(R.id.delete_gates_btn);

        dialogBuilder.setTitle("Updating rule: " + gatesName);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Gates = editTextGates.getText().toString().trim();
                if (TextUtils.isEmpty(Gates)) {
                    editTextGates.setError("gate name/number is required");
                    return;
                }
                updategates(gatesId,Gates);
                alertDialog.dismiss();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                deletegates(gatesId);
                alertDialog.dismiss();
            }
        });
    }

    private void updategates(String gatesId, String Gates ){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Add Gates").child(currentuserId).child(gatesId);
        GateKeeper_AddGatesModel gateKeeper_addGatesModel = new GateKeeper_AddGatesModel(gatesId , Gates);
        databaseReference.setValue(gateKeeper_addGatesModel);
    }
    private void deletegates(String gatesId) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Add Gates").child(currentuserId).child(gatesId);
        databaseReference.removeValue();

        Toast.makeText(this, "Gate is deleted", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReferenceGates.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                addGatesModelList.clear();

                for(DataSnapshot gatesdataSnapshot : dataSnapshot.getChildren()){
                    GateKeeper_AddGatesModel gateKeeper_addGatesModel = gatesdataSnapshot.getValue(GateKeeper_AddGatesModel.class);
                    addGatesModelList.add(gateKeeper_addGatesModel);
                }
                Gates_adapter adapter = new Gates_adapter(GateKeeper_gates.this, addGatesModelList);
                listViewGates.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void AddGates(View view) {
        Intent intent = new Intent(getApplicationContext(), GateKeeper_gates_add.class);
        startActivity(intent);
    }
}
