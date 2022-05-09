package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.model.Guest;
import com.example.hotelbookingsystem.model.Room;
import com.example.hotelbookingsystem.viewModel.GuestTableProperty;
import com.example.hotelbookingsystem.viewModel.GuestListViewModel;
import com.example.hotelbookingsystem.viewModel.ManageBookingViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

import java.sql.SQLException;
import java.util.ArrayList;

public class GuestListViewController implements Controller{

    private ViewHandler viewHandler;
    private GuestListViewModel viewModel;
    private Region root;

    @FXML
    private TextField firstName, lastName, eMail, phoneNumber, passportNumber;
    @FXML
    private TableView<GuestTableProperty> table;
    @FXML
    private TableColumn<GuestTableProperty, String> firstNameCol, lastNameCol, emailCol, phoneNumberCol, addressCol, passportNumberCol;
    @FXML
    private Button addBtn, editBtn, removeBtn;
    private Controller previousView;

    public void init(ViewHandler viewHandler, GuestListViewModel viewModel, Region root, Controller lastController){
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
        
        this.previousView = lastController;

        if(lastController instanceof ManageBookingViewController controller){
            ManageBookingViewModel manageViewModel = controller.getViewModel();
            addBtn.setVisible(false);
            editBtn.setText("Add");
            removeBtn.setText("Confirm");
            ObservableList<GuestTableProperty> guests = FXCollections.observableArrayList();
            editBtn.setOnAction(e->{
                Guest g = table.getSelectionModel().getSelectedItem().getGuest();
                if(g != null)
                    guests.add(new GuestTableProperty(g));
            });
            removeBtn.setOnAction(e -> {
                manageViewModel.setGuests(guests);
                viewHandler.openView(ViewHandler.MANAGE_BOOKING_VIEW, controller);
            });
        }

        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"){});
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        passportNumberCol.setCellValueFactory(new PropertyValueFactory<>("passportNumber"));

        viewModel.bindTableItemsProperty(table.itemsProperty());
    }

    @FXML
    void search(ActionEvent event) {
        // TODO CHECK USER INPUT
        try {
            viewModel.searchGuests(firstName.getText(), lastName.getText(), phoneNumber.getText(), passportNumber.getText(), eMail.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(ActionEvent actionEvent) {

    }

    public void edit(ActionEvent actionEvent) {
    }

    public void remove(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
        if(previousView instanceof ManageBookingViewController)
            viewHandler.openView(ViewHandler.MANAGE_BOOKING_VIEW, null);
    }

    public Region getRoot() {
        return root;
    }
}
