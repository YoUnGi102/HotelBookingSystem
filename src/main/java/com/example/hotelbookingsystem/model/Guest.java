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
    private ArrayList<File> personalFiles;

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
        this.personalFiles = personalFiles;
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

    public ArrayList<File> getPersonalFiles() {
        return personalFiles;
    }
}
