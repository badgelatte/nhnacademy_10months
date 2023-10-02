package com.nhnacademy;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Quiz05 {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 12345;

        byte[] buffer = new byte[2045];

        try (Socket socket = new Socket(host, port)) {
            System.out.println("서버에 연결되었습니다.");

            InputStream inputStream = socket.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            int length;
            while ((length = bufferedInputStream.read(buffer)) >= 0) {
                // System.out.println("Read : " + new String(buffer, 0, length - 1));
                // System.out.println("Read Length : " + (length - 1)); // -1을 하는 이유 : \n

                String line = new String(buffer, 0, length).trim(); // trim : 양쪽 공백 제거
                System.out.println("Read : " + line);

                if (line.equals("exit")) {
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
