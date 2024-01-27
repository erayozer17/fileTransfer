package com.erayoezer.services;

import com.erayoezer.repository.Db;
import org.springframework.stereotype.Service;

@Service
public class RemoveService {

    private final Db db;

    public RemoveService(Db db) {
        this.db = db;
    }
    public void removeFile() {
        System.out.println("remove file command");
    }
}
