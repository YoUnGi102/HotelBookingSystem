package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Model;

public class ViewModelFactory {

    // TODO (Add every View Model as you create it)

    private final GuestListViewModel guestListViewModel;
    private final RoomListViewModel roomListViewModel;
    private final BookingListViewModel bookingListViewModel;
    private final ManageBookingViewModel manageBookingViewModel;

    public ViewModelFactory(Model model) {
        this.guestListViewModel = new GuestListViewModel(model);
        this.roomListViewModel = new RoomListViewModel(model);
        this.bookingListViewModel = new BookingListViewModel(model);
        this.manageBookingViewModel = new ManageBookingViewModel(model);
    }

    public GuestListViewModel getGuestListViewModel(){
        return guestListViewModel;
    }
    public RoomListViewModel getRoomListViewModel() {
        return roomListViewModel;
    }
    public BookingListViewModel getBookingListViewModel() {
        return bookingListViewModel;
    }
    public ManageBookingViewModel getManageBookingViewModel() { return manageBookingViewModel; }
}
