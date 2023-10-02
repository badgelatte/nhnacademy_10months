package com.nhnacademy.multiserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int port = 1234;
        List<EchoServer> serverList = new LinkedList<>();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (!Thread.currentThread().isInterrupted()) {
                Socket socket = serverSocket.accept();

                EchoServer echoServer = new EchoServer(socket);
                echoServer.start();

                serverList.add(echoServer);
            }
        } catch (IOException e) {
            // TODO: handle exception
        }

        for (EchoServer server : serverList) {
            server.interrupt();
            try {
                server.join();
            } catch (InterruptedException ignore) {
                // TODO: handle exception
                Thread.currentThread().interrupt();
            }
        }
    }
}
