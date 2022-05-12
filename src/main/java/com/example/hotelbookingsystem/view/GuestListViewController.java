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
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.io.IOError;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class GuestListViewController implements Controller{

    private ViewHandler viewHandler;
    private GuestListViewModel viewModel;
    private Region root;

    @FXML
    private ScrollPane showAddGuest, guestScroll;

    @FXML
    private TextField firstName, lastName, eMail, phoneNumber, passportNumber;
    @FXML
    private TableView<GuestTableProperty> table, agTable;
    @FXML
    private TableColumn<GuestTableProperty, String> firstNameCol, lastNameCol, emailCol, phoneNumberCol, addressCol, passportNumberCol;
    @FXML
    private TableColumn<GuestTableProperty, String> agFirstNameCol, agLastNameCol, agPhoneNumberCol, agEmailCol, agPassportNumberCol, agAddressCol;

    @FXML
    private Button addBtn, editBtn, removeBtn;

    private Controller previousView;

    public void init(ViewHandler viewHandler, GuestListViewModel viewModel, Region root, Controller lastController){

        table.getSelectionModel().getSelectedItem();
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;

        this.previousView = lastController;

        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"){});
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        passportNumberCol.setCellValueFactory(new PropertyValueFactory<>("passportNumber"));

        viewModel.bindTableItemsProperty(table.itemsProperty());

        agFirstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        agLastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        agEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        agPhoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        agAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        agPassportNumberCol.setCellValueFactory(new PropertyValueFactory<>("passportNumber"));

        showAddGuest.setVisible(false);
        showAddGuest.setManaged(false);

        guestScroll.setPrefHeight(500);

        if(lastController instanceof ManageBookingViewController controller){

            guestScroll.setPrefHeight(300);
            showAddGuest.setVisible(true);
            showAddGuest.setManaged(true);

            ManageBookingViewModel manageViewModel = controller.getViewModel();
            addBtn.setOnAction(e->{

                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle("Error");
                alert.setContentText("Cannot add one guest twice");
                alert.getButtonTypes().add(ButtonType.OK);

                if(table.getSelectionModel().getSelectedItem() != null) {

                    Guest g = table.getSelectionModel().getSelectedItem().getGuest();

                    for (GuestTableProperty guest : manageViewModel.getGuests()) {
                        if (guest.getGuest().getPassportNumber().equals(g.getPassportNumber())) {
                            alert.show();
                            return;
                        }
                    }

                    for (GuestTableProperty guest : agTable.getItems()) {
                        if (guest.getGuest().getPassportNumber().equals(g.getPassportNumber())){
                            alert.show();
                            return;
                        }
                    }

                    agTable.getItems().add(new GuestTableProperty(g));
                }
            });

            editBtn.setText("Remove");
            editBtn.setOnAction(e->{
                GuestTableProperty g = agTable.getSelectionModel().getSelectedItem();
                if(g != null){
                    agTable.getItems().remove(g);
                    agTable.refresh();
                }
            });

            removeBtn.setText("Confirm");
            removeBtn.setOnAction(e -> {
                manageViewModel.setGuests(agTable.getItems());
                agTable.getItems().clear();
                viewHandler.openView(ViewHandler.MANAGE_BOOKING_VIEW, controller);
            });
        }
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
        viewHandler.openView(ViewHandler.MANAGE_GUEST_VIEW, this);
    }

    public void edit(ActionEvent actionEvent) {

    }

    public void remove(ActionEvent actionEvent) throws SQLException {
        ObservableList<GuestTableProperty> allGuests, singleGuest;
        allGuests = table.getItems();
        singleGuest = table.getSelectionModel().getSelectedItems();
        GuestTableProperty guestTableProperty = table.getSelectionModel().getSelectedItem();
        viewModel.removeGuest(guestTableProperty.getGuest());
        singleGuest.forEach(allGuests :: remove);

    }

    public void back(ActionEvent actionEvent) {
        if(previousView instanceof ManageBookingViewController)
            viewHandler.openView(ViewHandler.MANAGE_BOOKING_VIEW, this);
        else
            viewHandler.openView(ViewHandler.MENU_VIEW, this);
    }

    public Region getRoot() {
        return root;
    }
}
