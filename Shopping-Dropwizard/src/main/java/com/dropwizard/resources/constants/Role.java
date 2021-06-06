package com.dropwizard.resources.constants;

public enum Role {
    ADMIN(1),CUSTOMER(2);
    private final int role;

    Role(int i){
        this.role=i;
    }

    public int getRole(){
        return this.role;
    }
}