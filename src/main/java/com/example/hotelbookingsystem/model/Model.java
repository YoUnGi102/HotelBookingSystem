package com.example.hotelbookingsystem.model;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface Model {

    void addBooking(ArrayList<Guest> guests, Room room, LocalDate dateFrom, LocalDate dateTo);
    void removeBooking(Booking booking) throws SQLException;
    void editBooking(Booking booking);

    ObservableList<Guest> searchGuests(String firstName, String lastName, String phoneNumber, String passportNumber, String email) throws SQLException;
    ObservableList<Room> searchRooms(int floor, int size, int quality, LocalDate from, LocalDate to) throws SQLException;
    public ObservableList<Booking> searchBookings(String phoneNumber, String email, int roomNumber, LocalDate dateFrom, LocalDate dateTo) throws SQLException;
}


