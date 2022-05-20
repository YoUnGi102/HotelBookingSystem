package com.example.hotelbookingsystem.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class ServerImpl extends UnicastRemoteObject implements ServerIF
{

    private ArrayList<ClientIF> clients;
    protected ServerImpl() throws RemoteException {
        clients = new ArrayList<ClientIF>();
    }
    public synchronized void registerChatclient(ClientIF chatclient) throws RemoteException {
        this.clients.add(chatclient);
    }
    public synchronized void refresh() throws RemoteException {
//            int i =0;
//            while (i < chatclients.size()) {
//            chatclients.get(i++).retrieveMessage (message);

        for (ClientIF client: clients)
        {
            client.receive();
        }
    }
}

