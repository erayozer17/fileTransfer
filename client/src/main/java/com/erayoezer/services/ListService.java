package com.erayoezer.services;

import com.erayoezer.connections.ServerSocket;
import com.erayoezer.repository.Db;
import org.springframework.stereotype.Service;

@Service
public class ListService extends FileCommandsTemplate {

    public ListService(Db db, ServerSocket serverSocket) {
        super(db, serverSocket);
    }
    public void listFile(String username) {
        System.out.println("list file command");
    }

    @Override
    protected void command(String bucketLocation) {

    }

    @Override
    public String nameOfCommand() {
        return "list";
    }
}
