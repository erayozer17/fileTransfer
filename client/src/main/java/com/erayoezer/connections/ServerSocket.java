package com.erayoezer.connections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Component
public class ServerSocket {

    private SocketChannel clientChannel;

    public ServerSocket(
        @Value("${server.ip}") String hostname,
        @Value("${server.port}") int port
    ) throws IOException {
        InetSocketAddress hostAddress = new InetSocketAddress(hostname, port);
        clientChannel = SocketChannel.open();
        clientChannel.configureBlocking(false);
        clientChannel.connect(hostAddress);

        while (!clientChannel.finishConnect()) {
            System.out.println("Connecting to Server...");
        }
        System.out.println("Connected to Server.");
    }

    public void sendFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (fileChannel.read(buffer) > 0) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                clientChannel.write(buffer);
            }
            buffer.clear();
        }
        fileChannel.close();
        System.out.println("File sent to server.");
        clientChannel.close();
        System.out.println("Channel is closed.");
    }

    public void commandTemplate(String folderPath, String command) throws IOException {
        OutputStream outputStream = clientChannel.socket().getOutputStream();

        String fullCommand = command + " " + folderPath;
        outputStream.write(fullCommand.getBytes());

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int bytesRead = clientChannel.read(buffer);
        if (bytesRead > 0) {
            buffer.flip();
            byte[] responseBytes = new byte[buffer.remaining()];
            buffer.get(responseBytes);
            String response = new String(responseBytes);
            System.out.println("Server response: " + response);
        }

        clientChannel.close();
    }

    public void listFiles(String folderPath) throws IOException {
        commandTemplate(folderPath, "LIST_FILES");
    }

    public void removeFile(String folderPath) throws IOException {
        commandTemplate(folderPath, "REMOVE_FILE");
    }

    public void downloadFile(String folderPath) throws IOException {
        commandTemplate(folderPath, "READ_FILE");
    }
}
