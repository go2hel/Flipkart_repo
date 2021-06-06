package com.sample.dao;

import com.sample.constant.ModeOfPayment;
import com.sample.exceptions.PaymentNotDoneException;

public interface PaymentDaoInterface {

    public void makePayment(String custID) throws PaymentNotDoneException;

}
