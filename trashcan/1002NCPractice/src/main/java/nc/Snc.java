package nc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Snc {
    // input, output thread
    static class InputOutput extends Thread{
        BufferedReader reader;
        BufferedWriter writer;

        public InputOutput(BufferedReader reader, BufferedWriter writer) {
            this.reader = reader;
            this.writer = writer;
        }

        public void run() {
            String line;
            try {   
                while (!Thread.currentThread().isInterrupted()) {
                    line = reader.readLine();
                    writer.write(line + "\n");
                    writer.flush();
                }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }

    public static void main(String[] args) {
        // 입력된 args, host와 port로 입력
        String host = args[0];
        int port = Integer.parseInt(args[1]);

        // args 0번째가 -L인가 localhost인가에 따라 나뉘기
        
        // server socket 생성
        if(args[0].equals("-l")) {
            try (
                    // 서버 열기
                    ServerSocket serverSocket = new ServerSocket(port);
                ){
                    Socket socket = serverSocket.accept();
                    BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                    BufferedWriter stdOut = new BufferedWriter(new OutputStreamWriter(System.out));
                    
                    // inputoutput thread로 stdin stdout 열기
                    // server에서 입력
                    InputOutput serverInt = new InputOutput(stdIn, socketOut);
                    
                    // server에서 출력
                    InputOutput serverOut = new InputOutput(socketIn, stdOut);

                    serverInt.start();
                    serverOut.start();

                    serverInt.join();
                    serverOut.join();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        // socket, 즉 client socket 생성
        else if(args[0].equals("localhost")) {
            try (
                Socket socket = new Socket(host, port);
            ){

                BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                    BufferedWriter stdOut = new BufferedWriter(new OutputStreamWriter(System.out));
                    
                    // inputoutput thread로 stdin stdout 열기
                    // server에서 입력
                    InputOutput serverInt = new InputOutput(stdIn, socketOut);
                    
                    // server에서 출력
                    InputOutput serverOut = new InputOutput(socketIn, stdOut);

                    serverInt.start();
                    serverOut.start();

                    serverInt.join();
                    serverOut.join();
            }catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else {
            System.out.println("잘못된 입력");
        }
        
    }
}
