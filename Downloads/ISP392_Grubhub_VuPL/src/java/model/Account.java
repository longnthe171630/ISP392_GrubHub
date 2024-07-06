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
public class Account {
    private int id;
    private String username, password, email, phonenumber;
    private int role;
    // db mới 22/06------------------------ vupl
    
    private int addressID;
    private int active;
    private String img;
    private Date createDate;
    

    public Account() {
    }

    public Account(int id, String username, String password, String email, String phonenumber, int role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
        this.role = role;
    }

    public Account(String username, String password, String email, String phonenumber, int role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
        this.role = role;
    }
    // contructor mới 22/06

    public Account(int id, String username, String password, String email, String phonenumber, int role, int addressID, int active, String img, Date createDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
        this.role = role;
        this.addressID = addressID;
        this.active = active;
        this.img = img;
        this.createDate = createDate;
    }

    public Account(String username, String password, String email, String phonenumber, int role, int addressID, int active, String img) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
        this.role = role;
        this.addressID = addressID;
        this.active = active;
        this.img = img;

    }

    public Account(String userName, String passWord, String email, String phoneNumber, int role, int idAddress, int i, Object object, Object object0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", phonenumber=" + phonenumber + ", role=" + role + ", addressID=" + addressID + ", active=" + active + ", img=" + img + ", createDate=" + createDate + '}';
    }
    
    
    
    
}
