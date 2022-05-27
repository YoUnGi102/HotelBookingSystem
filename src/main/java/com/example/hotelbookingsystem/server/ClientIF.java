package com.example.hotelbookingsystem.server;

import java.rmi. Remote;
import java.rmi.RemoteException;
public interface ClientIF extends Remote {
    void sendRefresh() throws RemoteException;
    void receive() throws RemoteException;
}
