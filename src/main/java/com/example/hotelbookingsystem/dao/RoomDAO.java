package com.example.hotelbookingsystem.dao;

import com.example.hotelbookingsystem.model.Room;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RoomDAO {

    void insert(Room room) throws SQLException;
    void insertMany(ArrayList<Room> rooms) throws SQLException;

    Room select(int roomNumber) throws SQLException;
    ArrayList<Room> selectAll() throws SQLException;
    ArrayList<Room> selectAllAvailable() throws SQLException;

    void update(Room room) throws SQLException;

    void delete(Room room) throws SQLException;
    void deleteMany(ArrayList<Room> rooms) throws SQLException;

}

/*
    CREATE TABLE room(
        room_number SMALLINT PRIMARY KEY NOT NULL ,
        size SMALLINT NOT NULL,
        floor SMALLINT NOT NULL,
        quality SMALLINT NOT NULL
    );
*/
