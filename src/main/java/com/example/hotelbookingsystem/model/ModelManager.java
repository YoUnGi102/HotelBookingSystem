package com.example.hotelbookingsystem.model;

import com.example.hotelbookingsystem.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelManager implements Model{

    private ObservableList<Guest> guestList;
    private ObservableList <Room> rooms;

    public ModelManager(){
        guestList = FXCollections.observableArrayList();
        rooms = FXCollections.observableArrayList();
        // TODO REMOVE TEST CODE
        guestList.add(new Guest("Sherlock","Holmes", new Address("London", "Baker Street", "221B", "NW1"), "+44 1632 960153", "sherlock@holmes.uk", "882933" ));
        guestList.add(new Guest("John","Watson", new Address("London", "Baker Street", "221B", "NW1"), "+44 2334 962334", "john@watson.uk", "335993" ));
        rooms.add(new Room(1,"good",Room.AVAILABLE,2,5,"no","no"));
        rooms.add(new Room(2,"good",Room.BOOKED,1,2,"no","no"));

    }

    @Override
    public ObservableList<Guest> searchGuests(String firstName, String lastName, String phoneNumber, String passportNumber, String email) {
        ObservableList<Guest> searchedGuests = FXCollections.observableArrayList();
        for (Guest guest : guestList) {
            if(!firstName.equals("") && !guest.getFirstName().contains(firstName))
                continue;
            if(!lastName.equals("") && !guest.getLastName().contains(lastName))
                continue;
            if(!phoneNumber.equals("") && !guest.getPhoneNumber().contains(phoneNumber))
                continue;
            if(!passportNumber.equals("") && !guest.getPassportNumber().contains(passportNumber))
                continue;
            if(!email.equals("") && !guest.getEmail().contains(email))
                continue;
            searchedGuests.add(guest);
        }
        return searchedGuests;
    }

    @Override
    public ObservableList<Room> showALlBookedRooms() {
        ObservableList<Room> showAllRooms = FXCollections.observableArrayList();
        for (Room room: rooms) {
            if (room.getAvailability().equals(Room.BOOKED)) {
                showAllRooms.add(room);
            }
        }
        return showAllRooms;
    }

    public ObservableList<Room> showALlAvailableRooms() {
        ObservableList<Room> showAllRooms = FXCollections.observableArrayList();
        for (Room room: rooms) {
            if (room.getAvailability().equals(Room.AVAILABLE)) {
                showAllRooms.add(room);
            }
        }
        return showAllRooms;
    }
    public ObservableList<Room> showALlOutOfOrderRooms() {
        ObservableList<Room> showAllRooms = FXCollections.observableArrayList();
        for (Room room: rooms) {
            if (room.getAvailability().equals(Room.OUTOFORDER)) {
                showAllRooms.add(room);
            }
        }
        return showAllRooms;
    }
}
