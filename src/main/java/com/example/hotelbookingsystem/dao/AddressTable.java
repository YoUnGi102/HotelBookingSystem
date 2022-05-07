package com.example.hotelbookingsystem.dao;

import com.example.hotelbookingsystem.model.Address;
import com.example.hotelbookingsystem.model.Guest;
import com.example.hotelbookingsystem.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.hotelbookingsystem.dao.DatabaseConnection.SCHEMA;

public class AddressTable implements AddressDAO{

    // TABLE NAME
    private static final String TABLE_NAME = "address";

    // COLUMNS
    private static final String ADDRESS_ID = "address_id";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String HOUSE_NUMBER = "house_number";
    private static final String POSTAL_CODE = "postal_code";

    private static AddressTable instance;
    private final DatabaseConnection databaseConnection;

    private AddressTable(){
        databaseConnection = DatabaseConnection.getInstance();
    }
    public static synchronized AddressTable getInstance(){
        if (instance == null) {
            instance = new AddressTable();
        }
        return instance;
    }

    @Override
    public void insert(Address address) throws SQLException {
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO " + SCHEMA + "." + TABLE_NAME + "( "+CITY+", "+STREET+", "+HOUSE_NUMBER+", " + POSTAL_CODE + ") VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, address.getCity());
            statement.setString(2, address.getStreet());
            statement.setString(3, address.getHouseNumber());
            statement.setString(4, address.getPostalCode());
            statement.executeUpdate();
        }
    }

    @Override
    public Address select(int addressId) throws SQLException {
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM "  + SCHEMA + "." + TABLE_NAME + " WHERE " + ADDRESS_ID + " = ?");
            statement.setInt(1,  addressId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                int id = resultSet.getInt(ADDRESS_ID);
                String city = resultSet.getString(CITY);
                String street = resultSet.getString(STREET);
                String houseNumber = resultSet.getString(HOUSE_NUMBER);
                String postalCode = resultSet.getString(POSTAL_CODE);
                return new Address(id, city, street, houseNumber, postalCode);
            } else {
                return null;
            }
        }
    }

    @Override
    public Address select(Address address) throws SQLException {
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM "  + SCHEMA + "." + TABLE_NAME +
                            " WHERE " + CITY + " = ? AND " + STREET + " = ? AND " + HOUSE_NUMBER + " = ? AND " + POSTAL_CODE + " = ?");
            statement.setString(1, address.getCity());
            statement.setString(2, address.getStreet());
            statement.setString(3, address.getHouseNumber());
            statement.setString(4, address.getPostalCode());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                int id = resultSet.getInt(ADDRESS_ID);
                String resultCity = resultSet.getString(CITY);
                String resultStreet = resultSet.getString(STREET);
                String resultHouseNumber = resultSet.getString(HOUSE_NUMBER);
                String resultPostalCode = resultSet.getString(POSTAL_CODE);
                return new Address(id, resultCity, resultStreet, resultHouseNumber, resultPostalCode);
            } else {
                return null;
            }
        }
    }

    @Override
    public void update(Address address) throws SQLException {
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE "+SCHEMA+"."+TABLE_NAME+" SET "+CITY+" = ?, "+STREET+" = ?, "+HOUSE_NUMBER+" = ?, "+POSTAL_CODE+" = ? WHERE "+ADDRESS_ID+" = ?");
            statement.setString(1, address.getCity());
            statement.setString(2, address.getStreet());
            statement.setString(3, address.getHouseNumber());
            statement.setString(4, address.getPostalCode());
            statement.setInt(5, address.getAddressId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Address address) throws SQLException {
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM "+SCHEMA+"."+TABLE_NAME+" WHERE "+ADDRESS_ID+" = ?");
            statement.setInt(1, address.getAddressId());
            statement.executeUpdate();
        }
    }
}
