package com.example.hotelbookingsystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelManager implements Model{

    private ObservableList<Guest> guestList;

    ModelManager(){
        guestList = FXCollections.observableArrayList();
    }

    @Override
    public ObservableList<Guest> searchGuests(String name, String phoneNumber, String passportNumber, String email) {
        return null;
    }

}
