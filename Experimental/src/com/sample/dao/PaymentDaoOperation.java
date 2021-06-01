package com.sample.dao;

import com.sample.bean.Item;
import com.sample.bean.Payment;
import com.sample.constant.ModeOfPayment;
import com.sample.constant.SQLQueries;
import com.sample.exceptions.PaymentNotDoneException;
import com.sample.utils.DBUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PaymentDaoOperation implements PaymentDaoInterface{

    private static Logger logger = Logger.getLogger(PaymentDaoOperation.class);

    private static volatile PaymentDaoOperation instance = null;

    private PaymentDaoOperation(){}

    public static PaymentDaoOperation getInstance(){
        if(instance==null){
            synchronized (PaymentDaoOperation.class){
                instance = new PaymentDaoOperation();
            }
        }
        return instance;
    }

    @Override
    public void makePayment(String custID) throws PaymentNotDoneException {
        List<Item> items = CustomerDaoOperation.getInstance().viewCart(custID);
        float payment = calculate(custID,items);
        if(!isPossible(payment,custID)){
            throw new PaymentNotDoneException(custID + " due to insufficient balance.");
        }
        Connection connection = DBUtil.getConnection();
        try {

            this.emptyCart(custID,items);

            PreparedStatement statement = connection.prepareStatement(SQLQueries.MAKE_PAYMENT);

            statement.setFloat(1,payment);
            statement.setString(2,custID);

            int stats = statement.executeUpdate();

            logger.info(stats==1?"payment done":"payment not done");

            Payment payment1 = new Payment(custID,payment);

            NotificationDaoOperation.getInstance().paymentNotification(payment1);

        }catch (Exception e){
            logger.error(e.getMessage());
            throw new PaymentNotDoneException(custID);
        }finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    private void emptyCart(String custID, List<Item> items) throws PaymentNotDoneException{
        Connection connection = DBUtil.getConnection();
        try {

            for (Item item:items){
                int cur = AdminDaoOperartion.getInstance().getItemCount(item.getName());
                if(item.getCount()>cur){
                    throw new PaymentNotDoneException(custID);
                }
                AdminDaoOperartion.getInstance().addItemCount(item.getName(),-item.getCount());
            }

            PreparedStatement statement = connection.prepareStatement(SQLQueries.EMPTY_CART);

            statement.setString(1,custID);

            statement.executeUpdate();

        }catch (Exception e){
            logger.error(e.getMessage());
            throw new PaymentNotDoneException(custID);
        }finally {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    private float calculate(String custID, List<Item> items){
        float total = 0.0f;

        for(Item item : items){
            total += item.getPrice()*item.getCount();
        }

        return total;
    }

    private boolean isPossible(float amount, String custID){
        float current = CustomerDaoOperation.getInstance().viewBalance(custID);
        return current>=amount;
    }
}
