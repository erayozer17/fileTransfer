package com.erayoezer.services;

import com.erayoezer.exceptions.UserNotSavedException;
import com.erayoezer.repository.Db;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final Db db;

    public UserService(Db db) {
        this.db = db;
    }
    public String register(String username, String bucket) throws UserNotSavedException {
        db.writeToDb(username, bucket);
        Optional<String> path = db.readFromDb(username);
        if (path.isEmpty()) {
            throw new UserNotSavedException();
        }
        return path.get();
    }
}
