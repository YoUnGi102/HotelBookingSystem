package com.example.hotelbookingsystem.dao;

import com.example.hotelbookingsystem.model.Address;
import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.model.Guest;
import com.example.hotelbookingsystem.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static com.example.hotelbookingsystem.dao.BookingTable.*;
import static com.example.hotelbookingsystem.dao.DatabaseConnection.SCHEMA;

public class GuestTable implements GuestDAO {

    // TABLE NAME
    public static final String TABLE_NAME = "guest";

    // COLUMNS
    public static final String PASSPORT_NUMBER = "passport_number";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String EMAIL = "email";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String ADDRESS = "address_id";

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
    public void insertMany(ObservableList<Guest> guests) throws SQLException {
        StringBuilder sql = new StringBuilder("INSERT INTO " + SCHEMA + "." + TABLE_NAME +
                "(" + PASSPORT_NUMBER + ", " + FIRST_NAME + ", " + LAST_NAME + ", " + EMAIL + ", " + PHONE_NUMBER + ", " + ADDRESS + ") VALUES ");

        for (int i = 0; i < guests.size(); i++) {
            if(i == guests.size()-1)
                sql.append("(?, ?, ?, ?, ?, ?);");
            else
                sql.append("(?, ?, ?, ?, ?, ?),");
        }

        try(Connection connection = databaseConnection.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
            int counter = 0;
            for (Guest guest : guests) {
                System.out.println(setAddress(guest).getAddressId());
                System.out.println(guest.getAddress().getAddressId());
                statement.setString(counter * 6 + 1, guest.getPassportNumber());
                statement.setString(counter * 6 + 2, guest.getFirstName());
                statement.setString(counter * 6 + 3, guest.getLastName());
                statement.setString(counter * 6 + 4, guest.getEmail());
                statement.setString(counter * 6 + 5, guest.getPhoneNumber());
                statement.setInt(counter * 6 + 6, guest.getAddress().getAddressId());
                counter++;
            }
            statement.executeUpdate();
        }
    }

    @Override
    public Address setAddress(Guest guest) throws SQLException {
        Address address = addressTable.select(guest.getAddress());
        if(address == null){
            addressTable.insert(guest.getAddress());
            address = addressTable.select(guest.getAddress());
        }
        guest.setAddress(address);
        return address;
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
    public ObservableList<Guest> selectAll() throws SQLException {
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM "+SCHEMA+"."+TABLE_NAME);
            ResultSet resultSet = statement.executeQuery();
            ObservableList<Guest> guests = FXCollections.observableArrayList();
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
    public ObservableList<Guest> selectAllInBooking(int bookingId) throws SQLException {
        ObservableList<Guest> guests = FXCollections.observableArrayList();

        try(Connection connection = databaseConnection.getConnection()){
            String sql = "SELECT " + PASSPORT_NUMBER +","+ FIRST_NAME +","+ LAST_NAME +","+ EMAIL +","+ PHONE_NUMBER +","+ ADDRESS + " \n" +
                    "FROM " + SCHEMA+"."+GUEST_BOOKING_TABLE + "\n" +
                    "INNER JOIN "+SCHEMA+"."+GuestTable.TABLE_NAME + " g on "+GUEST_BOOKING_TABLE+"."+GUEST_ID+" = g."+PASSPORT_NUMBER + "\n" +
                    "WHERE "+BOOKING_ID+" = " + bookingId;

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String passportNumber = resultSet.getString(PASSPORT_NUMBER);
                String firstName = resultSet.getString(FIRST_NAME);
                String lastName = resultSet.getString(LAST_NAME);
                String email = resultSet.getString(EMAIL);
                String phoneNumber = resultSet.getString(PHONE_NUMBER);
                Address address = addressTable.select(resultSet.getInt(ADDRESS));
                guests.add(new Guest(firstName, lastName, address, phoneNumber, email, passportNumber));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return guests;
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
    public void updateGuestsInBooking(Booking booking) throws SQLException{

        ObservableList<Guest> oldGuestList = selectAllInBooking(booking.getBookingId());

        // REMOVE OLD GUESTS

        ObservableList<Guest> toRemove = FXCollections.observableArrayList();
        for (Guest oldGuest : oldGuestList) {
            boolean found = false;
            for (Guest newGuest : booking.getGuests()) {
                if(oldGuest.getPassportNumber().equals(newGuest.getPassportNumber())){
                    found = true;
                    break;
                }
            }
            if (!found)
                toRemove.add(oldGuest);
        }

        if(toRemove.size() > 0) {
            try (Connection connection = databaseConnection.getConnection()) {

                StringBuilder sql = new StringBuilder("DELETE FROM " + SCHEMA + "." + GUEST_BOOKING_TABLE + " WHERE " +
                        BOOKING_ID + " = " + booking.getBookingId() + " AND " + GUEST_ID + " IN (");
                for (int i = 0; i < toRemove.size(); i++) {
                    if (i != toRemove.size() - 1)
                        sql.append("?, ");
                    else
                        sql.append("?);");
                }

                PreparedStatement statement = connection.prepareStatement(sql.toString());
                int counter = 1;
                for (Guest g : toRemove) {
                    statement.setString(counter++, g.getPassportNumber());
                }


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // ADD NEW GUESTS

        ObservableList<Guest> toAdd = FXCollections.observableArrayList();
        for (Guest newGuest : booking.getGuests()) {
            boolean found = false;
            for (Guest oldGuest : oldGuestList) {
                if(oldGuest.getPassportNumber().equals(newGuest.getPassportNumber())){
                    found = true;
                    break;
                }
            }
            if (!found)
                toAdd.add(newGuest);
        }

        if(toAdd.size() > 0) {
            try (Connection connection = databaseConnection.getConnection()) {

                StringBuilder sql = new StringBuilder("INSERT INTO " + SCHEMA + "." + GUEST_BOOKING_TABLE + " (" + GUEST_ID + ", " + BOOKING_ID + ") VALUES ");
                for (int i = 0; i < toAdd.size(); i++) {
                    if (i != toAdd.size() - 1)
                        sql.append("(?,?), ");
                    else
                        sql.append("(?,?);");
                }

                System.out.println(sql);

                PreparedStatement statement = connection.prepareStatement(sql.toString());
                int counter = 0;
                for (Guest g : toAdd) {
                    statement.setString(counter * 2 + 1, g.getPassportNumber());
                    statement.setInt(counter * 2 + 2, booking.getBookingId());
                    counter++;
                }
                System.out.println(statement.executeUpdate());


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @Override
    public void delete(Guest guest) throws SQLException {

        try(Connection connection = databaseConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM " + SCHEMA+"."+GUEST_BOOKING_TABLE + " WHERE " + GUEST_ID + " = ?");
            statement.setString(1, guest.getPassportNumber());
            statement.executeUpdate();
        }

        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM "+SCHEMA+"."+TABLE_NAME+" WHERE "+PASSPORT_NUMBER+" = ?");
            statement.setString(1, guest.getPassportNumber());
            statement.executeUpdate();
        }
    }


}
