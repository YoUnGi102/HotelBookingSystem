package com.example.hotelbookingsystem.server;

import java.rmi. NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientDriver
{
    public static void main(String[] args) throws RemoteException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry("localhost", Registry.REGISTRY_PORT);
        ServerIF chatServer = (ServerIF) registry.lookup("RMIChatServer");
        //ChatServerIF chatServer = (ChatServerIF) Naming.lookup(chatServerURL);
        ClientIF client = new Client(chatServer);
    }
}
