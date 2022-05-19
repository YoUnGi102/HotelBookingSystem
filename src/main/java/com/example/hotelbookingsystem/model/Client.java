//package com.example.hotelbookingsystem.model;
//
//import com.example.hotelbookingsystem.server.ClientInterface;
//import com.example.hotelbookingsystem.server.RemoteClient;
//import java.beans.PropertyChangeSupport;
//
//import java.io.IOException;
//import java.rmi.NotBoundException;
//import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;
//
//import static com.example.hotelbookingsystem.model.ModelManager.UPDATE;
//
//
//public class Client implements ClientInterface
//{
//    private final PropertyChangeSupport support;
//    private RemoteClient client;
//
//    public Client(String host, int port, PropertyChangeSupport support) throws IOException, NotBoundException {
//        this.support = support;
//        Registry registry = LocateRegistry.getRegistry(host, port);
//        client = (RemoteClient) registry.lookup("client");
//        client.addClient(this);
//    }
//
//    @Override
//    public void sendMessage(String message) throws IOException {
//        System.out.println("Message sent");
//        client.sendMessage(message);
//    }
//
//    @Override
//    public void receiveMessage(String message) throws IOException {
//        support.firePropertyChange(UPDATE, "", message);
//    }
//}
