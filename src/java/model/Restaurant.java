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
    private int addressID;
    private Address address;
    private int restaurant_rating, account_id;  
    private int addres_id;  
     private String details;
    private String state;
    private String street;
    public Restaurant() {
    }

    public int getAddres_id() {
        return addres_id;
    }

    public void setAddres_id(int addres_id) {
        this.addres_id = addres_id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Restaurant(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    
    public Restaurant(String name, int restaurant_rating, int account_id) {
        this.name = name;
        this.restaurant_rating = restaurant_rating;
        this.account_id = account_id;
    }

    public Restaurant(int id, String name, String phonenumber, int addressID, int restaurant_rating, int account_id) {
        this.id = id;
        this.name = name;
        this.phonenumber = phonenumber;
        this.addressID = addressID;
        this.restaurant_rating = restaurant_rating;
        this.account_id = account_id;
    }
    public Restaurant(int id, String name, String phonenumber, String details, String state, String street) {
        this.id = id;
        this.name = name;
        this.phonenumber = phonenumber;
        this.details = details;
        this.state = state;
        this.street = street;
    }

    public Restaurant(int id, String name, String phonenumber, Address address, int restaurant_rating, int account_id) {
        this.id = id;
        this.name = name;
        this.phonenumber = phonenumber;
        this.address = address;
        this.restaurant_rating = restaurant_rating;
        this.account_id = account_id;
    }

    public Restaurant(int id, String name, int restaurant_rating, int account_id) {
        this.id = id;
        this.name = name;
        this.restaurant_rating = restaurant_rating;
        this.account_id = account_id;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    @Override
    public String toString() {
        return "Restaurant{" + "id=" + id + ", name=" + name + ", phonenumber=" + phonenumber + ", address=" + address + ", restaurant_rating=" + restaurant_rating + ", account_id=" + account_id + '}';
    }




}
