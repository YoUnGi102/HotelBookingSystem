package com.example.hotelbookingsystem.server;


import com.example.hotelbookingsystem.model.ModelManager;

import java.beans.PropertyChangeSupport;
import java.rmi. RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements ClientIF {

    private final PropertyChangeSupport support;
    private ServerIF server;
    private String name = null;

    protected Client(ServerIF server, PropertyChangeSupport support) throws RemoteException {
        this.server = server;
        this.support = support;
        server.registerChatclient(this);
        sendRefresh();
    }

    public void sendRefresh() throws RemoteException {
        System.out.println(" send refresh");
        server.refresh();
    }

    @Override
    public void receive() throws RemoteException {
        support.firePropertyChange(ModelManager.REFRESH, null, null);
        System.out.println("refresh");
    }


}

