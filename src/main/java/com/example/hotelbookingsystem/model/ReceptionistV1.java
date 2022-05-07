package com.example.hotelbookingsystem.model;

import com.example.hotelbookingsystem.model.Room;

import java.io.Serializable;
import java.util.ArrayList;

public class ReceptionistV1 implements Serializable
{

    private ArrayList<Room> rooms;
    private String room;

    public ReceptionistV1(String room)
    {
        this.room = room;
        this.rooms = new ArrayList<Room>();
    }

    //get room by id

    public Room getRoom(int id)
    {
        for (Room room : rooms)
        {
            if (room.getRoomNumber()== id) ;
        }
        return null;
    }


    // nu stiu daca trebuia dar iaca

//    public void updateStatusRoom(String id,String status){
//for (Room room: rooms){
//if (room.getId().equals(id))
//    room.getStatus(status);
//}
//    }


    public void addRoom(Room newRoom)
    {
        rooms.add(newRoom);
    }

    public String editRoom(String status)
    {
        String result = null;
        switch (status)
        {
            case "free":
                result = "free";
                break;

            case "broken":
                result = "Under Maintenance";
                break;

            case "not free":
                result = "Booked";
                break;

        }
        return result;
    }

    public void removeRoom(Room room)
    {
        rooms.remove(room);
    }

    //remove by id
    public void removeRoomByID(int id)
    {
        rooms.removeIf(room -> room.getRoomNumber() == id);
    }

    public int showRooms()
    {
        return rooms.size();
    }
}
