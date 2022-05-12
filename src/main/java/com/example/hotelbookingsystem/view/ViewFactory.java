package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.model.Guest;
import com.example.hotelbookingsystem.viewModel.GuestTableProperty;
import com.example.hotelbookingsystem.viewModel.ManageBookingViewModel;
import com.example.hotelbookingsystem.viewModel.ManageGuestViewModel;
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
    private ManageBookingViewController manageBookingViewController;
    private ManageGuestViewController manageGuestViewController;
    private MenuViewController menuViewController;
    private LoginViewController loginViewController;

    public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        this.viewModelFactory = viewModelFactory;
        manageGuestViewController = null;
        guestListViewController = null;
        roomListViewController = null;
        bookingListViewController = null;
        manageBookingViewController = null;
        menuViewController = null;
    }

    // TODO Add load method for each view

    public Region loadGuestListView(Controller previousView){
        if (guestListViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewHandler.class.getResource(ViewHandler.GUEST_LIST_VIEW));
            try {
                Region root = loader.load();
                guestListViewController = loader.getController();
                guestListViewController.init(viewHandler, viewModelFactory.getGuestListViewModel(), root, previousView);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        return guestListViewController.getRoot();
    }
    public Region loadBookingListView(Controller previousView){
        if (bookingListViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewHandler.class.getResource(ViewHandler.BOOKING_LIST_VIEW));
            try {
                Region root = loader.load();
                bookingListViewController = loader.getController();
                bookingListViewController.init(viewHandler, viewModelFactory.getBookingListViewModel(), root, previousView);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        return bookingListViewController.getRoot();
    }
    public Region loadRoomListView(Controller previousView){

        if (roomListViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewHandler.class.getResource(ViewHandler.ROOM_LIST_VIEW));
            try {
                Region root = loader.load();
                roomListViewController = loader.getController();
                roomListViewController.init(viewHandler, viewModelFactory.getRoomListViewModel(), root, previousView);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        return roomListViewController.getRoot();
    }
    public Region loadManageBookingView(Controller previousView){

        if (manageBookingViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewHandler.class.getResource(ViewHandler.MANAGE_BOOKING_VIEW));
            try {
                Region root = loader.load();
                manageBookingViewController = loader.getController();
                manageBookingViewController.init(viewHandler, viewModelFactory.getManageBookingViewModel(), root, previousView);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }

        if(previousView instanceof BookingListViewController controller &&
                controller.getViewModel().getCurrentBooking() != null){
            Booking booking = controller.getViewModel().getCurrentBooking();

            ObservableList<GuestTableProperty> guests = FXCollections.observableArrayList();
            for (Guest g : booking.getGuests()) {
                guests.add(new GuestTableProperty(g));
            }

            ManageBookingViewModel manageBookingViewModel = manageBookingViewController.getViewModel();
            manageBookingViewModel.setGuests(guests);
            manageBookingViewModel.setDateFrom(booking.getDateFrom());
            manageBookingViewModel.setDateTo(booking.getDateTo());
            manageBookingViewModel.setRoom(booking.getRoom());
            manageBookingViewModel.setCurrentBooking(booking);

        }

        return manageBookingViewController.getRoot();
    }

    public Region loadManageGuestView(Controller previousView) {

        if (manageGuestViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewHandler.class.getResource(ViewHandler.MANAGE_GUEST_VIEW));
            try {
                Region root = loader.load();
                manageGuestViewController = loader.getController();
                manageGuestViewController.init(viewHandler, viewModelFactory.getManageGuestViewModel(), root, previousView);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(previousView instanceof GuestListViewController controller &&
                controller.getViewModel().getCurrentGuest() != null){
            Guest guest = controller.getViewModel().getCurrentGuest();

            ManageGuestViewModel manageGuestViewModel = manageGuestViewController.getViewModel();
            manageGuestViewModel.setGuest(guest);

        }

        return manageGuestViewController.getRoot();
    }

    public Region loadMenuView(Controller previousView) {
        if (menuViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewHandler.class.getResource(ViewHandler.MENU_VIEW));
            try {
                Region root = loader.load();
                menuViewController = loader.getController();
                menuViewController.init(viewHandler, viewModelFactory.getMenuViewModel(), root, previousView);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        return menuViewController.getRoot();
    }
    public Region loadLoginView(Controller previousView) {
        if (loginViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewHandler.class.getResource(ViewHandler.LOGIN_VIEW));
            try {
                Region root = loader.load();
                loginViewController = loader.getController();
                loginViewController.init(viewHandler, viewModelFactory.getLoginViewModel(), root, previousView);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        return loginViewController.getRoot();
    }
}

