package com.dropwizard.service;

import com.dropwizard.core.Notification;
import com.dropwizard.core.Payment;
import com.dropwizard.db.NotificationDaoOperation;

import java.util.List;

public class NotificationOperation implements NotificationInterface{

    private static volatile NotificationOperation instance = null;

    private NotificationOperation(){}

    public static NotificationOperation getInstance() {
        if(instance==null){
            synchronized (NotificationOperation.class){
                instance = new NotificationOperation();
            }
        }
        return instance;
    }

    @Override
    public void paymentNotification(Payment payment) {
        NotificationDaoOperation.getInstance().paymentNotification(payment);
    }

    @Override
    public List<Notification> viewNotifications(String custID) {
        return NotificationDaoOperation.getInstance().viewNotifications(custID);
    }

    @Override
    public void sendNotification(Notification notification) {
        NotificationDaoOperation.getInstance().sendNotification(notification);
    }
}
