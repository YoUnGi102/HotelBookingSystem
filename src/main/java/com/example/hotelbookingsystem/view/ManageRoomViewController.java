package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.model.Room;
import com.example.hotelbookingsystem.viewModel.ManageRoomViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.sql.SQLException;

public class ManageRoomViewController implements Controller {

    @FXML
    private TextField roomNumber, floor, size, quality;

    private ViewHandler viewHandler;
    private ManageRoomViewModel viewModel;
    private Region root;
    private Controller previousView;

    private ObjectProperty<Room> room;

    public void init(ViewHandler viewHandler, ManageRoomViewModel viewModel, Region root, Controller previousView){
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
        this.previousView = previousView;

        this.room = new SimpleObjectProperty<>();
        viewModel.bindRoomProperty(room);

        room.addListener((observableValue, oldVal, newVal) -> {
            if(newVal != null){
                roomNumber.setText(String.valueOf(room.getValue().getRoomNumber()));
                floor.setText(String.valueOf(room.getValue().getFloor()));
                size.setText(String.valueOf(room.getValue().getRoomSize()));
                quality.setText(String.valueOf(room.getValue().getQuality()));
                roomNumber.setDisable(true);
            }else{
                roomNumber.setDisable(false);
                roomNumber.setText("");
                floor.setText("");
                size.setText("");
                quality.setText("");
            }
        });

    }

    public ManageRoomViewModel getViewModel(){
        return viewModel;
    }

    public void confirm() {
        if(viewModel.getRoom() == null){
            Room room = new Room(
                    Integer.parseInt(roomNumber.getText()),
                    Integer.parseInt(size.getText()),
                    Integer.parseInt(floor.getText()),
                    Integer.parseInt(quality.getText())
            );
            try {
                viewModel.addRoom(room);
            } catch (SQLException e) {
                Alert alert = new DatabaseErrorAlert();
                alert.show();
            }

        }else{
            Room room = this.room.getValue();
            room.setQuality(Integer.parseInt(quality.getText()));
            room.setRoomSize(Integer.parseInt(size.getText()));
            room.setFloor(Integer.parseInt(quality.getText()));

            try {
                viewModel.editRoom(room);
            } catch (SQLException e) {
                Alert alert = new DatabaseErrorAlert();
                alert.show();
            }
        }

        viewHandler.openView(ViewHandler.ROOM_LIST_VIEW, this);

    }

    public void back(ActionEvent actionEvent) {
        viewModel.setRoom(null);
        viewHandler.openView(ViewHandler.ROOM_LIST_VIEW, this);
    }

    public Region getRoot() {
        return root;
    }
}
