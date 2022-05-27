package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.view.alert.DatabaseErrorAlert;
import com.example.hotelbookingsystem.view.alert.ErrorAlert;
import com.example.hotelbookingsystem.viewModel.StaffListViewModel;
import com.example.hotelbookingsystem.viewModel.StaffTableProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

import java.sql.SQLException;

public class StaffListViewController implements Controller {

    private ViewHandler handler;
    private StaffListViewModel viewModel;
    private Region root;
    private Controller previousView;

    @FXML
    private TableView<StaffTableProperty> table;
    @FXML
    private TableColumn<String, StaffTableProperty> usernameCol, firstNameCol, lastNameCol, emailCol, phoneNumberCol;

    public void init(ViewHandler handler, StaffListViewModel viewModel, Region root, Controller previousView){
        this.handler = handler;
        this.viewModel = viewModel;
        this.root = root;
        this.previousView = previousView;

        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        viewModel.bindListObjectProperty(table.itemsProperty());

        try {
            viewModel.getAllStaff();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Region getRoot() {
        return root;
    }

    public StaffListViewModel getViewModel(){
        return viewModel;
    }

    @FXML
    void add() {
        handler.openView(ViewHandler.MANAGE_STAFF_VIEW, this);
    }

    @FXML
    void back() {
        handler.openView(ViewHandler.MENU_VIEW, this);
    }

    @FXML
    void edit() {
        if(table.getSelectionModel() != null){
            viewModel.setCurrentStaff(table.getSelectionModel().getSelectedItem().getStaff());
            handler.openView(ViewHandler.MANAGE_STAFF_VIEW, this);
        }else{
            (new ErrorAlert("No Staff Member Selected")).show();
        }
    }

    public void reset(){
        try {
            viewModel.getAllStaff();
        } catch (SQLException e) {
            Alert alert = new DatabaseErrorAlert();
            alert.show();
        }
    }

    @FXML
    void remove() {
        if(table.getSelectionModel() != null){
            try {
                viewModel.removeStaff(table.getSelectionModel().getSelectedItem().getStaff());
                viewModel.getAllStaff();
            } catch (SQLException e) {
                Alert alert = new DatabaseErrorAlert();
                alert.show();
            }
        }else{
            (new ErrorAlert("No Staff Member Selected")).show();
        }
    }
}
