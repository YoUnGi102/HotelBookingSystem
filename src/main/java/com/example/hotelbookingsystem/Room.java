package com.example.hotelbookingsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;

public class Room {
    public static final String BOOKED= "booked";
    public static final String AVAILABLE= "available";
    public static final String OUTOFORDER= "out of order";
    private String room;
    private int roomNumber;
    private String quality;
    private String availability;
    private int roomSize;
    private int floor;
    private String equipment;
    private String specialNeedsEquip;

    public int getRoomNumber()
    {
        return roomNumber;
    }

    public String getQuality()
    {
        return quality;
    }

    public int getRoomSize()
    {
        return roomSize;
    }

    public int getFloor()
    {
        return floor;
    }

    public String getEquipment()
    {
        return equipment;
    }

    public String getSpecialNeedsEquip()
    {
        return specialNeedsEquip;
    }

    public Room(int roomNumber, String quality, String availability, int roomSize, int floor, String equipment, String specialNeedsEquip) {
        this.roomNumber = roomNumber;
        this.quality = quality;
        this.availability = availability;
        this.roomSize = roomSize;
        this.floor = floor;
        this.equipment = equipment;
        this.specialNeedsEquip = specialNeedsEquip;

    }

    public String getRoom() {
        return room;
    }

    public String getAvailability() {
        return availability;
    }
}