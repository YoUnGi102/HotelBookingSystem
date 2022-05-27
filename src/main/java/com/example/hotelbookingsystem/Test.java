package com.example.hotelbookingsystem;

import com.example.hotelbookingsystem.dao.RoomDAO;
import com.example.hotelbookingsystem.dao.RoomTable;
import com.example.hotelbookingsystem.model.Model;
import com.example.hotelbookingsystem.model.ModelManager;
import com.example.hotelbookingsystem.server.ClientDriver;
import com.example.hotelbookingsystem.server.ServerDriver;
import com.example.hotelbookingsystem.server.ServerIF;

import java.beans.PropertyChangeSupport;
import java.rmi.AlreadyBoundException;
import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Locale;

public class Test
{
    public static void main(String[] args) throws SQLException, NotBoundException, RemoteException, AlreadyBoundException
    {
        ClientDriver clientDriver = null;
        ServerDriver s;
        try{
            clientDriver = new ClientDriver(new PropertyChangeSupport(new ModelManager()));
            System.out.println(clientDriver);
        } catch (ConnectException e)
        {
            System.out.println("there is no server, creating one");
            s = ServerDriver.getInstance();
            clientDriver = new ClientDriver(new PropertyChangeSupport(new ModelManager()));
            System.out.println(clientDriver);
        } finally
        {
            clientDriver.sendRefresh();
        }

    }
}
