package com.example.hotelbookingsystem.model.list;

import com.example.hotelbookingsystem.model.Guest;
import com.example.hotelbookingsystem.dao.GuestDAO;
import com.example.hotelbookingsystem.dao.GuestTable;
import com.example.hotelbookingsystem.model.Guest;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.Objects;

public class GuestList {

    private ObservableList<Guest> guests;
    GuestDAO guestTable;

    private static GuestList instance;

    private GuestList() throws SQLException {
        guestTable = GuestTable.getInstance();
        refresh();
    }
    public static synchronized GuestList getInstance() throws SQLException {
        if(instance == null)
            instance = new GuestList();
        return instance;
    }

    private void refresh() throws SQLException {
        guests = guestTable.selectAll();
    }

    public void add(Guest guest) throws SQLException {
        guestTable.insert(guest);
        refresh();
    }
    public void remove(Guest guest) throws SQLException {
        guestTable.delete(guest);
        refresh();
    }
    public Guest get(String passportNumber) throws SQLException {
        for (Guest guest : guests) {
            if(Objects.equals(guest.getPassportNumber(), passportNumber))
                return guest;
        }
        return null;
    }
    public ObservableList<Guest> getAll() throws SQLException {
        return guests;
    }
    public void update(Guest guest) throws SQLException {
        guestTable.update(guest);
        refresh();
    }

}
