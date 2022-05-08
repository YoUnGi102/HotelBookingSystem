package com.example.hotelbookingsystem.model;

import com.example.hotelbookingsystem.dao.RoomDAO;
import com.example.hotelbookingsystem.dao.RoomTable;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class RoomList {

    private ObservableList<Room> rooms;
    RoomDAO roomTable;

    private static RoomList instance;

    private RoomList() throws SQLException {
        roomTable = RoomTable.getInstance();
        refresh();
    }
    public static synchronized RoomList getInstance() throws SQLException {
        if(instance == null)
            instance = new RoomList();
        return instance;
    }

    private void refresh() throws SQLException {
        rooms = roomTable.selectAll();
    }

    public void add(Room room) throws SQLException {
        roomTable.insert(room);
        refresh();
    }
    public void remove(Room room) throws SQLException {
        roomTable.delete(room);
        refresh();
    }
    public Room get(int roomNumber) throws SQLException {
        for (Room room : rooms) {
            if(room.getRoomNumber() == roomNumber)
                return room;
        }
        return null;
    }
    public ObservableList<Room> getAll() throws SQLException {
        return rooms;
    }
    public void update(Room room) throws SQLException {
        roomTable.update(room);
        refresh();
    }

}
