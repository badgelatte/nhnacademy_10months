package com.nhnacademy;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Quiz02_tryWithResources {
    /* public static void main(String[] args) {
        try {
            Socket socket = new Socket("Port",);

            System.out.println("Port" + socket + "열려있습니다.");
            socket.close();
        } catch (UnknownHostException e) {
        } catch (IOException e) {
            System.out.println(e);
        }
    } */

    public static void main(String[] args) {
        // 포트 돌면서 스캔
        for(int port = 1; port < 65536; port++ ) { 
            try{
                Socket socket = new Socket("localhost", port);
                System.out.println(port +"가 열려있습니다.");
                
                // 소켓 닫기 필수!
                socket.close();

            } catch (IOException ignore) {
            }

        }
    }
}
