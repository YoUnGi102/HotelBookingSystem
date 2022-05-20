package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.model.Guest;
import com.example.hotelbookingsystem.model.Model;
import com.example.hotelbookingsystem.model.Room;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ManageBookingViewModel {

    private Model model;

    private ObjectProperty<ObservableList<GuestTableProperty>> guests;
    private ObjectProperty<Room> room;
    private ObjectProperty<LocalDate> dateFrom;
    private ObjectProperty<LocalDate> dateTo;

    private Booking currentBooking;

    public ManageBookingViewModel(Model model){
        this.currentBooking = null;
        guests = new SimpleObjectProperty<>();
        guests.setValue(FXCollections.observableArrayList());
        room = new SimpleObjectProperty<>();
        dateFrom = new SimpleObjectProperty<>();
        dateTo = new SimpleObjectProperty<>();
        this.model = model;
    }

    public void bindTableItemsProperty(ObjectProperty<ObservableList<GuestTableProperty>> itemsProperty){
        itemsProperty.bind(guests);
    }
    public void bindRoomProperty(ObjectProperty<Room> room){
        room.bind(this.room);
    }
    public void bindDateFromProperty(ObjectProperty<LocalDate> dateFrom){
        dateFrom.bindBidirectional(this.dateFrom);
    }
    public void bindDateToProperty(ObjectProperty<LocalDate> dateTo){
        dateTo.bindBidirectional(this.dateTo);
    }

    public void setRoom(Room room){
        this.room.setValue(room);
    }
    public void setGuests(ObservableList<GuestTableProperty> guests){
        ObservableList<GuestTableProperty> newGuests = FXCollections.observableArrayList();
        newGuests.addAll(guests);
        newGuests.addAll(this.guests.getValue());
        this.guests.setValue(newGuests);
    }
    public void setDateFrom(LocalDate dateFrom){
        this.dateFrom.setValue(dateFrom);
    }
    public void setDateTo(LocalDate dateTo){
        this.dateTo.setValue(dateTo);
    }

    public Room getRoom(){
        return room.getValue();
    }
    public ObservableList<GuestTableProperty> getGuests(){
        return guests.getValue();
    }
    public LocalDate getDateFrom(){
        return dateFrom.getValue();
    }
    public LocalDate getDateTo(){
        return dateTo.getValue();
    }

    public Booking getCurrentBooking() {
        return currentBooking;
    }
    public void setCurrentBooking(Booking currentBooking) {
        this.currentBooking = currentBooking;
    }

    public void addBooking() throws SQLException, RemoteException {
        ArrayList<Guest> guests = new ArrayList<>();
        this.guests.getValue().forEach((guest) -> {
            guests.add(guest.getGuest());
        });
        model.addBooking(guests, room.getValue(), dateFrom.getValue(), dateTo.getValue());
    }

    public void clear(){
        guests.setValue(FXCollections.observableArrayList());
        room.setValue(null);
        dateFrom.setValue(null);
        dateTo.setValue(null);
        currentBooking = null;
    }

    public void editBooking() throws SQLException, RemoteException {
        currentBooking.setRoom(room.getValue());
        currentBooking.setDateTo(dateTo.getValue());
        currentBooking.setDateFrom(dateFrom.getValue());
        ArrayList<Guest> guests = new ArrayList<>();
        this.guests.getValue().forEach((guest) -> {
            guests.add(guest.getGuest());
        });
        currentBooking.setGuests(guests);
        model.editBooking(currentBooking);
    }
}
