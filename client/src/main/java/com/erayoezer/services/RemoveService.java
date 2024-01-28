package com.erayoezer.services;

import com.erayoezer.connections.ServerSocket;
import com.erayoezer.repository.Db;
import org.springframework.stereotype.Service;

@Service
public class RemoveService extends FileCommandsTemplate {

    public RemoveService(Db db, ServerSocket serverSocket) {
        super(db, serverSocket);
    }
    public void removeFile(String username, String file) {
        System.out.println("remove file command");
    }

    @Override
    protected void command(String bucketLocation) {

    }

    @Override
    public String nameOfCommand() {
        return "remove";
    }
}
