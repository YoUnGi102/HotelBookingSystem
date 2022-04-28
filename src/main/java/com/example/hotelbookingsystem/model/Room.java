package com.example.hotelbookingsystem.model;

import java.io.Serializable;

public class Room implements Serializable {
    private int number;
    private int size;
    private int floor;
    private int quality;
    private int id;
    private String availability;
    private boolean specialNeedsEquipment;
    public static final String AVAILABLE = "Available";
    public static final String OUT_OF_ORDER = "Out_Of_Order";
    public static final String BOOKED = "Booked";


    public Room(int  id, int number,  int size, int floor, int quality, String availability, boolean specialNeedsEquipment){
        this.floor=floor;
        this.number=number;
        this.size=size;
        this.quality=quality;
        this.id = id;
        this.availability=availability;
        this.specialNeedsEquipment = specialNeedsEquipment;
    }

    public Room(int number, int size, int floor, int quality, String availability, boolean hasSpecialNeedsEquipment)
    {
        this.floor=floor;
        this.number=number;
        this.size=size;
        this.quality=quality;
        this.availability=availability;
        this.specialNeedsEquipment = hasSpecialNeedsEquipment;
    }

    public int getRoomNumber()
    {
        return number;
    }

    public void setRoomNumber(int number)
    {
        this.number = number;
    }

    public int getRoomSize()
    {
        return size;
    }

    public void setRoomSize(int size)
    {
        this.size = size;
    }

    public int getFloor()
    {
        return floor;
    }

    public void setFloor(int floor)
    {
        this.floor = floor;
    }

    public int getQuality()
    {
        return quality;
    }

    public void setQuality(int quality)
    {
        this.quality = quality;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public boolean hasSpecialNeedsEquipment() {
        return specialNeedsEquipment;
    }

    public void setSpecialNeedsEquipment(boolean specialNeedsEquipment) {
        this.specialNeedsEquipment = specialNeedsEquipment;
    }

    public String getAvailability()
    {
        return availability;
    }

    public void setAvailability(String availability)
    {
        this.availability = availability;
    }

    public String toString(){
        return "Hotel Room {ID: "+id + ", Number: " + number + ", Floor: " + floor + ", RoomSize: " + size + ", Quality: " + quality + ", Availability: "+ availability +" }";
}
}
