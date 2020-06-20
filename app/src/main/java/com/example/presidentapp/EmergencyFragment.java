package com.example.presidentapp;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.presidentapp.Adapter.Emergency_number_adapter;
import com.example.presidentapp.Model.Emergency_Num_Model;
import com.example.presidentapp.Model.EventModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmergencyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmergencyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ListView listViewEmergencyNumbers;
    DatabaseReference databaseEmergencyNumbers;
    List<Emergency_Num_Model> EmergencyNumberList;
    final String currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private static final String TAG = "Hello";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Activity context;
    TextInputEditText editTextEmergencyname;
    TextInputEditText editTextEmergencynumber;
    View dialogView;
    Button buttonUpdate;
    Button buttonDelete;
    String buildingId;
    AlertDialog alertDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_emergency, container, false);
        context=getActivity();
        GlobalClass globalClass = (GlobalClass) context.getApplicationContext();
        buildingId = globalClass.getBuildingId();
        databaseEmergencyNumbers = FirebaseDatabase.getInstance().getReference("Emergency_Numbers").child(buildingId).child(currentuserId);
        listViewEmergencyNumbers = (ListView) v.findViewById(R.id.listViewEmergencyNumber);
        EmergencyNumberList = new ArrayList<>();
        //Inflate the layout for this fragment
        listViewEmergencyNumbers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Emergency_Num_Model emergency_num_model =  EmergencyNumberList.get(position);
                showupdatedialog(parent,view.getContext(),emergency_num_model.getEmergencyNumberId(),emergency_num_model.getEmergencyName(),emergency_num_model.getEmergencyNumber());
                return false;
            }
        });
        return v;

    }

    public void onStart(){
        super.onStart();
        databaseEmergencyNumbers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                EmergencyNumberList.clear();
                for(DataSnapshot emergencyNumberSnapshot : dataSnapshot.getChildren()) {
                    Emergency_Num_Model emergency_num_model = emergencyNumberSnapshot.getValue(Emergency_Num_Model.class);
                    EmergencyNumberList.add(emergency_num_model);
                }
                Emergency_number_adapter emergency_number_adapter = new Emergency_number_adapter(getActivity(),EmergencyNumberList);
                listViewEmergencyNumbers.setAdapter(emergency_number_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FloatingActionButton bt= context.findViewById(R.id.Emergency_floatingActionButton);
        bt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                //create an Intent object
                Intent intent=new Intent(context, EmergencyForm.class);
                //add data to the Intent object
                startActivity(intent);
            }

        });
    }
    public EmergencyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EmergencyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EmergencyFragment newInstance(String param1, String param2) {
        EmergencyFragment fragment = new EmergencyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }
    private void showupdatedialog(AdapterView<?> parent,Context context,final String emergencynumberId, final String emergencyname, final String emergencynumber){
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.update_emergency_dialog, parent,false);


        dialogBuilder.setView(dialogView);

        editTextEmergencyname = dialogView.findViewById(R.id.update_emergency_name_txt_fld);
        editTextEmergencynumber = dialogView.findViewById(R.id.update_emergency_mobile_number__txt_fld);

        buttonUpdate = dialogView.findViewById(R.id.update_emergency_btn);
        buttonDelete = dialogView.findViewById(R.id.delete_emergency_btn);

        dialogBuilder.setTitle("Updating emergencynumber: " + emergencyname);

        alertDialog = dialogBuilder.create();
        alertDialog.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String emergencyName = editTextEmergencyname.getText().toString().trim();
                String emergencyMobileNumber = editTextEmergencynumber.getText().toString().trim();

                if (TextUtils.isEmpty(emergencyName)) {
                    editTextEmergencyname.setError("Emergency name required");
                    return;
                }
                if (TextUtils.isEmpty(emergencyMobileNumber)) {
                    editTextEmergencynumber.setError("Emergency Mobile Number required");
                    return;
                }
                updateEmergency(emergencynumberId, emergencyName, emergencyMobileNumber);
                alertDialog.dismiss();

            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                deleteEmergency(emergencynumberId);
                alertDialog.dismiss();
            }
        });
    }
    private void updateEmergency(String emergencyId, String emergencyName, String emergencyNumber){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Emergency_Numbers").child(currentuserId).child(buildingId).child(emergencyId);
        Emergency_Num_Model emergency_num_model = new Emergency_Num_Model(emergencyId, emergencyName, emergencyNumber);
        databaseReference.setValue(emergency_num_model);
        Toast.makeText(context, "Emergency Number Updated", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onClick: "+emergencyName);

    }
    private void deleteEmergency(String emergencyId) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Emergency_Numbers").child(currentuserId).child(buildingId).child(emergencyId);
        databaseReference.removeValue();

        Toast.makeText(context, "Emergency Number is deleted", Toast.LENGTH_LONG).show();
    }
}
