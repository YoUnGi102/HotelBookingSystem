package com.example.hotelbookingsystem.dao;

import com.example.hotelbookingsystem.model.Address;
import com.example.hotelbookingsystem.model.Guest;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GuestDAO {

    void insert(Guest guest) throws SQLException;
    void insertMany(ArrayList<Guest> guests) throws SQLException;

    Guest select(String passportNumber) throws SQLException;
    ArrayList<Guest> selectAll() throws SQLException;
    ArrayList<Guest> selectGuestsInRoom(int roomNumber) throws SQLException;

    Address setAddress(Guest guest) throws SQLException;

    void update(Guest guest) throws SQLException;

    void delete(Guest guest) throws SQLException;


}
