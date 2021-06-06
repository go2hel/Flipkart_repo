package com.sample.constant;

public enum ModeOfPayment {
    DEBIT_CARD(1),CREDIT_CARD(2),UPI(3);
    private final int mop;

    ModeOfPayment(int i){
        this.mop = i;
    }

    public int getMop(){
        return this.mop;
    }
}
