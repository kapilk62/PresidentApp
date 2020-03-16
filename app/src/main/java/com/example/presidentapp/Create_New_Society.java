package com.example.presidentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.presidentapp.Model.CreateNewSocietyModel;
import com.example.presidentapp.Model.EventModel;
import com.example.presidentapp.Model.Rule;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Struct;
import java.util.ArrayList;

public class Create_New_Society extends AppCompatActivity{

    Spinner sp_state, sp_city, sp_buildingtype;
    ArrayList<String> arrayList_parent;
    ArrayAdapter<String> arrayAdapter_parent;

    ArrayList<String> arrayList_AndhraPradesh, arrayList_ArunachalPradesh;
    ArrayAdapter<String> arrayAdapter_child;

    Button btnCreateNewSociety;
    DatabaseReference databaseReferenceNewSociety;
    TextInputEditText buildingname, buildingwings, buildingaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__new__society);

        sp_state = findViewById(R.id.building_state_spinner);
        sp_city = findViewById(R.id.building_city_spinner);
        sp_buildingtype = findViewById(R.id.building_type_spinner);
        btnCreateNewSociety = findViewById(R.id.submit_new_society_button);
        buildingname = findViewById(R.id.building_name);
        buildingwings = findViewById(R.id.building_wings);
        buildingaddress = findViewById(R.id.building_address);
        databaseReferenceNewSociety = FirebaseDatabase.getInstance().getReference("New Buildings");

        arrayList_parent = new ArrayList<>();
        arrayList_parent.add("Andhra Paresh");
        arrayList_parent.add("Arunachal Pradesh");

        arrayAdapter_parent = new ArrayAdapter<>(getApplication(),android.R.layout.simple_spinner_dropdown_item,arrayList_parent);

        sp_state.setAdapter(arrayAdapter_parent);

        btnCreateNewSociety.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CreateNewSociety();
                finish();
            }
        });

        //child City process
        //position 0
        arrayList_AndhraPradesh = new ArrayList<>();
        arrayList_AndhraPradesh.add("Anantapur");
        arrayList_AndhraPradesh.add("Chittoor");
        arrayList_AndhraPradesh.add("East Godavari");
        arrayList_AndhraPradesh.add("Guntur");
        arrayList_AndhraPradesh.add("Krishna");
        arrayList_AndhraPradesh.add("Kurnool");
        arrayList_AndhraPradesh.add("Nellore");
        arrayList_AndhraPradesh.add("Prakasam");
        arrayList_AndhraPradesh.add("Srikakulam");
        arrayList_AndhraPradesh.add("Visakhapatnam");
        arrayList_AndhraPradesh.add("Vizianagaram");
        arrayList_AndhraPradesh.add("West Godavari");
        arrayList_AndhraPradesh.add("YSR Kadapa");
        //position 1
        arrayList_ArunachalPradesh = new ArrayList<>();
        arrayList_ArunachalPradesh.add("Tawang");
        arrayList_ArunachalPradesh.add("West Kameng");
        arrayList_ArunachalPradesh.add("East Kameng");
        arrayList_ArunachalPradesh.add("Papum Pare");
        arrayList_ArunachalPradesh.add("Kurung Kumey");
        arrayList_ArunachalPradesh.add("Kra Daadi");
        arrayList_ArunachalPradesh.add("Lower Subansiri");
        arrayList_ArunachalPradesh.add("Upper Subansiri");
        arrayList_ArunachalPradesh.add("West Siang");
        arrayList_ArunachalPradesh.add("East Siang");
        arrayList_ArunachalPradesh.add("Siang");
        arrayList_ArunachalPradesh.add("Upper Siang");
        arrayList_ArunachalPradesh.add("Lower Siang");
        arrayList_ArunachalPradesh.add("Lower Dibang Valley");
        arrayList_ArunachalPradesh.add("Dibang Valley");
        arrayList_ArunachalPradesh.add("Anjaw");
        arrayList_ArunachalPradesh.add("Lohit");
        arrayList_ArunachalPradesh.add("Namsai");
        arrayList_ArunachalPradesh.add("Changlang");
        arrayList_ArunachalPradesh.add("Tirap");
        arrayList_ArunachalPradesh.add("Longding");


        sp_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_AndhraPradesh);
                }
                if(position==1){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList_ArunachalPradesh);
                }
                sp_city.setAdapter(arrayAdapter_child);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void CreateNewSociety(){

        String getbuildingtype =  sp_buildingtype.getSelectedItem().toString().trim();
        String getbuildingstate = sp_state.getSelectedItem().toString().trim();
        String getbuildingcity = sp_city.getSelectedItem().toString().trim();
        String getbuildingname = buildingname.getText().toString().trim();
        String getbuildingwings = buildingwings.getText().toString().trim();
        String getbuildingaddress = buildingaddress.getText().toString().trim();

        if(!TextUtils.isEmpty(getbuildingtype)){
            String buildingId = databaseReferenceNewSociety.push().getKey();
            CreateNewSocietyModel createNewSocietyModel = new CreateNewSocietyModel(buildingId, getbuildingtype,getbuildingname,getbuildingwings,getbuildingstate,getbuildingcity,getbuildingaddress);
            databaseReferenceNewSociety.child(buildingId).setValue(createNewSocietyModel);
            Toast.makeText(this,"New Society added",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "You should enter the type", Toast.LENGTH_LONG).show();
        }
    }
}
