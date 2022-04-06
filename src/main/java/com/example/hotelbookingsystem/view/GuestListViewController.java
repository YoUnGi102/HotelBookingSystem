package com.example.hotelbookingsystem.view;

import com.example.hotelbookingsystem.viewModel.GuestListViewModel;
import javafx.scene.layout.Region;

public class GuestListViewController {

    private ViewHandler viewHandler;
    private GuestListViewModel viewModel;
    private Region root;

    public void init(ViewHandler viewHandler, GuestListViewModel viewModel, Region root){
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
    }

    public Region getRoot() {
        return root;
    }
}
