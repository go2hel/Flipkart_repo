package com.sample.service;

import com.sample.bean.Customer;
import com.sample.bean.Item;
import com.sample.dao.AdminDaoOperartion;
import com.sample.exceptions.*;

import java.util.List;

public class AdminOperations implements AdminInterface {

    private static volatile AdminOperations instance = null;

    private AdminOperations(){}

    public static AdminOperations getInstance(){
        if (instance==null){
            synchronized (AdminOperations.class){
                instance = new AdminOperations();
            }
        }
        return instance;
    }

    @Override
    public List<Customer> viewPending() {
        return AdminDaoOperartion.getInstance().viewPending();
    }

    @Override
    public void approveCustomer(String custID) throws CustomerNotFoundException {
        AdminDaoOperartion.getInstance().approveCustomer(custID);
    }

    @Override
    public void banCustomer(String custID) throws CustomerNotFoundException, CustomerNotDeletedException {
        AdminDaoOperartion.getInstance().banCustomer(custID);
    }

    @Override
    public void addItem(Item item) throws ItemNotAddedException {
        AdminDaoOperartion.getInstance().addItem(item);
    }

    @Override
    public void removeItem(String name) throws ItemNotDeletedException {
        AdminDaoOperartion.getInstance().removeItem(name);
    }

    @Override
    public List<Item> viewItems() {
        return AdminDaoOperartion.getInstance().viewItems();
    }

    @Override
    public void addItemCount(String name, int count) throws ItemNotFoundException, ItemNotAddedException {
        AdminDaoOperartion.getInstance().addItemCount(name,count);
    }
}
