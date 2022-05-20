package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Model;
import com.example.hotelbookingsystem.model.Room;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ManageRoomViewModel {

    private final Model model;

    private ObjectProperty<Room> room;

    public ManageRoomViewModel(Model model) {
        this.model = model;
        this.room = new SimpleObjectProperty<>();
    }

    public void bindRoomProperty(ObjectProperty<Room> room){
        room.bindBidirectional(this.room);
    }

    public Model getModel() {
        return model;
    }

    public Room getRoom() {
        return room.get();
    }

    public void setRoom(Room room) {
        this.room.set(room);
    }

    public void addRoom(Room room) throws SQLException, RemoteException {
        model.addRoom(room);
    }

    public void editRoom(Room room) throws SQLException, RemoteException {
        model.editRoom(room);
    }

}
