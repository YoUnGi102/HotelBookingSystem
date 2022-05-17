package com.example.hotelbookingsystem.server;

import java.io.IOException;
import java.io.Serializable;

public interface ClientInterface extends Serializable
{
    void sendMessage(String message) throws IOException;
    void receiveMessage(String message) throws IOException;
}
