package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Model;
import javafx.scene.control.Menu;

public class ViewModelFactory {

    // TODO (Add every View Model as you create it)

    private final GuestListViewModel guestListViewModel;
    private final RoomListViewModel roomListViewModel;
    private final BookingListViewModel bookingListViewModel;
    private final ManageBookingViewModel manageBookingViewModel;
    private final MenuViewModel menuViewModel;
    private final LoginViewModel loginViewModel;

    public ViewModelFactory(Model model) {
        this.guestListViewModel = new GuestListViewModel(model);
        this.roomListViewModel = new RoomListViewModel(model);
        this.bookingListViewModel = new BookingListViewModel(model);
        this.manageBookingViewModel = new ManageBookingViewModel(model);
        this.menuViewModel = new MenuViewModel(model);
        this.loginViewModel = new LoginViewModel(model);
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
    public MenuViewModel getMenuViewModel() {
        return menuViewModel;
    }
    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }
}
