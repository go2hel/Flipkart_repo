package com.sample.client;


import com.sample.bean.Customer;
import com.sample.bean.Item;
import com.sample.bean.Notification;
import com.sample.constant.ModeOfPayment;
import com.sample.dao.*;
import com.sample.exceptions.ItemNotAddedException;

import java.util.List;

public class ClientApplication {
    public static void main(String[] args) {
        try {

            List<Notification> notifications = NotificationDaoOperation.getInstance().viewNotifications("cust2");

            notifications.forEach(notification -> System.out.println(notification.getNotificationID() + " " +
                    notification.getMessage()));

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
