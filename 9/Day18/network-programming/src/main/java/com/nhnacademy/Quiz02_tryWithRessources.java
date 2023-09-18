package com.nhnacademy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;

public class Quiz02_tryWithRessources {
    public static void main(String[] args) {
        // 포트 돌면서 스캔
        for(int port = 1; port < 65536; port++ ) { 
            // for문 안하면 socket try문을 빠져나가서 socket을 닫아 줄 수 없다
            try(Socket socket = new Socket("localhost", port);
            ){
                System.out.println(port +"가 열려있습니다.");
                // 소켓 닫기 필수!
                // socket.close();

            } catch (IOException ignore) {
            }

        }
    }
}
