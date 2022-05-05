package com.example.hotelbookingsystem.model;

import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Booking {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    private LocalDate dateFrom;
    private LocalDate dateTo;

    private List<Guest> guests;
    private Room room;

    public Booking(Guest guest, LocalDate dateFrom, LocalDate dateTo, Room room){
        guests = new ArrayList<>();
        guests.add(guest);
        this.room = room;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
    public Booking(List<Guest> guests, LocalDate dateFrom, LocalDate dateTo, Room room){
        this.guests = new ArrayList<>();
        this.guests.addAll(guests);
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.room = room;
    }

    public String getName(){
        return guests.get(0).getFirstName() + " " + guests.get(0).getLastName();
    }

    public String getPhoneNumber(){
        return guests.get(0).getPhoneNumber();
    }

    public String getEmail(){
        return guests.get(0).getEmail();
    }

    public LocalDate getDateFrom(){
        return dateFrom;
    }

    public LocalDate getDateTo(){
        return dateTo;
    }

    public Room getRoom() {
        return room;
    }

    public List<Guest> getGuests() {
        return guests;
    }
}
