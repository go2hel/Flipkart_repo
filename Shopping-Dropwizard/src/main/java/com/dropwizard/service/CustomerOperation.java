package com.dropwizard.service;

import com.dropwizard.core.Item;
import com.dropwizard.db.CustomerDaoOperation;
import com.dropwizard.resources.constants.ModeOfPayment;
import com.dropwizard.resources.exceptions.ItemNotAddedException;
import com.dropwizard.resources.exceptions.ItemNotDeletedException;

import java.util.List;

public class CustomerOperation implements CustomerInterface{

    private static volatile CustomerOperation instance = null;

    private CustomerOperation(){}

    public static CustomerOperation getInstance(){
        if(instance==null){
            synchronized (CustomerOperation.class){
                instance = new CustomerOperation();
            }
        }
        return instance;
    }

    @Override
    public List<Item> viewItems() {
        return CustomerDaoOperation.getInstance().viewItems();
    }

    @Override
    public List<Item> viewCart(String custID) {
        return CustomerDaoOperation.getInstance().viewCart(custID);
    }

    @Override
    public void addItem(String custID, String item, int count) throws ItemNotAddedException {
        CustomerDaoOperation.getInstance().addItem(custID,item,count);
    }

    @Override
    public void removeItem(String custID, String item) throws ItemNotDeletedException {
        CustomerDaoOperation.getInstance().removeItem(custID,item);
    }

    @Override
    public float viewBalance(String custID) {
        return CustomerDaoOperation.getInstance().viewBalance(custID);
    }

    @Override
    public void addMoney(String custID, float amount, ModeOfPayment modeOfPayment) {
        CustomerDaoOperation.getInstance().addMoney(custID,amount,modeOfPayment);
    }
}
