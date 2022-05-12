package com.example.hotelbookingsystem.test;

import com.example.hotelbookingsystem.dao.AddressTable;
import com.example.hotelbookingsystem.model.Address;

import java.sql.SQLException;
import java.util.ArrayList;


public class AddressTableTest {

    private static AddressTable addressTable;

    public static void main(String[] args) throws SQLException {
        addressTable = AddressTable.getInstance();
    }

    public static void testSelect(int addressNumber) throws SQLException {
        System.out.println(addressTable.select(addressNumber));
    }
//    public static void testSelect() throws SQLException {
//        Address address = new Address("Horsens", "Vimmelskaftet", "8A", "8700");
//        System.out.println(addressTable.select(address));
//    }
//
//    public static void testInsert() throws SQLException {
//        Address address = new Address("Horsens", "Vimmelskaftet", "8A", "8700");
//        addressTable.insert(address);
//        System.out.println("INSERT ADDRESS - " + address.getAddressId());
//    }

    public static void testUpdate() throws SQLException {
        System.out.println("TEST UPDATE");
        Address address = addressTable.select(1);
        System.out.println(address);
        address.setCity("Aarhus");
        address.setStreet("Strandehavn");
        address.setHouseNumber("17");
        address.setPostalCode("7000");
        addressTable.update(address);
        System.out.println(addressTable.select(1));
    }

    public static void testDelete(int addressNumber) throws SQLException {
        System.out.println("TEST DELETE");
        Address address = addressTable.select(addressNumber);
        addressTable.delete(address);
    }




}

