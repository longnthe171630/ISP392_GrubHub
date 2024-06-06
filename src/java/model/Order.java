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
    private int delivery_id;
    private int customer_id;
    private int total_amount;
    private String status;
    private Date order_date;

    public Order() {
    }

    public Order(int id, int restaurant_id, int delivery_id, int customer_id, int total_amount, String status, Date order_date) {
        this.id = id;
        this.restaurant_id = restaurant_id;
        this.delivery_id = delivery_id;
        this.customer_id = customer_id;
        this.total_amount = total_amount;
        this.status = status;
        this.order_date = order_date;
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

    public int getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(int delivery_id) {
        this.delivery_id = delivery_id;
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

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", restaurant_id=" + restaurant_id + ", delivery_id=" + delivery_id + ", customer_id=" + customer_id + ", total_amount=" + total_amount + ", status=" + status + ", order_date=" + order_date + '}';
    }
    
    
}
