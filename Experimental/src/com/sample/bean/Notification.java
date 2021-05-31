package com.sample.bean;

import java.time.LocalDate;

public class Notification {
    private String message;
    private String custID;
    private String notificationID;

    public Notification(String custID, String message){
        this.custID = custID;
        this.message = message;
        LocalDate localDate = LocalDate.now();
        this.notificationID = Integer.toString(localDate.getYear()) + Integer.toString(localDate.getDayOfYear())+this.custID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }
}