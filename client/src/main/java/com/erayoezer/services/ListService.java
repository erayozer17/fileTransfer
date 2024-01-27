package com.erayoezer.services;

import com.erayoezer.repository.Db;
import org.springframework.stereotype.Service;

@Service
public class ListService {
    private final Db db;

    public ListService(Db db) {
        this.db = db;
    }
    public void listFile() {
        System.out.println("list file command");
    }
}
