package com.example.hotelbookingsystem.dao;

import com.example.hotelbookingsystem.model.Address;
import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.model.Guest;

import java.sql.SQLException;
import javafx.collections.ObservableList;

public interface GuestDAO {

    void insert(Guest guest) throws SQLException;
    void insertMany(ObservableList<Guest> guests) throws SQLException;

    Guest select(String passportNumber) throws SQLException;
    ObservableList<Guest> selectAll() throws SQLException;
    ObservableList<Guest> selectAllInBooking(int bookingId) throws SQLException;

    Address setAddress(Guest guest) throws SQLException;

    void update(Guest guest) throws SQLException;
    void updateGuestsInBooking(Booking booking) throws SQLException;

    void delete(Guest guest) throws SQLException;

}
