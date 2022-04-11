package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Model;
import com.example.hotelbookingsystem.model.Room;
import javafx.collections.ObservableList;

public class GuestListViewModel {

    private Model model;

    GuestListViewModel(Model model){
        this.model = model;
    }

    public ObservableList<Room> showALlBookedRooms()
    {
         return model.showALlBookedRooms();
    }

    public ObservableList<Room> showALlAvailableRooms()
    {
        return model.showALlAvailableRooms();
    }

    public ObservableList<Room> showALlOutOfOrderRooms()
    {
        return model.showALlOutOfOrderRooms();
    }
}
