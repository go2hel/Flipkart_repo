package com.sample.constant;

public class SQLQueries {

    // User Dao Operations
    public static final String ADD_USER  = "insert into users (id,name,password,gender,role,balance) values (?,?,?,?,?,?)";
    public static final String IS_APPROVED = "select isApproved from users where id=?";
    public static final String LOGIN = "select * from users where id=?";

    //Admin Dao Operations
    public static final String VIEW_PENDING = "select * from users where isApproved=0";
    public static final String APPROVE_CUSTOMER = "update users set isApproved=1 where id=?";
    public static final String DELETE_CUSTOMER = "delete from users where id=?";
    public static final String ADD_ITEM = "insert into items (name,price,count) values (?,?,?)";
    public static final String ADD_ITEM_COUNT = "update items set count=? where name=?";
    public static final String GET_ITEM_COUNT = "select count from items where name=?";
    public static final String DELETE_ITEM = "delete from items where name=?";
    public static final String VIEW_ITEMS = "select *  from items";

    //Customer Dao Operations
    public static final String ADD_ITEM_CART = "insert into cart (name,id,price,count) values (?,?,?,?)";
    public static final String VIEW_CART = "select * from cart where id=?";
    public static final String VIEW_BALANCE = "select balance from users where id=?";
    public static final String ADD_MONEY = "update users set balance=? where id=?";
    public static final String REMOVE_ITEM_CART = "delete from cart where id=? and name=?";
    public static final String GET_ITEM = "select * from items where name=? and count>=?";

}
