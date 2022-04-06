package com.example.hotelbookingsystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelManager implements Model{

    private ObservableList<Guest> guestList;

    public ModelManager(){
        guestList = FXCollections.observableArrayList();
        // TODO REMOVE TEST CODE
        guestList.add(new Guest("Sherlock","Holmes", new Address("London", "Baker Street", "221B", "NW1"), "+44 1632 960153", "sherlock@holmes.uk", "882933" ));
        guestList.add(new Guest("John","Watson", new Address("London", "Baker Street", "221B", "NW1"), "+44 2334 962334", "john@watson.uk", "335993" ));

    }

    @Override
    public ObservableList<Guest> searchGuests(String firstName, String lastName, String phoneNumber, String passportNumber, String email) {
        ObservableList<Guest> searchedGuests = FXCollections.observableArrayList();
        for (Guest guest : guestList) {
            if(firstName != null && guest.getFirstName().contains(firstName))
                searchedGuests.add(guest);
            else if(lastName != null && guest.getLastName().contains(lastName))
                searchedGuests.add(guest);
            else if(phoneNumber != null && guest.getPhoneNumber().contains(phoneNumber))
                searchedGuests.add(guest);
            else if(passportNumber != null && guest.getPassportNumber().contains(passportNumber))
                searchedGuests.add(guest);
            else if(email != null && guest.getEmail().contains(email))
                searchedGuests.add(guest);
        }
        return searchedGuests;
    }

}
