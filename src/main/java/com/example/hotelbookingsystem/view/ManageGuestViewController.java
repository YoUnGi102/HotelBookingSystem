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

    private TextField firstName, lastName, eMail, phoneNumber, number, street, city, postCode, country, passNr;
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
    }

    public Region getRoot() {
        return root;
    }


    @FXML
    void add(ActionEvent event) throws SQLException {

        viewModel.add(new Guest(firstName.getText(), lastName.getText(), new Address(city.getText(), street.getText(), number.getText(), postCode.getText()), phoneNumber.getText(), eMail.getText(), passNr.getText()));
    }

    @FXML
    void back(ActionEvent event) {
        if (lastController instanceof GuestListViewController)
            viewHandler.openView(ViewHandler.GUEST_LIST_VIEW, this);
        else
            viewHandler.openView(ViewHandler.MANAGE_BOOKING_VIEW, this);
    }

    @FXML
    void confirm(ActionEvent event) {
        viewHandler.openView(ViewHandler.GUEST_LIST_VIEW, this);
    }

}