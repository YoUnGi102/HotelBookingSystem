package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.model.Address;
import com.example.hotelbookingsystem.model.Guest;
import com.example.hotelbookingsystem.viewModel.ManageGuestViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.sql.SQLException;

public class ManageGuestViewController implements Controller {

    @FXML
    private TextField firstName, lastName, eMail, phoneNumber, number, street, city, postCode, country, passNr;

    private Controller previousView;

    private Region root;
    private ViewHandler viewHandler;
    private ManageGuestViewModel viewModel;

    public void init(ViewHandler viewHandler, ManageGuestViewModel viewModel, Region root, Controller previousView) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
        this.previousView = previousView;
    }

    public Region getRoot() {
        return root;
    }

    @FXML
    void back() {
        if (previousView instanceof GuestListViewController)
            viewHandler.openView(ViewHandler.GUEST_LIST_VIEW, this);
        else
            viewHandler.openView(ViewHandler.MANAGE_BOOKING_VIEW, this);
    }

    @FXML
    void confirm() {
        viewHandler.openView(ViewHandler.GUEST_LIST_VIEW, this);
        try {
            viewModel.add(new Guest(firstName.getText(), lastName.getText(), new Address(city.getText(), street.getText(), number.getText(), postCode.getText()), phoneNumber.getText(), eMail.getText(), passNr.getText()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}