package com.example.hotelbookingsystem.dao;

import com.example.hotelbookingsystem.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.example.hotelbookingsystem.dao.DatabaseConnection.SCHEMA;

public class RoomTable implements RoomDAO{

    // TABLE NAME
    private static final String TABLE_NAME = "room";

    // COLUMNS
    private static final String ROOM_NUMBER = "room_number";
    private static final String SIZE = "size";
    private static final String QUALITY = "quality";
    private static final String FLOOR = "floor";

    private static RoomTable instance;
    private final DatabaseConnection databaseConnection;

    private RoomTable(){
        databaseConnection = DatabaseConnection.getInstance();
    }

    public static synchronized RoomTable getInstance(){
        if (instance == null) {
            instance = new RoomTable();
        }
        return instance;
    }

    @Override
    public void insert(Room room) throws SQLException {
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO " + SCHEMA + "." + TABLE_NAME + "("+ROOM_NUMBER+", "+SIZE+", "+FLOOR+", "+QUALITY+") VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, room.getRoomNumber());
            statement.setInt(2, room.getRoomSize());
            statement.setInt(3, room.getFloor());
            statement.setInt(4, room.getQuality());
            System.out.println(statement);
            statement.executeUpdate();
        }
    }

    @Override
    public Room select(int roomNumber) throws SQLException {
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM "  + SCHEMA + "." + TABLE_NAME + " WHERE " + ROOM_NUMBER + " = ?");
            statement.setInt(1,  roomNumber);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                int number = resultSet.getInt(ROOM_NUMBER);
                int size = resultSet.getInt(SIZE);
                int floor = resultSet.getInt(FLOOR);
                int quality = resultSet.getInt(QUALITY);
                return new Room(number,size,floor,quality);
            } else {
                return null;
            }
        }
    }

    @Override
    public void update(Room room) throws SQLException {

    }

    @Override
    public void delete(Room room) throws SQLException {

    }

    @Override
    public List<Room> getAllRooms() throws SQLException {
        return null;
    }

    @Override
    public List<Room> getAllAvailableRooms() throws SQLException {
        return null;
    }
}
