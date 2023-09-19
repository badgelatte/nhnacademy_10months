package com.nhnacademy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Quiz08_ServerSocketIp {
    public static void main(String[] args) {
        int port = 1234;

        try(ServerSocket serverSocket = new ServerSocket(port)) {
            // socket을 만드는 과정까지만 다르고 다 똑같다
            Socket socket = serverSocket.accept();

            System.out.println(socket.getInetAddress().getHostAddress() + " : " + socket.getPort());
            socket.getOutputStream().write("Hello!\n".getBytes());
            socket.getOutputStream().flush();

            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
