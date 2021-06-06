package com.dropwizard.core;

import java.time.LocalDate;
import java.time.LocalTime;

public class Payment {
    private String paymentID;
    private String custID;
    private float amount;

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Payment(){}

    public Payment(String custID, float amount){
        this.amount=amount;
        this.custID = custID;
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        this.paymentID = Integer.toString(localDate.getYear()) + Integer.toString(localDate.getMonthValue())
                + Integer.toString(localDate.getDayOfMonth()) + this.custID +
                localTime.getHour() + "#" + localTime.getMinute() + "#" + localTime.getSecond();
    }

    public String getPaymentID() {
        return paymentID;
    }
}
