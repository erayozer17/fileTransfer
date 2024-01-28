package com.erayoezer.services;

import com.erayoezer.connections.ServerSocket;
import com.erayoezer.repository.Db;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UploadService extends FileCommandsTemplate {

    public UploadService(Db db, ServerSocket serverSocket) {
        super(db, serverSocket);
    }

    @Override
    protected void command(String bucketLocation) {
        try {
            serverSocket.sendFile(bucketLocation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String nameOfCommand() {
        return "upload";
    }

    public void uploadFile(String username, String path) {
        executeCommand(username, path);
    }
}
