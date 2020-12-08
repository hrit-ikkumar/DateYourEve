package com.example.dateyoureve;

import java.lang.reflect.Array;

public class Notification {

    public String NotificationQueue[];

    public Notification(){}

    public Notification(String[] notificationQueue) {
        NotificationQueue = notificationQueue;
    }

    public String[] getNotificationQueue() {
        return NotificationQueue;
    }

    public void setNotificationQueue(String[] notificationQueue) {
        NotificationQueue = notificationQueue;
    }
}
