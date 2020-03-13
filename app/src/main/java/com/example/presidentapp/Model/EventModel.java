package com.example.presidentapp.Model;

public class EventModel{

    String eventId;
    String eventName;
    String eventDescription;
    String eventDate;
    String eventTime;

    public EventModel(){}

    public EventModel(String eventId, String eventName, String eventDescription, String eventDate, String eventTime) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }
}
