package com.example.hotelbookingsystem.model.list;

import com.example.hotelbookingsystem.dao.BookingDAO;
import com.example.hotelbookingsystem.dao.BookingTable;
import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.model.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;

public class BookingList {

    BookingDAO bookingTable;

    private static BookingList instance;

    private BookingList() {
        bookingTable = BookingTable.getInstance();
    }

    public static synchronized BookingList getInstance() throws SQLException {
        if(instance == null)
            instance = new BookingList();
        return instance;
    }

    public void add(Booking booking) throws SQLException {
        bookingTable.insert(booking);
    }
    public void remove(Booking booking) throws SQLException {
        bookingTable.delete(booking);
    }
    public Booking get(int bookingId) throws SQLException {
        return bookingTable.select(bookingId);
    }
    public ObservableList<Booking> getAll() throws SQLException {
        return bookingTable.selectAll();
    }
    public void update(Booking booking) throws SQLException {
        bookingTable.update(booking);
    }

}
