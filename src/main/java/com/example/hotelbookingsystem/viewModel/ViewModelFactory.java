package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Model;

public class ViewModelFactory {

    // TODO (Add every View Model as you create it)
    // EXAMPLE
    private final GuestListViewModel guestListViewModel;
    private final RoomListViewModel roomListViewModel;

    public ViewModelFactory(Model model) {
        // EXAMPLE
        this.guestListViewModel = new GuestListViewModel(model);
        this.roomListViewModel = new RoomListViewModel(model);
    }

    // EXAMPLE
    public GuestListViewModel getGuestListViewModel(){
        return guestListViewModel;
    }

    public RoomListViewModel getRoomListViewModel() {
        return roomListViewModel;
    }
}
