package com.nhnacademy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Exam04_ServerSocketClient {
    public static void main(String[] args) {
        int port = 1234;

        try(ServerSocket serverSocket = new ServerSocket(port)) {
            // socket을 만드는 과정까지만 다르고 다 똑같다
            Socket socket = serverSocket.accept();

            socket.getOutputStream().write("Hello!".getBytes());
            socket.getOutputStream().flush();

            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
