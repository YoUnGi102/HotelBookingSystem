package com.example.hotelbookingsystem;

import com.example.hotelbookingsystem.model.Model;
import com.example.hotelbookingsystem.model.ModelManager;
import com.example.hotelbookingsystem.view.ViewHandler;
import com.example.hotelbookingsystem.viewModel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) {
        Model model = new ModelManager();
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }
}
