package com.example.hotelbookingsystem.model;

import javafx.collections.ObservableList;

import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface Model {

    void addBooking(ArrayList<Guest> guests, Room room, LocalDate dateFrom, LocalDate dateTo) throws SQLException;
    void removeBooking(Booking booking) throws SQLException;
    void editBooking(Booking booking) throws SQLException;
    ObservableList<Booking> searchBookings(String phoneNumber, String email, int roomNumber, LocalDate dateFrom, LocalDate dateTo) throws SQLException;

    void addGuest(Guest guest) throws SQLException;
    void removeGuest(Guest guest) throws SQLException;
    void editGuest(Guest guest) throws SQLException;
    ObservableList<Guest> searchGuests(String firstName, String lastName, String phoneNumber, String passportNumber, String email) throws SQLException;

    void addRoom(Room room) throws SQLException;
    void removeRoom(Room room) throws SQLException;
    void editRoom(Room room) throws SQLException;
    ObservableList<Room> searchRooms(int floor, int size, int quality, LocalDate from, LocalDate to) throws SQLException;

    Staff getStaff();
    void login(String username, String password) throws SQLException, IllegalAccessException;
    void logOff();

    void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);
    void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);

}


