/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Feedback {

    int id, customerID, restaurantID, orderID, value, productID;
    String description;
    String img;
    
    private int customer_id;
    private int restaurant_id;  
    private int order_id;
    
    public Feedback() {
    }

    public Feedback(int id, int customerID, int restaurantID, int orderID, int value, String description, String img,int productID) {
        this.id = id;
        this.customerID = customerID;
        this.restaurantID = restaurantID;
        this.orderID = orderID;
        this.value = value;
        this.description = description;
        this.img = img;
        this.productID = productID;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

//    public Feedback(int customerId, int restaurantId, int orderId, int value, String description, String img) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    public Feedback(int customerID, int restaurantID, int orderID, int value, int productID, String description, String img) {
        this.customerID = customerID;
        this.restaurantID = restaurantID;
        this.orderID = orderID;
        this.value = value;
        this.productID = productID;
        this.description = description;
        this.img = img;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + "; customerID=" + customerID + "; restaurantID=" + restaurantID + "; orderID=" + orderID + "; value=" + value + "; description=" + description + "; img=" + img + '}';
    }

}
