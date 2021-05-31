package com.sample.dao;

import com.sample.bean.Customer;
import com.sample.bean.Item;
import com.sample.constant.Gender;
import com.sample.constant.Role;
import com.sample.constant.SQLQueries;
import com.sample.exceptions.*;
import com.sample.utils.DBUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoOperartion implements AdminDaoInterface{

    private static Logger logger = Logger.getLogger(AdminDaoOperartion.class);
    private static volatile AdminDaoOperartion instance = null;

    private AdminDaoOperartion(){}

    public static AdminDaoOperartion getInstance(){
        if(instance==null){
            synchronized (AdminDaoOperartion.class){
                instance = new AdminDaoOperartion();
            }
        }
        return instance;
    }

    @Override
    public List<Customer> viewPending() {
        Connection connection = DBUtil.getConnection();
        List<Customer> customers = new ArrayList<Customer>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.VIEW_PENDING);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Gender gender = Gender.values()[resultSet.getInt("gender")];
                customers.add(new Customer(resultSet.getString("name"), resultSet.getString("id"),
                        resultSet.getString("password"),gender, Role.CUSTOMER));
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return customers;
    }

    @Override
    public void approveCustomer(String custID) throws CustomerNotFoundException {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.APPROVE_CUSTOMER);

            preparedStatement.setString(1,custID);
            int stats = preparedStatement.executeUpdate();

            logger.info(stats + " approval done");

            if(stats==0){
                throw new CustomerNotFoundException(custID);
            }

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

    @Override
    public void banCustomer(String custID) throws CustomerNotFoundException, CustomerNotDeletedException {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.DELETE_CUSTOMER);

            statement.setString(1,custID);
            int stats = statement.executeUpdate();

            logger.info(stats + " customer deleted.");

            if(stats==0){
                throw new CustomerNotDeletedException(custID);
            }

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

    @Override
    public void addItemCount(String name, int count) throws ItemNotFoundException, ItemNotAddedException{
        Connection connection = DBUtil.getConnection();
        try {
            int current = getItemCount(name);
            PreparedStatement statement = connection.prepareStatement(SQLQueries.ADD_ITEM_COUNT);
            statement.setString(2,name);
            statement.setInt(1,current+count);
            int stats = statement.executeUpdate();

            logger.info(stats + " item updated");

        }catch (Exception e){
            logger.error(e.getMessage());
            throw new ItemNotAddedException(name);
        }finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    private int getItemCount(String name) throws ItemNotFoundException {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.GET_ITEM_COUNT);
            statement.setString(1,name);

            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            return resultSet.getInt("count");

        }catch (Exception e){
            logger.error(e.getMessage());
            throw new ItemNotFoundException(name);
        }finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public void addItem(Item item) throws ItemNotAddedException {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.ADD_ITEM);

            statement.setString(1,item.getName());
            statement.setFloat(2,item.getPrice());
            statement.setInt(3,item.getCount());
            int stats = statement.executeUpdate();

            logger.info(stats + " Item added");

            if(stats==0){
                throw new ItemNotAddedException(item.getName());
            }

        }catch (Exception e){
            logger.error(e.getMessage());
            throw new ItemNotAddedException(item.getName());
        }finally {
            try {
                connection.close();
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public void removeItem(String name) throws ItemNotDeletedException{
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.DELETE_ITEM);
            statement.setString(1,name);
            int stats = statement.executeUpdate();

            logger.info(stats + " item removed.");

        }catch (Exception e){
            logger.error(e.getMessage());
            throw new ItemNotDeletedException(name);
        }finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public List<Item> viewItems() {
        Connection connection = DBUtil.getConnection();
        List<Item> items = new ArrayList<Item>();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.VIEW_ITEMS);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                items.add(new Item(resultSet.getString("name"), resultSet.getFloat("price"),
                        resultSet.getInt("count")));
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try {
                connection.close();
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }
        return items;
    }
}
