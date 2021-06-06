package com.dropwizard.db;

import com.dropwizard.resources.exceptions.PaymentNotDoneException;

public interface PaymentDaoInterface {

    public void makePayment(String custID) throws PaymentNotDoneException;

}
