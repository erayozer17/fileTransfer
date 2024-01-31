package com.erayoezer.services;

import com.erayoezer.connections.ServerSocket;
import com.erayoezer.repository.Db;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class UploadService extends FileCommandsTemplate {

    public UploadService(Db db, ServerSocket serverSocket) {
        super(db, serverSocket);
    }

    @Override
    protected void command(String bucketLocation, Optional<String> path) {
        try {
            if (path.isEmpty()) {
                throw new RuntimeException("path should exist"); // TODO custom exception
            }
            serverSocket.sendFile(path.get());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String nameOfCommand() {
        return "upload";
    }

    public void uploadFile(String username, String path) {
        executeCommand(username, Optional.of(path));
    }
}
