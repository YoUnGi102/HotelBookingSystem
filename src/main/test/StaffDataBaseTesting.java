import com.example.hotelbookingsystem.dao.AddressTable;
import com.example.hotelbookingsystem.dao.GuestTable;
import com.example.hotelbookingsystem.dao.RoomTable;
import com.example.hotelbookingsystem.dao.StaffTable;
import com.example.hotelbookingsystem.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class StaffDataBaseTesting
{

    StaffTable staff;
    Staff testStaff;

    @BeforeEach
    void init()
    {
        staff = StaffTable.getInstance();
        testStaff = new Receptionist("bob","Bob", "Bobsen","1234" , "test@email.com", "45533565", new Address("Horsens", "Marsalle", "4", "8700", "Denmark"));

        try
        {
            staff.delete(testStaff);
        } catch (SQLException throwables)
        {

        }
    }

    @Test
    public void select_all() throws SQLException
    {
        assertDoesNotThrow(() -> staff.selectAll());
    }

    @Test
    public void select_by_username() throws SQLException
    {
        assertDoesNotThrow(() -> staff.select("bob"));
    }

    @Test
    public void insert() throws SQLException
    {
        //Run Only Once
        assertDoesNotThrow(() -> staff.insert(testStaff));

    }

    @Test
    public void delete() throws SQLException
    {

        staff.insert(testStaff);
        assertDoesNotThrow(() -> staff.delete(testStaff));

    }

    @Test
    public void insert_same_staff() throws SQLException
    {

        assertDoesNotThrow(() -> staff.insert(testStaff));
        assertThrows(SQLException.class, () -> staff.insert(testStaff));

    }


    @Test
    public void update() throws SQLException
    {
        staff.insert(testStaff);
        testStaff.setEmail("email2@mail.com");
        staff.update(testStaff);
        assertEquals("email2@mail.com", staff.select("bob").getEmail());
    }

}
