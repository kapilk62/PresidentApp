package com.example.presidentapp;

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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.presidentapp.Adapter.Announcement_adapter;
import com.example.presidentapp.Model.AddAnnouncementModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Announcements extends AppCompatActivity{

    ListView listViewAnnouncement;
    DatabaseReference databaseReferenceAnnouncement;
    List<AddAnnouncementModel> addAnnouncementModelList;
    final String currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);

        databaseReferenceAnnouncement = FirebaseDatabase.getInstance().getReference("Add Annoucements").child(currentuserId);

        listViewAnnouncement = findViewById(R.id.listviewannouncementdetails);
        addAnnouncementModelList = new ArrayList<>();

        listViewAnnouncement.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AddAnnouncementModel addAnnouncementModel = addAnnouncementModelList.get(position);
                showupdatedialog(addAnnouncementModel.getAnnoucementId(),addAnnouncementModel.getAnnoucementName(),addAnnouncementModel.getAnnoucementDescrpition(),addAnnouncementModel.getAnnoucementTime(),addAnnouncementModel.getAnnoucementTime(),addAnnouncementModel.getAnnoucementType());
                return false;
            }
        });
    }

    private void showupdatedialog(final String annoucementId,final String annoucementName,final String annoucementDescrpition,final String annoucementDate,final String annoucementTime,final String annoucementType) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_announcement_dialog, null);
        dialogBuilder.setView(dialogView);

        final TextInputEditText editTextAnnouncementname = dialogView.findViewById(R.id.update_announcement_name_txt_fld);
        final TextInputEditText editTextAnnouncementdescription = dialogView.findViewById(R.id.update_announcement_description_txt_fld);
        final Spinner editSpinnerAnnouncementtype = dialogView.findViewById(R.id.update_announcementtypespinner);
        final Button buttonUpdate = dialogView.findViewById(R.id.update_announcement_btn);
        final Button buttonDelete = dialogView.findViewById(R.id.delete_announcement_btn);

        dialogBuilder.setTitle("Updating announcement: " + annoucementName);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String annoucementName = editTextAnnouncementname.getText().toString().trim();
                String annoucementDescrpition = editTextAnnouncementdescription.getText().toString().trim();
                String annoucementType =  editSpinnerAnnouncementtype.getSelectedItem().toString().trim();

                if (TextUtils.isEmpty(annoucementName)) {
                    editTextAnnouncementname.setError("Annoucement name required");
                    return;
                }
                if (TextUtils.isEmpty(annoucementDescrpition)) {
                    editTextAnnouncementdescription.setError("Annoucement description required");
                    return;
                }
                updateAnnoucement(annoucementId,annoucementName,annoucementDescrpition, annoucementDate,annoucementTime,annoucementType);
                alertDialog.dismiss();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                deleteAnnoucement(annoucementId);
                alertDialog.dismiss();
            }
        });
    }
    private void updateAnnoucement(String annoucementId, String annoucementName, String annoucementDescrpition,String annoucementDate,String annoucementTime, String annoucementType) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Add Annoucements").child(currentuserId).child(annoucementId);
        AddAnnouncementModel addAnnouncementModel = new AddAnnouncementModel(annoucementId,annoucementName,annoucementDescrpition,annoucementDate,annoucementTime,annoucementType);
        databaseReference.setValue(addAnnouncementModel);
    }

    private void deleteAnnoucement(String annoucementId) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Add Annoucements").child(currentuserId).child(annoucementId);
        databaseReference.removeValue();

        Toast.makeText(this, "announcement is deleted", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onStart() {
        super.onStart();

        databaseReferenceAnnouncement.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                addAnnouncementModelList.clear();
                for(DataSnapshot announcementSnapshot : dataSnapshot.getChildren()){
                    AddAnnouncementModel addAnnouncementModel = announcementSnapshot.getValue(AddAnnouncementModel.class);
                    addAnnouncementModelList.add(addAnnouncementModel);
                }
                Announcement_adapter adapter = new Announcement_adapter(Announcements.this, addAnnouncementModelList);
                listViewAnnouncement.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void AddAnnouncement(View view) {
        Intent intent = new Intent(getApplicationContext(), Add_announcements.class);
        startActivity(intent);
    }
}
