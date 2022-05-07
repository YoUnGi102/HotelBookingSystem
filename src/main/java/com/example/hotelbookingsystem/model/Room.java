package com.example.hotelbookingsystem.model;

import java.io.Serializable;

public class Room implements Serializable {

    private int number;
    private int size;
    private int floor;
    private int quality;

    public Room(int number, int size, int floor, int quality) {
        this.number = number;
        this.size = size;
        this.floor = floor;
        this.quality = quality;
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

    public String getAvailability(){
        return "";
    }

    public String toString(){
        return "HotelRoom {RoomNumber: " + number + ", Floor: " + floor + ", RoomSize: " + size + ", Quality: " + quality + "}";
}
}
