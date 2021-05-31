package com.sample.client;


import com.sample.bean.Customer;
import com.sample.bean.Item;
import com.sample.dao.AdminDaoInterface;
import com.sample.dao.AdminDaoOperartion;
import com.sample.dao.CustomerDaoOperation;
import com.sample.dao.UserDaoOperation;
import com.sample.exceptions.ItemNotAddedException;

import java.util.List;

public class ClientApplication {
    public static void main(String[] args) {
        try {
            CustomerDaoOperation inst =  CustomerDaoOperation.getInstance();

            //inst.addItem("cust2","Parle G Biscuit",21);
            //inst.addItem("cust2","Parle",1);
            inst.addItem("cust2","Parle G Biscuit",20);

            List<Item> cart = inst.viewCart("cust2");
            cart.forEach(item -> System.out.println(item.getName() + " " + item.getPrice() + " " + item.getCount()));

            inst.removeItem("cust2","mankind");
            cart = inst.viewCart("cust2");
            cart.forEach(item -> System.out.println(item.getName() + " " + item.getPrice() + " " + item.getCount()));

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
