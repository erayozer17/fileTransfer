package com.erayoezer.services;

import com.erayoezer.connections.ServerSocket;
import com.erayoezer.repository.Db;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DownloadService extends FileCommandsTemplate {

    public DownloadService(Db db, ServerSocket serverSocket) {
        super(db, serverSocket);
    }
    public void downloadFile(String username, String path, String file) {
        System.out.println("download file command");
    }

    @Override
    protected void command(String bucketLocation, Optional<String> path) {

    }

    @Override
    public String nameOfCommand() {
        return "download";
    }
}
