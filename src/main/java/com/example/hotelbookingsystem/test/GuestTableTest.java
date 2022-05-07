package com.example.hotelbookingsystem.test;

import com.example.hotelbookingsystem.dao.GuestTable;
import com.example.hotelbookingsystem.model.Address;
import com.example.hotelbookingsystem.model.Guest;

import java.sql.SQLException;
import java.util.ArrayList;


public class GuestTableTest {

    private static GuestTable guestTable;

    public static void main(String[] args) throws SQLException {
        guestTable = GuestTable.getInstance();
        testDelete("882933");
    }

    public static void testSelect(String guestNumber) throws SQLException {
        System.out.println(guestTable.select(guestNumber));
    }
    public static void testSelectAll() throws SQLException {
        System.out.println(guestTable.selectAll());
    }

    public static void testInsert() throws SQLException {
        Guest guest = new Guest("Sherlock","Holmes", new Address("London", "Baker Street", "221B", "NW1"), "+441632960153", "sherlock@holmes.uk", "882933");
        guestTable.insert(guest);
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

