package com.example.hotelbookingsystem.model;

import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public interface Model {

    ObservableList<Guest> searchGuests(String firstName, String lastName, String phoneNumber, String passportNumber, String email);


//    int getRoomCount();
//    ArrayList<Room> getRooms();
//    User getCurrentUser();
//    void deleteRoom(Room room);
//    boolean logInUser(String name, String password) throws IOException;
//    void addPropertyChangeListener(String event, PropertyChangeListener listener);
//    void removePropertyChangeListener(String event, PropertyChangeListener listener);

    public ObservableList<Room> showALlBookedRooms();

    public ObservableList<Room> showALlAvailableRooms();

    public ObservableList<Room> showALlOutOfOrderRooms();

    public ObservableList<Booking> searchBookings(String phoneNumber, String email, LocalDateTime dateFrom, LocalDateTime dateTo);
}


