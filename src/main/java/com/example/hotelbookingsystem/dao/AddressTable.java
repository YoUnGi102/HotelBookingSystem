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
    private static final String COUNTRY = "country";

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

        if(select(address) != null)
            return;

        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO " + SCHEMA + "." + TABLE_NAME + "( "+CITY+", "+STREET+", "+HOUSE_NUMBER+", " + POSTAL_CODE + ", " + COUNTRY+") VALUES (?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, address.getCity());
            statement.setString(2, address.getStreet());
            statement.setString(3, address.getHouseNumber());
            statement.setString(4, address.getPostalCode());
            statement.setString(5, address.getCountry());
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                address.setAddressId(keys.getInt(1));
            } else {
                throw new SQLException("No keys generated");
            }
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
                String country = resultSet.getString(COUNTRY);
                return new Address(id, city, street, houseNumber, postalCode, country);
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
                            " WHERE " + CITY + " = ? AND " + STREET + " = ? AND " + HOUSE_NUMBER + " = ? AND " + POSTAL_CODE + " = ? AND " + COUNTRY + " = ?");
            statement.setString(1, address.getCity());
            statement.setString(2, address.getStreet());
            statement.setString(3, address.getHouseNumber());
            statement.setString(4, address.getPostalCode());
            statement.setString(5, address.getCountry());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                int id = resultSet.getInt(ADDRESS_ID);
                String resultCity = resultSet.getString(CITY);
                String resultStreet = resultSet.getString(STREET);
                String resultHouseNumber = resultSet.getString(HOUSE_NUMBER);
                String resultPostalCode = resultSet.getString(POSTAL_CODE);
                String resultCountry = resultSet.getString(COUNTRY);
                return new Address(id, resultCity, resultStreet, resultHouseNumber, resultPostalCode, resultCountry);
            } else {
                return null;
            }
        }
    }

    @Override
    public void update(Address address) throws SQLException {
        try(Connection connection = databaseConnection.getConnection()) {
            String sql = "UPDATE "+SCHEMA+"."+TABLE_NAME+" SET "+CITY+" = ?, "+STREET+" = ?, "+HOUSE_NUMBER+" = ?, "+POSTAL_CODE+" = ?, " + COUNTRY + " = ? WHERE "+ADDRESS_ID+" = ?";
            System.out.println(sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, address.getCity());
            statement.setString(2, address.getStreet());
            statement.setString(3, address.getHouseNumber());
            statement.setString(4, address.getPostalCode());
            statement.setString(5, address.getCountry());
            statement.setInt(6, address.getAddressId());
            statement.executeUpdate();
        }
    }

    public boolean isOnlyAddress(Address address) throws SQLException {
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM "+SCHEMA+".guest WHERE "+ADDRESS_ID+" = ?");
            statement.setInt(1, address.getAddressId());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int count = resultSet.getInt("count");
                System.out.println(count);
                return count == 1;
            }
        }
        return false;
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
