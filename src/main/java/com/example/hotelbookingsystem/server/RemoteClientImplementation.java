package com.example.hotelbookingsystem.server;

import java.io.IOException;
import java.util.ArrayList;

public class RemoteClientImplementation implements RemoteClient
{

    ArrayList<ClientInterface> clients = new ArrayList<>();
    @Override
    public void addClient(ClientInterface client) throws IOException
    {
        System.out.println("Client added");
        clients.add(client);
    }

    @Override
    public void sendMessage(String message) throws IOException
    {
        System.out.println("Message Something " + clients.size());
        for (ClientInterface client : clients) {
            client.receiveMessage(message);
        }
    }

}
