/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Long1
 */
public class Delivery {
    private int id, order_id, delivery_person_id;
    private int ship_price;
    private java.sql.Timestamp delivery_date;
    private String status;
    private String image;
    private java.sql.Timestamp start_time, end_time;
    int deliveryTime;

    public Delivery() {
    }

    public Delivery(Timestamp start_time, Timestamp end_time, int deliveryTime) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.deliveryTime = deliveryTime;
    }
    
    public Delivery(int order_id, int delivery_person_id, int ship_price, Timestamp delivery_date, String status, int deliveryTime, Timestamp start_time, Timestamp end_time) {
        this.order_id = order_id;
        this.delivery_person_id = delivery_person_id;
        this.ship_price = ship_price;
        this.delivery_date = delivery_date;
        this.status = status;
        this.start_time = start_time;
        this.end_time = end_time;
        this.deliveryTime = deliveryTime;
    }
    
    public Delivery(int id, int order_id, int delivery_person_id, int ship_price, Timestamp delivery_date, String status, String image, Timestamp start_time, Timestamp end_time) {
        this.id = id;
        this.order_id = order_id;
        this.delivery_person_id = delivery_person_id;
        this.ship_price = ship_price;
        this.delivery_date = delivery_date;
        this.status = status;
        this.image = image;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Delivery(int id, int order_id, int delivery_person_id, int ship_price, Timestamp delivery_date, String status, String image) {
        this.id = id;
        this.order_id = order_id;
        this.delivery_person_id = delivery_person_id;
        this.ship_price = ship_price;
        this.delivery_date = delivery_date;
        this.status = status;
        this.image = image;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getDelivery_person_id() {
        return delivery_person_id;
    }

    public void setDelivery_person_id(int delivery_person_id) {
        this.delivery_person_id = delivery_person_id;
    }

    public int getShip_price() {
        return ship_price;
    }

    public void setShip_price(int ship_price) {
        this.ship_price = ship_price;
    }

    public java.sql.Timestamp getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(java.sql.Timestamp delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @Override
    public String toString() {
        return "Delivery{" + "id=" + id + ", order_id=" + order_id + ", delivery_person_id=" + delivery_person_id + ", ship_price=" + ship_price + ", delivery_date=" + delivery_date + ", status=" + status + ", image=" + image + ", start_time=" + start_time + ", end_time=" + end_time + ", deliveryTime=" + deliveryTime + '}';
    }
    
}
