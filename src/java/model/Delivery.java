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
public class Delivery {
    private int id, order_id, delivery_person_id;
    private int ship_price;
    private java.sql.Date delivery_date;
    private String status, image;

    public Delivery() {
    }

    public Delivery(int id, int order_id, int delivery_person_id, int ship_price, java.sql.Date delivery_date, String status, String image) {
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

    public java.sql.Date getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(java.sql.Date delivery_date) {
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

    @Override
    public String toString() {
        return "Delivery{" + "id=" + id + ", order_id=" + order_id + ", delivery_person_id=" + delivery_person_id + ", ship_price=" + ship_price + ", delivery_date=" + delivery_date + ", status=" + status + ", image=" + image + '}';
    }

}
