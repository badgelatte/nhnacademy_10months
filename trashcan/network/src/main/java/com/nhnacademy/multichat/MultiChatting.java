package com.nhnacademy.multichat;

import java.net.ServerSocket;
import java.net.Socket;

public class MultiChatting {
    static int port = 1234;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (!Thread.currentThread().isInterrupted()) {
                Socket socket = serverSocket.accept();

                ChatServer chatServer = new ChatServer(socket);
                chatServer.start();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
