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
public class EchoServer_Broadcasting extends Thread{
    static List<EchoServer_Broadcasting> serverList = new LinkedList<>();
    Socket socket;
    BufferedWriter writer;

    //socket을 주면 동작을 하겠다
    public EchoServer_Broadcasting(Socket socket) {
        this.socket = socket;
        serverList.add(this);
    }

    // 다른 client들에게 메세지 보내기
    public void send(String message) throws IOException{
        // message를 받으면 output으로 보내라
        writer.write(message);
        writer.flush();
    }
    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {
            this.writer = writer;
            while(!Thread.currentThread().isInterrupted()) {
                String line = reader.readLine() + "\n";
                // 내가 가진 메세지를 모두에게 보낸다
                for(EchoServer_Broadcasting server : serverList) {
                    server.send(line);
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
                EchoServer_Broadcasting server = new EchoServer_Broadcasting(socket);
                server.start();

            }
            
        } catch (IOException e) {
            // TODO: handle exception
        }

        for(EchoServer_Broadcasting server : serverList) {
            server.interrupt(); // server 멈춤
            try{
                server.join();      // 다 끝날때까지 server가 기다림
            } catch(InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void write(String line) {
    }
}
