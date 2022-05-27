package com.example.hotelbookingsystem.dao;

import com.example.hotelbookingsystem.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.hotelbookingsystem.model.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static com.example.hotelbookingsystem.dao.DatabaseConnection.SCHEMA;

public class StaffTable implements StaffDAO {

    // TABLE NAME
    public static final String TABLE_NAME = "staff";

    // COLUMNS
    public static final String USERNAME = "username";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String ADDRESS = "address_id";
    public static final String STAFF_TYPE = "staff_type";

    public static final String MANAGER = "manager";
    public static final String RECEPTIONIST = "receptionist";

    private static StaffTable instance;
    private final DatabaseConnection databaseConnection;

    private final AddressTable addressTable;

    private StaffTable(){
        databaseConnection = DatabaseConnection.getInstance();
        addressTable = AddressTable.getInstance();
    }
    public static synchronized StaffTable getInstance(){
        if (instance == null) {
            instance = new StaffTable();
        }
        return instance;
    }

    @Override
    public void insert(Staff staff) throws SQLException {

        String staffType = (staff instanceof Receptionist) ? RECEPTIONIST : MANAGER;

        setAddress(staff);

        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO " + SCHEMA + "." + TABLE_NAME +
                            "("+USERNAME+", "+FIRST_NAME+", "+LAST_NAME+", "+ PASSWORD +", "+ EMAIL+ ", " + PHONE_NUMBER + ", " + ADDRESS + ", " + STAFF_TYPE + ")" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, staff.getUsername());
            statement.setString(2, staff.getFirstName());
            statement.setString(3, staff.getLastName());
            statement.setString(4, staff.getPassword());
            statement.setString(5, staff.getEmail());
            statement.setString(6, staff.getPhoneNumber());
            statement.setInt(7, staff.getAddress().getAddressId());
            statement.setString(8, staffType);
            statement.executeUpdate();
        }
    }

    @Override
    public Staff select(String searchUsername) throws SQLException {
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
                String staffType = resultSet.getString(STAFF_TYPE);
                Address address = addressTable.select(resultSet.getInt(ADDRESS));
                if(staffType.equals(RECEPTIONIST))
                    return new Receptionist(username, firstName, lastName, password, email, phoneNumber, address);
                else
                    return new Manager(username, firstName, lastName, password, email, phoneNumber, address);
            } else {
                return null;
            }
        }
    }

    @Override
    public ObservableList<Staff> selectAll() throws SQLException {
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM "+SCHEMA+"."+TABLE_NAME);
            ResultSet resultSet = statement.executeQuery();
            ObservableList<Staff> staff = FXCollections.observableArrayList();
            while (resultSet.next()) {
                String username = resultSet.getString(USERNAME);
                String firstName = resultSet.getString(FIRST_NAME);
                String lastName = resultSet.getString(LAST_NAME);
                String password = resultSet.getString(PASSWORD);
                String email = resultSet.getString(EMAIL);
                String phoneNumber = resultSet.getString(PHONE_NUMBER);
                String staffType = resultSet.getString(STAFF_TYPE);
                Address address = addressTable.select(resultSet.getInt(ADDRESS));
                if(staffType.equals(MANAGER))
                    staff.add(new Manager(username, firstName, lastName, password, email, phoneNumber, address));
                else
                    staff.add(new Receptionist(username, firstName, lastName, password, email, phoneNumber, address));
            }
            return staff;
        }
    }

    @Override
    public void update(Staff staff) throws SQLException {
        setAddress(staff);
        try(Connection connection = databaseConnection.getConnection()) {
            String sql = "UPDATE "+SCHEMA+"."+TABLE_NAME+" SET " +
                    FIRST_NAME+" = ?, "+LAST_NAME+" = ?, " + PASSWORD+" = ?, " +EMAIL+" = ?, "+PHONE_NUMBER+" = ?, "+ADDRESS+" = ?, "+ STAFF_TYPE + " = ? WHERE "+USERNAME+" = ?";
            System.out.println(sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, staff.getFirstName());
            statement.setString(2, staff.getLastName());
            statement.setString(3, staff.getPassword());
            statement.setString(4, staff.getEmail());
            statement.setString(5, staff.getPhoneNumber());
            statement.setInt(6, staff.getAddress().getAddressId());
            statement.setString(7, (staff instanceof Receptionist)? RECEPTIONIST : MANAGER);
            statement.setString(8, staff.getUsername());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Staff staff) throws SQLException {
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM "+SCHEMA+"."+TABLE_NAME+" WHERE "+USERNAME+" = ?");
            statement.setString(1, staff.getUsername());
            statement.executeUpdate();
        }
    }

    @Override
    public Staff logIn(String logInUsername, String logInPassword) throws SQLException {
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
                String staffType = resultSet.getString(STAFF_TYPE);
                Address address = addressTable.select(resultSet.getInt(ADDRESS));

                if(staffType.equals("manager"))
                    return new Manager(username, firstName, lastName, password, email, phoneNumber, address);
                else
                    return new Receptionist(username, firstName, lastName, password, email, phoneNumber, address);

            } else {
                return null;
            }
        }
    }

    @Override
    public Address setAddress(Staff staff) throws SQLException {
        Address address = addressTable.select(staff.getAddress());
        if(address == null){
            addressTable.insert(staff.getAddress());
            address = addressTable.select(staff.getAddress());
        }
        staff.setAddress(address);
        return address;
    }
}
