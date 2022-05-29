import com.example.hotelbookingsystem.dao.AddressTable;
import com.example.hotelbookingsystem.dao.RoomTable;
import com.example.hotelbookingsystem.model.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DataBaseTesting
{
    RoomTable room;
    Room testRoom;
    @BeforeEach
    void init()
    {
        room = RoomTable.getInstance();
        testRoom = new Room(999, 2, 1, 1);

        try
        {
            room.delete(testRoom);
        } catch (SQLException throwables)
        {

        }
    }

    @Test
    public void select_all() throws SQLException
    {
        assertDoesNotThrow(() -> room.selectAll());
    }

    @Test
    public void select_by_room_number() throws SQLException
    {
        assertDoesNotThrow(() -> room.select(1));
    }

    @Test
    public void insert() throws SQLException
    {
        //Run Only Once
        assertDoesNotThrow(() -> room.insert(testRoom));

    }

    @Test
    public void delete() throws SQLException
    {
        //Run only after inserting
        assertDoesNotThrow(()-> room.delete(testRoom));

    }

    @Test
    public void insert_same_room() throws SQLException
    {
        //Run only after inserting
        assertDoesNotThrow(() -> room.insert(testRoom));
        assertThrows(SQLException.class, ()-> room.insert(testRoom));

    }

    @Test
    public void insert_multiple_same_rooms()
    {
        ObservableList<Room> rooms = FXCollections.observableArrayList();
        rooms.add(new Room(105, 2, 1, 1));
        rooms.add(new Room(106, 2, 1, 1));
        rooms.add(new Room(107, 3, 1, 2));
        rooms.add(new Room(108, 3, 1, 2));
        rooms.add(new Room(205, 4, 2, 3));
        rooms.add(new Room(206, 4, 2, 3));
        rooms.add(new Room(207, 3, 2, 2));
        rooms.add(new Room(208, 3, 2, 2));
        rooms.add(new Room(305, 2, 3, 1));
        rooms.add(new Room(306, 2, 3, 1));
        rooms.add(new Room(307, 1, 3, 4));
        rooms.add(new Room(308, 1, 3, 4));
        assertThrows(SQLException.class, ()->room.insertMany(rooms));
    }

    @Test
    public void update() throws SQLException
    {
        room.insert(testRoom);
        testRoom.setRoomSize(4);
        testRoom.setQuality(2);
        room.update(testRoom);
        assertEquals(4,room.select(testRoom.getRoomNumber()).getRoomSize());

    }









}
