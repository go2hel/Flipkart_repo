package com.dropwizard.resources.exceptions;

public class CustomerAlreadyExistsException extends Exception {
    private String custID;

    public CustomerAlreadyExistsException(String custID){
        this.custID = custID;
    }

    @Override
    public String getMessage(){
        return "Customer with ID " + custID + " already exists.";
    }
}