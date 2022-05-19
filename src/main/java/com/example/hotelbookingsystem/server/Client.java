package com.example.hotelbookingsystem.server;


import java.rmi. RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements ClientIF
{

    private ServerIF server;
    private String name = null;

    protected Client(ServerIF server) throws RemoteException
    {
        this.server = server;
        server.registerChatclient(this);
        sendRefresh();
    }

    public void sendRefresh() throws RemoteException
    {
        server.refresh();
    }

    @Override
    public void receive() throws RemoteException
    {
        System.out.println("Refresh");
    }


}

