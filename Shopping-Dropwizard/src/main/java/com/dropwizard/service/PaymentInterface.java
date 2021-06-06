package com.dropwizard.service;

import com.dropwizard.resources.exceptions.PaymentNotDoneException;

public interface PaymentInterface {

    public void makePayment(String custID) throws PaymentNotDoneException;

}
