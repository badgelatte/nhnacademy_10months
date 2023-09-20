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
        List<EchoServer> serverList = new LinkedList<>();

        try (
            ServerSocket serverSocket = new ServerSocket(port)
        ) {
            while(Thread.currentThread().isInterrupted()){
                Socket socket = serverSocket.accept();

                // socket땜에 멈추지 않음
                EchoServer server = new EchoServer(socket);
                server.start();

                serverList.add(server);
            }
            
        } catch (IOException e) {
            // TODO: handle exception
        }

        for(EchoServer server : serverList) {
            server.interrupt(); // server 멈춤
            try{
                server.join();      // 다 끝날때까지 server가 기다림
            } catch(InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
