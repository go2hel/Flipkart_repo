package com.sample.client;

import com.sample.bean.Customer;
import com.sample.bean.Item;
import com.sample.service.AdminOperation;

import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    private boolean looping = true;

    private Scanner scanner = new Scanner(System.in);

    public void MainMenu(){
        while (looping){
            System.out.println();
            System.out.println("==============================\n");
            System.out.println("ADMIN MENU");
            System.out.println("1. View pending customers");
            System.out.println("2. Approve customer");
            System.out.println("3. Ban customer");
            System.out.println("4. Add new Item");
            System.out.println("5. Add Item's count");
            System.out.println("6. Remove Item");
            System.out.println("7. View Items");
            System.out.println("8. Logout\n");
            System.out.println("==============================");

            System.out.println();
            System.out.println("Enter your choice");

            String choice = scanner.nextLine();

            switch (choice){
                case "1":
                    viewPending();
                    break;

                case "2":
                    approveCustomer();
                    break;

                case "3":
                    banCustomer();
                    break;

                case "4":
                    addItem();
                    break;

                case "5":
                    addCount();
                    break;

                case "6":
                    removeItem();
                    break;

                case "7":
                    viewItems();
                    break;

                case "8":
                    logout();
                    break;

                default:
                    System.out.println("Enter valid choice");
                    break;
            }
        }
    }

    private void viewPending(){
        List<Customer> customers = AdminOperation.getInstance().viewPending();

        System.out.println();
        System.out.printf("%-20s%-30s\n","ID", "Name");
        customers.forEach(customer -> System.out.printf("%-20s%-30s\n",
                customer.getID(),customer.getName()));
    }

    private void approveCustomer(){
        viewPending();

        System.out.println();
        System.out.println("Enter the ID of the customer to be approved");
        String ID = scanner.nextLine();

        try {
            AdminOperation.getInstance().approveCustomer(ID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void banCustomer(){
        System.out.println();
        System.out.println("Enter ID of the customer to be banned");
        String ID = scanner.nextLine();

        try {
            AdminOperation.getInstance().banCustomer(ID);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void addItem(){
        System.out.println();
        System.out.println("Enter the name of the item");
        String name = scanner.nextLine();
        System.out.println("Enter the price of the item");
        float price = scanner.nextFloat();scanner.nextLine();
        System.out.println("Enter the amount of items to be added");
        int count = scanner.nextInt();scanner.nextLine();

        try {
            AdminOperation.getInstance().addItem(new Item(name,price,count));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addCount(){
        System.out.println();
        System.out.println("Enter the name of the item");
        String name = scanner.nextLine();
        System.out.println("Enter the amount of items to be added");
        int count = scanner.nextInt();scanner.nextLine();

        try {
            AdminOperation.getInstance().addItemCount(name,count);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void removeItem(){
        System.out.println();
        System.out.println("Enter the name of Item to be removed");
        String name = scanner.nextLine();

        try {
            AdminOperation.getInstance().removeItem(name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewItems(){
        List<Item> itemList = AdminOperation.getInstance().viewItems();

        System.out.println();
        System.out.printf("%-15s%-15s%-15s\n","Name","Price","Availability");
        itemList.forEach(item -> System.out.printf("%-15s%-15.2f%-15d\n",item.getName(),
                item.getPrice(),item.getCount()));
    }

    private void logout(){
        looping=false;
        System.out.println();
        System.out.println("------Logging out------");
    }

}
