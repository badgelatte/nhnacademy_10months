package com.nhnacademy.multiserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class EchoServer extends Thread {
    Socket socket;

    public EchoServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {

            while (!Thread.currentThread().isInterrupted()) {
                writer.write(reader.readLine() + "\n");
                writer.flush();

            }
        } catch (IOException ignore) {
            // TODO: handle exception
        }

        try {
            socket.close();
        } catch (IOException ignore) {
            // TODO: handle exception
        }
    }
}
