package com.nhnacademy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

// 순서가 정해지지 않은것 -> 비동기 통신
public class Quiz07 {
    static class Receiver implements Runnable {
        BufferedInputStream inputStream;

        public Receiver(BufferedInputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public void run() {
            try {
                byte[] buffer = new byte[2048];
                int length;

                while ((length = inputStream.read(buffer)) >= 0) {
                    System.out.println(new String(buffer, 0, length));
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    public static void main(String[] args) {
        String host = "ems.nhnacademy.com";
        int port = 12345;

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                Socket socket = new Socket(host, port);
                BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
                BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());) {

            Receiver receiver = new Receiver(inputStream);

            Thread receiverThread = new Thread(receiver);
            receiverThread.start();
            // 받을 준비 끝!

            String line;
            while (!(line = reader.readLine()).equals("exit")) {
                outputStream.write(line.getBytes());
                outputStream.flush();
            }

        } catch (IOException ignore) {
            // System.out.println(e);
        }
    }
}

// inputstream에서 데이터를 기다렸다가 받으면 찍는거
// 버퍼를 가지고있다가 run 하면 inputstream을 찍음
