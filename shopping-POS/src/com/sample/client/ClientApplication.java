package com.sample.client;

import com.sample.bean.Customer;
import com.sample.constant.Gender;
import com.sample.constant.Role;
import com.sample.service.UserOperation;

import java.util.Scanner;

public class ClientApplication {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean looping = true;
        while (looping){
            System.out.println();
            System.out.println("=====================================\n");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit\n");
            System.out.println("=====================================");
            System.out.println();
            System.out.println("Enter your choice : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    login();
                    break;

                case "2":
                    signup();
                    break;

                case "3":
                    exit();looping=false;
                    break;

                default:
                    System.out.println("Enter valid choice");
                    break;
            }
        }
    }

    public static void login(){
        System.out.println();
        System.out.println("Enter your UserId");
        String userID = scanner.nextLine();
        System.out.println("Enter your password");
        String password = scanner.nextLine();
        try {
            Role role =  UserOperation.getInstance().login(userID,password);

            if(role==Role.CUSTOMER){
                CustomerMenu customerMenu = new CustomerMenu(userID);
                customerMenu.MainMenu();
            }else {
                AdminMenu adminMenu = new AdminMenu();
                adminMenu.MainMenu();
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void signup(){
        System.out.println();
        System.out.println("Sign Up");
        System.out.println();
        System.out.println("Enter your Name");
        String name = scanner.nextLine();
        System.out.println("Enter your ID");
        String ID = scanner.nextLine();
        System.out.println("Enter your password");
        String password = scanner.nextLine();
        System.out.println("Enter your Gender 1-Male, 2-Female, 3-Other");
        String genderS = scanner.nextLine();
        Gender gender = Gender.OTHER;
        switch (genderS){
            case "1":
                gender = Gender.MALE;
                break;

            case "2":
                gender = Gender.FEMALE;
                break;

            case "3":
                gender = Gender.OTHER;
                break;
        }
        try {
            UserOperation.getInstance().signUp(new Customer(name,ID,password,gender,Role.CUSTOMER));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void exit(){
        System.out.println("\n----Exiting the system-----");
    }

}
