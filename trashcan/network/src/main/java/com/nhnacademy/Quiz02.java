package com.nhnacademy;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Quiz02 {
    public static void main(String[] args) {
        for (int i = 1; i < 65536; i++) {
            try (Socket socket = new Socket("localhost", i)) {
                Thread.sleep(1000);
                System.out.println("Port " + i + " 열려있습니다.");
                System.out.println("Inet address : " + socket.getInetAddress());
                System.out.println("local address : " + socket.getLocalAddress());
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            } catch (UnknownHostException e) {
                // System.out.println(e);
            } catch (IOException ignore) {
                // System.out.println(e);
            }
        }
    }
}
