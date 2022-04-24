package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Model;
import com.example.hotelbookingsystem.Room;
import javafx.collections.ObservableList;

public class RoomListViewModel {
    private Model model;

    RoomListViewModel(Model model){
        this.model = model;
    }
    public ObservableList<Room> showAllBookedRooms()
    {
        return model.showALlBookedRooms();
    }

    public ObservableList<Room> showALlAvailableRooms()
    {
        return model.showALlAvailableRooms();
    }

    public ObservableList<Room> showAllOutOfOrderRooms()
    {
        return model.showALlOutOfOrderRooms();
    }
}
