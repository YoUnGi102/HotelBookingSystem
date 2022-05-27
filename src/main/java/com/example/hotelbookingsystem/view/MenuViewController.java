package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.model.Receptionist;
import com.example.hotelbookingsystem.model.Staff;
import com.example.hotelbookingsystem.viewModel.MenuViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;

public class MenuViewController implements Controller {

    private ViewHandler viewHandler;
    private MenuViewModel viewModel;
    private Region root;

    @FXML
    private Button manageGuests, manageBooking, manageRooms, manageStaff, logOut;

    public void init(ViewHandler viewHandler, MenuViewModel viewModel, Region root, Controller lastController){
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;

        manageBooking.setOnAction(e -> {
            viewHandler.openView(ViewHandler.BOOKING_LIST_VIEW, this);
        });
        manageRooms.setOnAction(e -> {
            viewHandler.openView(ViewHandler.ROOM_LIST_VIEW, this);
        });
        manageStaff.setOnAction(e -> {
            viewHandler.openView(ViewHandler.STAFF_LIST_VIEW, this);
        });
        manageGuests.setOnAction(e -> {
            viewHandler.openView(ViewHandler.GUEST_LIST_VIEW, this);
        });
        logOut.setOnAction(e -> {
            viewHandler.openView(ViewHandler.LOGIN_VIEW, this);
        });

    }

    public void setStaff(Staff staff){
        manageStaff.setVisible(!(viewModel.getStaff() instanceof Receptionist));
    }

    public Region getRoot() {
        return root;
    }
}
