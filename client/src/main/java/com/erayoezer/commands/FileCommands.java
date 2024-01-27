package com.erayoezer.commands;

import com.erayoezer.exceptions.UserNotSavedException;
import com.erayoezer.services.*;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.springframework.stereotype.Component;

@Component
@Command(group = "File Commands")
public class FileCommands {

    private final UploadService uploadService;
    private final DownloadService downloadService;
    private final ListService listService;
    private final RemoveService removeService;
    private final UserService userService;

    public FileCommands(UploadService uploadService,
                        DownloadService downloadService,
                        ListService listService,
                        RemoveService removeService,
                        UserService userService) {
        this.uploadService = uploadService;
        this.downloadService = downloadService;
        this.listService = listService;
        this.removeService = removeService;
        this.userService = userService;
    }

    @Command(command = "upload", description = "Uploads file.")
    public void uploadFile(
        @Option(required = true, description = "Path of the file", longNames = "path", shortNames = 'p') String path
    ) {
        uploadService.uploadFile(path);
    }

    @Command(command = "download", description = "Uploads file.")
    public void downloadFile(
        @Option(required = true, description = "Path of the file", longNames = "path", shortNames = 'p') String path
    ) {
        downloadService.downloadFile();
    }

    @Command(command = "remove", description = "Uploads file.")
    public void removeFile(
        @Option(required = true, description = "Path of the file", longNames = "path", shortNames = 'p') String path
    ) {
        removeService.removeFile();
    }

    @Command(command = "list", description = "Uploads file.")
    public void listFile(
        @Option(required = true, description = "Path of the file", longNames = "path", shortNames = 'p') String path
    ) {
        listService.listFile();
    }

    @Command(command = "register", description = "Uploads file.")
    public String register(
            @Option(required = true, description = "Username of the account", longNames = "username", shortNames = 'u') String username,
            @Option(required = true, description = "The location where the files will be saved", longNames = "bucket", shortNames = 'b') String bucket
    ) {
        try {
            return userService.register(username, bucket);
        } catch (UserNotSavedException e) {
            throw new RuntimeException(e);//TODO log
        }
    }
}
