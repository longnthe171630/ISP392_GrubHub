/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Long1
 */
public class Restaurant {
    private int id;
    private String name, phonenumber;
    private int address_id, restaurant_rating, account_id;
    private String image;

    public Restaurant() {
    }

    public Restaurant(int id, String name, String phonenumber, int address_id, int restaurant_rating, int account_id, String image) {
        this.id = id;
        this.name = name;
        this.phonenumber = phonenumber;
        this.address_id = address_id;
        this.restaurant_rating = restaurant_rating;
        this.account_id = account_id;
        this.image = image;
    }

    public Restaurant(int id, String name, String phonenumber, int address_id, int restaurant_rating, int account_id) {
        this.id = id;
        this.name = name;
        this.phonenumber = phonenumber;
        this.address_id = address_id;
        this.restaurant_rating = restaurant_rating;
        this.account_id = account_id;
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getRestaurant_rating() {
        return restaurant_rating;
    }

    public void setRestaurant_rating(int restaurant_rating) {
        this.restaurant_rating = restaurant_rating;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Restaurant{" + "id=" + id + ", name=" + name + ", phonenumber=" + phonenumber + ", address_id=" + address_id + ", restaurant_rating=" + restaurant_rating + ", account_id=" + account_id + ", image=" + image + '}';
    }
    
    
}
