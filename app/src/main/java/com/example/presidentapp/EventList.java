package com.example.presidentapp;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.presidentapp.Model.EventModel;

import java.util.List;


public class EventList extends ArrayAdapter<EventModel> {
    private static final String TAG = "ProfileActivity11";
    private Activity context;
    private List<EventModel> eventModelList;

    public EventList(Activity context, List<EventModel> eventModelList) {
        super(context, R.layout.event_adapter, eventModelList);
        this.context = context;
        this.eventModelList = eventModelList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.event_adapter, null, true);

        TextView textVieweventname = listViewItem.findViewById(R.id.eventnameshow_txt_view);
        TextView textVieweventdescription = listViewItem.findViewById(R.id.eventdescriptionshow_txt_view);
        TextView textVieweventdate = listViewItem.findViewById(R.id.eventdateshow_txt_view);
        TextView textVieweventtime = listViewItem.findViewById(R.id.eventtimeshow_txt_view);

        EventModel eventModel = eventModelList.get(position);

        textVieweventname.setText(eventModel.getEventName());
        textVieweventdescription.setText(eventModel.getEventDescription());
        textVieweventdate.setText(eventModel.getEventDate());
        textVieweventtime.setText(eventModel.getEventTime());

        Log.d(TAG, "getView: " + eventModel.getEventName());
        Log.d(TAG, "getViewd : " + eventModel.getEventDescription());
        Log.d(TAG, "getViewDate: " + eventModel.getEventDate());

        return listViewItem;
    }

}
