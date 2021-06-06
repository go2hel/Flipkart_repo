package com.dropwizard.service;

import com.dropwizard.core.Customer;
import com.dropwizard.resources.constants.Role;
import com.dropwizard.resources.exceptions.*;

public interface UserInterface {

    public Role login(String ID, String password) throws CustomerNotFoundException, PasswordMismatchException, CustomerNotApprovedException;

    public void signUp(Customer customer) throws CustomerNotAddedException, CustomerAlreadyExistsException;

    public boolean isApproved(String custID) throws CustomerNotFoundException;

}
