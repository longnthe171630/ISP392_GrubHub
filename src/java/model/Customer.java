/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.*;

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
    private String name, userName, passWord, email, phoneNumber, dob;
    private boolean gender;
    private int address_id;
    private Date createDate;

    public Customer() {
    }

    public Customer(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public Customer(String userName, String passWord, String email) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
    }

    public Customer(String userName, String passWord, String email, String phoneNumber, String dob, boolean gender) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.gender = gender;
    }

    public Customer(int id, String name, String userName, String passWord, String email, String phoneNumber, String dob, boolean gender, int address_id, Date createDate) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.gender = gender;
        this.address_id = address_id;
        this.createDate = createDate;
    }

    public Customer(String userName, String email, String phoneNumber, String dob, boolean gender) {
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.gender = gender;
    }

    public Customer(String name, String userName, String passWord, String email, String phoneNumber, String dob, boolean gender, int address_id) {
        this.name = name;
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.gender = gender;
        this.address_id = address_id;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + ", userName=" + userName + ", passWord=" + passWord + ", email=" + email + ", phoneNumber=" + phoneNumber + ", dob=" + dob + ", gender=" + gender + ", address_id=" + address_id + ", createDate=" + createDate + '}';
    }

}
