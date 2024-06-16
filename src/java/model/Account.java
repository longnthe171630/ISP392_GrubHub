/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Long1
 */
public class Account {

    private int id;
    private String username, password, email, phonenumber;
    private int role;
    private boolean active;
    private String image;

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

    
    
    public Account(int id, String username, String password, String email, String phonenumber, int role, boolean active, String image) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
        this.role = role;
        this.active = active;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", phonenumber=" + phonenumber + ", role=" + role + ", active=" + active + ", image=" + image + '}';
    }

}

