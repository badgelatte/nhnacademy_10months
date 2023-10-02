package com.nhnacademy;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Ex02 {
    public static void main(String[] args) {
        String host = "ems.nhnacademy.com";
        int port = 12345;

        try (Socket socket = new Socket(host, port)) {
            System.out.println("서버에 연결되었습니다.");

            socket.getOutputStream().write("Hello".getBytes());
            // String.getBytes() -> String을 Byte Array로 변환

            socket.getOutputStream().flush();
            // flush -> 얼른 버퍼에 있는걸 저장, 출력 등을 해라
            // flush를 하면 앞에 버퍼에 있는걸 다 보낸다.

        } catch (UnknownHostException e) {
            // System.out.println(e);
        } catch (ConnectException e) {
            System.err.println(host + " : " + port + "에 연결할 수 없습니다.");
        } catch (IOException ignore) {
            // System.out.println(e);
        }
    }
}
