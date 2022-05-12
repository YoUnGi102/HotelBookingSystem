package com.example.hotelbookingsystem.model;

public class Manager extends Staff{

    public Manager(String username, String firstName, String lastName, String password, String email, String phoneNumber, Address address) {
        super(username, firstName, lastName, password, email, phoneNumber, address);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address=" + address +
                '}';
    }
}
