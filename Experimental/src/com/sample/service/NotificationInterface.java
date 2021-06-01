package com.sample.service;

import com.sample.bean.Notification;
import com.sample.bean.Payment;

import java.util.List;

public interface NotificationInterface {

    public void paymentNotification(Payment payment);

    public List<Notification> viewNotifications(String custID);

    public void sendNotification(Notification notification);

}
