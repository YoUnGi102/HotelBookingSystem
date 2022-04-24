package com.example.hotelbookingsystem.model;

import com.example.hotelbookingsystem.dao.RoomDAO;
import com.example.hotelbookingsystem.dao.RoomDAOImpl;
import com.example.hotelbookingsystem.model.Room;

import java.sql.SQLException;
import java.util.ArrayList;

public class RoomList {

    private ArrayList<Room> rooms;
    RoomDAO roomDAO;

    public RoomList() throws SQLException
    {
        roomDAO = (RoomDAO) RoomDAOImpl.getInstance();
        //this.rooms = new ArrayList<Room>();
        this.rooms = (ArrayList<Room>) roomDAO.getAllRooms();
    }

    public Room getRoom(int id) {
        return rooms.get(id);
    }

    public Room getRoombyNumber(int number) throws SQLException
    {
        return roomDAO.readByNumber(number);
    }
    public Room getRoombyID(int ID) throws SQLException
    {
        return roomDAO.readById(ID);
    }


    public void addRoom(int number,  int size, int floor, int quality, String status) throws SQLException
    {
        Room room = new Room(number,size,floor,quality,status);
        rooms.add( roomDAO.create(
                room
        ));
    }

    public void addRoom(Room newRoom) throws SQLException
    {
        rooms.add( roomDAO.create(
                newRoom
        ));
    }
    public String editStatus(Room room, String status) throws SQLException
    {
        for (Room searchRoom: rooms)
        {
            if (searchRoom.equals(room) )
            {
                searchRoom.setStatus(status);
                roomDAO.update(searchRoom);
            }
        }return "Status Set To: "+ status;
    }

    public void removeRoom(Room room) throws SQLException
    {
        roomDAO.delete(room);
        rooms.remove(room);
    }

    public int getRoomsNumber() {
        return rooms.size();
    }

    public ArrayList<Room> getRoomsByStatus(String status) throws SQLException
    {
        return (ArrayList<Room>) roomDAO.getAllRoomsByStatus(status);
    }

    public String toString()
    {
        String s = "";
        for (Room room:rooms)
        {
            s += room.toString();
            s += "\n";
        }
        return s;
    }
}
