package com.example.hotelbookingsystem.model;

import java.io.File;
import java.util.ArrayList;

public class Guest {

    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private String email;
    private String passportNumber;

    public Guest(String firstName, String lastName, Address address, String phoneNumber, String email, String passportNumber) {
        this(firstName, lastName, address, phoneNumber, email, passportNumber, new ArrayList<>());
    }

    public Guest(String firstName, String lastName, Address address, String phoneNumber, String email, String passportNumber, ArrayList<File> personalFiles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passportNumber = passportNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public Address getAddress() {
        return address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public String getPassportNumber() {
        return passportNumber;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                '}';
    }
}
