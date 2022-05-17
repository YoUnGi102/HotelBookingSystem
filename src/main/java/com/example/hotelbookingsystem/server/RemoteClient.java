package com.example.hotelbookingsystem.server;


import java.io.IOException;
import java.rmi.Remote;

public interface RemoteClient extends Remote {

    void addClient(ClientInterface client) throws IOException;
    void sendMessage(String message) throws IOException;

}
