package com.practice.service;

import com.practice.bean.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerOperation implements CustomerInterface{

    private List<Customer> customerList = new ArrayList<Customer>();

    @Override
    public void createCustomer(int custID, String custName, String custAddress) {
        Customer c = new Customer();
        c.setAddress(custAddress);
        c.setId(custID);
        c.setName(custName);
        this.customerList.add(c);
    }

    @Override
    public boolean updateCustomer(int custID, String custName, String custAddress) {
        for(int i=0;i<this.customerList.size();i++){
            if(this.customerList.get(i).getId()==custID){
                Customer c = new Customer();
                c.setAddress(custAddress);
                c.setId(custID);
                c.setName(custName);
                this.customerList.set(i,c);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(int custID) {
        for(int i=0;i<this.customerList.size();i++){
            if(this.customerList.get(i).getId()==custID){
                this.customerList.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void listCustomer() {
        for (Customer c:this.customerList){
            System.out.println("-----" + c.getId() + "-----" +  c.getName() + "-----" + c.getAddress() + "------");
        }
    }
}
