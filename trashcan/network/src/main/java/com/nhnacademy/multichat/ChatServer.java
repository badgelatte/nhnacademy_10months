package com.nhnacademy.multichat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer extends Thread {
    static List<ChatServer> chatServerList = new ArrayList<>();
    Socket socket;
    BufferedWriter writer;

    public ChatServer(Socket socket) {
        chatServerList.add(this);
        this.socket = socket;
    }

    public void tocken(String tocken) throws IOException {
        String[] tokens = tocken.trim().split(":");

        if (tokens.length == 1) {
            // id 출력
            if (tokens[0].equalsIgnoreCase("who")) {
                // System.out.println(getName());
                writer.write("id : " + getName() + "\n");
                writer.flush();
            }
            // 종료
            else if (tokens[0].equalsIgnoreCase("exit")) {
                socket.close();
            }
            // 일반 출력
            else {
                System.out.println(tokens[0]);
            }
        }
        // 아이디 세팅
        else if (tokens.length == 2 && tokens[0].equalsIgnoreCase("id")) {
            setName(tokens[1]);
            send("아이디 세팅 완료 :" + tokens[1] + "\n");
        }
        // 메세지 보내기
        else if (tokens[0].charAt(0) == '@' && tokens[0].length() > 1) {
            String targetId = tokens[0].substring(1, tokens[0].length()); // 누구에게 보낼지
            // broad casting
            if (targetId.equalsIgnoreCase("all")) {
                for (ChatServer server : chatServerList) {
                    server.send("#" + getName() + ":" + tokens[1] + "\n");
                }
            }
            // uni casting
            else {
                for (ChatServer server : chatServerList) {
                    if (server.getName().equals(targetId)) {
                        server.send("#" + getName() + ":" + tokens[1] + "\n");
                        break;
                    }
                }
                // 람다식 쓰기
                // chatServerList.stream().filter(server ->
                // server.getName().equals(targetId)).forEach(server.send()~~);
            }
        }

    }

    public void send(String message) throws IOException {
        writer.write(message);
        writer.flush();
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            this.writer = writer;
            System.out.println("address : " + socket.getInetAddress() + " port : " + socket.getPort());

            while (!Thread.currentThread().isInterrupted()) {
                String line = reader.readLine() + "\n";
                tocken(line);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        try {
            socket.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private void sendTo(String sender, String receiver, String message) throws IOException {
        ChatServer send = null;
        ChatServer recieve = null;
        for (ChatServer server : chatServerList) {
            if (server.getName().equals(sender)) {
                send = server;
            } else if (server.getName().equals(receiver)) {
                recieve = server;
            }
        }
        if (send == null || recieve == null) {
            throw new IOException("잘못된 sender or reciever");
        } else {
            recieve.writer.write(sender + ":" + message);
        }
    }
}
