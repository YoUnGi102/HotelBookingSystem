package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.viewModel.ManageGuestViewModel;
import com.example.hotelbookingsystem.viewModel.RoomListViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.sql.Connection;

import static com.example.hotelbookingsystem.dao.BookingTable.*;
import static com.example.hotelbookingsystem.dao.DatabaseConnection.SCHEMA;

public class ManageGuestViewController implements Controller {


    @FXML
    private TextField firstName, lastName, eMail, phoneNumber, number, street, city, postCode, country;
    @FXML
    private Button addBtn, confirmBtn;
    private Controller previousView;

    private Region root;
    private ViewHandler viewHandler;
    private ManageGuestViewModel viewModel;
    private Controller lastController;

    public void init(ViewHandler viewHandler, ManageGuestViewModel viewModel, Region root, Controller lastController) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
        this.lastController = lastController;
        this.previousView = lastController;
    }

    public Region getRoot() {
        return root;
    }
}

