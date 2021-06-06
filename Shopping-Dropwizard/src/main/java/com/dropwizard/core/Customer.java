package com.dropwizard.core;

import com.dropwizard.resources.constants.Gender;
import com.dropwizard.resources.constants.Role;

public class Customer {
    private String name;
    private String ID;
    private String password;
    private Gender gender;
    private float balance;
    private Role role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Customer(){}

    public Customer(String name, String ID, String password, Gender gender, Role role){
        this.name=name;this.ID=ID;this.password=password;this.gender=gender;
        this.role=role;
        this.balance=0;
    }
}
