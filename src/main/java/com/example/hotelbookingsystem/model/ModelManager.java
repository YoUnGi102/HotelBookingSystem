package com.example.hotelbookingsystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class ModelManager implements Model{

    private ObservableList<Guest> guestList;
    private ObservableList <Room> rooms;

    public ModelManager(){
        guestList = FXCollections.observableArrayList();
        rooms = FXCollections.observableArrayList();
        // TODO REMOVE TEST CODE
        guestList.add(new Guest("Sherlock","Holmes", new Address("London", "Baker Street", "221B", "NW1"), "+44 1632 960153", "sherlock@holmes.uk", "882933" ));
        guestList.add(new Guest("John","Watson", new Address("London", "Baker Street", "221B", "NW1"), "+44 2334 962334", "john@watson.uk", "335993" ));
        rooms.add(new Room(1, 101, 4, 1, 1, Room.AVAILABLE, false));
        rooms.add(new Room(2, 102, 4, 1, 2, Room.BOOKED, true));
        rooms.add(new Room(3, 103, 3, 1, 3, Room.AVAILABLE, true));
        rooms.add(new Room(4, 104, 3, 1, 3, Room.BOOKED, false));
        rooms.add(new Room(5, 201, 2, 2, 2, Room.AVAILABLE, false));
        rooms.add(new Room(6, 202, 2, 2, 2, Room.BOOKED, false));
        rooms.add(new Room(7, 203, 4, 2, 1, Room.AVAILABLE, false));
        rooms.add(new Room(8, 301, 4, 3, 1, Room.BOOKED, false));
        rooms.add(new Room(9, 302, 3, 3, 2, Room.AVAILABLE, false));
        rooms.add(new Room(10, 303, 3, 3, 3, Room.BOOKED, false));

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
    public ObservableList<Room> searchRooms(int floor, int size, int quality, LocalDate from, LocalDate to)
    {
        ObservableList<Room> searchedRooms = FXCollections.observableArrayList();
        for (Room room : rooms) {
            // TODO Add searching by date
            if (room.getFloor() != floor && floor !=0)
                continue;
            if (room.getRoomSize() != size && size !=0)
                continue;
            if (room.getQuality() != quality && quality !=0)
                continue;


            searchedRooms.add(room);
        }
        System.out.println(searchedRooms.size());
        return searchedRooms;
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
            if (room.getAvailability().equals(Room.OUT_OF_ORDER)) {
                showAllRooms.add(room);
            }
        }
        return showAllRooms;
    }

    @Override
    public ObservableList<Booking> searchBookings(String phoneNumber, String email, int roomNumber, LocalDate dateFrom, LocalDate dateTo) {
        // TODO ADD SEARCH
        return null;
    }
}
