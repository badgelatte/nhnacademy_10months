package com.nhnacademy.multichat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import com.nhnacademy.snc.Transfer;

public class ChatClient extends Thread {
    List<ChatClient> clientList = new LinkedList<>();
    String host;
    int port;
    String name;
    static int count = 0;

    public ChatClient(String host, int port, String name) {
        this.host = host;
        this.port = port;
        if (checkName(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("중복된 ID입니다.");
        }
        clientList.add(this);
    }

    // 수정
    public ChatClient() {
        this("localhost", 1234, "user" + count++);
    }

    public boolean checkName(String clientID) {
        for (ChatClient client : clientList) {
            if (client.getName().equals(clientID)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(host, port)) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter stdOut = new BufferedWriter(new OutputStreamWriter(System.out));
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // 최초 접속시 id 전송
            socketOut.write("#" + getName() + "\n");
            socketOut.flush();

            Transfer stdSocket = new Transfer(stdIn, socketOut);
            Transfer socketStd = new Transfer(socketIn, stdOut);

            stdSocket.start();
            socketStd.start();

            stdSocket.join();
            socketStd.join();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        client.start();
    }
}
