package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.model.Receptionist;
import com.example.hotelbookingsystem.model.Room;
import com.example.hotelbookingsystem.view.alert.DatabaseErrorAlert;
import com.example.hotelbookingsystem.view.alert.ErrorAlert;
import com.example.hotelbookingsystem.viewModel.ManageBookingViewModel;
import com.example.hotelbookingsystem.viewModel.RoomListViewModel;
import com.example.hotelbookingsystem.viewModel.RoomTableProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;
import java.sql.SQLException;

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

    private Controller previousView;

    public void init(ViewHandler viewHandler, RoomListViewModel viewModel, Region root, Controller lastController) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;

        previousView = lastController;

        if (lastController instanceof ManageBookingViewController controller){
            ManageBookingViewModel manageViewModel = controller.getViewModel();
            addBtn.setVisible(false);
            removeBtn.setVisible(false);
            editBtn.setVisible(false);
            bookBtn.setText("Select");

            if (manageViewModel.getGuests().size() > 0)
                size.setText(String.valueOf(manageViewModel.getGuests().size()));
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
            if(viewModel.getStaff() instanceof Receptionist){
                addBtn.setVisible(false);
                removeBtn.setVisible(false);
                editBtn.setVisible(false);
            }
            bookBtn.setOnAction(actionEvent -> book(null));
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

    public RoomListViewModel getViewModel() {
        return viewModel;
    }

    public void reset(){
        if(table.getItems() != null)
            table.getItems().clear();
        previousView = null;
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void add(ActionEvent actionEvent) {
        viewModel.setCurrentRoom(null);
        viewHandler.openView(ViewHandler.MANAGE_ROOM_VIEW, this);
    }

    public void edit(ActionEvent actionEvent) {
        if(table.getSelectionModel().getSelectedItem() != null) {
            viewModel.setCurrentRoom(table.getSelectionModel().getSelectedItem().getRoom());
            viewHandler.openView(ViewHandler.MANAGE_ROOM_VIEW, this);
        }else{
            new ErrorAlert("No Room Selected").show();
        }
    }

    public void remove(ActionEvent actionEvent) {
        if(table.getSelectionModel().getSelectedItem() != null){
            try {
                viewModel.removeRoom(table.getSelectionModel().getSelectedItem().getRoom());
            } catch (SQLException e) {
                Alert alert = new DatabaseErrorAlert();
                alert.show();
            } catch (RemoteException e) {
                // TODO ADD ACTION
            }
        }

    }

    public void book(ActionEvent actionEvent) {
        if(table.getSelectionModel().getSelectedItem() != null){
            viewModel.setCurrentRoom(table.getSelectionModel().getSelectedItem().getRoom());
            viewHandler.openView(ViewHandler.MANAGE_BOOKING_VIEW, this);
        }else {
            new ErrorAlert("No Room Selected").show();
        }

    }

    public void back() {
        if(previousView instanceof ManageBookingViewController) {
            reset();
            viewHandler.openView(ViewHandler.MANAGE_BOOKING_VIEW, this);
        }else {
            reset();
            viewHandler.openView(ViewHandler.MENU_VIEW, this);
        }
    }

    public Region getRoot() {
        return root;
    }

}
