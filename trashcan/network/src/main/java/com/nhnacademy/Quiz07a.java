package com.nhnacademy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

// Sender, Reciever 분리
public class Quiz07a {
    static class Sender implements Runnable {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedOutputStream outputStream;

        public Sender(BufferedOutputStream outputStream) {
            this.outputStream = outputStream;
        }

        @Override
        public void run() {
            try {
                String line;
                while (!(line = reader.readLine()).equals("exit")) {
                    outputStream.write(line.getBytes());
                    outputStream.flush();
                }
            } catch (IOException ignore) {
                // System.err.println();
            }
        }
    }

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
            } catch (IOException ignore) {
                // System.err.println(e);
            }
        }
    }

    public static void main(String[] args) {
        String host = "ems.nhnacademy.com";
        // String host = "localhost";
        int port = 12345;

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                Socket socket = new Socket(host, port);
                BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
                BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());) {

            Sender sender = new Sender(outputStream);
            Thread senderThread = new Thread(sender);
            senderThread.start();

            Receiver receiver = new Receiver(inputStream);
            Thread receiverThread = new Thread(receiver);
            receiverThread.start();
            // 받을 준비 끝!

        } catch (IOException ignore) {
            // System.out.println(e);
        }
    }
}
