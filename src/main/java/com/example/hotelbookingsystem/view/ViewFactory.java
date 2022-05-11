package com.example.hotelbookingsystem.view;

<<<<<<< Updated upstream
=======
import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.model.Guest;
import com.example.hotelbookingsystem.viewModel.GuestTableProperty;
import com.example.hotelbookingsystem.viewModel.ManageBookingViewModel;
>>>>>>> Stashed changes
import com.example.hotelbookingsystem.viewModel.ViewModelFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOError;
import java.io.IOException;

public class ViewFactory {

    private ViewHandler viewHandler;
    private ViewModelFactory viewModelFactory;

    // TODO ADD Controller for each view
    // EXAMPLE
    private GuestListViewController guestListViewController;
    private RoomListViewController roomListViewController;
    private BookingListViewController bookingListViewController;

    public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        this.viewModelFactory = viewModelFactory;
        // EXAMPLE
        this.guestListViewController = null;
        this.roomListViewController = null;
        this.bookingListViewController = null;
    }

    // TODO Add load method for each view
    // EXAMPLE
    public Region loadGuestListView(){
        if (guestListViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewHandler.class.getResource(ViewHandler.GUEST_LIST_VIEW));
            try {
                Region root = loader.load();
                guestListViewController = loader.getController();
                guestListViewController.init(viewHandler, viewModelFactory.getGuestListViewModel(), root);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        return guestListViewController.getRoot();
    }

    public Region loadBookingListView(){
        if (bookingListViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewHandler.class.getResource(ViewHandler.BOOKING_LIST_VIEW));
            try {
                Region root = loader.load();
                bookingListViewController = loader.getController();
                bookingListViewController.init(viewHandler, viewModelFactory.getBookingListViewModel(), root);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        return bookingListViewController.getRoot();
    }


    public Region loadRoomListView(){
        if (roomListViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewHandler.class.getResource("room_list_view.fxml"));
            try {
                Region root = loader.load();
                roomListViewController = loader.getController();
                roomListViewController.init(viewHandler, viewModelFactory.getRoomListViewModel(), root);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        return roomListViewController.getRoot();
    }

<<<<<<< Updated upstream
=======
    public Region loadManageBookingView(Controller lastController){

        if (manageBookingViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewHandler.class.getResource("manage_booking_view.fxml"));
            try {
                Region root = loader.load();
                manageBookingViewController = loader.getController();
                manageBookingViewController.init(viewHandler, viewModelFactory.getManageBookingViewModel(), root, lastController);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }

        if(lastController instanceof BookingListViewController controller &&
                controller.getViewModel().getCurrentBooking() != null){
            Booking booking = controller.getViewModel().getCurrentBooking();

            ObservableList<GuestTableProperty> guests = FXCollections.observableArrayList();
            for (Guest g : booking.getGuests()) {
                guests.add(new GuestTableProperty(g));
            }

            ManageBookingViewModel manageBookingViewModel = manageBookingViewController.getViewModel();
            manageBookingViewModel.setGuests(guests);
            manageBookingViewModel.setRoom(booking.getRoom());
            manageBookingViewModel.setDateFrom(booking.getDateFrom());
            manageBookingViewModel.setDateTo(booking.getDateTo());
            manageBookingViewModel.setCurrentBooking(booking);

        }

        return manageBookingViewController.getRoot();
    }

>>>>>>> Stashed changes
}

