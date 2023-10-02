package com.nhnacademy;

import java.net.ServerSocket;
import java.net.Socket;

public class Ex04 {
    public static void main(String[] args) {
        int port = 1234;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket = serverSocket.accept();

            socket.getOutputStream().write("Hello!".getBytes());
            socket.getOutputStream().flush();

            socket.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
