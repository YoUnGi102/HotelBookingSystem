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

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        roomNumberCol.setCellValueFactory(new PropertyValueFactory<>("dateFrom"));
        dateFromCol.setCellValueFactory(new PropertyValueFactory<>("dateTo"));
        dateToCol.setCellValueFactory(new PropertyValueFactory<>("availability"));

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
            viewModel.searchBookings(phoneNumber.getText(), email.getText(), Integer.parseInt(roomNumber.getText()), dateFrom.getValue(), dateTo.getValue());
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

}
