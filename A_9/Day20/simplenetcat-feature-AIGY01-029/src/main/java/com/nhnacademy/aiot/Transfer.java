package com.nhnacademy.aiot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;

public class Transfer extends Thread{
    BufferedReader reader;
    BufferedWriter writer;
    // 아래와 같이 BufferedWriter, BufferedReader로 최종적으로 바꿔줬기 때문에 BufferedReader, BufferedWriter만 선언해서 사용하면 된다.
    // BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    // BufferedReader terminalIn = new BufferedReader(new InputStreamReader(System.in));



    public Transfer (BufferedReader reader, BufferedWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void run() {
        String line;
        try {
            while ((line = reader.readLine())!= null) {
                writer.write(line +"\n");   
                writer.flush();
            }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
