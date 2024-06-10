/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author manh0
 */
public class Product {

    private int id;
    private String name;
    private int price;
    private String description, image;
    private boolean status;
    private Date create_date;
    private int category_id;
    private int restaurant_id;
    private String restaurant_name;

    public Product() {
    }

    public Product(int id, String name, int price, String description, String image, boolean status, Date create_date, int category_id, int restaurant_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.status = status;
        this.create_date = create_date;
        this.category_id = category_id;
        this.restaurant_id = restaurant_id;
    }

    public Product(int id, String name, int price, String description, String image, boolean status, Date create_date, int category_id, int restaurant_id, String restaurant_name) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.status = status;
        this.create_date = create_date;
        this.category_id = category_id;
        this.restaurant_id = restaurant_id;
        this.restaurant_name = restaurant_name;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_at(Date create_at) {
        this.create_date = create_at;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", description=" + description + ", image=" + image + ", status=" + status + ", create_at=" + create_date + ", category_id=" + category_id + ", restaurant_id=" + restaurant_id + '}';
    }

}
