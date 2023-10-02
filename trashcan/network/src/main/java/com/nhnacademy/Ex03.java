package com.nhnacademy;

import java.io.IOException;
import java.net.Socket;

public class Ex03 {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 12345;

        try (Socket socket = new Socket(host, port)) {
            System.out.println("서버에 연결되었습니다.");

            int ch;
            // 스트림이 끝나면 -1을 리턴
            while ((ch = socket.getInputStream().read()) >= 0) {
                System.out.write(ch);
            }

        } catch (IOException ignore) {
            // System.out.println(e);
        }
    }
}
