package com.nhnacademy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
// 상대방이 입력한 것을 이쪽으로 보내면 그대로 다시 되돌려준다.
public class Quiz09_EchoServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234);
            Socket socket = serverSocket.accept();
            // -> 이거(Buffered 줄)만 바뀌면 파일이냐 버퍼냐 다른 거 정할 수 있다.
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // BufferedReader terminalIn = new BufferedReader(new InputStreamReader(System.in));
            //     BufferedWriter terminalOut = new BufferedWriter(new OutputStreamWriter(System.out));
        ) {
            // socket에서 읽어서 socket에서 나간다.
            String line;
            // line이 null이 아닐때 계속 돈다
            while ((line = socketIn.readLine())!= null) {   // 상대방이 socket을 보냈을 때, 상대방이 적은 것이 있을 때 그것을 line에 넣어주고
                // line 내용물을 상대방에게 다시 보낸다
                socketOut.write(line + "\n");   
                // 혹시나 안 나갈까봐 나가는거 적어줌
                socketOut.flush();
            }
        } catch (IOException ignore) {
            System.out.println("연결에 실패하였습니다.");   
        } 
    }
}
