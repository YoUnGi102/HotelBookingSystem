package com.example.hotelbookingsystem;

import com.example.hotelbookingsystem.model.Model;
import com.example.hotelbookingsystem.model.ModelManager;
import com.example.hotelbookingsystem.view.ViewHandler;
import com.example.hotelbookingsystem.viewModel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws SQLException {
        Model model = null;
        try {
            model = new ModelManager();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e)
        {
            e.printStackTrace();
        }
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }
}
