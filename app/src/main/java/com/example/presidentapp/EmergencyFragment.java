package com.example.presidentapp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.presidentapp.Adapter.Emergency_number_adapter;
import com.example.presidentapp.Model.Emergency_Num_Model;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Activity context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_emergency, container, false);
        context=getActivity();
        String currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseEmergencyNumbers = FirebaseDatabase.getInstance().getReference("Emergency_Numbers").child(currentuserId);
        listViewEmergencyNumbers = (ListView) v.findViewById(R.id.listViewEmergencyNumber);
        EmergencyNumberList = new ArrayList<>();
        //Inflate the layout for this fragment
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
}
