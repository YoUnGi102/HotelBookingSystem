package com.example.hotelbookingsystem.dao;

import com.example.hotelbookingsystem.model.Room;

import java.sql.SQLException;
import java.util.List;

public interface RoomDAO {

    void insert(Room room) throws SQLException;
    Room select(int roomNumber) throws SQLException;
    void update(Room room) throws SQLException;
    void delete(Room room) throws SQLException;

    List<Room> getAllRooms() throws SQLException;
    List<Room> getAllAvailableRooms() throws SQLException;

}

/*

    CREATE TABLE room(
        room_number SMALLINT PRIMARY KEY NOT NULL ,
        size SMALLINT NOT NULL,
        floor SMALLINT NOT NULL,
        quality SMALLINT NOT NULL
    );

*/
