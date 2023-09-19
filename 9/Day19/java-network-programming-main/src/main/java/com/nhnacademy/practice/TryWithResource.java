package com.nhnacademy.practice;

import java.net.Socket;

public class TryWithResource {
    public static void main(String[] args) {
        for(int port = 1; port < 65535; port++) {
            try {
                Socket socket = new Socket("localhost", port);
                System.out.println(port +"가 열려있습니다.");

                socket.close();
            } catch (Exception e) {
            }
        }
    }
}
