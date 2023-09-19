package com.nhnacademy;

import java.io.IOException;
import java.net.Socket;

public class Quiz03_SocketClientServer {
    public static void main(String[] args) {
            try( Socket socket = new Socket("ems.nhnacademy.com", 12345)){
                        Thread.sleep(1000);
                        System.out.println(12345 + "가 열려있습니다");
                    } catch(InterruptedException ignore) {
                        Thread.currentThread().interrupt();
                    }catch(IOException e) {
                }

        /* for(int port = 1; port < 65536; port++ ) { 
            // for문 안하면 socket try문을 빠져나가서 socket을 닫아 줄 수 없다
            try{
                Socket socket = new Socket("localhost", port);
                System.out.println(port +"가 열려있습니다.");
                System.out.println("local address" + socket.getLocalAddress());
                // loccal address : 내 주소
                System.out.println("local port" +socket.getLocalPort());
                // local port : 내 포트
                System.out.println("remote address" + socket.getRemoteSocketAddress());
                // remote address : 원격 저장소 -> 내꺼 제외한 모든 저장소
                System.out.println();
                
                // 소켓 닫기 필수!
                socket.close();

            } catch (IOException ignore) {
            }

        } */
    }
}