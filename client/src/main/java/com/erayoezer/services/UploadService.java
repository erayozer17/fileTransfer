package com.erayoezer.services;

import com.erayoezer.repository.Db;
import org.springframework.stereotype.Service;

@Service
public class UploadService {

    private final Db db;

    public UploadService(Db db) {
        this.db = db;
    }
    public void uploadFile(String path) {
        System.out.println("upload file command " + path);
    }
}
