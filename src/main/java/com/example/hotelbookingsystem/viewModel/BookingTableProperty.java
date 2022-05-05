package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Booking;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.DatePicker;

import java.time.LocalDateTime;

public class BookingTableProperty {

    private StringProperty name;
    private StringProperty phoneNumber;
    private StringProperty email;
    private IntegerProperty roomNumber;
    private StringProperty dateFrom;
    private StringProperty dateTo;

    public BookingTableProperty(Booking booking) {
        setBooking(booking);
    }

    public void setBooking(Booking booking) {
        name = new SimpleStringProperty(booking.getName());
        phoneNumber = new SimpleStringProperty(booking.getPhoneNumber());
        email = new SimpleStringProperty(booking.getEmail());
        roomNumber = new SimpleIntegerProperty(booking.getRoom().getRoomNumber());
        dateFrom = new SimpleStringProperty(Booking.DATE_FORMATTER.format(booking.getDateFrom()));
        dateTo = new SimpleStringProperty(Booking.DATE_FORMATTER.format(booking.getDateFrom()));
    }


}
