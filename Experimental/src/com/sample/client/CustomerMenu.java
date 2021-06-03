package com.sample.client;

import com.sample.bean.Item;
import com.sample.bean.Notification;
import com.sample.constant.ModeOfPayment;
import com.sample.service.CustomerOperations;
import com.sample.service.NotificationOperation;
import com.sample.service.PaymentOperation;
import com.sample.service.UserOperations;

import java.util.List;
import java.util.Scanner;

public class CustomerMenu {

    private final String custID;

    public CustomerMenu(String custID){
        this.custID = custID;
    }

    Scanner scanner = new Scanner(System.in);

    private boolean looping = true;

    public void MainMenu(){
        boolean approved;

        try {
            approved = UserOperations.getInstance().isApproved(custID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        if (!approved){
            System.out.println();
            System.out.println("Waiting for approval from admin.");
            return;
        }

        while (looping){
            System.out.println();
            System.out.println("==========================================\n");
            System.out.println("CUSTOMER MENU");
            System.out.println("1. View Items");
            System.out.println("2. View Cart");
            System.out.println("3. Add Item to cart");
            System.out.println("4. Remove Item from cart");
            System.out.println("5. View Balance");
            System.out.println("6. Add money");
            System.out.println("7. Check Out");
            System.out.println("8. View Notifications");
            System.out.println("9. Logout\n");
            System.out.println("===========================================");

            System.out.println();
            System.out.println("Enter your choice");

            String choice = scanner.nextLine();

            switch (choice){
                case "1":
                    viewItems();
                    break;

                case "2":
                    viewCart();
                    break;

                case "3":
                    addItem();
                    break;

                case "4":
                    removeItem();
                    break;

                case "5":
                    viewBalance();
                    break;

                case "6":
                    addMoney();
                    break;

                case "7":
                    checkOut();
                    break;

                case "8":
                    viewNotifications();
                    break;

                case "9":
                    logout();
                    break;

                default:
                    System.out.println("Enter valid choice");
                    break;
            }

        }

    }

    private void viewItems(){
        List<Item> itemList = CustomerOperations.getInstance().viewItems();
        System.out.printf("%-15s%-15s%-15s\n","Name","Price","Availability");
        itemList.forEach(item -> System.out.printf("%-15s%-15.2f%-15d\n",item.getName(),
                item.getPrice(),item.getCount()));
    }

    private void viewCart(){
        List<Item> itemList = CustomerOperations.getInstance().viewCart(custID);
        if(itemList.isEmpty()){
            System.out.println("Cart is empty.");
            return;
        }
        System.out.printf("%-15s%-15s%-15s\n","Name","Price","Amount");
        itemList.forEach(item -> System.out.printf("%-15s%-15.2f%-15d\n",item.getName(),
                item.getPrice(),item.getCount()));
    }

    private void addItem(){
        System.out.println();
        System.out.println("Enter the name of the item you want to add to cart");
        String name = scanner.nextLine();
        System.out.println("Enter the number of such items to be added in cart");
        int count = scanner.nextInt();scanner.nextLine();

        try {
            CustomerOperations.getInstance().addItem(custID,name,count);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void removeItem(){
        System.out.println();
        System.out.println("Enter the name of the item you want to remove from cart");
        String name = scanner.nextLine();

        try {
            CustomerOperations.getInstance().removeItem(custID,name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewBalance(){
        System.out.println();
        float balance = CustomerOperations.getInstance().viewBalance(custID);

        System.out.println("Your current balance is Rs." + balance );
    }

    private void addMoney(){
        System.out.println();
        System.out.println("Enter the amount of money you want to add");
        float amount = scanner.nextFloat();scanner.nextLine();

        System.out.println("How do you want to make payment?");
        System.out.println("1.UPI 2.Credit Card 3.Debit Card");
        String choice = scanner.nextLine();

        switch (choice){
            case "1":
                addViaUPI(amount);
                break;

            case "2":
                addViaCard(amount,ModeOfPayment.CREDIT_CARD);
                break;

            case "3":
                addViaCard(amount,ModeOfPayment.DEBIT_CARD);
                break;
        }
    }

    private void addViaCard(float amount, ModeOfPayment modeOfPayment){

        System.out.println("Enter card number");
        String cardno = scanner.nextLine();
        while (cardno.length()!=16){
            System.out.println("Invalid details. Please enter details again");
            cardno = scanner.nextLine();
        }

        System.out.println("Enter CVV");
        String cvv = scanner.nextLine();
        while (cvv.length()!=3){
            System.out.println("Invalid details. Please enter details again");
            cvv = scanner.nextLine();
        }

        System.out.println("$$$$ Adding Money $$$$");

        CustomerOperations.getInstance().addMoney(custID,amount,modeOfPayment);
    }

    private void addViaUPI(float amount){

        System.out.println("Enter UPI ID");
        String id = scanner.nextLine();
        while (id.length()>45){
            System.out.println("Invalid details. Please enter details again");
            id = scanner.nextLine();
        }

        System.out.println("Enter UPI PIN");
        String pin = scanner.nextLine();
        while (pin.length()!=4){
            System.out.println("Invalid details. Please enter details again");
            pin = scanner.nextLine();
        }

        System.out.println("$$$$ Adding Money $$$$");

        CustomerOperations.getInstance().addMoney(custID,amount,ModeOfPayment.UPI);
    }

    private void checkOut(){
        System.out.println();
        System.out.println("-----Making Payment----");
        try {
            PaymentOperation.getInstance().makePayment(custID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewNotifications(){
        List<Notification> notifications = NotificationOperation.getInstance().viewNotifications(custID);

        System.out.printf("%-30s%-60s\n","Notification ID", "Message");

        notifications.forEach(notification -> System.out.printf("%-30s%-60s\n",notification.getNotificationID(),
                notification.getMessage()));

    }

    private void logout(){
        looping = false;
        System.out.println();
        System.out.println("----------Logging out----------");
    }

}
