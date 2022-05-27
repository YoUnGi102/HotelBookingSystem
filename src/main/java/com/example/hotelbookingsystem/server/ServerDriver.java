package com.example.hotelbookingsystem.server;

import java.net. MalformedURLException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;

public class ServerDriver
{

    ServerIF server;
    Registry registry;
    private static ServerDriver serverDriver;
    private ServerDriver() throws RemoteException, AlreadyBoundException
    {
        ServerIF server = new ServerImpl();
        Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        //Remote exported = UnicastRemoteObject.exportObject(server, 8888);
        registry.bind("server", server);
    }


    public static ServerDriver getInstance() throws RemoteException, AlreadyBoundException
    {
        if(serverDriver == null)
        {
            serverDriver = new ServerDriver();

        }
        return serverDriver;
    }



}