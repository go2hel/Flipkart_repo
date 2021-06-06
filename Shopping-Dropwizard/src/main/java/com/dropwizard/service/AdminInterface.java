package com.dropwizard.service;

import com.dropwizard.core.Customer;
import com.dropwizard.core.Item;
import com.dropwizard.resources.exceptions.*;

import java.util.List;

public interface AdminInterface {

    public List<Customer> viewPending();

    public void approveCustomer(String custID) throws CustomerNotFoundException;

    public void banCustomer(String custID) throws CustomerNotFoundException, CustomerNotDeletedException;

    public void addItem(Item item) throws ItemNotAddedException;

    public void removeItem(String name) throws ItemNotDeletedException;

    public List<Item> viewItems();

    public void addItemCount(String name, int count) throws ItemNotFoundException, ItemNotAddedException;

}