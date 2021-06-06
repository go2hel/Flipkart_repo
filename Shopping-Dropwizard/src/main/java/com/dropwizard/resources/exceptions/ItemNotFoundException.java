package com.dropwizard.resources.exceptions;

public class ItemNotFoundException extends Exception {
    private String name;

    public ItemNotFoundException(String name){
        this.name = name;
    }

    public String getMessage(){
        return "Item with name " + name + " not found.";
    }
}
