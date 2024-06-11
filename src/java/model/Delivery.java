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
    private int id;
    private int address_id;
    private float ship_price;
    private int order_id;
    private Date delivery_date;
    private String status;
    private int delivery_person_id;
    private int account_id;

    public Delivery() {
    }

    public Delivery(int id, int address_id, float ship_price, int order_id, Date delivery_date, String status, int delivery_person_id, int account_id) {
        this.id = id;
        this.address_id = address_id;
        this.ship_price = ship_price;
        this.order_id = order_id;
        this.delivery_date = delivery_date;
        this.status = status;
        this.delivery_person_id = delivery_person_id;
        this.account_id = account_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public float getShip_price() {
        return ship_price;
    }

    public void setShip_price(float ship_price) {
        this.ship_price = ship_price;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDelivery_person_id() {
        return delivery_person_id;
    }

    public void setDelivery_person_id(int delivery_person_id) {
        this.delivery_person_id = delivery_person_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    @Override
    public String toString() {
        return "Delivery{" + "id=" + id + ", address_id=" + address_id + ", ship_price=" + ship_price + ", order_id=" + order_id + ", delivery_date=" + delivery_date + ", status=" + status + ", delivery_person_id=" + delivery_person_id + ", account_id=" + account_id + '}';
    }

    
}
