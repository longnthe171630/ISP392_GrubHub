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
    private int quantity;
    private String description, image;
    private boolean status;
    private Date create_date;
    
    private Restaurant restaurant;
    private Category category;

    public Product() {
    }

    public Product(int id, String name, int price, String description, String image, boolean status, Date create_date, int quantity, Restaurant restaurant, Category category) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.status = status;
        this.create_date = create_date;

        this.quantity = quantity;
        this.restaurant = restaurant;
        this.category = category;

    }

    public String getRestaurant_name() {
        return restaurant.getName();
    }

    public void setRestaurant_name(String restaurant_name) {
        if (restaurant != null) {
            restaurant.setName(restaurant_name);
        }
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

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public int getQuantity() {
        return quantity;

    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", description=" + description + ", image=" + image + ", status=" + status + ", create_date=" + create_date + ", quantity=" + quantity + ", restaurant=" + restaurant + ", category=" + category + '}';
    }

}
