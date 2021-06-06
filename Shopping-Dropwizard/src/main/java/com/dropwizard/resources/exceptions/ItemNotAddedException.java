package com.dropwizard.resources.exceptions;

public class ItemNotAddedException extends Exception {
    private String name;

    public ItemNotAddedException(String name){
        this.name = name;
    }

    @Override
    public String getMessage(){
        return "Item with name " + name + " is not added.";
    }
}
