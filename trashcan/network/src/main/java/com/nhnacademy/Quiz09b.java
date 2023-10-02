package com.nhnacademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

// Echo Server
public class Quiz09b {
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(12345);
                Socket socket = serverSocket.accept();
                BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {

            String line;
            while ((line = socketIn.readLine()) != null) {
                socketOut.write(line + "\n");
                socketOut.flush();
            }

        } catch (IOException e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }
}
