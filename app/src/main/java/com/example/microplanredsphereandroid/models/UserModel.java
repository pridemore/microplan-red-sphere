package com.example.microplanredsphereandroid.models;

import java.time.LocalDate;

public class UserModel {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String refNumber;
    private String cellNumber;
    private String gender;
    private LocalDate date_of_birth;
    private String houseAddress;
    private String ipAddress;
    private String role;

    public UserModel() {
    }

    public UserModel(long id, String firstName, String lastName, String email, String refNumber, String cellNumber, String gender, LocalDate date_of_birth, String houseAddress, String ipAddress,  String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.refNumber = refNumber;
        this.cellNumber = cellNumber;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.houseAddress = houseAddress;
        this.ipAddress = ipAddress;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
