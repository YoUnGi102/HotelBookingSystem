package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.viewModel.BookingListViewModel;
import com.example.hotelbookingsystem.viewModel.BookingTableProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class BookingListViewController implements Controller {

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

    private Controller previousView;

    public void init(ViewHandler viewHandler, BookingListViewModel viewModel, Region root, Controller previousView) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;

        this.previousView = previousView;

        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        roomNumberCol.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        dateFromCol.setCellValueFactory(new PropertyValueFactory<>("dateFrom"));
        dateToCol.setCellValueFactory(new PropertyValueFactory<>("dateTo"));

        viewModel.bindTableItemsProperty(table.itemsProperty());


    }

    public BookingListViewModel getViewModel() {
        return viewModel;
    }

    @FXML
    void add() {
        if(table.getItems() != null)
            table.getItems().clear();
        viewHandler.openView(ViewHandler.MANAGE_BOOKING_VIEW, this);
    }

    @FXML
    void back() {
        if(table.getItems() != null)
            table.getItems().clear();
        viewHandler.openView(ViewHandler.MENU_VIEW, this);
    }

    @FXML
    void edit() {
        if (table.getSelectionModel().getSelectedItem() != null){
            Booking booking = table.getSelectionModel().getSelectedItem().getBooking();
            viewModel.setCurrentBooking(booking);
            table.getItems().clear();
            viewHandler.openView(ViewHandler.MANAGE_BOOKING_VIEW, this);
        }
    }

    @FXML
    void remove() {
        try {
            BookingTableProperty property = table.getSelectionModel().getSelectedItem();
            table.getItems().remove(property);
            viewModel.removeBooking(property.getBooking());
            table.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            // TODO ADD ACTION
        }
    }

    @FXML
    void search() {
        try {
            if(roomNumber.getText().equals("")){
                viewModel.searchBookings(phoneNumber.getText(), email.getText(), -1, dateFrom.getValue(), dateTo.getValue());
            }else {
                viewModel.searchBookings(phoneNumber.getText(), email.getText(), Integer.parseInt(roomNumber.getText()), dateFrom.getValue(), dateTo.getValue());
            }
        }catch (IllegalArgumentException | SQLException e){
            e.printStackTrace();
        }
    }

    public void reset(){
        if(table.getItems() != null)
            table.getItems().clear();
        previousView = null;
    }

    public Region getRoot() {
        return root;
    }
}
