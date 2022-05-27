package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Guest;
import com.example.hotelbookingsystem.model.Model;
import com.example.hotelbookingsystem.model.ModelManager;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;

public class GuestListViewModel implements PropertyChangeListener {

    private Model model;
    private ObjectProperty<ObservableList<GuestTableProperty>> guests;
    private Guest currentGuest;

    private String lastFirstName;
    private String lastLastName;
    private String lastPhoneNumber;
    private String lastPassportNumber;
    private String lastEmail;

    public GuestListViewModel(Model model){
        guests = new SimpleObjectProperty<>();
        this.model = model;
        this.currentGuest = null;
        model.addPropertyChangeListener(ModelManager.REFRESH, this);
    }

    public Guest getCurrentGuest() {
        return currentGuest;
    }
    public void setCurrentGuest(Guest currentGuest) {
        this.currentGuest = currentGuest;
    }

    public void bindTableItemsProperty(ObjectProperty<ObservableList<GuestTableProperty>> itemsProperty){
        itemsProperty.bind(guests);
    }

    public void searchGuests(String firstName, String lastName, String phoneNumber, String passportNumber, String email) throws SQLException {

        lastFirstName = firstName;
        lastLastName = lastName;
        lastPhoneNumber = phoneNumber;
        lastPassportNumber = passportNumber;
        lastEmail = email;

        ObservableList<GuestTableProperty> guestsFormatted = FXCollections.observableArrayList();
        for (Guest g : model.searchGuests(firstName, lastName, phoneNumber, passportNumber, email)) {
            guestsFormatted.add(new GuestTableProperty(g));
        }
        guests.setValue(guestsFormatted);
    }

    public void removeGuest(Guest guest) throws SQLException {
        model.removeGuest(guest);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try
        {
            searchGuests(lastFirstName,lastLastName,lastPhoneNumber,lastPassportNumber,lastEmail);
        } catch (SQLException e) {
            System.err.println("Database error occurred, while trying to refresh the table");
        }
    }
}
