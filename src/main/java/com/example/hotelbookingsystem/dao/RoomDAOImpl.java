package com.example.hotelbookingsystem.dao;

import com.example.hotelbookingsystem.model.Room;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import java.sql.*;
import java.util.ArrayList;

public class RoomDAOImpl implements RoomDAO
{
    private static RoomDAOImpl instance;

    private RoomDAOImpl() throws SQLException
    {
        //DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized RoomDAOImpl getInstance() throws SQLException
    {
        if (instance == null) {
            instance = new RoomDAOImpl();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=rooms", "postgres", "1234");
    }

    @Override
    public Room create(Room room) throws SQLException
    {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Rooms(number ,size ,quality, status,floor) VALUES (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, Integer.valueOf(room.getNumber()));
            statement.setInt(2, room.getSize());
            statement.setInt(3, room.getQuality());
            statement.setString(4, room.getStatus());
            statement.setInt(5, room.getFloor());
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                return new Room(keys.getInt(1), room.getNumber(), room.getSize(),room.getFloor(),room.getQuality(),room.getStatus());
            } else {
                throw new SQLException("No keys generated");
            }
        }
    }

    @Override
    public Room readById(int id) throws SQLException
    {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Rooms WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int number = resultSet.getInt("number");
                int size = resultSet.getInt("size");
                int floor = resultSet.getInt("floor");
                int quality = resultSet.getInt("quality");
                int id2 = resultSet.getInt("id");
                String status = resultSet.getString("status");
                return new Room(id2, number,size,floor,quality,status);
            } else {
                return null;
            }
        }
    }

    @Override
    public Room readByNumber(int searchNumber) throws SQLException
    {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Rooms WHERE number = ?");
            statement.setInt(1,  searchNumber);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                int number = resultSet.getInt("number");
                int size = resultSet.getInt("size");
                int floor = resultSet.getInt("floor");
                int quality = resultSet.getInt("quality");
                int id2 = resultSet.getInt("id");
                String status = resultSet.getString("status");
                return new Room(id2, number,size,floor,quality,status);
            } else {
                return null;
            }
        }
    }

    @Override
    public List<Room> getAllRooms() throws SQLException
    {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Rooms");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Room> rooms = new ArrayList<>();
            while (resultSet.next())
            {
                int number = resultSet.getInt("number");
                int size = resultSet.getInt("size");
                int floor = resultSet.getInt("floor");
                int quality = resultSet.getInt("quality");
                int id2 = resultSet.getInt("id");
                String status = resultSet.getString("status");
                rooms.add(new Room(id2, number, size, floor, quality, status));
            }
            return rooms;
        }

    }

    @Override
    public List<Room> getAllRoomsByStatus(String status) throws SQLException
    {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Rooms where status = ?");
            statement.setString(1,  status);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Room> rooms = new ArrayList<>();
            while (resultSet.next())
            {
                int number = resultSet.getInt("number");
                int size = resultSet.getInt("size");
                int floor = resultSet.getInt("floor");
                int quality = resultSet.getInt("quality");
                int id2 = resultSet.getInt("id");
                String status1 = resultSet.getString("status");
                rooms.add(new Room(id2, number, size, floor, quality, status1));
            }
            return rooms;
        }
    }

    @Override
    public void update(Room room) throws SQLException
    {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE Rooms SET number = ?,size = ?, quality = ?, status = ?  WHERE id = ?");
            statement.setInt(1, room.getNumber());
            statement.setInt(2, room.getSize());
            statement.setInt(3, room.getQuality());
            statement.setString(4, room.getStatus());
            statement.setInt(5, room.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Room room) throws SQLException
    {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Rooms WHERE id = ?");
            statement.setInt(1, room.getId());
            statement.executeUpdate();
        }
    }
}
