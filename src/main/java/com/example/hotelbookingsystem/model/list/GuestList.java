package com.example.hotelbookingsystem.model.list;

import com.example.hotelbookingsystem.model.Guest;
import com.example.hotelbookingsystem.dao.GuestDAO;
import com.example.hotelbookingsystem.dao.GuestTable;
import com.example.hotelbookingsystem.model.Guest;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.Objects;

public class GuestList {

    GuestDAO guestTable;

    private static GuestList instance;

    private GuestList() throws SQLException {
        guestTable = GuestTable.getInstance();
    }
    public static synchronized GuestList getInstance() throws SQLException {
        if(instance == null)
            instance = new GuestList();
        return instance;
    }

    public void add(Guest guest) throws SQLException {
        guestTable.insert(guest);
    }
    public void remove(Guest guest) throws SQLException {
        guestTable.delete(guest);
    }
    public Guest get(String passportNumber) throws SQLException {
        return guestTable.select(passportNumber);
    }
    public ObservableList<Guest> getAll() throws SQLException {
        return guestTable.selectAll();
    }
    public void update(Guest guest) throws SQLException {
        guestTable.update(guest);
    }

}
