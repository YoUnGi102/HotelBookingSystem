package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Model;
import com.example.hotelbookingsystem.model.Room;
import com.example.hotelbookingsystem.model.Staff;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;

public class RoomListViewModel {
    private Model model;

    private ObjectProperty<ObservableList<RoomTableProperty>> rooms;
    private Room currentRoom;

    RoomListViewModel(Model model){
        rooms = new SimpleObjectProperty<>();
        this.model = model;
        this.currentRoom = null;
    }

    public void bindTableItemsProperty(ObjectProperty<ObservableList<RoomTableProperty>> itemsProperty){
        itemsProperty.bind(rooms);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void searchRooms(int floor, int size, int quality, LocalDate from, LocalDate to) throws SQLException {
        ObservableList<RoomTableProperty> roomsFormatted = FXCollections.observableArrayList();
        for (Room g : model.searchRooms(floor, size, quality, from, to)) {
            roomsFormatted.add(new RoomTableProperty(g));
        }
        rooms.setValue(roomsFormatted);
    }

    public Staff getStaff(){
        return model.getStaff();
    }

}
