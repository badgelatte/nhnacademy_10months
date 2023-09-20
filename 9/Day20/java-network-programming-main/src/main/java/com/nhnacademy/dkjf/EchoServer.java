package com.nhnacademy.dkjf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

// EchoServer 서비스 돌림
public class EchoServer extends Thread{
    Socket socket;

    //socket을 주면 동작을 하겠다
    public EchoServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {
            while(!Thread.currentThread().isInterrupted()) {
                writer.write(reader.readLine() + "\n");
                writer.flush();
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
                EchoServer server = new EchoServer(socket);
                server.start();
            }
            
        } catch (IOException e) {
            // TODO: handle exception
        }
    }
}
