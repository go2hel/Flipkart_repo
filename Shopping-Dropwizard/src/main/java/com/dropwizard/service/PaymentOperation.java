package com.dropwizard.service;

import com.dropwizard.db.PaymentDaoOperation;
import com.dropwizard.resources.exceptions.PaymentNotDoneException;

public class PaymentOperation implements PaymentInterface{

    private static volatile PaymentOperation instance = null;

    private PaymentOperation(){}

    public static PaymentOperation getInstance(){
        if (instance==null){
            synchronized (PaymentOperation.class){
                instance = new PaymentOperation();
            }
        }
        return instance;
    }

    @Override
    public void makePayment(String custID) throws PaymentNotDoneException {
        PaymentDaoOperation.getInstance().makePayment(custID);
    }
}
