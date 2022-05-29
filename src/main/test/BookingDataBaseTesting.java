import com.example.hotelbookingsystem.dao.*;
import com.example.hotelbookingsystem.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import org.postgresql.util.PSQLException;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class BookingDataBaseTesting
{
    Booking testBooking;
    BookingTable booking;
    Guest testGuest;
    Room testRoom;
    LocalDate dateFrom;
    LocalDate dateTo;
    ArrayList<Guest> guests;
    Staff staff;
    @BeforeEach
    void init()
    {
        booking = BookingTable.getInstance();
        testGuest = new Guest("Bob", "Bobsen", new Address("Horsens", "Marsalle", "4", "8700", "Denmark"), "45533565", "test@email.com", "55500032");
        testRoom = new Room(999, 2, 1, 1);
        guests = new ArrayList<>();
        guests.add(testGuest);
        staff = new Receptionist("bob","Bob", "Bobsen","1234" , "test@email.com", "45533565", new Address("Horsens", "Marsalle", "4", "8700", "Denmark"));



        dateFrom = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        dateTo = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().lengthOfMonth());

        testBooking = new Booking(guests,dateFrom,dateTo,testRoom,staff);

        //testBooking = new Booking(guests, new LocalDate);

        try
        {
            booking.delete(testBooking);
        } catch (SQLException throwables)
        {

        }

    }

    @Test
    public void update() throws SQLException
    {
        booking.insert(testBooking);
        Booking test = booking.select(testBooking.getBookingId());
        Room room2 = new Room(991,1,1,1);
        test.setRoom(room2);
        try
        {
            RoomTable.getInstance().insert(room2);
        } catch (SQLException e){}

        booking.update(test);
        assertEquals(991, booking.select(test.getBookingId()).getRoom().getRoomNumber());
    }

    @Test
    public void select_all() throws SQLException
    {
        assertDoesNotThrow(() -> booking.selectAll());
    }

    @Test
    public void select_by_id() throws SQLException
    {
        assertDoesNotThrow(() -> booking.select(testBooking.getBookingId()));
    }

    @Test
    public void insert() throws SQLException
    {

        assertDoesNotThrow(() -> booking.insert(testBooking));

    }

    @Test
    public void delete() throws SQLException
    {

        booking.insert(testBooking);
        assertDoesNotThrow(() -> booking.delete(testBooking));

    }




}
