package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Model;
import com.example.hotelbookingsystem.model.Room;

import java.sql.SQLException;

public class ManageRoomViewModel {

    private final Model model;

    public ManageRoomViewModel(Model model) {
        this.model = model;
    }

    void addRoom(Room room) throws SQLException {
        model.addRoom(room);
    }

    void editRoom(Room room) throws SQLException {
        model.editRoom(room);
    }

}
