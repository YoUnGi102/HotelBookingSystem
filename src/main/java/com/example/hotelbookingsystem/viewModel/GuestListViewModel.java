package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Guest;
import com.example.hotelbookingsystem.model.Model;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class GuestListViewModel {

    private Model model;

    private ObjectProperty<ObservableList<GuestTableProperty>> guests;

    public GuestListViewModel(Model model){
        guests = new SimpleObjectProperty<>();
        this.model = model;
    }

    public void bindTableItemsProperty(ObjectProperty<ObservableList<GuestTableProperty>> itemsProperty){
        itemsProperty.bind(guests);
    }

    public void searchGuests(String firstName, String lastName, String phoneNumber, String passportNumber, String email) throws SQLException {
        ObservableList<GuestTableProperty> guestsFormatted = FXCollections.observableArrayList();
        for (Guest g : model.searchGuests(firstName, lastName, phoneNumber, passportNumber, email)) {
            guestsFormatted.add(new GuestTableProperty(g));
        }
        guests.setValue(guestsFormatted);
    }

    public void removeGuest(Guest guest) throws SQLException {
            model.removeGuest(guest);

    }


}
