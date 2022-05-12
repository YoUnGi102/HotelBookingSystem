package com.example.hotelbookingsystem.model;

import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Booking {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private int bookingId;

    private LocalDate dateFrom;
    private LocalDate dateTo;

    private ArrayList<Guest> guests;
    private Room room;

    private Staff createdBy;

    public Booking(int bookingId, Guest guest, LocalDate dateFrom, LocalDate dateTo, Room room, Staff createdBy){
        this.bookingId = bookingId;
        guests = new ArrayList<>();
        guests.add(guest);
        this.room = room;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.createdBy = createdBy;
    }
    public Booking(int bookingId, List<Guest> guests, LocalDate dateFrom, LocalDate dateTo, Room room, Staff createdBy){
        this.bookingId = bookingId;
        this.guests = new ArrayList<>();
        this.guests.addAll(guests);
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.room = room;
        this.createdBy = createdBy;
    }
    public Booking(Guest guest, LocalDate dateFrom, LocalDate dateTo, Room room, Staff createdBy){
        this(-1, guest, dateFrom, dateTo, room, createdBy);
    }
    public Booking(List<Guest> guests, LocalDate dateFrom, LocalDate dateTo, Room room, Staff createdBy){
        this(-1, guests, dateFrom, dateTo, room, createdBy);
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }
    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
    public void setGuests(ArrayList<Guest> guests) {
        this.guests = guests;
    }
    public void setRoom(Room room) {
        this.room = room;
    }
    public void setCreatedBy(Staff createdBy) {
        this.createdBy = createdBy;
    }

    public String getName(){
        for (Guest g : guests) {
            if(g.getPhoneNumber() != null && g.getEmail() != null)
                return g.getFirstName() + " " + g.getLastName();
        }
        return null;
    }
    public String getPhoneNumber(){
        for (Guest g : guests) {
            if(g.getPhoneNumber() != null && g.getEmail() != null)
                return g.getPhoneNumber();
        }
        return null;
    }
    public String getEmail(){
        for (Guest g : guests) {
            if(g.getPhoneNumber() != null && g.getEmail() != null)
                return g.getEmail();
        }
        return null;
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
    public ArrayList<Guest> getGuests() {
        return guests;
    }
    public Staff getCreatedBy() {
        return createdBy;
    }

    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        if(this.bookingId == -1)
            this.bookingId = bookingId;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", guests=" + guests +
                ", room=" + room +
                ", createdBy=" + createdBy +
                '}';
    }
}
