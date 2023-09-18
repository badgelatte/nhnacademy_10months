package com.nhnacademy;

import java.io.IOException;
import java.net.Socket;

public class Quiz04_ClientSocketExit {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("ems.nhnacademy.com", 12345);

            System.out.println("서버에 연결되었습니다.");
            
            // 이거 풀면 ems.nhnacademy.com의 12345 port로 socket.getOutputStream이 100번 들어간다.
            for(int i = 0; i < 100; i++) {
                socket.getOutputStream().write("Hello!!".getBytes());
                // 다 차면 한번에 보내기
                socket.getOutputStream().flush();
            }


            socket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
