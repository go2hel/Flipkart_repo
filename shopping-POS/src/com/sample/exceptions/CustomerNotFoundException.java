package com.sample.exceptions;

public class CustomerNotFoundException extends Exception {
    private String custID;

    public CustomerNotFoundException(String custID){
        this.custID=custID;
    }

    public String getMessage(){
        return "User with custID " + custID + " not found.";
    }
}
