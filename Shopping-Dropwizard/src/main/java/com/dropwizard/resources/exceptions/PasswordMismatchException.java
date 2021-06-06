package com.dropwizard.resources.exceptions;

public class PasswordMismatchException extends Exception{
    public String getMessage(){
        return "Invalid Credentials";
    }
}
