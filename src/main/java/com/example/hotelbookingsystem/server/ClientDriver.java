package com.example.hotelbookingsystem.server;

import java.beans.PropertyChangeSupport;
import java.rmi. NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientDriver {

    private ClientIF client;

    public ClientDriver(PropertyChangeSupport support) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost", Registry.REGISTRY_PORT);
        ServerIF chatServer = (ServerIF) registry.lookup("RMIChatServer");
        //ChatServerIF chatServer = (ChatServerIF) Naming.lookup(chatServerURL);
        this.client = new Client(chatServer, support);
    }

    public void sendRefresh() throws RemoteException {
        client.sendRefresh();
    }


}
