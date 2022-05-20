package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Model;
import com.example.hotelbookingsystem.model.ModelManager;
import com.example.hotelbookingsystem.model.Room;
import com.example.hotelbookingsystem.model.Staff;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;

public class RoomListViewModel implements PropertyChangeListener {
    private Model model;

    private ObjectProperty<ObservableList<RoomTableProperty>> rooms;
    private Room currentRoom;

    RoomListViewModel(Model model){
        rooms = new SimpleObjectProperty<>();
        this.model = model;
        this.currentRoom = null;
        model.addPropertyChangeListener(ModelManager.REFRESH, this);
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

    public void removeRoom(Room room) throws SQLException, RemoteException {
        model.removeRoom(room);
    }

    public Staff getStaff(){
        return model.getStaff();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
