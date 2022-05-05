package com.example.hotelbookingsystem.view;

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

}

