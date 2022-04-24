package com.example.hotelbookingsystem.model;

import java.io.Serializable;

public class Room implements Serializable {
    private int number;
    private int size;
    private int floor;
    private int quality;
    private int id;
    private String status;
    public static final String StatusFree = "Free";
    public static final String StatusBroken = "Broken";
    public static final String StatusBusy = "Busy";



    public Room(int  id,int number,  int size, int floor, int quality, String status){
        this.floor=floor;
        this.number=number;
        this.size=size;
        this.quality=quality;
        this.id = id;
        this.status=status;
    }

    public Room(int number,  int size, int floor, int quality, String status){
        this.floor=floor;
        this.number=number;
        this.size=size;
        this.quality=quality;
        this.status=status;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
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

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String toString(){
        return "Hotel Room {ID: "+id + ", Number: " + number + ", Floor: " + floor + ", Size: " + size + ", Quality: " + quality + ", Status: "+ status +" }";
}
}
