package com.example.presidentapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.presidentapp.Model.EventModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Add_event extends AppCompatActivity implements
        View.OnClickListener {


    Button btnDatePicker, btnTimePicker, btnEventShow;
    TextInputEditText txtDate, txtTime, EventName, EventDescription;
    private int mYear, mMonth, mDay, mHour, mMinute;

    DatabaseReference databaseEvents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        String currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseEvents = FirebaseDatabase.getInstance().getReference("Events").child(currentuserId);

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=findViewById(R.id.in_date);
        txtTime=findViewById(R.id.in_time);
        EventName=findViewById(R.id.event_name_txt_fld);
        EventDescription=findViewById(R.id.event_description_txt_fld);
        btnEventShow=findViewById(R.id.add_bank_button);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        btnEventShow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addEvent();
                EventName.setText(null);
                EventDescription.setText(null);
                txtDate.setText(null);
                txtTime.setText(null);
                Intent intent = new Intent(getApplicationContext(), Event.class);
                startActivity(intent);
            }
        });

    }

    private void addEvent(){

        String eventname =  EventName.getText().toString().trim();
        String eventdescription = EventDescription.getText().toString().trim();
        String eventdate = txtDate.getText().toString().trim();
        String eventtime = txtTime.getText().toString().trim();

        if(!TextUtils.isEmpty(eventname)){
            String eventId = databaseEvents.push().getKey();
            EventModel eventModel = new EventModel(eventId,eventname,eventdescription,eventdate,eventtime);
            databaseEvents.child(eventId).setValue(eventModel);
            Toast.makeText(this,"Event added",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "You should enter the Event Name", Toast.LENGTH_LONG).show();
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