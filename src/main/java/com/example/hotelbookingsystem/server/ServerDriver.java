package com.example.hotelbookingsystem.server;

import java.net. MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerDriver
{
    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException
    {
        ServerIF server = new ServerImpl();
        Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        //Remote exported = UnicastRemoteObject.exportObject(server, 8888);
        registry.bind("RMIChatServer", server);


        //Naming.rebind("RMIChatServer", new ChatServer(1));
    }
}