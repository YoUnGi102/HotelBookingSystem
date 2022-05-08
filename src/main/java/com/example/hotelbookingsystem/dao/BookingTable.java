package com.example.hotelbookingsystem.dao;

import com.example.hotelbookingsystem.model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static com.example.hotelbookingsystem.dao.DatabaseConnection.SCHEMA;
import static com.example.hotelbookingsystem.dao.GuestTable.*;
import static com.example.hotelbookingsystem.model.Booking.DATE_FORMATTER;

public class BookingTable implements BookingDAO{

    // TABLE NAME
    public static final String TABLE_NAME = "booking";

    // COLUMNS
    public static final String BOOKING_ID = "booking_id";
    public static final String ROOM_NUMBER = "room_number";
    public static final String DATE_FROM = "date_from";
    public static final String DATE_TO = "date_to";
    public static final String USERNAME = "username";

    // GUEST BOOKING TABLE
    public static final String GUEST_BOOKING_TABLE = "guest_booking";
    public static final String GUEST_BOOKING_ID = "guest_booking_id";
    public static final String GUEST_ID = "guest_id";

    private static BookingTable instance;
    private final DatabaseConnection databaseConnection;

    private final GuestTable guestTable;
    private final RoomTable roomTable;

    private BookingTable(){
        databaseConnection = DatabaseConnection.getInstance();
        guestTable = GuestTable.getInstance();
        roomTable = RoomTable.getInstance();
    }
    public static synchronized BookingTable getInstance(){
        if (instance == null) {
            instance = new BookingTable();
        }
        return instance;
    }

    @Override
    public void insert(Booking booking) throws SQLException {

        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO " + SCHEMA + "." + TABLE_NAME +
                    "("+ROOM_NUMBER+", "+DATE_FROM+", "+DATE_TO+ ", " + USERNAME + ") VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, booking.getRoom().getRoomNumber());
            statement.setDate(2, Date.valueOf(DATE_FORMATTER.format(booking.getDateFrom())));
            statement.setDate(3, Date.valueOf(DATE_FORMATTER.format(booking.getDateTo())));
            statement.setString(4, booking.getCreatedBy().getUsername());
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                booking.setBookingId(keys.getInt(1));
            } else {
                throw new SQLException("No keys generated");
            }

            StringBuilder sql = new StringBuilder("INSERT INTO " + SCHEMA+"."+GUEST_BOOKING_TABLE +
                    "("+BOOKING_ID+", "+GUEST_ID+") VALUES ");
            for (int i = 0; i < booking.getGuests().size(); i++) {
                if(i != booking.getGuests().size()-1)
                    sql.append("(?,?),");
                else
                    sql.append("(?,?);");
            }

            statement = connection.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);

            int counter = 0;
            for (Guest guest : booking.getGuests()) {
                statement.setInt(counter*2+1, booking.getBookingId());
                statement.setString(counter*2+2, guest.getPassportNumber());
                counter++;
            }
            statement.executeUpdate();

        }
    }

    @Override
    public Booking select(int bookingId) throws SQLException {

        ArrayList<Guest> guests = guestTable.selectAllInBooking(bookingId);

        try(Connection connection = databaseConnection.getConnection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM "  + SCHEMA + "." + TABLE_NAME + " WHERE " + BOOKING_ID + " = ?");
            statement.setInt(1,  bookingId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                Room room = roomTable.select(resultSet.getInt(ROOM_NUMBER));
                LocalDate dateFrom = LocalDate.parse(resultSet.getString(DATE_FROM), DATE_FORMATTER);
                LocalDate dateTo = LocalDate.parse(resultSet.getString(DATE_TO), DATE_FORMATTER);
                // TODO FIX WHEN RECEPTIONIST TABLE FINISHED
                Receptionist receptionist = new Receptionist(resultSet.getString(USERNAME), null, null, null, null, null, null);
                return new Booking(bookingId, guests, dateFrom, dateTo, room, receptionist);
            } else {
                return null;
            }
        }
    }

    @Override
    public ArrayList<Booking> selectAll() throws SQLException {

        ArrayList<Booking> bookings = new ArrayList<>();
        try(Connection connection = databaseConnection.getConnection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM "  + SCHEMA + "." + TABLE_NAME);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int bookingId = resultSet.getInt(BOOKING_ID);
                ArrayList<Guest> guests = guestTable.selectAllInBooking(bookingId);

                Room room = roomTable.select(resultSet.getInt(ROOM_NUMBER));
                LocalDate dateFrom = LocalDate.parse(resultSet.getString(DATE_FROM), DATE_FORMATTER);
                LocalDate dateTo = LocalDate.parse(resultSet.getString(DATE_TO), DATE_FORMATTER);
                // TODO FIX WHEN RECEPTIONIST TABLE FINISHED
                Receptionist receptionist = new Receptionist(resultSet.getString(USERNAME), null, null, null, null, null, null);
                bookings.add(new Booking(bookingId, guests, dateFrom, dateTo, room, receptionist));
            }
        }
        return bookings;
    }

    @Override
    public void update(Booking booking) throws SQLException {

        guestTable.updateGuestsInBooking(booking);

        try(Connection connection = databaseConnection.getConnection()) {

            System.out.println(booking);

            String sql = "UPDATE "+SCHEMA+"."+TABLE_NAME+" SET " +
                    ROOM_NUMBER+" = ?, "+DATE_FROM+" = ?, "+DATE_TO+" = ?, "+USERNAME+" = ? WHERE " + BOOKING_ID + " = ?";
            System.out.println(sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, booking.getRoom().getRoomNumber());
            statement.setDate(2, Date.valueOf(DATE_FORMATTER.format(booking.getDateFrom())));
            statement.setDate(3, Date.valueOf(DATE_FORMATTER.format(booking.getDateTo())));
            statement.setString(4, booking.getCreatedBy().getUsername());
            statement.setInt(5, booking.getBookingId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Booking booking) throws SQLException {
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM "+SCHEMA+"."+TABLE_NAME+" WHERE "+BOOKING_ID+" = ?");
            statement.setInt(1, booking.getBookingId());
            statement.executeUpdate();
        }
    }
}
