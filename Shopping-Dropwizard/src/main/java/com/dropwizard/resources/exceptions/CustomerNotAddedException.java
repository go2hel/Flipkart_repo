package com.dropwizard.resources.exceptions;

public class CustomerNotAddedException extends Exception {
    private String custID;

    public CustomerNotAddedException(String custID){
        this.custID = custID;
    }

    @Override
    public String getMessage(){
        return "Customer with ID " + custID + " is not added.";
    }
}