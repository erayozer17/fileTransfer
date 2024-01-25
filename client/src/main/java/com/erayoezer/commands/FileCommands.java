package com.erayoezer.commands;

import com.erayoezer.service.UploadService;
import com.erayoezer.service.DownloadService;
import com.erayoezer.service.ListService;
import com.erayoezer.service.RemoveService;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.springframework.stereotype.Component;

@Component
@Command(group = "File Commands")
public class FileCommands implements Runnable{

    private final UploadService uploadService;
    private final DownloadService downloadService;
    private final ListService listService;
    private final RemoveService removeService;

    public FileCommands(UploadService uploadService,
                        DownloadService downloadService,
                        ListService listService,
                        RemoveService removeService) {
        this.uploadService = uploadService;
        this.downloadService = downloadService;
        this.listService = listService;
        this.removeService = removeService;
    }

    @Command(command = "upload", description = "Uploads file.")
    public void uploadFile(
        @Option(required = true, description = "Path of the file", longNames = "path", shortNames = 'p') String path
    ) {
        uploadService.uploadFile(path);
    }

    @Command(command = "download", description = "Uploads file.")
    public void downloadFile() {
        downloadService.downloadFile();
    }

    @Command(command = "remove", description = "Uploads file.")
    public void removeFile() {
        removeService.removeFile();
    }

    @Command(command = "list", description = "Uploads file.")
    public void listFile() {
        listService.listFile();
    }
    @Override
    public void run() {
        System.out.println("File commands");
    }
}
