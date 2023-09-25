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
import java.util.concurrent.atomic.AtomicInteger;

// EchoServer 서비스 돌림
public class MultiConnectionEchoServer extends Thread{
    static List<MultiConnectionEchoServer> serverList = new LinkedList<>();
    Socket socket;
    BufferedWriter writer;

    //socket을 주면 동작을 하겠다
    public MultiConnectionEchoServer(Socket socket) {
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

                System.out.println(getName() + " - " + line);
                String[] tokens = line.trim().split(":");
                if(tokens.length == 1) {
                    if(tokens[0].equalsIgnoreCase("who")) {
                        writer.write(getName() + "\n");
                        writer.flush();
                    }
                }
                else if(tokens.length > 1) {
                    // equasIgnoreCase - 같은 영어일까? 근데 소문자 대문자 상관없이 비교해줌
                    // 아이디 등록 과정
                    if(tokens[0].equalsIgnoreCase("ID")) {
                        setName(tokens[1]);
                    
                    } else if(tokens[0].charAt(0) == '@'&& (tokens[0].length() > 1)) {
                        String targetId = tokens[0].substring(1, tokens[0].length());
                        if(targetId.equals("@")) {  // 모두에게 보내기
                            for(MultiConnectionEchoServer server : MultiConnectionEchoServer.serverList) {  // MultiConnectionEchoServer.serverList : class variale인지 알기 쉽게 표기한것
                                server.send("#" + getName() + ":" + tokens[1]);
                            }
                        }else {
                            for (MultiConnectionEchoServer server : MultiConnectionEchoServer.serverList) {
                                if(server.getName().equals(targetId)) {     // server = target
                                    server.send("#" + getName() + ":" + tokens[1]);
                                }
                            }
                        }
                    }
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
            while(!Thread.currentThread().isInterrupted()){
                Socket socket = serverSocket.accept();

                // socket땜에 멈추지 않음
                MultiConnectionEchoServer server = new MultiConnectionEchoServer(socket);
                server.start();

            }
            
        } catch (IOException e) {
            System.out.println(e);
        }

        for(MultiConnectionEchoServer server : serverList) {
            server.interrupt(); // server 멈춤
            try{
                server.join();      // 다 끝날때까지 server가 기다림
            } catch(InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
