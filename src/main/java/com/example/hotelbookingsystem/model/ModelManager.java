package com.example.hotelbookingsystem.model;

import com.example.hotelbookingsystem.dao.StaffTable;
import com.example.hotelbookingsystem.model.list.BookingList;
import com.example.hotelbookingsystem.model.list.GuestList;
import com.example.hotelbookingsystem.model.list.RoomList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ModelManager implements Model{

    RoomList roomList;
    BookingList bookingList;
    GuestList guestList;
    StaffTable staffTable;

    private Staff staff;

    public ModelManager() throws SQLException {
        roomList = RoomList.getInstance();
        bookingList = BookingList.getInstance();
        guestList = GuestList.getInstance();
        staffTable = StaffTable.getInstance();
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
    public void addRoom(Room room) throws SQLException {

    }

    @Override
    public void removeRoom(Room room) throws SQLException {

    }

    @Override
    public void editRoom(Room room) throws SQLException {

    }

    @Override
    public void removeGuest(Guest guest) throws SQLException {
        guestList.remove(guest);
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

    @Override
    public Staff getStaff() {
        return staff;
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
    public void addBooking(ArrayList<Guest> guests, Room room, LocalDate dateFrom, LocalDate dateTo) throws SQLException {
        Booking booking = new Booking(guests, dateFrom, dateTo, room, staff);
        bookingList.add(booking);
    }
    @Override
    public void removeBooking(Booking booking) throws SQLException {
        bookingList.remove(booking);
    }
    @Override
    public void editBooking(Booking booking) throws SQLException {
        bookingList.update(booking);
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
}
