package com.example.hotelbookingsystem.test;

import com.example.hotelbookingsystem.dao.BookingTable;
import com.example.hotelbookingsystem.dao.GuestTable;
import com.example.hotelbookingsystem.dao.RoomTable;
import com.example.hotelbookingsystem.model.*;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class BookingTableTest {

    private static final Receptionist RECEPTIONIST = new Receptionist("rec001", "Jane", "Dove", "Pa$$w0rd.+", "rec001@hbooking.com", "+4512893245", new Address("Horsens", "Emil Molesgade", "17A", "8700"));

    private static BookingTable bookingTable;
    private static GuestTable guestTable;
    private static RoomTable roomTable;

    public static void main(String[] args) throws SQLException {
        bookingTable = BookingTable.getInstance();
        guestTable = GuestTable.getInstance();
        roomTable = RoomTable.getInstance();

    }

    public static void testSelect(int bookingId) throws SQLException {
        System.out.println(bookingTable.select(bookingId));
    }
    public static void testSelectAll() throws SQLException {
        System.out.println(bookingTable.selectAll());
    }

    public static void testInsert() throws SQLException {

        ObservableList<Guest> guests = guestTable.selectAll();
        Room room = new Room(101, 2, 1, 2);
        //roomTable.insert(room);
        Receptionist receptionist = new Receptionist("rec001", "Jane", "Dove", "Pa$$w0rd.+", "rec001@hbooking.com", "+4512893245", new Address("Horsens", "Emil Molesgade", "17A", "8700"));
        Booking booking1 = (new Booking(guests, LocalDate.of(2022, 6, 10), LocalDate.of(2022, 6, 15), room,receptionist));
        //bookingList.add(new Booking(guests, LocalDate.of(2022, 5, 10), LocalDate.of(2022, 5, 15), rooms.get(5), receptionist));
        bookingTable.insert(booking1);

    }

    public static void testInsert2() throws SQLException {

        ArrayList<Guest> guests = new ArrayList<>();
        guests.add(guestTable.select("12300933"));
        guests.add(guestTable.select("39283833"));
        guests.add(guestTable.select("89948594"));
        guests.add(guestTable.select("98574895"));

        Room room = roomTable.select(101);

        Booking booking = new Booking(guests, LocalDate.of(2022, 7, 10), LocalDate.of(2022, 7, 17), room, RECEPTIONIST);
        bookingTable.insert(booking);

    }

    public static void testUpdateGuestsInBooking() throws SQLException {

        Booking booking = bookingTable.select(6);

        booking.getGuests().remove(0);

        booking.setRoom(roomTable.select(102));
        booking.setDateFrom(LocalDate.of(2022, 7, 2));
        booking.setDateTo(LocalDate.of(2022, 7, 10));

        System.out.println(booking.getGuests());

        bookingTable.update(booking);
    }

    public static void testUpdate() throws SQLException {

    }

    public static void testDelete() throws SQLException {
        ObservableList<Guest> guests = guestTable.selectAll();
        Room room = new Room(101, 2, 1, 2);
        //roomTable.insert(room);
        Receptionist receptionist = new Receptionist("rec001", "Jane", "Dove", "Pa$$w0rd.+", "rec001@hbooking.com", "+4512893245",
                new Address("Horsens", "Emil Molesgade", "17A", "8700"));
        Booking booking1 = (new Booking(1, guests, LocalDate.of(2022, 5, 10), LocalDate.of(2022, 5, 15), room,receptionist));
        //bookingList.add(new Booking(guests, LocalDate.of(2022, 5, 10), LocalDate.of(2022, 5, 15), rooms.get(5), receptionist));
        bookingTable.delete(booking1);
    }

}
