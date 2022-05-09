package com.example.hotelbookingsystem.model.list;

import com.example.hotelbookingsystem.dao.BookingDAO;
import com.example.hotelbookingsystem.dao.BookingTable;
import com.example.hotelbookingsystem.model.Booking;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class BookingList {

    private ObservableList<Booking> bookings;
    BookingDAO bookingTable;

    private static BookingList instance;

    private BookingList() throws SQLException {
        bookingTable = BookingTable.getInstance();
        refresh();
    }
    public static synchronized BookingList getInstance() throws SQLException {
        if(instance == null)
            instance = new BookingList();
        return instance;
    }

    private void refresh() throws SQLException {
        bookings = bookingTable.selectAll();
    }

    public void add(Booking booking) throws SQLException {
        bookingTable.insert(booking);
        refresh();
    }
    public void remove(Booking booking) throws SQLException {
        bookingTable.delete(booking);
        refresh();
    }
    public Booking get(int bookingId) throws SQLException {
        for (Booking booking : bookings) {
            if(booking.getBookingId() == bookingId)
                return booking;
        }
        return null;
    }
    public ObservableList<Booking> getAll() throws SQLException {
        return bookings;
    }
    public void update(Booking booking) throws SQLException {
        bookingTable.update(booking);
        refresh();
    }

}
