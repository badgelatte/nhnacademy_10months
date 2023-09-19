package com.nhnacademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Exam00 {
    // socket 열고 받음


    // terminal 입력시 line으로 들어가고 그걸 line은 socketOut으로 다른 사람에게 보낸다
    // 상대방이 나에게 보내주면 socketIn으로 받은걸 읽어서 다시 line으로 저장
    // line을 terminalOut으로 적는다. terminalOut = System.out이므로 terminal에 나온다.
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234);   // Socket(host, port)
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // 직접 터미널에 쓰는 걸 받는 거
            BufferedReader terminalIn = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter terminalOut = new BufferedWriter(new OutputStreamWriter(System.out));
        ) {
            // 터미널에 쓰는 걸 받는 게 line
            String line;
            // line이 null이 아닐때 계속 돈다
            // 
            while ((line = terminalIn.readLine()) != null) {
                socketOut.write(line + "\n");   // 줄바꿈 해줌
                // 혹시나 안 나갈까봐 나가는거 적어줌
                socketOut.flush();


                line = socketIn.readLine();
                // System.out.println(line);
                // system out 대신
                terminalOut.write(line +"\n");
                // flush 안하면 buffer에 저장만 한다.
                terminalOut.flush();
            }
        } catch (IOException ignore) {
            System.out.println("연결에 실패하였습니다.");   
        } 
    }
}
