package com.practice.service;

import com.practice.bean.Customer;

public interface CustomerInterface {

    public void createCustomer(int custID, String custName, String custAddress);

    public boolean updateCustomer(int custID, String custName, String custAddress);

    public boolean deleteCustomer(int custID);

    public void listCustomer();

}
