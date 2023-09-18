package com.nhnacademy;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Quiz05_ClientSocketData {
    public static void main(String[] args) {
        byte[] buffer = new byte[2048];
        try (Socket socket = new Socket("localhost", 12345);
            InputStream inputStream = socket.getInputStream();
            // inputStream을 읽어서 한번에 보냄
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)){
            // bufferedInputStream.read(buffer);

            int  length;
            while((length = bufferedInputStream.read(buffer)) >= 0) {
                // 입력한 값 길이 찍기
                // System.out.println("Read Length" + length);
                // buffer에서 0부터 length까지만 줘라 => 입력한 값 그대로 찍기
                System.out.println("Read : " +new String(buffer, 0, length));
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
