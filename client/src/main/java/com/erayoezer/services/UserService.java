package com.erayoezer.services;

import com.erayoezer.exceptions.UserNotSavedException;
import com.erayoezer.repository.Db;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final Db db;

    public UserService(Db db) {
        this.db = db;
    }
    public String register(String username, String bucket) throws UserNotSavedException {
        db.writeToDb(username, bucket);
        String path = db.readFromDb(username);
        if (path == null) {
            throw new UserNotSavedException();
        }
        return db.readFromDb(username);
    }
}
