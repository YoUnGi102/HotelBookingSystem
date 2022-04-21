package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.viewModel.RoomListViewModel;
import javafx.scene.layout.Region;

public class RoomListViewController {

    private ViewHandler viewHandler;
    private RoomListViewModel viewModel;
    private Region root;

    public void init(ViewHandler viewHandler, RoomListViewModel viewModel, Region root){
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
    }

    public Region getRoot() {
        return root;
    }
}
