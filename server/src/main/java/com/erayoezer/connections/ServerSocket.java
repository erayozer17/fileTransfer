package com.erayoezer.connections;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSocket {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private final ExecutorService fileExecutor = Executors.newSingleThreadExecutor(); // Single thread for file writes

    public ServerSocket() throws IOException {
        properties = new Properties();
        loadProperties();
        int port = Integer.parseInt(getProperty("serverPort"));
        String hostname = getProperty("serverIp");
        // TODO debug logging for port
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(hostname, port));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    private void start() {
        try {
            while (true) {
                System.out.println("before select");
                selector.select(); // This is a blocking call, but it's non-blocking in terms of I/O.
                System.out.println("after select");
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    if (key.isAcceptable()) {
                        handleAccept(key);
                    }
                    if (key.isReadable()) {
                        handleRead(key);
                    }
                    iter.remove();
                }
                System.out.println("end of while");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                selector.close();
                serverSocketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = serverChannel.accept();
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_READ);
    }

    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(256);
        int bytesRead = clientChannel.read(buffer);
        if (bytesRead == -1) {
            clientChannel.close();
            return;
        }
        buffer.flip();
        String message = StandardCharsets.UTF_8.decode(buffer).toString();
        fileExecutor.submit(() -> writeToFile(message));
    }

    private void writeToFile(String message) {
        String filePath = System.getProperty("user.dir") + File.separator + "server_output.txt";
        Path path = Paths.get(filePath);
        System.out.println("writing to -> " + filePath);
        try {
            Files.write(path, message.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Properties properties;

    public static void main(String[] args) {


        try {
            ServerSocket server = new ServerSocket();
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadProperties() {
        // Try with resources to ensure the InputStream is closed after use
        try (InputStream input = ServerSocket.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return;
            }
            // Load the properties file from the classpath
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();// TODO logging
        }
    }

    private static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
