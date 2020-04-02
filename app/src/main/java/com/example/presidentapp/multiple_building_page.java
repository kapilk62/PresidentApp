package com.example.presidentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.presidentapp.Model.CreateNewSocietyModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class multiple_building_page extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView adminBuildingList;
    DatabaseReference databaseReference;
    CardView createNewSociety;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_building_page);
        String currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(currentuserId).child("New Building");
        databaseReference.keepSynced(true);

        createNewSociety = findViewById(R.id.create_new_society_cardView);
        adminBuildingList = findViewById(R.id.recyclerViewAdmin);
        adminBuildingList.setHasFixedSize(true);
        adminBuildingList.setLayoutManager( new LinearLayoutManager(this));

        createNewSociety.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<CreateNewSocietyModel,buildingAdminViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<CreateNewSocietyModel, buildingAdminViewHolder>
                (CreateNewSocietyModel.class,R.layout.cardview_admin,buildingAdminViewHolder.class,databaseReference) {
            @Override
            protected void populateViewHolder(buildingAdminViewHolder buildingAdminViewHolder, CreateNewSocietyModel createNewSocietyModel, final int i) {
                buildingAdminViewHolder.setBuildingName(createNewSocietyModel.getBuildingName());
                buildingAdminViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String buildingId = getRef(i).getKey();
                        Intent intent = new Intent(multiple_building_page.this,MainActivity.class);
                        intent.putExtra("BUILDINGID",buildingId);
                        startActivity(intent);
                    }
                });

            }
        };
        adminBuildingList.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), Create_New_Society.class);
        startActivity(intent);
    }

    public static class buildingAdminViewHolder extends RecyclerView.ViewHolder{
        View view;
        public buildingAdminViewHolder(View itemView)
        {
            super(itemView);
            view=itemView;
        }
        public void setBuildingName(String buildingName){
            TextView building_name = view.findViewById(R.id.building_name_cardView);
            building_name.setText(buildingName);
        }
    }
}
