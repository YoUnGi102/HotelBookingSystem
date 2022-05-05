package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Booking;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.DatePicker;

public class BookingTableProperty {

    private StringProperty phoneNumber;
    private StringProperty email;
    private DatePicker dateFrom;
    private DatePicker dateTo;

    public BookingTableProperty(Booking booking) {
        setBooking(booking);
    }

    public void setBooking(Booking booking)
    {
        this.phoneNumber= new SimpleStringProperty(booking.getPhoneNumber());
        this.email = new SimpleStringProperty(booking.getEmail());
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }
    public StringProperty phoneNumberProperty(){
        return phoneNumber;
    }

    public String getEmail() {
        return email.get();
    }
    public StringProperty emailProperty(){
        return email;
    }

    public DatePicker getDateFrom() {
        return dateFrom;
    }

}
