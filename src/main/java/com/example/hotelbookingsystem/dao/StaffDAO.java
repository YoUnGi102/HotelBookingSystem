package com.example.hotelbookingsystem.dao;

import com.example.hotelbookingsystem.model.Address;
import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.model.Staff;

import java.sql.SQLException;
import javafx.collections.ObservableList;

public interface StaffDAO {

    void insert(Staff receptionist) throws SQLException;

    Staff select(String username) throws SQLException;
    ObservableList<Staff> selectAll() throws SQLException;

    void update(Staff receptionist) throws SQLException;

    void delete(Staff receptionist) throws SQLException;

    Staff logIn(String username, String password) throws SQLException;

    Address setAddress(Staff receptionist) throws SQLException;

}
