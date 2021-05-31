package com.sample.exceptions;

public class ItemNotDeletedException extends Exception {
    private String name;

    public ItemNotDeletedException(String name){
        this.name = name;
    }

    @Override
    public String getMessage(){
        return "Item with name " + name + " not removed.";
    }
}
