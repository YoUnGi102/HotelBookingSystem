package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.model.Room;
import com.example.hotelbookingsystem.viewModel.ManageBookingViewModel;
import com.example.hotelbookingsystem.viewModel.RoomListViewModel;
import com.example.hotelbookingsystem.viewModel.RoomTableProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class RoomListViewController implements Controller {

    @FXML
    private TableColumn<RoomTableProperty, String> availabilityCol, sizeCol, floorCol, numberCol, qualityCol;

    @FXML
    private DatePicker dateFrom, dateTo;

    @FXML
    private TextField floor, quality, size;

    @FXML
    private TableView<RoomTableProperty> table;

    @FXML
    private Button addBtn, editBtn, removeBtn, bookBtn;

    private ViewHandler viewHandler;
    private RoomListViewModel viewModel;
    private Region root;

    public void init(ViewHandler viewHandler, RoomListViewModel viewModel, Region root, Controller lastController) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;

        if (lastController instanceof ManageBookingViewController controller){
            ManageBookingViewModel manageViewModel = controller.getViewModel();
            addBtn.setVisible(false);
            removeBtn.setVisible(false);
            editBtn.setVisible(false);
            bookBtn.setText("Select");

            if (manageViewModel.getDateFrom() != null)
                dateFrom.setValue(manageViewModel.getDateFrom());
            if (manageViewModel.getDateTo() != null)
                dateTo.setValue(manageViewModel.getDateTo());

            bookBtn.setOnAction(e -> {
                Room room = table.getSelectionModel().getSelectedItem().getRoom();
                if(room != null){
                    manageViewModel.setRoom(room);
                    viewHandler.openView(ViewHandler.MANAGE_BOOKING_VIEW, controller);
                }
            });
        }else {
            bookBtn.setOnAction(actionEvent -> RoomListViewController.this.book(null));
        }

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

        try {
            int floor1, size1,quality1;
            if (floor.getText().equals("")){
                floor1 = 0;
            } else {
                floor1 = Integer.parseInt(floor.getText());
            }
            if (size.getText().equals("")){
                size1 = 0;
            } else {
                size1 = Integer.parseInt(size.getText());
            }
            if (quality.getText().equals("")){
                quality1 = 0;
            } else {
                quality1 = Integer.parseInt(quality.getText());
            }
            viewModel.searchRooms(floor1, size1, quality1, dateFrom.getValue(), dateTo.getValue());
        }catch(NumberFormatException e){
            // TODO Show error message
        }


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
