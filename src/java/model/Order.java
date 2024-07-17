/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Long1
 */
public class Order {
    private int id;
    private int restaurant_id;
    private int customer_id;
    private int total_amount;
    private String status;
    private java.sql.Timestamp order_date;
    private Address fromAddress;
    private Address toAddress;
    private Customer customer;
    private String res_name, res_phone, cus_name, cus_phone;
     private Date order_date2;

    public Date getOrder_date2() {
        return order_date2;
    }

    public void setOrder_date2(Date order_date2) {
        this.order_date2 = order_date2;
    }
    public Order() {
    }
    public Order(int id, String nameRes, String nameCus, int total_amount, String status, java.sql.Timestamp order_date) {
        this.id = id;
        this.res_name = nameRes;
        this.cus_name = nameCus;
        this.total_amount = total_amount;
        this.status = status;
        this.order_date = order_date;
    }
    public Order(int id, int restaurant_id, int customer_id, int total_amount, String status, Date order_date) {
        this.id = id;
        this.restaurant_id = restaurant_id;
        this.customer_id = customer_id;
        this.total_amount = total_amount;
        this.status = status;
        this.order_date2 = order_date;
    }

    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public String getRes_phone() {
        return res_phone;
    }

    public void setRes_phone(String res_phone) {
        this.res_phone = res_phone;
    }

    public String getCus_name() {
        return cus_name;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    public String getCus_phone() {
        return cus_phone;
    }

    public void setCus_phone(String cus_phone) {
        this.cus_phone = cus_phone;
    }

    public Order(int id, int total_amount, String status, java.sql.Timestamp order_date, Address fromAddress, Address toAddress) {
        this.id = id;
        this.total_amount = total_amount;
        this.status = status;
        this.order_date = order_date;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
    }

    
    public Order(int id, int restaurant_id, int customer_id, int total_amount, String status, java.sql.Timestamp order_date) {
        this.id = id;
        this.restaurant_id = restaurant_id;
        this.customer_id = customer_id;
        this.total_amount = total_amount;
        this.status = status;
        this.order_date = order_date;
    }

    public Order(int id, int restaurant_id, int total_amount, String status, java.sql.Timestamp order_date, Customer customer) {
        this.id = id;
        this.restaurant_id = restaurant_id;
        this.total_amount = total_amount;
        this.status = status;
        this.order_date = order_date;
        this.customer = customer;
    }
    
    public Order(int id, String status, java.sql.Timestamp order_date, String res_name, String res_phone, String cus_name, String cus_phone) {
        this.id = id;
        this.status = status;
        this.order_date = order_date;
        this.res_name = res_name;
        this.res_phone = res_phone;
        this.cus_name = cus_name;
        this.cus_phone = cus_phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.sql.Timestamp getOrder_date() {
        return order_date;
    }

    public void setOrder_date(java.sql.Timestamp order_date) {
        this.order_date = order_date;
    }

    public Address getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(Address fromAddress) {
        this.fromAddress = fromAddress;
    }

    public Address getToAddress() {
        return toAddress;
    }

    public void setToAddress(Address toAddress) {
        this.toAddress = toAddress;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", restaurant_id=" + restaurant_id + ", customer_id=" + customer_id + ", total_amount=" + total_amount + ", status=" + status + ", order_date=" + order_date + ", fromAddress=" + fromAddress + ", toAddress=" + toAddress + '}';
    }

}