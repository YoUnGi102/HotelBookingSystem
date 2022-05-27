package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Model;
import com.example.hotelbookingsystem.model.ModelManager;
import com.example.hotelbookingsystem.model.Room;
import com.example.hotelbookingsystem.model.Staff;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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
        if (from != null && from.isBefore(LocalDate.now()))
        {
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("From Date is Wrong");
            alert.setContentText("Start Date is before today");
            alert.getButtonTypes().add(ButtonType.OK);
            alert.show();
        }
        if (from != null && to != null && to.isBefore(from))
        {
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Finish Date is Wrong");
            alert.setContentText("Finish Date is before strat Date");
            alert.getButtonTypes().add(ButtonType.OK);
            alert.show();
            to = null;
        }
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
