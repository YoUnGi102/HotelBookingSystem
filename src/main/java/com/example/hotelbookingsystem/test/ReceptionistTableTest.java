package com.example.hotelbookingsystem.test;

import com.example.hotelbookingsystem.dao.ReceptionistTable;
import com.example.hotelbookingsystem.model.Address;
import com.example.hotelbookingsystem.model.Receptionist;

import java.sql.SQLException;

public class ReceptionistTableTest {

    private static final ReceptionistTable receptionistTable = ReceptionistTable.getInstance();

    public static void main(String[] args) throws SQLException {
        testDelete();
    }

    public static void testInsert() throws SQLException {
        Receptionist receptionist = new Receptionist("rec002", "Jim", "Olsen", "PassW0Rd.+1", "rec002@hbooking.com", "+4500235410", new Address("Odense", "Horsevej", "29", "9300"));
        receptionistTable.insert(receptionist);
    }

    public static void testSelect() throws SQLException {
        Receptionist receptionist = receptionistTable.select("rec002");
        System.out.println(receptionist);
    }
    public static void testSelectAll() throws SQLException {
        System.out.println(receptionistTable.selectAll());
    }

    public static void testUpdate() throws SQLException {
        Receptionist receptionist = receptionistTable.select("rec002");
        receptionist.setEmail("rec002@hbooking.dk");
        receptionist.setFirstName("Oscar");
        receptionist.setLastName("Wild");
        receptionist.setPassword("drowssaP");
        receptionist.setPhoneNumber("+4506060606");
        receptionist.setAddress(new Address("Coppenhagen", "Copenhagenvej", "19", "1000"));
        receptionistTable.update(receptionist);
    }

    public static void testLogin() throws SQLException {
        System.out.println(receptionistTable.logIn("rec002", "drowssaP"));
    }

    public static void testDelete() throws SQLException {
        Receptionist receptionist = receptionistTable.select("rec002");
        receptionistTable.delete(receptionist);
    }


}
