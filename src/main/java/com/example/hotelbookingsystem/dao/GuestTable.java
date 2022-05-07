package com.example.hotelbookingsystem.dao;

import com.example.hotelbookingsystem.model.Address;
import com.example.hotelbookingsystem.model.Guest;
import com.example.hotelbookingsystem.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.example.hotelbookingsystem.dao.DatabaseConnection.SCHEMA;

public class GuestTable implements GuestDAO {

    // TABLE NAME
    private static final String TABLE_NAME = "guest";

    // COLUMNS
    private static final String PASSPORT_NUMBER = "passport_number";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String ADDRESS = "address_id";

    private static GuestTable instance;
    private final DatabaseConnection databaseConnection;

    private final AddressTable addressTable;

    private GuestTable(){
        databaseConnection = DatabaseConnection.getInstance();
        addressTable = AddressTable.getInstance();
    }
    public static synchronized GuestTable getInstance(){
        if (instance == null) {
            instance = new GuestTable();
        }
        return instance;
    }

    @Override
    public void insert(Guest guest) throws SQLException {

        Address address = setAddress(guest);

        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO " + SCHEMA + "." + TABLE_NAME + "("+PASSPORT_NUMBER+", "+FIRST_NAME+", "+LAST_NAME+", "+EMAIL+ ", " + PHONE_NUMBER + ", " + ADDRESS + ") VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, guest.getPassportNumber());
            statement.setString(2, guest.getFirstName());
            statement.setString(3, guest.getLastName());
            statement.setString(4, guest.getEmail());
            statement.setString(5, guest.getPhoneNumber());
            statement.setInt(6, address.getAddressId());
            statement.executeUpdate();
        }
    }

    @Override
    public Address setAddress(Guest guest) throws SQLException {
        Address address = addressTable.select(guest.getAddress());
        if(address == null){
            addressTable.insert(guest.getAddress());
            address = addressTable.select(guest.getAddress());
            guest.setAddress(address);
        }
        return address;
    }

    @Override
    public void insertMany(ArrayList<Guest> guests) throws SQLException {

    }

    @Override
    public Guest select(String searchPassportNumber) throws SQLException {
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM "  + SCHEMA + "." + TABLE_NAME + " WHERE " + PASSPORT_NUMBER + " = ?");
            statement.setString(1,  searchPassportNumber);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                String passportNumber = resultSet.getString(PASSPORT_NUMBER);
                String firstName = resultSet.getString(FIRST_NAME);
                String lastName = resultSet.getString(LAST_NAME);
                String email = resultSet.getString(EMAIL);
                String phoneNumber = resultSet.getString(PHONE_NUMBER);
                Address address = addressTable.select(resultSet.getInt(ADDRESS));
                return new Guest(firstName, lastName, address, phoneNumber, email, passportNumber);
            } else {
                return null;
            }
        }
    }

    @Override
    public ArrayList<Guest> selectAll() throws SQLException {
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM "+SCHEMA+"."+TABLE_NAME);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Guest> guests = new ArrayList<>();
            while (resultSet.next()) {
                String passportNumber = resultSet.getString(PASSPORT_NUMBER);
                String firstName = resultSet.getString(FIRST_NAME);
                String lastName = resultSet.getString(LAST_NAME);
                String email = resultSet.getString(EMAIL);
                String phoneNumber = resultSet.getString(PHONE_NUMBER);
                Address address = addressTable.select(resultSet.getInt(ADDRESS));
                guests.add(new Guest(firstName, lastName, address, phoneNumber, email, passportNumber));
            }
            return guests;
        }
    }

    // TODO WHEN BOOKING TABLE FINISHED
    @Override
    public ArrayList<Guest> selectGuestsInRoom(int roomNumber) throws SQLException {
        return null;
    }

    @Override
    public void update(Guest guest) throws SQLException {
        setAddress(guest);
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE "+SCHEMA+"."+TABLE_NAME+" SET " +
                    FIRST_NAME+" = ?, "+LAST_NAME+" = ?, "+EMAIL+" = ?, "+PHONE_NUMBER+" = ?, "+ADDRESS+" = ? WHERE "+PASSPORT_NUMBER+" = ?");
            statement.setString(1, guest.getFirstName());
            statement.setString(2, guest.getLastName());
            statement.setString(3, guest.getEmail());
            statement.setString(4, guest.getPhoneNumber());
            statement.setInt(5, guest.getAddress().getAddressId());
            statement.setString(6, guest.getPassportNumber());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Guest guest) throws SQLException {
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM "+SCHEMA+"."+TABLE_NAME+" WHERE "+PASSPORT_NUMBER+" = ?");
            statement.setString(1, guest.getPassportNumber());
            statement.executeUpdate();
        }
    }


}
