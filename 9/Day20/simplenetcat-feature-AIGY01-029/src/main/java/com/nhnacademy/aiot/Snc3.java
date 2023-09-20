package com.nhnacademy.aiot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Snc3 {// made by 수석님


    public static void main(String[] args) {
        String host = "localhost";
        int port = 1234;
        boolean serverMode = true;
        
        // args는 항상2보다 컷다
        if(args.length < 2) {
            System.out.println("설정이 잘못되었습니다.");
        }
        
        // host 지정
        if(!args[0].equals("-l")){ // client mode -> snc localhost 1234
            host = args[0];
            serverMode = false;
        }
        // port 번호 지정
        try{
            port = Integer.parseInt(args[1]);
        } catch(NumberFormatException e){
            System.err.println(e);
            System.exit(1);
        }
        if(serverMode) {
            try (ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();){
                BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader terminalIn = new BufferedReader(new InputStreamReader(System.in));   // -> 표준 입력에서 받는다
                BufferedWriter terminalOut = new BufferedWriter(new OutputStreamWriter(System.out));
                
                Transfer transfer1 = new Transfer(socketIn, terminalOut);   // socket에서 terminal로 나가는 거
                Transfer transfer2 = new Transfer(terminalIn, socketOut);   // terminal에서 socket으로 나가는 거
                
                transfer1.start();
                transfer2.start();
                
                transfer1.join();
                transfer2.join();
            }  catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (IOException e) {
            }
        } else {    // client mode
            try (Socket socket = new Socket(host, port); ){
                BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader terminalIn = new BufferedReader(new InputStreamReader(System.in));   // -> 표준 입력에서 받는다
                BufferedWriter terminalOut = new BufferedWriter(new OutputStreamWriter(System.out));
                
                Transfer transfer1 = new Transfer(socketIn, terminalOut);   // socket에서 terminal로 나가는 거
                Transfer transfer2 = new Transfer(terminalIn, socketOut);   // terminal에서 socket으로 나가는 거
                
                transfer1.start();
                transfer2.start();
                
                transfer1.join();
                transfer2.join();
            }  catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (IOException e) {
            }
        }
        
    }
}
