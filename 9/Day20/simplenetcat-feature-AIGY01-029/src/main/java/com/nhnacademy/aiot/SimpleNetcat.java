package com.nhnacademy.aiot;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SimpleNetcat {
    
    public static void main(String[] args) {
        String hostname = args[1];

        String port = args[2];
        int portnum = Integer.parseInt(port);

        /* BufferedReader st = new BufferedReader(new InputStreamReader(System.in));
        String l = st.readLine();

        StringTokenizer str = new StringTokenizer(l);
        String option = str.nextToken();
        String hostname = str.nextToken();
        String port = str.nextToken();
        int portnum = Integer.parseInt(port); */

        // if(snc == "snc") {}
        if(hostname.equals("-l")) {
            //servermode
            try (
                ServerSocket serverSocket = new ServerSocket(portnum);
                Socket socket = serverSocket.accept();

                BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader terminalIn = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter terminalOut = new BufferedWriter(new OutputStreamWriter(System.out));
            ) {
                String line;
                while ((line = terminalIn.readLine())!= null) {
                    socketOut.write(line + "\n");
                    socketOut.flush();

                    line = socketIn.readLine();
                    terminalOut.write(line + "\n");
                    terminalOut.flush();
                }

                // while(() != null) {
                // }
            } catch (IOException e) {
                String stderr = "서버에 연결하지 못하였습니다.";
                System.out.println(stderr);
            }
        }
        else {
            try (
                Socket socket = new Socket(hostname,portnum);

                BufferedReader socketIn2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter socketOut2 = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader terminalIn2 = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter terminalOut2 = new BufferedWriter(new OutputStreamWriter(System.out));
            ) {
                String line;
                while ((line = terminalIn2.readLine())!= null) {
                    socketOut2.write(line + "\n");
                    socketOut2.flush();
                    line = socketIn2.readLine();
                    terminalOut2.write(line + "\n");
                    terminalOut2.flush();
                }

                // while((line = socketIn2.readLine()) != null) {}
            } catch (IOException e) {
                String stderr = "서버에 연결하지 못하였습니다.";
                System.out.println(stderr);
            }
        }
    }
}
