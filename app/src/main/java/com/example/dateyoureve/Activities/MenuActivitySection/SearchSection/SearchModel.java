package com.example.dateyoureve.Activities.MenuActivitySection.SearchSection;

public class SearchModel {

    int entryFee;
    String eventName;

    public SearchModel(int image, String eventName)
    {
        this.entryFee = image;
        this.eventName = eventName;
    }

    public int getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(int entryFee) {
        this.entryFee = entryFee;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

}