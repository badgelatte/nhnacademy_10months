package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class server extends Thread {
    static List<server> serverlist = new LinkedList<>();
    Socket socket;
    BufferedWriter writer;
    
    public server(Socket socket) {
        this.socket = socket;
        serverlist.add(this);
    }
    
    // 메세지 보내기
    public void send(String message) throws IOException {
        writer.write(message);
        writer.newLine();
        writer.flush();
    }
    
    @Override
    public void run() {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            ){
                this.writer = writer;
                while(!Thread.currentThread().isInterrupted()) {
                    // 작성한 걸 읽고
                    String line = reader.readLine();
                    
                // :기준으로 나눠서 배열에 넣어준다
                String[] tokens = line.trim().split(":");

                // 나눠진게 없으면
                if(tokens.length < 2) {
                    // 그 내용이 who라면
                    if(tokens[0].equalsIgnoreCase("who")) {
                        // 이름을 불러와서 보낸다
                        writer.write(getName() + "\n");
                        writer.flush();
                    }
                    /* else if(tokens[0].equalsIgnoreCase("!exit")){
                        writer.write("종료합니다.");
                        writer.flush();
                        System.exit(0);
                    } */
                    else if(tokens[0].equalsIgnoreCase("list")) {
                        writer.write("목록: \n");
                        for (server ser : serverlist) {
                            writer.write(ser.getName() + "\n");
                            writer.flush();
                        }
                    }
                }
                else if(tokens.length > 1) {
                    // ID:A -> 아이디 지정할때
                    if(tokens[0].equalsIgnoreCase("ID")) {
                        setName(tokens[1]);
                    }
                    // @A:message-> message 보낼때
                    else if(tokens[0].charAt(0) == '@' && (tokens[0].length() > 1)) { // @만 보내면 오류떠야하니까
                        // 모두에게 메세지 보내기 - @@:message
                        String targetId = tokens[0].substring(1, tokens[0].length());   // substring(1) - 1번째 인덱스부터 끝까지 자르기
                        System.out.println(targetId);
                        if(targetId.equals("@"))    {
                            
                            for (server ser : serverlist) {
                                System.out.println("dfdf");
                                ser.send("#" + getName() + ":" + tokens[1]);
                            }
                        }
                        else {
                            // 누군가에게 지정해서 메세지 보내기 - @B:message
                            // 누군지 찾기 위해 @다음의 아이디를 targetId에 저장한다
                            for (server ser : serverlist) {
                                if(ser.getName().equals(targetId)){
                                    ser.send("#" + getName() + ":" + tokens[1]);
                                }
                            }
                        }
                    }
                    
                }
            }
        } catch (Exception e) {
            System.out.println("");
            System.out.println(e);
        }
        try {
            socket.close();
        } catch (Exception e) {
        }
        
    }
    

    public static void main(String[] args) {
        int port = 1232;
        try (
            ServerSocket serverSocket = new ServerSocket(port);
        ) {
            while (!Thread.currentThread().isInterrupted()) {
                Socket socket = serverSocket.accept();
                
                server server = new server(socket);
                
                server.start();
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        for (server ser : serverlist) {
            ser.interrupt();
            try {
                ser.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
