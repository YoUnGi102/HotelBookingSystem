package com.example.hotelbookingsystem.server;

import java.rmi.Remote;
import java.rmi. RemoteException;

public interface ServerIF extends Remote {
    void registerChatclient (ClientIF chatClient) throws RemoteException;
    void refresh() throws RemoteException;
}
