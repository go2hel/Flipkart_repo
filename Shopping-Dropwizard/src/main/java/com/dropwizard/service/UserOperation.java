package com.dropwizard.service;

import com.dropwizard.core.Customer;
import com.dropwizard.db.UserDaoOperation;
import com.dropwizard.resources.constants.Role;
import com.dropwizard.resources.exceptions.*;

public class UserOperation implements UserInterface{

    private static volatile UserOperation instance = null;

    private UserOperation(){}

    public static UserOperation getInstance(){
        if(instance==null){
            synchronized (UserOperation.class){
                instance = new UserOperation();
            }
        }
        return instance;
    }

    @Override
    public Role login(String ID, String password) throws CustomerNotFoundException, PasswordMismatchException, CustomerNotApprovedException {
        try {
            return UserDaoOperation.getInstance().login(ID,password);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void signUp(Customer customer) throws CustomerNotAddedException, CustomerAlreadyExistsException {
        try {
            UserDaoOperation.getInstance().signUp(customer);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public boolean isApproved(String custID) throws CustomerNotFoundException {
        try {
            return UserDaoOperation.getInstance().isApproved(custID);
        }catch (Exception e){
            throw e;
        }
    }
}
