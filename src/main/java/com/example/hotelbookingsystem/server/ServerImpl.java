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

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for (ClientIF client: clients)
                {
                    try
                    {
                        client.receive();
                    } catch (RemoteException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }
}

