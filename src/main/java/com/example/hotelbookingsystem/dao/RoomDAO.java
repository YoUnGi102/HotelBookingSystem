package com.example.hotelbookingsystem.dao;

import com.example.hotelbookingsystem.model.Room;

import java.sql.SQLException;
import java.util.List;

public interface RoomDAO
{
    Room create(Room room) throws SQLException;
    Room readById(int id) throws SQLException;
    Room readByNumber(int searchNumber) throws SQLException;
    List<Room> getAllRooms() throws SQLException;
    List<Room> getAllRoomsByStatus(String status) throws SQLException;
    void update(Room room) throws SQLException;
    void delete(Room room) throws SQLException;
}

//    create Schema rooms;
//        set SCHEMA 'rooms';
//
//        CREATE TABLE Rooms (
//        id SERIAL PRIMARY KEY,
//        number int NOT NULL,
//        size int not null,
//        quality int not null check ( quality<=5 and  quality >= 1),
//        status varchar(50) not null  check ( status in ('Free','Broken','Busy') ),
//        floor int not null
//
//        ) ;
