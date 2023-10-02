package com.nhnacademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Quiz09a {
    public static void main(String[] args) {

        try (Socket socket = new Socket("ems.nhacademy.com", 12345);
                BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader terminalIn = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter terminalOut = new BufferedWriter(new OutputStreamWriter(System.out));) {

            String line;
            while ((line = terminalIn.readLine()) != null) {
                socketOut.write(line + "\n");
                socketOut.flush();

                line = socketIn.readLine();
                // System.out.println(line);
                terminalOut.write(line + "\n");
                terminalOut.flush();
            }

        } catch (IOException e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }
}

// Quiz09a
// sysin : 컴퓨터에서 in-> 서버로 out
// 서버에서 in-> 컴퓨터로 out

// Quiz09b
// 서버 : 터미널에서 in -> 터미널로 out