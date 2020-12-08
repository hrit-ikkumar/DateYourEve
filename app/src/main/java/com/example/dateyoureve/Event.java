package com.example.dateyoureve;

import android.provider.CalendarContract;

import java.lang.ref.Reference;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;

public class Event {

    public double EntryFee;
    public String EventDescription;
    public String EventName;
    public Timestamp EventStartTime;
    public String EntryType;
    public String EventLocation;
    public int EventId;
    public String CreatedByUserId;
    public Timestamp EventEndTime;
    public int EventMaxSeats;
    public Timestamp RegistrationStartTime;
    public Timestamp RegistrationEndTime;
    public String UserId;
    public String[] Attendees;
    public int AttendeesSize;

    public Event(){
    }

    public Event(String eventName, String eventDescription, double entryFee, Timestamp eventStartTime) {

        EventName = eventName;
        EventDescription = eventDescription;
        EntryFee = entryFee;
        EventStartTime = eventStartTime;

//        String entryType, String eventLocation,
//        int eventId, String createdByUserId, Timestamp evenEndTime, int eventmaxseats, Timestamp registrationStartTime,
//                Timestamp registrationEndTime, String userId, String[] attendees
//        EntryType=entryType;
//        EventLocation=eventLocation;
//        EventId=eventId;
//        CreatedByUserId=createdByUserId;
//        EventEndTime=evenEndTime;
//        EventMaxSeats=eventmaxseats;
//        RegistrationStartTime=registrationStartTime;
//        RegistrationEndTime=registrationEndTime;
//        UserId=userId;
//        AttendeesSize=attendees.length;
//        for(int i=0;i<AttendeesSize;i++)
//            Attendees[i]=attendees[i];
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public String getEventDescription() {
        return EventDescription;
    }

    public void setEventDescription(String eventDescription) {
        EventDescription = eventDescription;
    }

    public double getEntryFee() {
        return EntryFee;
    }

    public void setEntryFee(double entryFee) {
        EntryFee = entryFee;
    }

    public Timestamp getEventStartTime() {
        return EventStartTime;
    }

    public void setEventStartTime(Timestamp eventStartTime) {
        EventStartTime = eventStartTime;
    }

//    public String getEntryType() {
//        return EntryType;
//    }
//
//    public void setEntryType(String entryType) {
//        EntryType = entryType;
//    }
//
//    public String getEventLocation() {
//        return EventLocation;
//    }
//
//    public void setEventLocation(String eventLocation) {
//        EventLocation = eventLocation;
//    }
//
//    public int getEventId() {
//        return EventId;
//    }
//
//    public void setEventId(int eventId) {
//        EventId = eventId;
//    }
//
//    public String getCreatedByUserId() {
//        return CreatedByUserId;
//    }
//
//    public void setCreatedByUserId(String createdByUserId) {
//        CreatedByUserId = createdByUserId;
//    }
//
//    public Timestamp getEventEndTime() {
//        return EventEndTime;
//    }
//
//    public void setEventEndTime(Timestamp eventEndTime) {
//        EventEndTime = eventEndTime;
//    }
//
//    public int getEventMaxSeats() {
//        return EventMaxSeats;
//    }
//
//    public void setEventMaxSeats(int eventMaxSeats) {
//        EventMaxSeats = eventMaxSeats;
//    }
//
//    public Timestamp getRegistrationStartTime() {
//        return RegistrationStartTime;
//    }
//
//    public void setRegistrationStartTime(Timestamp registrationStartTime) {
//        RegistrationStartTime = registrationStartTime;
//    }
//
//    public Timestamp getRegistrationEndTime() {
//        return RegistrationEndTime;
//    }
//
//    public void setRegistrationEndTime(Timestamp registrationEndTime) {
//        RegistrationEndTime = registrationEndTime;
//    }
//
//    public String getUserId() {
//        return UserId;
//    }
//
//    public void setUserId(String userId) {
//        UserId = userId;
//    }
//
//    public String[] getAttendees() {
//        return Attendees;
//    }
//
//    public void setAttendees(String[] attendees) {
//        Attendees = attendees;
//    }
//
//    public int getAttendeesSize() {
//        return AttendeesSize;
//    }
//
//    public void setAttendeesSize(int attendeesSize) {
//        AttendeesSize = attendeesSize;
//    }

}
