package com.dropwizard.resources.exceptions;

public class PaymentNotDoneException extends Exception{
    private String custID;

    public PaymentNotDoneException(String custID){this.custID=custID;}

    public String getMessage(){
        return "Payment not done for customer with ID " + custID;
    }
}
