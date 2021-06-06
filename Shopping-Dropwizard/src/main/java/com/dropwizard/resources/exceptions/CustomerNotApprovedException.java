package com.dropwizard.resources.exceptions;

public class CustomerNotApprovedException extends Exception{
    public String getMessage(){
        return "Waiting for approval";
    }
}
