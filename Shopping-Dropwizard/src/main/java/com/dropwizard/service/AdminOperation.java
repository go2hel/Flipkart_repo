package com.dropwizard.service;

import com.dropwizard.core.Customer;
import com.dropwizard.core.Item;
import com.dropwizard.db.AdminDaoOperation;
import com.dropwizard.resources.exceptions.*;

import java.util.List;

public class AdminOperation implements AdminInterface {

    private static volatile AdminOperation instance = null;

    private AdminOperation(){}

    public static AdminOperation getInstance(){
        if (instance==null){
            synchronized (AdminOperation.class){
                instance = new AdminOperation();
            }
        }
        return instance;
    }

    @Override
    public List<Customer> viewPending() {
        return AdminDaoOperation.getInstance().viewPending();
    }

    @Override
    public void approveCustomer(String custID) throws CustomerNotFoundException {
        AdminDaoOperation.getInstance().approveCustomer(custID);
    }

    @Override
    public void banCustomer(String custID) throws CustomerNotFoundException, CustomerNotDeletedException {
        AdminDaoOperation.getInstance().banCustomer(custID);
    }

    @Override
    public void addItem(Item item) throws ItemNotAddedException {
        AdminDaoOperation.getInstance().addItem(item);
    }

    @Override
    public void removeItem(String name) throws ItemNotDeletedException {
        AdminDaoOperation.getInstance().removeItem(name);
    }

    @Override
    public List<Item> viewItems() {
        return AdminDaoOperation.getInstance().viewItems();
    }

    @Override
    public void addItemCount(String name, int count) throws ItemNotFoundException, ItemNotAddedException {
        AdminDaoOperation.getInstance().addItemCount(name,count);
    }
}
