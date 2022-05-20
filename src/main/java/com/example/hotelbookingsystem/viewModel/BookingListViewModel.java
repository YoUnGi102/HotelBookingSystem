package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.model.Model;
import com.example.hotelbookingsystem.model.ModelManager;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingListViewModel implements PropertyChangeListener {

    private Model model;

    private Booking currentBooking;

    private ObjectProperty<ObservableList<BookingTableProperty>> bookings;

    private String lastPhoneNumber;
    private String lastEmail;
    private int lastRoomNumber;
    private LocalDate lastDateFrom;
    private LocalDate lastDateTo;

    public BookingListViewModel(Model model){
        bookings = new SimpleObjectProperty<>();
        currentBooking = null;
        this.model= model;
        model.addPropertyChangeListener(ModelManager.REFRESH, this);
        lastRoomNumber = -1;
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
        lastPhoneNumber = phoneNumber;
        lastEmail = email;
        lastRoomNumber = roomNumber;
        lastDateFrom = dateFrom;
        lastDateTo = dateTo;

        ObservableList<BookingTableProperty> bookingsFormatted = FXCollections.observableArrayList();
        for (Booking b: model.searchBookings(phoneNumber, email, roomNumber, dateFrom,dateTo)) {
            bookingsFormatted.add(new BookingTableProperty(b));
        }
        bookings.setValue(bookingsFormatted);
    }

    public void removeBooking(Booking booking) throws SQLException, RemoteException {
        model.removeBooking(booking);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            searchBookings(lastPhoneNumber, lastEmail, lastRoomNumber, lastDateFrom, lastDateTo);
        } catch (SQLException e) {
            System.err.println("Database error occurred, while trying to refresh the table");
        }
    }
}