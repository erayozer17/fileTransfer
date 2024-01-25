package com.erayoezer.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UploadService {

    public void uploadFile(String path) {
        System.out.println("upload file command " + path);
    }
}
