package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Model;
import com.example.hotelbookingsystem.model.Room;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RoomListViewModel {
    private Model model;

    private ObjectProperty<ObservableList<RoomTableProperty>> rooms;

    RoomListViewModel(Model model){
        rooms = new SimpleObjectProperty<>();
        this.model = model;

    }
//    public ObservableList<Room> showAllBookedRooms()
//    {
//        return model.showALlBookedRooms();
//    }

//    public ObservableList<Room> showALlAvailableRooms()
//    {
//        return model.showALlAvailableRooms();
//    }

    public void searchRooms(int floor, int size, int quality, LocalDate from, LocalDate to){
        ObservableList<RoomTableProperty> roomsFormatted = FXCollections.observableArrayList();
        for (Room g : model.searchRooms(floor, size, quality, from, to)) {
            roomsFormatted.add(new RoomTableProperty(g));
        }
        rooms.setValue(roomsFormatted);
    }

    public void showAllAvailableRooms()
    {
        ObservableList<RoomTableProperty> roomsFormatted = FXCollections.observableArrayList();
        for (Room g : model.showALlAvailableRooms()) {
            roomsFormatted.add(new RoomTableProperty(g));
        }
        rooms.setValue(roomsFormatted);
    }

    public void showAllBookedRooms()
    {
        ObservableList<RoomTableProperty> roomsFormatted = FXCollections.observableArrayList();
        for (Room g : model.showALlBookedRooms()) {
            roomsFormatted.add(new RoomTableProperty(g));
        }
        rooms.setValue(roomsFormatted);
    }

    public void bindTableItemsProperty(ObjectProperty<ObservableList<RoomTableProperty>> itemsProperty){
        itemsProperty.bind(rooms);
    }

    public ObservableList<Room> showAllOutOfOrderRooms()
    {
        return model.showALlOutOfOrderRooms();
    }
}
