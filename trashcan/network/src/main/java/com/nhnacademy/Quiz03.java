package com.nhnacademy;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Quiz03 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("ems.nhnacademy.com", 12345)) {
            System.out.println("서버에 연결되었습니다.");
            System.out.println("Inet address : " + socket.getInetAddress());
            System.out.println("local address : " + socket.getLocalAddress());
            System.out.println("Remote address : " + socket.getRemoteSocketAddress());
            System.out.println("Remote prot : " + socket.getPort());
        } catch (UnknownHostException e) {
            // System.out.println(e);
        } catch (IOException ignore) {
            // System.out.println(e);
        } catch (NumberFormatException ignore) {
            System.err.println("Port가 올바르지 않습니다.");
            System.exit(1);
        }
    }
}
