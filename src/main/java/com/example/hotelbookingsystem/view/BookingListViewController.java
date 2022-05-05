package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.viewModel.BookingListViewModel;
import com.example.hotelbookingsystem.viewModel.BookingTableProperty;
import com.example.hotelbookingsystem.viewModel.RoomListViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class BookingListViewController {

    @FXML
    private TextField phoneNumber, email, roomNumber;
    @FXML
    private DatePicker dateFrom, dateTo;
    @FXML
    private TableView<BookingTableProperty> table;
    @FXML
    private TableColumn<BookingTableProperty, String> nameCol, phoneNumberCol, emailCol, roomNumberCol, dateFromCol, dateToCol;

    private ViewHandler viewHandler;
    private BookingListViewModel viewModel;
    private Region root;

    public void init(ViewHandler viewHandler, BookingListViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;

        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        roomNumberCol.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        dateFromCol.setCellValueFactory(new PropertyValueFactory<>("dateFrom"));
        dateToCol.setCellValueFactory(new PropertyValueFactory<>("dateTo"));

        viewModel.bindTableItemsProperty(table.itemsProperty());
    }

    @FXML
    void add(ActionEvent event) {

    }

    @FXML
    void edit(ActionEvent event) {

    }

    @FXML
    void remove(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {
        try {
            if(roomNumber.getText().equals("")){
                viewModel.searchBookings(phoneNumber.getText(), email.getText(), -1, dateFrom.getValue(), dateTo.getValue());
            }else {
                viewModel.searchBookings(phoneNumber.getText(), email.getText(), Integer.parseInt(roomNumber.getText()), dateFrom.getValue(), dateTo.getValue());
            }
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    public Region getRoot() {
        return root;
    }
}
