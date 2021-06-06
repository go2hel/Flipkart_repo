package com.sample.service;

import com.sample.constant.ModeOfPayment;
import com.sample.exceptions.PaymentNotDoneException;

public interface PaymentInterface {

    public void makePayment(String custID) throws PaymentNotDoneException;

}
