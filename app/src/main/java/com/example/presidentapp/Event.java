package com.example.presidentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Event extends AppCompatActivity{

    FloatingActionButton btnAddEvent;
    ListView listViewEvents;
    DatabaseReference databaseEvent;;
    List<EventModel> eventModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        btnAddEvent  = findViewById(R.id.add_event_button);
        databaseEvent = FirebaseDatabase.getInstance().getReference("Events");

        listViewEvents = (ListView) findViewById(R.id.listviewevents);
        eventModelList = new ArrayList<>();
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

    public void AddEvent(View view) {
        Intent intent = new Intent(getApplicationContext(), Add_event.class);
        startActivity(intent);
    }
}
