package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.model.Guest;
import com.example.hotelbookingsystem.model.Room;
import com.example.hotelbookingsystem.viewModel.GuestTableProperty;
import com.example.hotelbookingsystem.viewModel.ManageBookingViewModel;
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

    private ObservableList<GuestTableProperty> guests;
    private Room room;

    public void init(ViewHandler viewHandler, ManageBookingViewModel viewModel, Region root, Controller lastController){
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;

        guests = FXCollections.observableArrayList();
        setRoomAdded(false);
        setGuestAdded(false);

        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"){});
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        passportNumberCol.setCellValueFactory(new PropertyValueFactory<>("passportNumber"));

        viewModel.bindTableItemsProperty(guestTable.itemsProperty());
    }

    public void setRoom(Room room){
        this.room = room;
        if(room != null){
            roomNumber.setText(String.valueOf(room.getRoomNumber()));
            floor.setText(String.valueOf(room.getFloor()));
            quality.setText(String.valueOf(room.getQuality()));
            size.setText(String.valueOf(room.getRoomSize()));
            setRoomAdded(true);
        }
    }
    public void setGuests(ObservableList<GuestTableProperty> guests){
        this.guests.addAll(guests);
        this.guestTable.itemsProperty().setValue(this.guests);
        if(guests.size() > 0)
            setGuestAdded(true);
    }
    public void setDateFrom(LocalDate dateFrom){
        this.dateFrom.setValue(dateFrom);
    }
    public void setDateTo(LocalDate dateTo){
        this.dateTo.setValue(dateTo);
    }

    public Room getRoom(){
        return room;
    }
    public ObservableList<GuestTableProperty> getGuests(){
        return guests;
    }
    public LocalDate getDateFrom(){
        return dateFrom.getValue();
    }
    public LocalDate getDateTo(){
        return dateTo.getValue();
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
    void addGuest(ActionEvent event) {
        // TODO ADD NEW GUEST
        System.out.println(room);
        System.out.println(roomNumber.getText());
    }

    @FXML
    void removeGuest(ActionEvent event) {
        guests.remove(guestTable.getSelectionModel().getSelectedItem());
        if(guests.size() == 0)
            setGuestAdded(false);
    }

    @FXML
    void removeRoom() {
        room = null;
        setRoomAdded(false);
    }

    @FXML
    void searchGuest(ActionEvent event) {
        viewHandler.openView(ViewHandler.GUEST_LIST_VIEW, this);
    }

    @FXML
    void searchRoom() {
        System.out.println("Search ROom");
        viewHandler.openView(ViewHandler.ROOM_LIST_VIEW, this);
    }

    @FXML
    void cancel(ActionEvent event) {
        viewHandler.closeView();
    }

    @FXML
    void confirm(ActionEvent event) {

    }


    public Region getRoot() {
        return root;
    }

    public enum ManageAction{
        ADD,
        EDIT,
        REMOVE,
    }

}
