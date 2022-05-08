package com.example.hotelbookingsystem.dao;

import com.example.hotelbookingsystem.model.Address;
import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.model.Receptionist;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReceptionistDAO {

    void insert(Receptionist receptionist) throws SQLException;

    Receptionist select(String username) throws SQLException;
    ArrayList<Receptionist> selectAll() throws SQLException;

    void update(Receptionist receptionist) throws SQLException;

    void delete(Receptionist receptionist) throws SQLException;

    Receptionist logIn(String username, String password) throws SQLException;

    Address setAddress(Receptionist receptionist) throws SQLException;

}
