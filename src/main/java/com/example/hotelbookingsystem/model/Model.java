package com.example.hotelbookingsystem.model;

import javafx.collections.ObservableList;

public interface Model {

    ObservableList<Guest> searchGuests(String name, String phoneNumber, String passportNumber, String email);

}
