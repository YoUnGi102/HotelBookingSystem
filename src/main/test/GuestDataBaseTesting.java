import com.example.hotelbookingsystem.dao.AddressTable;
import com.example.hotelbookingsystem.dao.GuestTable;
import com.example.hotelbookingsystem.dao.RoomTable;
import com.example.hotelbookingsystem.model.Address;
import com.example.hotelbookingsystem.model.Guest;
import com.example.hotelbookingsystem.model.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class GuestDataBaseTesting
{
    GuestTable guest;
    Guest testGuest;

    @BeforeEach
    void init()
    {
        guest = GuestTable.getInstance();
        testGuest = new Guest("Bob", "Bobsen", new Address("Horsens", "Marsalle", "4", "8700", "Denmark"), "45533565", "test@email.com", "55500032");

        try
        {
            guest.delete(testGuest);
        } catch (SQLException throwables)
        {

        }
    }

    @Test
    public void select_all() throws SQLException
    {
        assertDoesNotThrow(() -> guest.selectAll());
    }

    @Test
    public void select_by_passport_number() throws SQLException
    {
        assertDoesNotThrow(() -> guest.select("55500032"));
    }

    @Test
    public void insert() throws SQLException
    {
        assertDoesNotThrow(() -> guest.insert(testGuest));
    }

    @Test
    public void delete() throws SQLException
    {
        assertDoesNotThrow(() -> guest.delete(testGuest));
    }

    @Test
    public void insert_same_guest() throws SQLException
    {
        assertDoesNotThrow(() -> guest.insert(testGuest));
        assertThrows(SQLException.class, () -> guest.insert(testGuest));
    }


    @Test
    public void update() throws SQLException
    {
        guest.insert(testGuest);
        testGuest.setEmail("email2@mail.com");
        guest.update(testGuest);
        assertEquals("email2@mail.com", guest.select("55500032").getEmail());
    }


}
