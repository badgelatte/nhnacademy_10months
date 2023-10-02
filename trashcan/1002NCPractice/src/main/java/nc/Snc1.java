package nc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

// BufferedReader로 들어온 값을 BufferedWriter로 내보냄  
class Transfer extends Thread{
    BufferedReader reader;
    BufferedWriter writer;
    public Transfer(BufferedReader reader, BufferedWriter writer) {
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
            System.out.println("전송 불가");
        }
    }
}

public class Snc1 {
    // socket thread
    static class socketThread {
        Socket socket;
        public socketThread(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try (
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter stdOut = new BufferedWriter(new OutputStreamWriter(System.out));
                BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            ) {
                // transfer로 stdIn, socketOut / socketOut, stdOut 생성 - 작성해서 보냈을때 바로 받기 위해
                Transfer stdInSocketOut = new Transfer(stdIn, socketOut);
                Transfer socketInStdOut = new Transfer(socketIn, stdOut);

                stdInSocketOut.start();
                socketInStdOut.start();

                stdInSocketOut.join();
                socketInStdOut.join();
                
            } catch (IOException e) {
                System.err.println(e);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }

    }

    public static void main(String[] args) {
        // 1번째는 host, 2번째는 port
        String host = args[0];
        int port = Integer.parseInt(args[1]);

        // args입력이 1개 이하일 경우 err
        if(args.length < 2) {
            System.err.println("입력이 부족합니다.");
        }        
        // server 
        if(host.equals("-l")){
            try (
                ServerSocket serverSocket = new ServerSocket(port);
            ){
                Socket socket = serverSocket.accept();
                socketThread server = new socketThread(socket);
                
                server.run();

            } catch (IOException e) {
            }
        }
        // client
        else if(host.equals("localhost")){
            try (
                Socket socket = new Socket(host, port);
            ) {
                socketThread server = new socketThread(socket);
                
                server.run();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        //
    }
}
