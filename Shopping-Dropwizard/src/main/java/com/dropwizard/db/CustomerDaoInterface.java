package com.dropwizard.db;

import com.dropwizard.core.Item;
import com.dropwizard.resources.constants.ModeOfPayment;
import com.dropwizard.resources.exceptions.ItemNotAddedException;
import com.dropwizard.resources.exceptions.ItemNotDeletedException;

import java.util.List;

public interface CustomerDaoInterface {

    public List<Item> viewItems();

    public List<Item> viewCart(String custID);

    public void addItem(String custID, String item, int count) throws ItemNotAddedException;

    public void removeItem(String custID, String item) throws ItemNotDeletedException;

    public float viewBalance(String custID);

    public void addMoney(String custID, float amount, ModeOfPayment modeOfPayment);

}
