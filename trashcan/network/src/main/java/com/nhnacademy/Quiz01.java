package com.nhnacademy;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Quiz01 {
    public static void main(String[] args) {
        for (int i = 1; i < 65536; i++) {
            try {
                Socket socket = new Socket("localhost", i);

                System.out.println("Port " + i + " 열려있습니다.");

                socket.close();
            } catch (UnknownHostException e) {
                // System.out.println(e);
            } catch (IOException ignore) {
                // System.out.println(e);
            }
        }
    }
}
