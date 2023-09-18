package com.nhnacademy;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Quiz05_ClientSocketData {
    public static void main(String[] args) {
        // byte 수 -> 최대한 받을 수 있는 글자 수
        byte[] buffer = new byte[2048];
        // port 12345에 
        try (Socket socket = new Socket("localhost", 12345);

            // socket을 주겠다
            InputStream inputStream = socket.getInputStream();

            // inputStream을 읽어서 한번에 보냄
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)){
            // bufferedInputStream.read(buffer);

            int  length;
            // bufferedInputStream - 입력하는 글
            // (length = bufferedInputStream.read(buffer)) >= 0 -> 글자 입력하면 byte가 크기가 늘어남 => 조건 성립
            while((length = bufferedInputStream.read(buffer)) >= 0) {
                // 입력한 값 길이 찍기
                // System.out.println("Read Length" + length);
                // buffer에서 0부터 length까지만 줘라 => 입력한 값 그대로 찍기
                System.out.println("Read : " + new String(buffer, 0, length));

                String line = new String(buffer, 0, length).trim();
                // offset -> 시작 위치
                // trim -> 문자열 공백 제거

                // exit라 쓰면 종료하기
                if(line.equals("exit")) {
                    break;
                }
                System.out.println("Read : " + line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
