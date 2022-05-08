package com.example.hotelbookingsystem.test;

import com.example.hotelbookingsystem.dao.RoomTable;
import com.example.hotelbookingsystem.model.Room;

import java.sql.SQLException;
import java.util.ArrayList;


public class RoomTableTest {

    private static RoomTable roomTable;

    public static void main(String[] args) throws SQLException {
        roomTable = RoomTable.getInstance();
        testInsert();
    }

    public static void testSelect(int roomNumber) throws SQLException {
        System.out.println(roomTable.select(roomNumber));
    }
    public static void testSelectAll() throws SQLException {
        System.out.println(roomTable.selectAll());
    }

    public static void testInsert() throws SQLException {
        Room room = new Room(102, 2, 1, 1);
        roomTable.insert(room);
    }

    public static void testInsertMultiple() throws SQLException {
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room(105, 2, 1, 1));
        rooms.add(new Room(106, 2, 1, 1));
        rooms.add(new Room(107, 3, 1, 2));
        rooms.add(new Room(108, 3, 1, 2));
        rooms.add(new Room(205, 4, 2, 3));
        rooms.add(new Room(206, 4, 2, 3));
        rooms.add(new Room(207, 3, 2, 2));
        rooms.add(new Room(208, 3, 2, 2));
        rooms.add(new Room(305, 2, 3, 1));
        rooms.add(new Room(306, 2, 3, 1));
        rooms.add(new Room(307, 1, 3, 4));
        rooms.add(new Room(308, 1, 3, 4));
        roomTable.insertMany(rooms);

    }

    public static void testUpdate() throws SQLException {
        System.out.println("TEST UPDATE");
        Room room = roomTable.select(101);
        System.out.println(room);
        room.setRoomSize(4);
        room.setQuality(2);
        roomTable.update(room);
        System.out.println(roomTable.select(101));
    }

    public static void testDelete(int roomNumber) throws SQLException {
        System.out.println("TEST DELETE");
        Room room = roomTable.select(roomNumber);
        roomTable.delete(room);
    }

    public static void testDeleteMultiple() throws SQLException {
        ArrayList<Room> rooms = roomTable.selectAll();
        roomTable.deleteMany(rooms);
    }



}

