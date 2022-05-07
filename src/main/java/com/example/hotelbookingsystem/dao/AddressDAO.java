package com.example.hotelbookingsystem.dao;

import com.example.hotelbookingsystem.model.Address;
import com.example.hotelbookingsystem.model.Guest;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AddressDAO {

    void insert(Address address) throws SQLException;

    Address select(int addressId) throws SQLException;
    Address select(Address address) throws SQLException;

    void update(Address address) throws SQLException;

    void delete(Address address) throws SQLException;

}
