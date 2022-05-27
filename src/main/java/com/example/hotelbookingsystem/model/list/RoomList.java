package com.example.hotelbookingsystem.model.list;

import com.example.hotelbookingsystem.dao.RoomDAO;
import com.example.hotelbookingsystem.dao.RoomTable;
import com.example.hotelbookingsystem.model.Room;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;

public class RoomList {

    RoomDAO roomTable;

    private static RoomList instance;

    private RoomList() {
        roomTable = RoomTable.getInstance();
    }
    public static synchronized RoomList getInstance() throws SQLException {
        if(instance == null)
            instance = new RoomList();
        return instance;
    }

    public void add(Room room) throws SQLException {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    roomTable.insert(room);
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    public void remove(Room room) throws SQLException {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    roomTable.delete(room);
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    public Room get(int roomNumber) throws SQLException {
        return roomTable.select(roomNumber);
    }
    public ObservableList<Room> getAll() throws SQLException {
        return roomTable.selectAll();
    }
    public void update(Room room) throws SQLException {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    roomTable.update(room);
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public ObservableList<Room> selectAllSearched(LocalDate to) throws SQLException {
        return roomTable.selectAllSearched(to);
    }

}
