package com.example.hotelbookingsystem.model;

import javafx.scene.control.DatePicker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Booking {

    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;

    private List<Guest> guests;
    private Room room;

    public Booking(Guest guest, LocalDateTime dateFrom, LocalDateTime dateTo, Room room){
        guests = new ArrayList<>();
        guests.add(guest);
        this.room = room;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
    public Booking(List<Guest> guests, LocalDateTime dateFrom, LocalDateTime dateTo, Room room){
        this.guests = new ArrayList<>();
        this.guests.addAll(guests);
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getPhoneNumber(){
        return guests.get(0).getPhoneNumber();
    }

    public String getEmail(){
        return guests.get(0).getEmail();
    }

    public LocalDateTime getDateFrom(){
        return dateFrom;
    }

    public LocalDateTime getDateTo(){
        return dateTo;
    }
}
