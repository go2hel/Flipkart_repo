package com.sample.exceptions;

public class ItemAlreadyExistsException extends Exception {
    private String name;

    public ItemAlreadyExistsException(String name){
        this.name = name;
    }

    @Override
    public String getMessage(){
        return "Item with Name " + name + " already exists.";
    }
}
