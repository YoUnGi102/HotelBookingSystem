package com.example.hotelbookingsystem.server;

import com.example.hotelbookingsystem.model.ModelManager;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class Server
{

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        System.setProperty("java.rmi.server.hostname","127.0.0.2");
        Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

        RemoteClient client = new RemoteClientImplementation();
        Remote exported1 = UnicastRemoteObject.exportObject(client, 8888);
        registry.bind("client", exported1);

        ModelManager manager = null;
        try {
            manager = new ModelManager();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Remote exported2 = UnicastRemoteObject.exportObject(manager, 8888);
        registry.bind("manager", exported2);

        System.out.println("Server running on " + Registry.REGISTRY_PORT);
    }

}
