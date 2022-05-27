package com.example.hotelbookingsystem.viewModel;

import com.example.hotelbookingsystem.model.Model;
import javafx.scene.control.Menu;

public class ViewModelFactory {

    // TODO (Add every View Model as you create it)

    private final GuestListViewModel guestListViewModel;
    private final RoomListViewModel roomListViewModel;
    private final BookingListViewModel bookingListViewModel;
    private final ManageBookingViewModel manageBookingViewModel;
    private final ManageGuestViewModel manageGuestViewModel;
    private final ManageRoomViewModel manageRoomViewModel;
    private final MenuViewModel menuViewModel;
    private final LoginViewModel loginViewModel;
    private final StaffListViewModel staffListViewModel;
    private final ManageStaffViewModel manageStaffViewModel;

    public ViewModelFactory(Model model) {
        this.guestListViewModel = new GuestListViewModel(model);
        this.roomListViewModel = new RoomListViewModel(model);
        this.bookingListViewModel = new BookingListViewModel(model);
        this.manageBookingViewModel = new ManageBookingViewModel(model);
        this.manageGuestViewModel = new ManageGuestViewModel(model);
        this.manageRoomViewModel = new ManageRoomViewModel(model);
        this.menuViewModel = new MenuViewModel(model);
        this.loginViewModel = new LoginViewModel(model);
        this.staffListViewModel = new StaffListViewModel(model);
        this.manageStaffViewModel = new ManageStaffViewModel(model);
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
    public StaffListViewModel getStaffListViewModel() {
        return staffListViewModel;
    }
    public ManageBookingViewModel getManageBookingViewModel() { return manageBookingViewModel; }
    public ManageGuestViewModel getManageGuestViewModel() {
        return manageGuestViewModel;
    }
    public MenuViewModel getMenuViewModel() {
        return menuViewModel;
    }
    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }
    public ManageRoomViewModel getManageRoomViewModel() {
        return manageRoomViewModel;
    }
    public ManageStaffViewModel getManageStaffViewModel() {
        return manageStaffViewModel;
    }
}
