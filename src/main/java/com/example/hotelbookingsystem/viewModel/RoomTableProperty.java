package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.Room;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RoomTableProperty
{

    //private StringProperty room;
    private IntegerProperty number;
    private StringProperty quality;
    private StringProperty availability;
    private IntegerProperty size;
    private IntegerProperty floor;
    private StringProperty equipment;
    private StringProperty specialNeedsEquip;

    public RoomTableProperty(Room room)
    {
        setRoom(room);
    }

    public void setRoom(Room room)
    {
        this.number = new SimpleIntegerProperty(room.getRoomNumber());
        this.quality = new SimpleStringProperty(room.getQuality());
        this.availability = new SimpleStringProperty(room.getAvailability());
        this.size = new SimpleIntegerProperty(room.getRoomSize());
        this.floor = new SimpleIntegerProperty(room.getFloor());
        this.equipment = new SimpleStringProperty(room.getEquipment());
        this.specialNeedsEquip = new SimpleStringProperty(room.getSpecialNeedsEquip());
    }

    public int getNumber()
    {
        return number.get();
    }

    public IntegerProperty numberProperty()
    {
        return number;
    }

    public String getQuality()
    {
        return quality.get();
    }

    public StringProperty qualityProperty()
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

    public String getEquipment()
    {
        return equipment.get();
    }

    public StringProperty equipmentProperty()
    {
        return equipment;
    }

    public String getSpecialNeedsEquip()
    {
        return specialNeedsEquip.get();
    }

    public StringProperty specialNeedsEquipProperty()
    {
        return specialNeedsEquip;
    }
}
