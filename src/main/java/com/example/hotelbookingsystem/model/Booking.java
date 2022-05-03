package com.example.hotelbookingsystem.model;

import javafx.scene.control.DatePicker;

public class Booking {
    private String phoneNumber;
    private String email;
    private DatePicker dateFrom;
    private DatePicker dateTo;
    private int roomNumber;

    public Booking(String phoneNumber, String email, DatePicker dateFrom, DatePicker dateTo, int roomNumber)
    {
        this.phoneNumber=phoneNumber;
        this.email=email;
        this.dateFrom=dateFrom;
        this.dateTo=dateTo;
        this.roomNumber=roomNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public DatePicker getDateFrom() {
        return dateFrom;
    }

    public DatePicker getDateTo() {
        return dateTo;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
}
