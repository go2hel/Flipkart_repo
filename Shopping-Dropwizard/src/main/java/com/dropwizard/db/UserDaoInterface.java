package com.dropwizard.db;

import com.dropwizard.core.Customer;
import com.dropwizard.resources.constants.Role;
import com.dropwizard.resources.exceptions.*;

public interface UserDaoInterface {

    public Role login(String ID, String password) throws PasswordMismatchException, CustomerNotApprovedException;

    public void signUp(Customer customer) throws CustomerNotAddedException, CustomerAlreadyExistsException;

    public boolean isApproved(String custID) throws CustomerNotFoundException;

}
