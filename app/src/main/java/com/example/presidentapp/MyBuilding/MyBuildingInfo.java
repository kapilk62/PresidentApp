package com.example.presidentapp.MyBuilding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Snapshot;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.presidentapp.Create_New_Society;
import com.example.presidentapp.Model.CreateNewSocietyModel;
import com.example.presidentapp.Model.User;
import com.example.presidentapp.Profile;
import com.example.presidentapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

public class MyBuildingInfo extends AppCompatActivity {

    TextView buildingname, buildingnumber, buildingaddress;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_building_info);

        buildingname = findViewById(R.id.textViewbuildingname);
        buildingnumber = findViewById(R.id.textViewbuildingmobilenumber);
        buildingaddress = findViewById(R.id.textViewbuildingaddress);

        //CreateNewSocietyModel createNewSocietyModel = new CreateNewSocietyModel();
        //User user = new User();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReferenceuser = firebaseDatabase.getInstance().getReference("Users").child(firebaseAuth.getUid());
        DatabaseReference databaseReferencecreatenewsociety = firebaseDatabase.getReference("New Buildings");

        databaseReferenceuser.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                buildingnumber.setText(user.getMobile_number());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MyBuildingInfo.this, databaseError.getCode(),Toast.LENGTH_LONG).show();
            }
        });

        databaseReferencecreatenewsociety.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CreateNewSocietyModel createNewSocietyModel = dataSnapshot.getValue(CreateNewSocietyModel.class);
                buildingname.setText(createNewSocietyModel.getBuildingName());
                buildingaddress.setText(createNewSocietyModel.getBuildingAddress());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MyBuildingInfo.this, databaseError.getCode(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
