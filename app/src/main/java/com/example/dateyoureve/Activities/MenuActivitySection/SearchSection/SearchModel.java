package com.example.dateyoureve.Activities.MenuActivitySection.SearchSection;

public class SearchModel {

    int eventImage;
    String eventName,
            eventLocation,
            EntryFee,
            EntryType,
            EventMaxSeats,
            EventStartTime,
            EventEndTime,
            EventDescription,
            RegistrationStartTime,
            RegistrationEndTime;

    public SearchModel(int eventImage,
                     String eventName,
                     String eventLocation,
                     String entryFee,
                     String entryType,
                     String eventMaxSeats,
                     String eventStartTime,
                     String eventEndTime,
                     String eventDescription,
                     String registrationStartTime,
                     String registrationEndTime) {
        this.eventImage = eventImage;
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        EntryFee = entryFee;
        EntryType = entryType;
        EventMaxSeats = eventMaxSeats;
        EventStartTime = eventStartTime;
        EventEndTime = eventEndTime;
        EventDescription = eventDescription;
        RegistrationStartTime = registrationStartTime;
        RegistrationEndTime = registrationEndTime;
    }

    public SearchModel(int image, String eventName)
    {
        this.eventImage = image;
        this.eventName = eventName;
    }

    public int getEventImage() {
        return eventImage;
    }

    public void setEventImage(int eventImage) {
        this.eventImage = eventImage;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEntryFee() {
        return EntryFee;
    }

    public void setEntryFee(String entryFee) {
        EntryFee = entryFee;
    }

    public String getEntryType() {
        return EntryType;
    }

    public void setEntryType(String entryType) {
        EntryType = entryType;
    }

    public String getEventMaxSeats() {
        return EventMaxSeats;
    }

    public void setEventMaxSeats(String eventMaxSeats) {
        EventMaxSeats = eventMaxSeats;
    }

    public String getEventStartTime() {
        return EventStartTime;
    }

    public void setEventStartTime(String eventStartTime) {
        EventStartTime = eventStartTime;
    }

    public String getEventEndTime() {
        return EventEndTime;
    }

    public void setEventEndTime(String eventEndTime) {
        EventEndTime = eventEndTime;
    }

    public String getEventDescription() {
        return EventDescription;
    }

    public void setEventDescription(String eventDescription) {
        EventDescription = eventDescription;
    }

    public String getRegistrationStartTime() {
        return RegistrationStartTime;
    }

    public void setRegistrationStartTime(String registrationStartTime) {
        RegistrationStartTime = registrationStartTime;
    }

    public String getRegistrationEndTime() {
        return RegistrationEndTime;
    }

    public void setRegistrationEndTime(String registrationEndTime) {
        RegistrationEndTime = registrationEndTime;
    }
}