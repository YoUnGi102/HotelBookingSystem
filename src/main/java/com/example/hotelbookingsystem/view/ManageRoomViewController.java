package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.viewModel.ManageRoomViewModel;
import javafx.event.ActionEvent;
import javafx.scene.layout.Region;

public class ManageRoomViewController implements Controller {

    private ViewHandler viewHandler;
    private ManageRoomViewModel viewModel;
    private Region root;
    private Controller previousView;

    public void init(ViewHandler viewHandler, ManageRoomViewModel viewModel, Region root, Controller previousView){
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
        this.previousView = previousView;
    }


    public void confirm(ActionEvent actionEvent) {

    }

    public void back(ActionEvent actionEvent) {
        viewHandler.openView(ViewHandler.ROOM_LIST_VIEW, this);
    }

    public Region getRoot() {
        return root;
    }
}
