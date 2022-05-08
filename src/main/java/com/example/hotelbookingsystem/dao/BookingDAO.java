package com.example.hotelbookingsystem.dao;

import com.example.hotelbookingsystem.model.Address;
import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.model.Guest;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingDAO {

    void insert(Booking booking) throws SQLException;

    Booking select(int bookingId) throws SQLException;
    ArrayList<Booking> selectAll() throws SQLException;

    void update(Booking booking) throws SQLException;
    void delete(Booking booking) throws SQLException;

}
