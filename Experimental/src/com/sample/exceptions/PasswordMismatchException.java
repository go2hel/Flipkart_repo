package com.sample.exceptions;

public class PasswordMismatchException extends Exception{
    public String getMessage(){
        return "Invalid Credentials";
    }
}
