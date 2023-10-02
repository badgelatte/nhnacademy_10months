package com.nhnacademy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Quiz06a {
    public static void main(String[] args) {
        String host = "ems.nhnacademy.com";
        int port = 12345;

        byte[] buffer = new byte[2048];

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                Socket socket = new Socket(host, port);
                BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
                BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());) {

            String line;
            while (!(line = reader.readLine()).equals("exit")) {
                outputStream.write(line.getBytes());
                outputStream.flush();

                int length = inputStream.read(buffer);
                System.out.println(new String(buffer, 0, length));
            }

        } catch (IOException ignore) {
            // System.out.println(e);
        }
    }
}
