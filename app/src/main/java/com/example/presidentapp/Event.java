package com.example.presidentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Event extends AppCompatActivity{

    FloatingActionButton btnAddEvent;
    ListView listViewEvents;
    DatabaseReference databaseEvent;
    List<EventModel> eventModelList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        btnAddEvent  = findViewById(R.id.add_event_button);
        databaseEvent = FirebaseDatabase.getInstance().getReference("Events");

        listViewEvents = (ListView) findViewById(R.id.listviewevents);
        eventModelList = new ArrayList<>();

        listViewEvents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                EventModel eventModel = eventModelList.get(position);

                showupdatedialog(eventModel.getEventId(),eventModel.getEventName(),eventModel.getEventDescription(),eventModel.getEventDate(),eventModel.getEventTime());
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseEvent.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventModelList.clear();
                for(DataSnapshot eventSnapshot : dataSnapshot.getChildren()){
                    EventModel eventModel = eventSnapshot.getValue(EventModel.class);

                    eventModelList.add(eventModel);
                }
                EventList adapter = new EventList(Event.this, eventModelList);
                listViewEvents.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showupdatedialog(final String eventId, final String eventName, final String eventDescription, final String eventDate, final String eventTime) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_event_dialog, null);
        dialogBuilder.setView(dialogView);

        final TextInputEditText editTextEventname = dialogView.findViewById(R.id.update_event_name_txt_fld);
        final TextInputEditText editTextEventdescription = dialogView.findViewById(R.id.update_event_description_txt_fld);

        final Button buttonUpdate = dialogView.findViewById(R.id.update_event_btn);
        final Button buttonDelete = dialogView.findViewById(R.id.delete_event_btn);


        dialogBuilder.setTitle("Updating event: " + eventName);

        final Button btnEventShow;

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String eventName = editTextEventname.getText().toString().trim();
                String eventDescription = editTextEventdescription.getText().toString().trim();

                if (TextUtils.isEmpty(eventName)) {
                    editTextEventname.setError("Event name required");
                    return;
                }
                if (TextUtils.isEmpty(eventDescription)) {
                    editTextEventdescription.setError("Event description required");
                    return;
                }
                /*if(TextUtils.isEmpty(eventName)){
                    editTextEventname.setError("Event name required");
                    return;
                }
                if(TextUtils.isEmpty(eventName)){
                    editTextEventname.setError("Event name required");
                    return;
                }*/
                updateEvent(eventId, eventName, eventDescription, eventDate, eventTime);
                alertDialog.dismiss();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                deleteEvent(eventId);
                alertDialog.dismiss();
            }
        });
    }

    private void deleteEvent(String eventId) {
        DatabaseReference drRule = FirebaseDatabase.getInstance().getReference("Events").child(eventId);
        drRule.removeValue();

        Toast.makeText(this, "event is deleted", Toast.LENGTH_LONG).show();
    }

    private boolean updateEvent(String eventId, String eventName, String eventDescription, String eventDate, String eventTime){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Events").child(eventId);
        EventModel eventModel = new EventModel(eventId, eventName, eventDescription, eventDate, eventTime);
        databaseReference.setValue(eventModel);
        Toast.makeText(this, "Event Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    public void AddEvent(View view) {
        Intent intent = new Intent(getApplicationContext(), Add_event.class);
        startActivity(intent);
    }

}
