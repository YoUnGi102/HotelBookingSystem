package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.model.Guest;
import com.example.hotelbookingsystem.model.Room;
import com.example.hotelbookingsystem.viewModel.GuestTableProperty;
import com.example.hotelbookingsystem.viewModel.ManageBookingViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.time.LocalDate;

public class ManageBookingViewController implements Controller {

    private ViewHandler viewHandler;
    private ManageBookingViewModel viewModel;
    private Region root;

    @FXML
    private HBox roomNotSelected, roomSelected, guestNotSelected, guestSelected;
    @FXML
    private Label roomNumber, floor, size, quality;
    @FXML
    private TableView<GuestTableProperty> guestTable;
    @FXML
    private TableColumn<GuestTableProperty, String> firstNameCol, lastNameCol, phoneNumberCol, addressCol, passportNumberCol;
    @FXML
    private DatePicker dateFrom, dateTo;

    private ObjectProperty<Room> room;

    public void init(ViewHandler viewHandler, ManageBookingViewModel viewModel, Region root, Controller lastController){
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;

        setRoomAdded(false);
        setGuestAdded(false);

        // ROOM PROPERTY
        room = new SimpleObjectProperty<>();
        viewModel.bindRoomProperty(room);
        room.addListener((observableValue, oldVal, newVal) -> {
            if(newVal != null){
                roomNumber.setText(String.valueOf(newVal.getRoomNumber()));
                floor.setText(String.valueOf(newVal.getFloor()));
                quality.setText(String.valueOf(newVal.getQuality()));
                size.setText(String.valueOf(newVal.getRoomSize()));
                setRoomAdded(true);
            }
        });

        // TABLE PROPERTY
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"){});
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        passportNumberCol.setCellValueFactory(new PropertyValueFactory<>("passportNumber"));

        viewModel.bindTableItemsProperty(guestTable.itemsProperty());
        guestTable.itemsProperty().addListener((observableValue, oldVal, newVal) -> {
            if(observableValue.getValue().size() > 0)
                setGuestAdded(true);
        });

        // DATE FROM PROPERTY
        viewModel.bindDateFromProperty(dateFrom.valueProperty());

        // DATE TO PROPERTY
        viewModel.bindDateToProperty(dateTo.valueProperty());
    }

    public ManageBookingViewModel getViewModel(){
        return viewModel;
    }

    private void setRoomAdded(boolean roomAdded){
        if(roomAdded){
            roomSelected.setVisible(true);
            roomSelected.setManaged(true);
            roomNotSelected.setVisible(false);
            roomNotSelected.setManaged(false);
        }else{
            roomSelected.setVisible(false);
            roomSelected.setManaged(false);
            roomNotSelected.setVisible(true);
            roomNotSelected.setManaged(true);

        }
    }
    private void setGuestAdded(boolean guestAdded){
        if(guestAdded){
            guestSelected.setVisible(true);
            guestSelected.setManaged(true);
            guestNotSelected.setVisible(false);
            guestNotSelected.setManaged(false);
        }else{
            guestSelected.setVisible(false);
            guestSelected.setManaged(false);
            guestNotSelected.setVisible(true);
            guestNotSelected.setManaged(true);
        }
    }

    @FXML
    void addGuest() {
        // TODO ADD NEW GUEST   aici bleadi
        viewHandler.openView(ViewHandler.MANAGE_GUEST_VIEW, this);
    }
    @FXML
    void searchGuest() {
        viewHandler.openView(ViewHandler.GUEST_LIST_VIEW, this);
    }
    @FXML
    void removeGuest() {
        guestTable.itemsProperty().getValue().remove(guestTable.getSelectionModel().getSelectedItem());
        if(guestTable.itemsProperty().getValue().size() == 0)
            setGuestAdded(false);
    }

    @FXML
    void searchRoom() {
        viewHandler.openView(ViewHandler.ROOM_LIST_VIEW, this);
    }
    @FXML
    void removeRoom() {
        room = null;
        setRoomAdded(false);
    }

    @FXML
    void cancel(ActionEvent event) {
        viewHandler.openView(ViewHandler.BOOKING_LIST_VIEW, null);
    }

    @FXML
    void confirm(ActionEvent event) {
        viewModel.addBooking();
    }

    public Region getRoot() {
        return root;
    }



}
