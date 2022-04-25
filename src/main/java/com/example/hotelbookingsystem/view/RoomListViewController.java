package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.viewModel.RoomListViewModel;
import com.example.hotelbookingsystem.viewModel.RoomTableProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class RoomListViewController {

    @FXML
    private TableColumn<RoomTableProperty, String> availabilityCol, sizeCol, floorCol, numberCol, qualityCol;

    @FXML
    private DatePicker dateFrom, dateTo;

    @FXML
    private TextField floor, quality, size;

    @FXML
    private TableView<RoomTableProperty> table;

    private ViewHandler viewHandler;
    private RoomListViewModel viewModel;
    private Region root;

    public void init(ViewHandler viewHandler, RoomListViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;


        numberCol.setCellValueFactory(new PropertyValueFactory<>("number") {
        });
        floorCol.setCellValueFactory(new PropertyValueFactory<>("floor"));
        //emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        qualityCol.setCellValueFactory(new PropertyValueFactory<>("quality"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        availabilityCol.setCellValueFactory(new PropertyValueFactory<>("availability"));

        viewModel.bindTableItemsProperty(table.itemsProperty());
    }


    public void search(ActionEvent actionEvent) {
        viewModel.showAllBookedRooms();
    }

    public void add(ActionEvent actionEvent) {
    }

    public void edit(ActionEvent actionEvent) {
    }

    public void remove(ActionEvent actionEvent) {
    }

    public void book(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public Region getRoot() {
        return root;
    }

}
