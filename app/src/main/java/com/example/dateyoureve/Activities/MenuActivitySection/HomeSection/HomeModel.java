package com.example.dateyoureve.Activities.MenuActivitySection.HomeSection;

public class HomeModel {
    int entryFee;
    String eventName;

    public HomeModel(int image, String eventName)
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
