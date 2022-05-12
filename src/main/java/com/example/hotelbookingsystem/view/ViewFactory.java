package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.viewModel.ManageBookingViewModel;
import com.example.hotelbookingsystem.viewModel.ViewModelFactory;
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
    private ManageBookingViewController manageBookingViewController;
    private ManageGuestViewController manageGuestViewController;

    public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        this.viewModelFactory = viewModelFactory;
        this.guestListViewController = null;
        this.roomListViewController = null;
        this.bookingListViewController = null;
        this.manageBookingViewController = null;
        this.manageGuestViewController = null;
    }

    // TODO Add load method for each view

    public Region loadGuestListView(Controller lastController){
        if (guestListViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewHandler.class.getResource(ViewHandler.GUEST_LIST_VIEW));
            try {
                Region root = loader.load();
                guestListViewController = loader.getController();
                guestListViewController.init(viewHandler, viewModelFactory.getGuestListViewModel(), root, lastController);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        return guestListViewController.getRoot();
    }

    public Region loadBookingListView(Controller lastController){
        if (bookingListViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewHandler.class.getResource(ViewHandler.BOOKING_LIST_VIEW));
            try {
                Region root = loader.load();
                bookingListViewController = loader.getController();
                bookingListViewController.init(viewHandler, viewModelFactory.getBookingListViewModel(), root, lastController);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        return bookingListViewController.getRoot();
    }

    public Region loadRoomListView(Controller lastController){

        if (roomListViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewHandler.class.getResource("room_list_view.fxml"));
            try {
                Region root = loader.load();
                roomListViewController = loader.getController();
                roomListViewController.init(viewHandler, viewModelFactory.getRoomListViewModel(), root, lastController);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        return roomListViewController.getRoot();
    }

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
        return manageBookingViewController.getRoot();
    }

    public Region loadManageGuestView(Controller lastController){

        if (manageGuestViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewHandler.class.getResource("manage_guest_view.fxml"));
            try {
                Region root = loader.load();
                manageGuestViewController = loader.getController();
                manageGuestViewController.init(viewHandler, viewModelFactory.getManageGuestViewModel(), root, lastController);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        return manageGuestViewController.getRoot();
    }

}

