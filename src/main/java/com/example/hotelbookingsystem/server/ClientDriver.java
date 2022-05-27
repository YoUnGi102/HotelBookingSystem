package com.example.hotelbookingsystem.server;

import com.example.hotelbookingsystem.model.ModelManager;

import java.beans.PropertyChangeSupport;
import java.rmi. NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientDriver {

    private ClientIF client;

    public ClientDriver(PropertyChangeSupport support) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost", Registry.REGISTRY_PORT);
        ServerIF server = (ServerIF) registry.lookup("server");
        System.out.println(server);
        //ChatServerIF chatServer = (ChatServerIF) Naming.lookup(chatServerURL);
        this.client = new Client(server, support);
    }

    public void sendRefresh() throws RemoteException {
        client.sendRefresh();
    }


}
