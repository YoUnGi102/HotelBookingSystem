package com.example.hotelbookingsystem.model;

import com.example.hotelbookingsystem.dao.BookingTable;
import com.example.hotelbookingsystem.dao.ReceptionistTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ModelManager implements Model{

    RoomList roomList;

    private ObservableList<Guest> guestList;
    private ObservableList<Room> rooms;
    private ObservableList<Booking> bookingList;

    private Receptionist receptionist;

    public ModelManager() throws SQLException {

        roomList = RoomList.getInstance();

        ReceptionistTable receptionistTable = ReceptionistTable.getInstance();
        receptionist = receptionistTable.select("rec001");

        guestList = FXCollections.observableArrayList();
        rooms = FXCollections.observableArrayList();
        bookingList = FXCollections.observableArrayList();

        // TODO REMOVE TEST CODE
        guestList.add(new Guest("Sherlock","Holmes", new Address("London", "Baker Street", "221B", "NW1"), "+441632960153", "sherlock@holmes.uk", "882933" ));
        guestList.add(new Guest("John","Watson", new Address("London", "Baker Street", "221B", "NW1"), "+442334962334", "john@watson.uk", "335993" ));

        rooms.add(new Room(101, 4, 1, 2));
        rooms.add(new Room(102, 4, 1, 2));
        rooms.add(new Room(103, 3, 1, 2));
        rooms.add(new Room(104, 3, 1, 2));
        rooms.add(new Room(201, 2, 2, 2));
        rooms.add(new Room(202, 2, 2, 2));
        rooms.add(new Room(203, 4, 2, 2));
        rooms.add(new Room(301, 4, 3, 2));
        rooms.add(new Room(302, 3, 3, 2));
        rooms.add(new Room( 303, 3,3, 2));

        ArrayList<Guest> guests = new ArrayList<>();
        guests.add(guestList.get(0));
        guests.add(guestList.get(1));
        Room room = rooms.get(4);
        Receptionist receptionist = new Receptionist("rec001", "Jane", "Dove", "Pa$$w0rd.+", "rec001@hbooking.com", "+4512893245", new Address("Horsens", "Emil Molesgade", "17A", "8700"));
        bookingList.add(new Booking(guests, LocalDate.of(2022, 5, 10), LocalDate.of(2022, 5, 15), room,receptionist));
        bookingList.add(new Booking(guests, LocalDate.of(2022, 5, 10), LocalDate.of(2022, 5, 15), rooms.get(5), receptionist));

    }

    @Override
    public void addBooking(ArrayList<Guest> guests, Room room, LocalDate dateFrom, LocalDate dateTo) {
        Booking booking = new Booking(guests, dateFrom, dateTo, room, receptionist);
        BookingTable bookingTable = BookingTable.getInstance();
        try {
            bookingTable.insert(booking);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        return searchedRooms;
    }

    // TODO REMOVE

    @Override
    public ObservableList<Room> showALlBookedRooms() {
        ObservableList<Room> showAllRooms = FXCollections.observableArrayList();
        for (Room room: rooms) {

        }
        return showAllRooms;
    }
    public ObservableList<Room> showALlAvailableRooms() {
        ObservableList<Room> showAllRooms = FXCollections.observableArrayList();
        for (Room room: rooms) {

        }
        return showAllRooms;
    }
    public ObservableList<Room> showALlOutOfOrderRooms() {
        ObservableList<Room> showAllRooms = FXCollections.observableArrayList();
        for (Room room: rooms) {

        }
        return showAllRooms;
    }

    @Override
    public ObservableList<Booking> searchBookings(String phoneNumber, String email, int roomNumber, LocalDate dateFrom, LocalDate dateTo) {
        ObservableList<Booking> searchedBookings = FXCollections.observableArrayList();
        for (Booking booking : bookingList) {
            if(!phoneNumber.equals("") && !booking.getPhoneNumber().contains(phoneNumber))
                continue;
            if(!email.equals("") && !booking.getEmail().contains(email))
                continue;
            if(roomNumber != -1 && booking.getRoom().getRoomNumber() != roomNumber)
                continue;
            if(dateFrom != null && booking.getDateFrom().isAfter(dateFrom))
                continue;
            if(dateTo != null && booking.getDateTo().isBefore(dateTo))
                continue;
            searchedBookings.add(booking);
        }
        return searchedBookings;
    }
}
