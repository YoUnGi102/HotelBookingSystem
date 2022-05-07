package com.example.hotelbookingsystem.test;

import com.example.hotelbookingsystem.dao.RoomTable;
import com.example.hotelbookingsystem.model.Room;

import java.sql.SQLException;


public class RoomTableTest {

    public static void main(String[] args) throws SQLException {

        RoomTable roomTable = RoomTable.getInstance();

        // SELECT
        System.out.println(roomTable.select(101));


        // INSERT
//        Room room = new Room(101, 2, 1, 1);
//        roomTable.insert(room);

    }

}

