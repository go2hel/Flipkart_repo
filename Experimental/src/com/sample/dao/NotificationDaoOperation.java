package com.sample.dao;

import com.sample.bean.Notification;
import com.sample.bean.Payment;
import com.sample.constant.SQLQueries;
import com.sample.utils.DBUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NotificationDaoOperation implements NotificationDaoInterface {

    private static Logger logger = Logger.getLogger(NotificationDaoOperation.class);

    private static volatile NotificationDaoOperation instance = null;

    private NotificationDaoOperation(){}

    public static NotificationDaoOperation getInstance(){
        if(instance==null){
            synchronized (NotificationDaoOperation.class){
                instance = new NotificationDaoOperation();
            }
        }
        return instance;
    }

    @Override
    public void paymentNotification(Payment payment) {
        String message = "Payment done with reference ID "+ payment.getPaymentID() + " of amount "
                + payment.getAmount();

        Notification notification = new Notification(payment.getCustID(),message);
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.PAYMENT_NOTIFICATION);

            statement.setString(1,notification.getNotificationID());
            statement.setString(2,notification.getCustID());
            statement.setString(3,notification.getMessage());

            int stats = statement.executeUpdate();

            logger.info(stats+" notification sent");

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

    @Override
    public List<Notification> viewNotifications(String custID) {
        List<Notification> notifications = new ArrayList<Notification>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.VIEW_NOTIFICATIONS);
            statement.setString(1,custID);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Notification n = new Notification(custID,resultSet.getString("message"));
                n.setNotificationID(resultSet.getString("notfID"));
                notifications.add(n);
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
        return notifications;
    }
}
