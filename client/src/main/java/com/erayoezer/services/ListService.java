package com.erayoezer.services;

import com.erayoezer.connections.ServerSocket;
import com.erayoezer.repository.Db;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class ListService extends FileCommandsTemplate {

    public ListService(Db db, ServerSocket serverSocket) {
        super(db, serverSocket);
    }
    public void listFile(String username) {
        executeCommand(username, Optional.empty());
    }

    @Override
    protected void command(String bucketLocation, Optional<String> path) {
        try {
            serverSocket.listFiles(bucketLocation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String nameOfCommand() {
        return "list";
    }
}
