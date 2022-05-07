package com.example.hotelbookingsystem.dao;

import com.example.hotelbookingsystem.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
            statement.executeUpdate();
        }
    }
    @Override
    public void insertMany(ArrayList<Room> rooms) throws SQLException {

        StringBuilder sql = new StringBuilder("INSERT INTO " + SCHEMA + "." + TABLE_NAME +
                "(" + ROOM_NUMBER + ", " + SIZE + ", " + FLOOR + ", " + QUALITY + ") VALUES ");

        for (int i = 0; i < rooms.size(); i++) {
            if(i == rooms.size()-1)
                sql.append("(?, ?, ?, ?);");
            else
                sql.append("(?, ?, ?, ?),");
        }

        try(Connection connection = databaseConnection.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
            int counter = 0;
            for (Room room : rooms) {
                statement.setInt(counter * 4 + 1, room.getRoomNumber());
                statement.setInt(counter * 4 + 2, room.getRoomSize());
                statement.setInt(counter * 4 + 3, room.getFloor());
                statement.setInt(counter * 4 + 4, room.getQuality());
                counter++;
            }
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
    public ArrayList<Room> selectAll() throws SQLException {
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM "+SCHEMA+"."+TABLE_NAME);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Room> rooms = new ArrayList<>();
            while (resultSet.next()) {
                int number = resultSet.getInt(ROOM_NUMBER);
                int size = resultSet.getInt(SIZE);
                int floor = resultSet.getInt(FLOOR);
                int quality = resultSet.getInt(QUALITY);
                rooms.add(new Room(number, size, floor, quality));
            }
            return rooms;
        }
    }
    // TODO DO AFTER FINISHING BOOKING DAO
    @Override
    public ArrayList<Room> selectAllAvailable() throws SQLException {
        return null;
    }

    @Override
    public void update(Room room) throws SQLException {
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE "+SCHEMA+"."+TABLE_NAME+" SET "+SIZE+" = ?, "+QUALITY+" = ? WHERE "+ROOM_NUMBER+" = ?");
            statement.setInt(1, room.getRoomSize());
            statement.setInt(2, room.getQuality());
            statement.setInt(3, room.getRoomNumber());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Room room) throws SQLException {
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM "+SCHEMA+"."+TABLE_NAME+" WHERE "+ROOM_NUMBER+" = ?");
            statement.setInt(1, room.getRoomNumber());
            statement.executeUpdate();
        }
    }
    @Override
    public void deleteMany(ArrayList<Room> rooms) throws SQLException {

        StringBuilder sql = new StringBuilder("DELETE FROM " + SCHEMA + "." + TABLE_NAME + " WHERE " + ROOM_NUMBER + " IN (");
        for (int i = 0; i < rooms.size(); i++) {
            if(i != rooms.size()-1)
                sql.append(rooms.get(i).getRoomNumber()).append(", ");
            else
                sql.append(rooms.get(i).getRoomNumber()).append(")");
        }

        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
        }

    }


}
