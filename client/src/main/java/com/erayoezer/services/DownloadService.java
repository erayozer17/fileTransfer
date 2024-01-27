package com.erayoezer.services;

import com.erayoezer.repository.Db;
import org.springframework.stereotype.Service;

@Service
public class DownloadService {

    private final Db db;

    public DownloadService(Db db) {
        this.db = db;
    }
    public void downloadFile() {
        System.out.println("download file command");
    }
}
