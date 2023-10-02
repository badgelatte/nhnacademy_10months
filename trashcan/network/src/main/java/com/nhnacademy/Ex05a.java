package com.nhnacademy;

import java.net.ServerSocket;
import java.net.Socket;

public class Ex05a {
    public static void main(String[] args) {
        int port = 1234;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket;
            while ((socket = serverSocket.accept()) != null) {
                System.out.println(socket.getInetAddress().getHostAddress() + " : " + socket.getPort());
                socket.getOutputStream().write("Hello".getBytes());
                socket.getOutputStream().flush();
                socket.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
