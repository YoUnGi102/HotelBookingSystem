package com.example.hotelbookingsystem.dao;

import com.example.hotelbookingsystem.model.Room;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;

public interface RoomDAO {

    void insert(Room room) throws SQLException;
    void insertMany(ObservableList<Room> rooms) throws SQLException;

    Room select(int roomNumber) throws SQLException;
    ObservableList<Room> selectAll() throws SQLException;
    ObservableList<Room> selectAllSearched(LocalDate to) throws SQLException;
    ObservableList<Room> selectAllAvailable() throws SQLException;

    void update(Room room) throws SQLException;

    void delete(Room room) throws SQLException;
    void deleteMany(ObservableList<Room> rooms) throws SQLException;

}

/*
    CREATE TABLE room(
        room_number SMALLINT PRIMARY KEY NOT NULL ,
        size SMALLINT NOT NULL,
        floor SMALLINT NOT NULL,
        quality SMALLINT NOT NULL
    );
*/
