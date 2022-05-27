package com.example.hotelbookingsystem.server;

import java.net. MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerDriver implements Runnable
{

    private static ServerDriver serverDriver;
    private ServerDriver()
    {
        
    }
    
    public static ServerDriver getInstance()
    {
        if(serverDriver == null)
        {
            serverDriver = new ServerDriver();
        }
        return serverDriver;
    }
//    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException
//    {
//        ServerIF server = new ServerImpl();
//        Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
//        //Remote exported = UnicastRemoteObject.exportObject(server, 8888);
//        registry.bind("RMIChatServer", server);
//        //Naming.rebind("RMIChatServer", new ChatServer(1));
//    }

    @Override
    public void run()
    {
        ServerIF server = null;
        try
        {
            server = new ServerImpl();
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
        Registry registry = null;
        try
        {
            registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
        //Remote exported = UnicastRemoteObject.exportObject(server, 8888);
        try
        {
            registry.bind("RMIChatServer", server);
        } catch (RemoteException e)
        {
            e.printStackTrace();
        } catch (AlreadyBoundException e)
        {
            e.printStackTrace();
        }
        //Naming.rebind("RMIChatServer", new ChatServer(1));
    }
}