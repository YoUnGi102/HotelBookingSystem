package com.example.hotelbookingsystem.model;

import com.example.hotelbookingsystem.dao.StaffTable;
import com.example.hotelbookingsystem.model.list.BookingList;
import com.example.hotelbookingsystem.model.list.GuestList;
import com.example.hotelbookingsystem.model.list.RoomList;
import com.example.hotelbookingsystem.server.Client;
import com.example.hotelbookingsystem.server.ClientDriver;
import com.example.hotelbookingsystem.server.ServerDriver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ModelManager implements Model, Remote {

    public static final String REFRESH = "refresh";

    private PropertyChangeSupport support;

    private RoomList roomList;
    private BookingList bookingList;
    private GuestList guestList;
    private StaffTable staffTable;

    private Staff staff;

    private ClientDriver client;

    public ModelManager() throws SQLException, NotBoundException, RemoteException {
        roomList = RoomList.getInstance();
        bookingList = BookingList.getInstance();
        guestList = GuestList.getInstance();
        staffTable = StaffTable.getInstance();
        support = new PropertyChangeSupport(this);
        try
        {
            client = new ClientDriver(support);
        } catch (ConnectException e)
        {
            System.out.println("Server is created");
            new Thread(ServerDriver.getInstance()).start();
        }

    }

    @Override
    public void addGuest(Guest guest) throws SQLException {
        guestList.add(guest);
    }
    @Override
    public void editGuest(Guest guest) throws SQLException {
        guestList.update(guest);
    }
    @Override
    public void removeGuest(Guest guest) throws SQLException {
        guestList.remove(guest);
    }
    @Override
    public ObservableList<Guest> searchGuests(String firstName, String lastName, String phoneNumber, String passportNumber, String email) throws SQLException {

        ObservableList<Guest> searchedGuests = FXCollections.observableArrayList();
        for (Guest guest : guestList.getAll()) {
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
    public void addRoom(Room room) throws SQLException, RemoteException {
        roomList.add(room);
        client.sendRefresh();
    }
    @Override
    public void removeRoom(Room room) throws SQLException, RemoteException {
        roomList.remove(room);
        client.sendRefresh();
    }
    @Override
    public void editRoom(Room room) throws SQLException, RemoteException {
        roomList.update(room);
        client.sendRefresh();
    }
    @Override
    public ObservableList<Room> searchRooms(int floor, int size, int quality, LocalDate from, LocalDate to) throws SQLException {
        ObservableList<Room> searchedRooms = FXCollections.observableArrayList();
        for (Room room : roomsByDate(from, to)) {
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

    private ObservableList<Room> roomsByDate(LocalDate from, LocalDate to) throws SQLException {
        ObservableList<Room> roomsSelected = roomList.getAll();

        if (from == null)
            from = LocalDate.now();
        if (to == null)
            to = LocalDate.now().plusYears(1);

        for (Booking booking : bookingList.getAll()) {
            // start1 <= end2 and start2 <= end1
            if(booking.getDateFrom().isBefore(to) && from.isBefore(booking.getDateTo())){
                roomsSelected.removeIf(room -> room.getRoomNumber() == booking.getRoom().getRoomNumber());
            }
        }

        return roomsSelected;

    }
    @Override
    public ObservableList<Booking> searchBookings(String phoneNumber, String email, int roomNumber, LocalDate dateFrom, LocalDate dateTo) throws SQLException {
        ObservableList<Booking> searchedBookings = FXCollections.observableArrayList();
        for (Booking booking : bookingList.getAll()) {
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

    @Override
    public void addBooking(ArrayList<Guest> guests, Room room, LocalDate dateFrom, LocalDate dateTo) throws SQLException, RemoteException {
        Booking booking = new Booking(guests, dateFrom, dateTo, room, staff);
        bookingList.add(booking);
        client.sendRefresh();
    }
    @Override
    public void removeBooking(Booking booking) throws SQLException, RemoteException {
        bookingList.remove(booking);
        client.sendRefresh();
    }
    @Override
    public void editBooking(Booking booking) throws SQLException, RemoteException {
        bookingList.update(booking);
        client.sendRefresh();
    }

    @Override
    public Staff getStaff() {
        return staff;
    }

    @Override
    public void login(String username, String password) throws SQLException, IllegalAccessException {
        Staff staff = staffTable.logIn(username, password);
        if(staff == null)
            throw new IllegalAccessException("Invalid username or password!");
        else
            this.staff = staff;
    }
    @Override
    public void logOff() {
        staff = null;
    }

    @Override
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(propertyName, listener);
    }
    @Override
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(propertyName, listener);
    }



}
