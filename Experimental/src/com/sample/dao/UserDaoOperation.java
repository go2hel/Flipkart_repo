package com.sample.dao;

import com.sample.bean.Customer;
import com.sample.constant.Role;
import com.sample.constant.SQLQueries;
import com.sample.exceptions.CustomerAlreadyExistsException;
import com.sample.exceptions.CustomerNotAddedException;
import com.sample.exceptions.CustomerNotFoundException;
import com.sample.exceptions.PasswordMismatchException;
import com.sample.utils.DBUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoOperation implements UserDaoInterface{

    private static Logger logger = Logger.getLogger(UserDaoOperation.class);

    private static volatile UserDaoOperation instance = null;

    private UserDaoOperation(){}

    public static UserDaoOperation getInstance(){
        if(instance==null){
            synchronized (UserDaoOperation.class){
                instance = new UserDaoOperation();
            }
        }
        return instance;
    }

    @Override
    public Role login(String ID, String password) throws CustomerNotFoundException, PasswordMismatchException {
        Connection connection = DBUtil.getConnection();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.LOGIN);

            preparedStatement.setString(1,ID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()){
                throw new CustomerNotFoundException(ID);
            }

            String gotPassword = resultSet.getString("password");
            int roleInt = resultSet.getInt("role");

            if(!gotPassword.equals(password)){
                throw new PasswordMismatchException();
            }
            return Role.values()[roleInt-1];

        }catch (Exception e){
            logger.error(e.getMessage());
            throw new CustomerNotFoundException(ID);
        }finally {
            try {
                connection.close();
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public void signUp(Customer customer) throws CustomerNotAddedException, CustomerAlreadyExistsException {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.ADD_USER);

            preparedStatement.setString(1,customer.getID());
            preparedStatement.setString(2,customer.getName());
            preparedStatement.setString(3,customer.getPassword());
            preparedStatement.setInt(4,customer.getGender().getGender());
            preparedStatement.setInt(5,customer.getRole().getRole());
            preparedStatement.setFloat(6,customer.getBalance());

            int k = preparedStatement.executeUpdate();

            if(k==0){
                logger.info("Customer with custID " + customer.getID() + " not added");
                throw new CustomerAlreadyExistsException(customer.getID());
            }

            logger.info("Customer with custID " + customer.getID() + " added.");

        }catch (Exception e){
            logger.error(e.getMessage());
            throw new CustomerNotAddedException(customer.getID());
        }finally {
            try {
                connection.close();
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public boolean isApproved(String custID) throws CustomerNotFoundException {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.IS_APPROVED);

            preparedStatement.setString(1,custID);
            ResultSet resultSet  = preparedStatement.executeQuery();

            resultSet.next();

            return resultSet.getBoolean("isApproved");

        }catch (Exception e){
            logger.error(e.getMessage());
            throw new CustomerNotFoundException(custID);
        }finally {
            try {
                connection.close();
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }
    }
}
