package client_broadcasting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class server {
    public static void main(String[] args) {
        int port = 1232;
        List<Trans> serverlist = new LinkedList<>();
        try (
            ServerSocket serverSocket = new ServerSocket(port);
        ) {
            while (!Thread.currentThread().isInterrupted()) {
                Socket socket = serverSocket.accept();
                
                BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                
                Trans server = new Trans(socketIn, socketOut);
                
                server.start();
                serverlist.add(server);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        for (Trans ser : serverlist) {
            ser.interrupt();
            try {
                ser.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
