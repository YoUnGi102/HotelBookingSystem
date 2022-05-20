package com.example.hotelbookingsystem.server;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi. RemoteException;

public interface ServerIF extends Remote, Serializable {
    void registerChatclient (ClientIF chatClient) throws RemoteException;
    void refresh() throws RemoteException;
}
