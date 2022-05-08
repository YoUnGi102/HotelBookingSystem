package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Room;
import javafx.beans.property.*;

public class RoomTableProperty
{

    //private StringProperty room;
    private IntegerProperty number;
    private IntegerProperty quality;
    private StringProperty availability;
    private IntegerProperty size;
    private IntegerProperty floor;

    private Room room;

    public RoomTableProperty(Room room)
    {
        setRoom(room);
    }

    public void setRoom(Room room)
    {
        this.room = room;
        this.number = new SimpleIntegerProperty(room.getRoomNumber());
        this.quality = new SimpleIntegerProperty(room.getQuality());
        this.availability = new SimpleStringProperty(room.getAvailability());
        this.size = new SimpleIntegerProperty(room.getRoomSize());
        this.floor = new SimpleIntegerProperty(room.getFloor());
    }

    public int getNumber()
    {
        return number.get();
    }

    public IntegerProperty numberProperty()
    {
        return number;
    }

    public int getQuality()
    {
        return quality.get();
    }

    public IntegerProperty qualityProperty()
    {
        return quality;
    }

    public String getAvailability()
    {
        return availability.get();
    }

    public StringProperty availabilityProperty()
    {
        return availability;
    }

    public int getSize()
    {
        return size.get();
    }

    public IntegerProperty sizeProperty()
    {
        return size;
    }

    public int getFloor()
    {
        return floor.get();
    }

    public IntegerProperty floorProperty()
    {
        return floor;
    }

    public Room getRoom() {
        return room;
    }
}
