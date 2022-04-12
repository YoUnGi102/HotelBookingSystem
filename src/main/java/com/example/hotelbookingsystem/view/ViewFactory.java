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

    public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        this.viewModelFactory = viewModelFactory;
        // EXAMPLE
        this.guestListViewController = null;
    }

    // TODO Add load method for each view
    // EXAMPLE
    public Region loadGuestListView(){
        if (guestListViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewHandler.class.getResource("guest-list-view.fxml"));
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

}

