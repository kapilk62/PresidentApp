package com.example.presidentapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.presidentapp.Adapter.Emergency_number_adapter;
import com.example.presidentapp.Adapter.Vendor_number_adapter;
import com.example.presidentapp.Model.Emergency_Num_Model;
import com.example.presidentapp.Model.Vendor_Num_Model;
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
 * Use the {@link VendorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VendorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ListView listViewVendorNumber;
    DatabaseReference databaseVendorDetails;
    List<Vendor_Num_Model> VendorDetailList;
    final String currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Activity context;
    TextInputEditText editTextVendorname;
    TextInputEditText editTextVendornumber;
    TextInputEditText editTextVendoraddress;
    Spinner editSpinnervendorCategory;
    View dialogView;
    Button buttonUpdate;
    Button buttonDelete;
    AlertDialog alertDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_vendor, container, false);
        context=getActivity();

        String currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseVendorDetails = FirebaseDatabase.getInstance().getReference("Vendor_details").child(currentuserId);
        listViewVendorNumber = (ListView) v.findViewById(R.id.listViewVendorDetails);
        VendorDetailList = new ArrayList<>();
        //Inflate the layout for this fragment
        listViewVendorNumber.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Vendor_Num_Model vendor_num_model =  VendorDetailList.get(position);
                showupdatedialog(parent,view.getContext(),vendor_num_model.getVendorNumberid(),vendor_num_model.getVendorName(),vendor_num_model.getVendorNumber(),vendor_num_model.getVendorAddress(),vendor_num_model.getVendorCategory());
                return false;
            }
        });
        return v;

        //Inflate the layout for this fragment

    }


    public void onStart(){
        super.onStart();
        databaseVendorDetails.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                VendorDetailList.clear();
                for(DataSnapshot vendorDetailSnapshot : dataSnapshot.getChildren()) {
                    Vendor_Num_Model vendor_num_model = vendorDetailSnapshot.getValue(Vendor_Num_Model.class);
                    VendorDetailList.add(vendor_num_model);
                }
                Vendor_number_adapter vendor_number_adapter = new Vendor_number_adapter(getActivity(),VendorDetailList);
                listViewVendorNumber.setAdapter(vendor_number_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FloatingActionButton bt= context.findViewById(R.id.Vendor_floatingActionButton);
        bt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                //create an Intent object
                Intent intent=new Intent(context, VendorForm.class);
                //add data to the Intent object
                startActivity(intent);
            }

        });
    }
    public VendorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VendorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VendorFragment newInstance(String param1, String param2) {
        VendorFragment fragment = new VendorFragment();
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
    private void showupdatedialog(AdapterView<?> parent, Context context, final String vendorNumberid, String vendorName, String vendorNumber, String vendorAddress, String vendorCategory) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.update_vendor_dialog, parent,false);
        dialogBuilder.setView(dialogView);

        editTextVendorname = dialogView.findViewById(R.id.update_vendor_name_txt_fld);
        editTextVendornumber = dialogView.findViewById(R.id.update_vendor_mobile_number__txt_fld);
        editTextVendoraddress = dialogView.findViewById(R.id.update_vendor_address__txt_fld);
        editSpinnervendorCategory = dialogView.findViewById(R.id.update_vendorspinner);

        buttonUpdate = dialogView.findViewById(R.id.update_vendor_btn);
        buttonDelete = dialogView.findViewById(R.id.delete_vendor_btn);

        dialogBuilder.setTitle("Updating emergencynumber: " + vendorName);

        alertDialog = dialogBuilder.create();
        alertDialog.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String newvendorName = editTextVendorname.getText().toString().trim();
                String newvendorMobileNumber = editTextVendornumber.getText().toString().trim();
                String newvendorAddress = editTextVendoraddress.getText().toString().trim();
                String newvendorCategory = editSpinnervendorCategory.getSelectedItem().toString().trim();
                if (TextUtils.isEmpty(newvendorName)) {
                    editTextVendorname.setError("Vendor name required");
                    return;
                }
                if (TextUtils.isEmpty(newvendorMobileNumber)) {
                    editTextVendornumber.setError("Vendor Mobile Number required");
                    return;
                }
                updateVendor(vendorNumberid, newvendorName, newvendorMobileNumber, newvendorAddress ,newvendorCategory);
                alertDialog.dismiss();

            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                deleteVendor(vendorNumberid);
                alertDialog.dismiss();
            }
        });
    }
    private void updateVendor(String vendorId, String vendorName, String vendorNumber, String vendorAddress, String vendorCategory){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Vendor_details").child(currentuserId).child(vendorId);
        Vendor_Num_Model vendor_num_model = new Vendor_Num_Model(vendorId, vendorName, vendorAddress, vendorAddress, vendorCategory);
        databaseReference.setValue(vendor_num_model);
        Toast.makeText(context, "Vendor Updated", Toast.LENGTH_LONG).show();


    }
    private void deleteVendor(String vendorId) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Vendor_details").child(currentuserId).child(vendorId);
        databaseReference.removeValue();

        Toast.makeText(context, "Vendor is deleted", Toast.LENGTH_LONG).show();
    }
}
