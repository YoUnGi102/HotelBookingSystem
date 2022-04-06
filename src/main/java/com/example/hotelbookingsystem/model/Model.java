package com.example.hotelbookingsystem.model;

import javafx.collections.ObservableList;

public interface Model {

    ObservableList<Guest> searchGuests(String firstName, String lastName, String phoneNumber, String passportNumber, String email);

}
