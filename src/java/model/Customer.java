/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Long1
 */
import java.util.*;

/**
 *
 * @author Long1
 */
public class Customer {

    private int id;
    private String name, userName, passWord, email, phoneNumber;
    private boolean gender;
    private int address_id;
    private Date createDate, dob;

    public Customer() {
    }

    public Customer(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }
    

    public Customer(int id, String name, String userName, String passWord, String email, String phoneNumber, boolean gender, int address_id, Date createDate, Date dob) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.address_id = address_id;
        this.createDate = createDate;
        this.dob = dob;
    }

    public Customer(String name, String userName, String passWord, String email, String phoneNumber, boolean gender, int address_id, Date createDate, Date dob) {
        this.name = name;
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.address_id = address_id;
        this.createDate = createDate;
        this.dob = dob;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    
}
