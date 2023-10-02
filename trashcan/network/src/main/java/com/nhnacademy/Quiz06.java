package com.nhnacademy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Quiz06 {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 12345;

        byte[] buffer = new byte[2048];

        try (Socket socket = new Socket(host, port)) {
            System.out.println("서버에 연결되었습니다.");

            OutputStream outputStream = socket.getOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

            bufferedOutputStream.write("Hello".getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.write("exit".getBytes());
            bufferedOutputStream.flush();

            InputStream inputStream = socket.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            int length;
            while ((length = bufferedInputStream.read(buffer)) >= 0) {
                String line = new String(buffer, 0, length).trim();
                System.out.println("Read : " + line);

                if (line.equals("exit")) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
