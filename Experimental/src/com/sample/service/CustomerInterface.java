package com.sample.service;

import com.sample.bean.Item;
import com.sample.constant.ModeOfPayment;
import com.sample.exceptions.ItemNotAddedException;
import com.sample.exceptions.ItemNotDeletedException;

import java.util.List;

public interface CustomerInterface {

    public List<Item> viewItems();

    public List<Item> viewCart(String custID);

    public void addItem(String custID, String item, int count) throws ItemNotAddedException;

    public void removeItem(String custID, String item) throws ItemNotDeletedException;

    public float viewBalance(String custID);

    public void addMoney(String custID, float amount, ModeOfPayment modeOfPayment);

}
