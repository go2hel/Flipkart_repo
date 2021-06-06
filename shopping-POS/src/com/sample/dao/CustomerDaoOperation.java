package com.sample.dao;

import com.sample.bean.Item;
import com.sample.bean.Notification;
import com.sample.constant.ModeOfPayment;
import com.sample.constant.SQLQueries;
import com.sample.exceptions.ItemNotAddedException;
import com.sample.exceptions.ItemNotDeletedException;
import com.sample.exceptions.ItemNotFoundException;
import com.sample.utils.DBUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoOperation implements CustomerDaoInterface{
    private static Logger logger = Logger.getLogger(CustomerDaoOperation.class);

    private static volatile CustomerDaoOperation instance = null;

    private CustomerDaoOperation(){}

    public static CustomerDaoOperation getInstance(){
        if(instance==null){
            synchronized (CustomerDaoOperation.class){
                instance = new CustomerDaoOperation();
            }
        }
        return instance;
    }

    @Override
    public List<Item> viewItems() {
        return AdminDaoOperation.getInstance().viewItems();
    }

    @Override
    public List<Item> viewCart(String custID) {
        Connection connection = DBUtil.getConnection();
        List<Item> items = new ArrayList<Item>();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.VIEW_CART);

            statement.setString(1,custID);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                items.add(new Item(resultSet.getString("name"),resultSet.getFloat("price"),
                        resultSet.getInt("count")));
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return items;
    }

    @Override
    public void addItem(String custID,String item, int count) throws ItemNotAddedException {
        Connection connection = DBUtil.getConnection();
        try {

            Item item1 = getItem(item,count);

            PreparedStatement statement = connection.prepareStatement(SQLQueries.ADD_ITEM_CART);

            statement.setString(1,item);
            statement.setString(2,custID);
            statement.setFloat(3,item1.getPrice());
            statement.setInt(4,count);

            int stats = statement.executeUpdate();

            logger.info(stats + " Items added.");

        }catch (Exception e){
            logger.error(e.getMessage());
            throw new ItemNotAddedException(item);
        }finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public void removeItem(String custID, String item) throws ItemNotDeletedException {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.REMOVE_ITEM_CART);

            statement.setString(1,custID);
            statement.setString(2,item);

            int stats = statement.executeUpdate();

            logger.info(stats + " Item removed");

        }catch (Exception e){
            logger.error(e.getMessage());
            throw new ItemNotDeletedException(item);
        }
    }

    @Override
    public float viewBalance(String custID) {
        float balance = 0.0f;
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.VIEW_BALANCE);
            statement.setString(1,custID);

            ResultSet set = statement.executeQuery();
            set.next();

            balance = set.getFloat("balance");

        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return balance;
    }

    @Override
    public void addMoney(String custID, float amount, ModeOfPayment modeOfPayment) {
        Connection connection = DBUtil.getConnection();
        try {
            float current = this.viewBalance(custID);

            PreparedStatement statement = connection.prepareStatement(SQLQueries.ADD_MONEY);
            statement.setFloat(1,current+amount);
            statement.setString(2,custID);

            int stats = statement.executeUpdate();

            logger.info(stats==1?"balance updated to " + (current+amount):"Balance not updated");

            String message = "";

            switch (modeOfPayment){
                case UPI:
                    message = "Added Rs." + amount + " via UPI";
                    break;

                case DEBIT_CARD:
                    message = "Added Rs." + amount + " via debit card";
                    break;

                case CREDIT_CARD:
                    message = "Added Rs." + amount + " via credit card";
                    break;
            }

            Notification notification = new Notification(custID,message);
            NotificationDaoOperation.getInstance().sendNotification(notification);

        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    private Item getItem(String name, int count) throws ItemNotFoundException {
        Item item = null;
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.GET_ITEM);

            statement.setString(1,name);
            statement.setInt(2,count);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                item = new Item(resultSet.getString("name"),resultSet.getFloat("price"),resultSet.getInt("count"));
            }

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
        return item;
    }
}
