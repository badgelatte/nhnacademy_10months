package com.nhnacademy.broadcastingserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class BroadcastServer extends Thread {
    static List<BroadcastServer> broadcastServers = new LinkedList<>();
    BufferedWriter writer;

    static int port = 1234;
    Socket socket;

    public BroadcastServer(Socket socket) {
        this.socket = socket;
        broadcastServers.add(this);
    }

    public void send(String message) throws IOException {
        writer.write(message);
        writer.flush();
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            this.writer = writer;

            while (!Thread.currentThread().isInterrupted()) {
                String message = reader.readLine() + "\n";

                for (BroadcastServer server : broadcastServers) {
                    server.send(message);
                }
            }
        } catch (IOException e) {
            // TODO: handle exception
        }

        try {
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(port);) {
            while (!Thread.currentThread().isInterrupted()) {
                Socket socket = server.accept();
                BroadcastServer broadcastingserver = new BroadcastServer(socket);
                broadcastingserver.start();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        for (BroadcastServer server : broadcastServers) {
            server.interrupt();
            try {
                server.join();
            } catch (InterruptedException e) {
                // TODO: handle exception
                Thread.currentThread().interrupt();
            }
        }
    }
}
