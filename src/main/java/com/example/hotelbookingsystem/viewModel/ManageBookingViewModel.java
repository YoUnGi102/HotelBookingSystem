package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Guest;
import com.example.hotelbookingsystem.model.Model;
import com.example.hotelbookingsystem.model.Room;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ManageBookingViewModel {

    private Model model;

    private ObjectProperty<ObservableList<GuestTableProperty>> guests;

    public ManageBookingViewModel(Model model){
        guests = new SimpleObjectProperty<>();
        this.model = model;
    }

    public void bindTableItemsProperty(ObjectProperty<ObservableList<GuestTableProperty>> itemsProperty){
        itemsProperty.bindBidirectional(guests);
    }

//    public void searchGuests(String firstName, String lastName, String phoneNumber, String passportNumber, String email){
//        ObservableList<GuestTableProperty> guestsFormatted = FXCollections.observableArrayList();
//        for (Guest g : model.searchGuests(firstName, lastName, phoneNumber, passportNumber, email)) {
//            guestsFormatted.add(new GuestTableProperty(g));
//        }
//        guests.setValue(guestsFormatted);
//    }

}
