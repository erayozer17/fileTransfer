package com.erayoezer.service;

import org.springframework.stereotype.Service;

@Service
public class UploadService {
    public void uploadFile(String path) {
        System.out.println("upload file command " + path);
    }
}
