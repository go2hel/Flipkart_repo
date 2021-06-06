package com.dropwizard.resources.exceptions;

public class CustomerNotDeletedException extends Exception {
    private String custID;

    public CustomerNotDeletedException(String custID){
        this.custID = custID;
    }

    @Override
    public String getMessage(){
        return "Customer with ID " + custID + " not banned.";
    }
}