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
public class Notification {
    private int id;
    private String descripsion;
    private java.sql.Timestamp notice_time;
    private int order_id;
    private String image;
    
    public Notification() {
    }

    public Notification(String descripsion, Timestamp notice_time, int order_id, String image) {
        this.descripsion = descripsion;
        this.notice_time = notice_time;
        this.order_id = order_id;
        this.image = image;
    }
     
    
    public Notification(int id, String descripsion, java.sql.Timestamp notice_time, int order_id) {
        this.id = id;
        this.descripsion = descripsion;
        this.notice_time = notice_time;
        this.order_id = order_id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripsion() {
        return descripsion;
    }

    public void setDescripsion(String descripsion) {
        this.descripsion = descripsion;
    }

    public java.sql.Timestamp getNotice_time() {
        return notice_time;
    }

    public void setNotice_time(java.sql.Timestamp notice_time) {
        this.notice_time = notice_time;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Notification{" + "id=" + id + ", descripsion=" + descripsion + ", notice_time=" + notice_time + ", order_id=" + order_id + ", image=" + image + '}';
    }

    
}
