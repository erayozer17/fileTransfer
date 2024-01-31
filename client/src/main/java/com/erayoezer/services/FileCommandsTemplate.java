package com.erayoezer.services;

import com.erayoezer.connections.ServerSocket;
import com.erayoezer.exceptions.UserNotFoundException;
import com.erayoezer.repository.Db;
import org.apache.logging.log4j.util.Strings;

import java.util.Optional;


public abstract class FileCommandsTemplate {
    private final Db db;
    protected final ServerSocket serverSocket;

    public FileCommandsTemplate(Db db, ServerSocket serverSocket) {
        this.db = db;
        this.serverSocket = serverSocket;
    }
    public void executeCommand(String username, Optional<String> path) {
        try {
            System.out.println("Reading the file from -> " + path);
            Optional<String> bucketLocation = db.readFromDb(username);
            if (bucketLocation.isEmpty()) {
                throw new UserNotFoundException();
            }
            command(bucketLocation.get(), path);
        } catch (Exception e) {
            throw new RuntimeException(e); // TODO logging
        }
        System.out.printf("%s file command %s%n", nameOfCommand(), path);

    }

    protected abstract void command(String bucketLocation, Optional<String> path);
    public abstract String nameOfCommand();
}
