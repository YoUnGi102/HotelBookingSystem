package com.example.hotelbookingsystem.test;

import com.example.hotelbookingsystem.dao.GuestTable;
import com.example.hotelbookingsystem.model.Address;
import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.model.Guest;

import java.sql.SQLException;
import java.util.ArrayList;

public class GuestTableTest {

    private static GuestTable guestTable;

    public static void main(String[] args) throws SQLException {
        guestTable = GuestTable.getInstance();
        testInsertMany();
    }

    public static void testSelect(String guestNumber) throws SQLException {
        System.out.println(guestTable.select(guestNumber));
    }
    public static void testSelectAll() throws SQLException {
        System.out.println(guestTable.selectAll());
    }

    public static void testSelectFromBooking() throws SQLException {
        System.out.println(guestTable.selectAllInBooking(4));
    }

    public static void testInsert() throws SQLException {

        Guest g1 = new Guest("John", "Doe", new Address("Horsens", "Vejlevej", "18B", "8700"), "+4599191881", "tomas.gres@gmail.com", "12300933");
        Guest g2 = new Guest("Jimmy", "Doe", new Address("Horsens", "Vejlevej", "18B", "8700"), "", "", "39283833");
        Guest g3 = new Guest("Jane", "Doe", new Address("Horsens", "Vejlevej", "18B", "8700"), "", "", "89948594");
        Guest g4 = new Guest("George", "Doe", new Address("Horsens", "Vejlevej", "18B", "8700"), "", "", "98574895");

        guestTable.insert(g1);
        guestTable.insert(g2);
        guestTable.insert(g3);
        guestTable.insert(g4);

    }
    public static void testInsertMany() throws SQLException {

        ArrayList<Guest> guests = new ArrayList<>();
        Guest g1 = new Guest("Adam", "Adams", new Address("Aarhus", "Vejlevej", "18B", "8700"), "+4599191881", "tomas.gres@gmail.com", "98237");
        Guest g2 = new Guest("Boris", "Adams", new Address("Aarhus", "Vejlevej", "18B", "8700"), "", "", "394242");
        Guest g3 = new Guest("Claire", "Adams", new Address("Aarhus", "Vejlevej", "18B", "8700"), "", "", "9384934");
        Guest g4 = new Guest("David", "Adams", new Address("Aarhus", "Vejlevej", "18B", "8700"), "", "", "0293740");
        guests.add(g1);
        guests.add(g2);
        guests.add(g3);
        guests.add(g4);
        guestTable.insertMany(guests);
    }


    public static void testInsertSameAddress() throws SQLException {
        Guest guest = (new Guest("John","Watson", new Address("London", "Baker Street", "221B", "NW1"), "+442334962334", "john@watson.uk", "335993" ));
        guestTable.insert(guest);
    }

    public static void testUpdate() throws SQLException {
        System.out.println("TEST UPDATE");
        Guest guest = guestTable.select("882933");
        System.out.println(guest);
        guest.setFirstName("John");
        guest.setLastName("Doe");
        guestTable.update(guest);
        System.out.println(guestTable.select("882933"));
    }

    public static void testDelete(String guestNumber) throws SQLException {
        System.out.println("TEST DELETE");
        Guest guest = guestTable.select(guestNumber);
        guestTable.delete(guest);
    }

}

