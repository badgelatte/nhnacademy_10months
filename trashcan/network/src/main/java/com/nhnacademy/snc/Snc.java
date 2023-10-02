package com.nhnacademy.snc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Snc {
    public static void run(Socket socket) {
        try (
                BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader terminalIn = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter terminalOut = new BufferedWriter(new OutputStreamWriter(System.out));) {
            Transfer receiver1 = new Transfer(socketIn, terminalOut);
            receiver1.start();

            Transfer receiver2 = new Transfer(terminalIn, socketOut);
            receiver2.start();

            receiver1.join();
            receiver2.join();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        String host = "ems.nhnacademy.com";
        int port = 12345;
        boolean serverMode = true;

        if (args.length != 2) {
            System.out.println("설정이 잘못되었습니다.");
        }

        if (!args[0].equals("-l")) {
            host = args[0];
            serverMode = false;
        } else {
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println(e);
                System.exit(1);
            }
        }

        try (Socket socket = (serverMode) ? new ServerSocket(port).accept() : new Socket(host, port)) {
            Snc.run(socket);
        } catch (IOException e) {
            // TODO: handle exception
        }
    }
}
