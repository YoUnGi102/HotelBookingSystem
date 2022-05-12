package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.model.Model;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingListViewModel {

    private Model model;

    private Booking currentBooking;

    private ObjectProperty<ObservableList<BookingTableProperty>> bookings;

    public BookingListViewModel(Model model){
        bookings = new SimpleObjectProperty<>();
        currentBooking = null;
        this.model= model;
    }

    public Booking getCurrentBooking() {
        return currentBooking;
    }
    public void setCurrentBooking(Booking currentBooking) {
        this.currentBooking = currentBooking;
    }

    public void bindTableItemsProperty(ObjectProperty<ObservableList<BookingTableProperty>> itemsProperty){
            itemsProperty.bind(bookings);
        }

    public void searchBookings(String phoneNumber, String email, int roomNumber, LocalDate dateFrom, LocalDate dateTo) throws SQLException {
        ObservableList<BookingTableProperty> bookingsFormatted = FXCollections.observableArrayList();
        for (Booking b: model.searchBookings(phoneNumber, email, roomNumber, dateFrom,dateTo)) {
            bookingsFormatted.add(new BookingTableProperty(b));
        }
        bookings.setValue(bookingsFormatted);
    }
    public void removeBooking(Booking booking) throws SQLException {
        model.removeBooking(booking);
    }
}