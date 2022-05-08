package com.example.hotelbookingsystem.dao;

import com.example.hotelbookingsystem.model.Address;
import com.example.hotelbookingsystem.model.Guest;
import com.example.hotelbookingsystem.model.Receptionist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.example.hotelbookingsystem.dao.DatabaseConnection.SCHEMA;

public class ReceptionistTable implements ReceptionistDAO {

    // TABLE NAME
    public static final String TABLE_NAME = "receptionist";

    // COLUMNS
    public static final String USERNAME = "username";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String ADDRESS = "address_id";

    private static ReceptionistTable instance;
    private final DatabaseConnection databaseConnection;

    private final AddressTable addressTable;

    private ReceptionistTable(){
        databaseConnection = DatabaseConnection.getInstance();
        addressTable = AddressTable.getInstance();
    }
    public static synchronized ReceptionistTable getInstance(){
        if (instance == null) {
            instance = new ReceptionistTable();
        }
        return instance;
    }

    @Override
    public void insert(Receptionist receptionist) throws SQLException {

        setAddress(receptionist);

        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO " + SCHEMA + "." + TABLE_NAME +
                            "("+USERNAME+", "+FIRST_NAME+", "+LAST_NAME+", "+ PASSWORD +", "+ EMAIL+ ", " + PHONE_NUMBER + ", " + ADDRESS + ")" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, receptionist.getUsername());
            statement.setString(2, receptionist.getFirstName());
            statement.setString(3, receptionist.getLastName());
            statement.setString(4, receptionist.getPassword());
            statement.setString(5, receptionist.getEmail());
            statement.setString(6, receptionist.getPhoneNumber());
            statement.setInt(7, receptionist.getAddress().getAddressId());
            statement.executeUpdate();
        }
    }

    @Override
    public Receptionist select(String searchUsername) throws SQLException {
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM "  + SCHEMA + "." + TABLE_NAME + " WHERE " + USERNAME + " = ?");
            statement.setString(1,  searchUsername);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                String username = resultSet.getString(USERNAME);
                String firstName = resultSet.getString(FIRST_NAME);
                String lastName = resultSet.getString(LAST_NAME);
                String password = resultSet.getString(PASSWORD);
                String email = resultSet.getString(EMAIL);
                String phoneNumber = resultSet.getString(PHONE_NUMBER);
                Address address = addressTable.select(resultSet.getInt(ADDRESS));
                return new Receptionist(username, firstName, lastName, password, email, phoneNumber, address);
            } else {
                return null;
            }
        }
    }
    @Override
    public ArrayList<Receptionist> selectAll() throws SQLException {
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM "+SCHEMA+"."+TABLE_NAME);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Receptionist> receptionists = new ArrayList<>();
            while (resultSet.next()) {
                String username = resultSet.getString(USERNAME);
                String firstName = resultSet.getString(FIRST_NAME);
                String lastName = resultSet.getString(LAST_NAME);
                String password = resultSet.getString(PASSWORD);
                String email = resultSet.getString(EMAIL);
                String phoneNumber = resultSet.getString(PHONE_NUMBER);
                Address address = addressTable.select(resultSet.getInt(ADDRESS));
                receptionists.add(new Receptionist(username, firstName, lastName, password, email, phoneNumber, address));
            }
            return receptionists;
        }
    }

    @Override
    public void update(Receptionist receptionist) throws SQLException {
        setAddress(receptionist);
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE "+SCHEMA+"."+TABLE_NAME+" SET " +
                    FIRST_NAME+" = ?, "+LAST_NAME+" = ?, " + PASSWORD+" = ?, " +EMAIL+" = ?, "+PHONE_NUMBER+" = ?, "+ADDRESS+" = ? WHERE "+USERNAME+" = ?");
            statement.setString(1, receptionist.getFirstName());
            statement.setString(2, receptionist.getLastName());
            statement.setString(3, receptionist.getPassword());
            statement.setString(4, receptionist.getEmail());
            statement.setString(5, receptionist.getPhoneNumber());
            statement.setInt(6, receptionist.getAddress().getAddressId());
            statement.setString(7, receptionist.getUsername());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Receptionist receptionist) throws SQLException {
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM "+SCHEMA+"."+TABLE_NAME+" WHERE "+USERNAME+" = ?");
            statement.setString(1, receptionist.getUsername());
            statement.executeUpdate();
        }
    }

    @Override
    public Receptionist logIn(String logInUsername, String logInPassword) throws SQLException {
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM "  + SCHEMA + "." + TABLE_NAME + " WHERE " + USERNAME + " = ? AND " + PASSWORD + " = ?");
            statement.setString(1,  logInUsername);
            statement.setString(2,  logInPassword);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                String username = resultSet.getString(USERNAME);
                String firstName = resultSet.getString(FIRST_NAME);
                String lastName = resultSet.getString(LAST_NAME);
                String password = resultSet.getString(PASSWORD);
                String email = resultSet.getString(EMAIL);
                String phoneNumber = resultSet.getString(PHONE_NUMBER);
                Address address = addressTable.select(resultSet.getInt(ADDRESS));
                return new Receptionist(username, firstName, lastName, password, email, phoneNumber, address);
            } else {
                return null;
            }
        }
    }

    @Override
    public Address setAddress(Receptionist receptionist) throws SQLException {
        Address address = addressTable.select(receptionist.getAddress());
        if(address == null){
            addressTable.insert(receptionist.getAddress());
            address = addressTable.select(receptionist.getAddress());
        }
        receptionist.setAddress(address);
        return address;
    }
}
