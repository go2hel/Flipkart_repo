package com.practice.client;

import com.practice.service.CustomerOperation;

import java.util.Scanner;

public class ManagerClient {
    public static void main(String[] args) {
        CustomerOperation manager = new CustomerOperation();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please add some customers first");
        for (int i=0;i<3;i++){
            int id = scanner.nextInt();scanner.nextLine();
            String name = scanner.nextLine();
            String address = scanner.nextLine();
            manager.createCustomer(id,name,address);
        }

        while (true){
            System.out.println();
            System.out.println("Please specify operation to perform");
            System.out.println("c - create customer, d - delete customer, l- view list of customer");
            System.out.println("u - update customer, t - terminate program");

            char c = scanner.next().charAt(0);

            if(c=='c'){
                System.out.println("Please enter details of customer.");
                int id = scanner.nextInt();scanner.nextLine();
                String name = scanner.nextLine();
                String address = scanner.nextLine();
                manager.createCustomer(id,name,address);
            }else if(c=='d'){
                System.out.println("Enter customer ID of customer to be deleted.");
                int id = scanner.nextInt();scanner.nextLine();
                if(manager.deleteCustomer(id)){
                    System.out.println("**********Customer deleted*********");
                }else{
                    System.out.println("---------Customer not found--------");
                }
            }else if(c=='l'){
                System.out.println("___________________________");
                manager.listCustomer();
                System.out.println("---------------------------");
            }else if(c=='u'){
                System.out.println("Enter the updated details by specifying customer ID first");
                int id = scanner.nextInt();scanner.nextLine();
                String name = scanner.nextLine();
                String address = scanner.nextLine();
                if(manager.updateCustomer(id,name,address)){
                    System.out.println("{{{{{-----Customer updated successfully-----}}}}}}");
                }else{
                    System.out.println("<<<<-------------Customer not found--------->>>>");
                }
            }else if(c=='t'){
                break;
            }else{
                System.out.println("Please make valid request");
            }
        }
    }
}
