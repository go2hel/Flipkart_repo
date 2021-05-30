package com.sample.bean;

import com.sample.constant.ModeOfPayment;

import java.time.LocalDate;

public class Payment {
    private String paymentID;
    private String custID;
    private ModeOfPayment modeOfPayment;
    private float amount;

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public ModeOfPayment getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(ModeOfPayment modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Payment(String custID, ModeOfPayment modeOfPayment, float amount){
        this.amount=amount;
        this.modeOfPayment = modeOfPayment;
        this.custID = custID;
        LocalDate localDate = LocalDate.now();
        this.paymentID = Integer.toString(localDate.getYear()) + Integer.toString(localDate.getMonthValue())
                + Integer.toString(localDate.getDayOfMonth()) + this.custID;
    }
}
