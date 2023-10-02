package com.nhnacademy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Quiz09 {
    public static void main(String[] args) {
        ClientSocketOut clientSocketOut = new ClientSocketOut();
        ServerSocketOut serverSocketOut = new ServerSocketOut();

        clientSocketOut.start();
        serverSocketOut.start();
    }

}

class ServerSocketOut extends Thread {
    int port;

    public ServerSocketOut(int port) {
        this.port = port;
    }

    public ServerSocketOut() {
        this(12345);
    }

    public void run() {
        byte[] buffer = new byte[2045];

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket = serverSocket.accept();

            socket.getOutputStream().write(
                    ("Client[" + socket.getInetAddress() + ":" + socket.getPort() + "]가 접속하였습니다.").getBytes());
            socket.getOutputStream().flush();

            // 클라에서 보낸거 다시 보내기
            BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
            int length;
            while ((length = bufferedInputStream.read(buffer)) >= 0) {
                String line = new String(buffer, 0, length).trim();
                socket.getOutputStream().write(("Server : " + line + "\n").getBytes());
                socket.getOutputStream().flush();
            }

            socket.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

}

class ClientSocketOut extends Thread {
    String host;
    int port;

    ClientSocketOut(String host, int port) {
        this.host = host;
        this.port = port;
    }

    ClientSocketOut() {
        this("localhost", 12345);
    }

    public void run() {
        byte[] buffer = new byte[2048];

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                Socket socket = new Socket(host, port);
                BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
                BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());) {

            String line;
            while (!(line = reader.readLine()).equals("exit")) {
                outputStream.write(("Client : " + line + "\n").getBytes());
                outputStream.flush();

                int length = inputStream.read(buffer);
                System.out.println(new String(buffer, 0, length));
            }

        } catch (IOException ignore) {
            // System.out.println(e);
        }
    }

}
/*
 * 실행시 서비스를 위한 port를 지정할 수 있다. 별도의 port 지정이 없는 경우, 1234를 기본으로 한다.
 * 
 * Server는 실행 후 대기 상태를 유지하고, client가 접속되면 client 정보를 출력한다.
 * 
 * Server에서는 연결된 socket이 끊어지기 전까지 client에서 보내온 데이터를 client로 다시 돌려 보낸다.
 * 
 * Client 연결이 끊어지면, server socket을 닫고 프로그램을 종료한다.
 */
