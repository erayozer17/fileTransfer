package com.erayoezer.connections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.Socket;

@Component
public class ServerSocket {

    @Value("${server.ip}")
    private String ip;
    @Value("${server.port}")
    private int port;

    public Socket getConnection() {
        try {
            return new Socket(ip, port);
            // server side socket below
            // ServerSocket serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            // TODO: add logging
            throw new RuntimeException(e);
        }
    }
}
