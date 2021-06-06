package com.dropwizard.service;

import com.dropwizard.core.Notification;
import com.dropwizard.core.Payment;

import java.util.List;

public interface NotificationInterface {

    public void paymentNotification(Payment payment);

    public List<Notification> viewNotifications(String custID);

    public void sendNotification(Notification notification);

}
