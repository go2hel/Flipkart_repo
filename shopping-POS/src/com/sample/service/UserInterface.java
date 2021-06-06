package com.sample.service;

import com.sample.bean.Customer;
import com.sample.constant.Role;
import com.sample.exceptions.CustomerAlreadyExistsException;
import com.sample.exceptions.CustomerNotAddedException;
import com.sample.exceptions.CustomerNotFoundException;
import com.sample.exceptions.PasswordMismatchException;

public interface UserInterface {

    public Role login(String ID, String password) throws CustomerNotFoundException, PasswordMismatchException;

    public void signUp(Customer customer) throws CustomerNotAddedException, CustomerAlreadyExistsException;

    public boolean isApproved(String custID) throws CustomerNotFoundException;

}
