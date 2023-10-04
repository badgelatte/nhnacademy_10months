package com.nhnacademy.dkjf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Quiz12_BroadcastingServer extends Thread {
    Socket socket;
    static List<Quiz12_BroadcastingServer> serverList = new LinkedList<>();
    BufferedWriter writer;
    
    //socket을 주면 동작을 하겠다
    public Quiz12_BroadcastingServer(Socket socket) {
        this.socket = socket;
    }
    
    public void write(String line) throws IOException{
        writer.write(line);
        writer.flush();
    }
    
    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {
            while(!Thread.currentThread().isInterrupted()) {
                String line = reader.readLine() + "\n";
                for (Quiz12_BroadcastingServer server : Quiz12_BroadcastingServer.serverList) {
                    server.write(line);
                    
                }
            }
        } catch (IOException ignore) {
            //
        }

        try {
            socket.close();
        } catch (IOException e) {
            //
        }
    }
    
    public static void main(String[] args) {
        // server socket고정
        int port = 1234;

        try (
            ServerSocket serverSocket = new ServerSocket(port)
        ) {
            while(Thread.currentThread().isInterrupted()){
                Socket socket = serverSocket.accept();

                // socket땜에 멈추지 않음
                EchoServer server = new EchoServer(socket);
                server.start();

            }
            
        } catch (IOException e) {
            // TODO: handle exception
        }

    }
}
