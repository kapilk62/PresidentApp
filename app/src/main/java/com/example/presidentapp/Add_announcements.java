package com.example.presidentapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.presidentapp.Model.AddAnnouncementModel;
import com.example.presidentapp.Model.EventModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_announcements extends AppCompatActivity implements
        View.OnClickListener{

    private int mYear, mMonth, mDay, mHour, mMinute;
    Button btnDatePicker, btnTimePicker, btnAddAnnoucement;
    TextInputEditText txtDate, txtTime, AnnouncementName, AnnouncementDescription;
    String currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    Spinner AnnoucementType;
    DatabaseReference databaseAnnoucements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_announcements);

        databaseAnnoucements = FirebaseDatabase.getInstance().getReference("Add Annoucements").child(currentuserId);


        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=findViewById(R.id.in_date);
        txtTime=findViewById(R.id.in_time);
        AnnouncementName=findViewById(R.id.announcement_name_txt_fld);
        AnnouncementDescription=findViewById(R.id.announcement_description_txt_fld);
        btnAddAnnoucement=findViewById(R.id.add_announcement_button);
        AnnoucementType=findViewById(R.id.announcement_type_spinner);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        btnAddAnnoucement.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addAnnouncement();
                AnnouncementName.setText(null);
                AnnouncementDescription.setText(null);
                txtDate.setText(null);
                txtTime.setText(null);
                //annoucementType.setSelected();
                Intent intent = new Intent(getApplicationContext(), Announcements.class);
                startActivity(intent);
            }
        });
    }

    private void addAnnouncement(){

        String announcementname =  AnnouncementName.getText().toString().trim();
        String announcementdescription = AnnouncementDescription.getText().toString().trim();
        String announcementdate = txtDate.getText().toString().trim();
        String announcementtime = txtTime.getText().toString().trim();
        String announcementtype = AnnoucementType.getSelectedItem().toString().trim();

        if(!TextUtils.isEmpty(announcementname)){
            String announcementId = databaseAnnoucements.push().getKey();
            AddAnnouncementModel addAnnouncementModel = new AddAnnouncementModel(announcementId,announcementname,announcementdescription,announcementdate,announcementtime,announcementtype);
            databaseAnnoucements.child(announcementId).setValue(addAnnouncementModel);
            Toast.makeText(this,"Announcement added",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "You should enter the announcement Name", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
}
