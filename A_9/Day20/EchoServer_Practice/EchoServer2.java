import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class EchoServer2 extends Thread {
    static List<EchoServer2> serverList = new LinkedList<>();
    Socket socket;
    BufferedWriter writer;

    //socket을 주면 동작을 하겠다
    public EchoServer2(Socket socket) {
        this.socket = socket;
        serverList.add(this);
    }

    // 받은 메세지 전달하기
    public void send(String line) throws IOException {
        writer.write(line + "\n");
        writer.flush();
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {
            while(!Thread.currentThread().isInterrupted()) {
                String line = reader.readLine() + "\n";
                for(EchoServer2 server : serverList) {
                    server.send(line);
                }
            }
        } catch (IOException ignore) {
            //
        }

        try {
            socket.close();
        } catch (IOException e) {
            //
        }
    }
    
    public static void main(String[] args) {
        // server socket고정
        int port = 1234;

        try (
            ServerSocket serverSocket = new ServerSocket(port)
        ) {
            while(Thread.currentThread().isInterrupted()){
                Socket socket = serverSocket.accept();

                // socket땜에 멈추지 않음
                EchoServer2 server = new EchoServer2(socket);
                server.start();

                serverList.add(server);
            }
            
        } catch (IOException e) {
            // TODO: handle exception
        }

        for(EchoServer2 server : serverList) {
            server.interrupt(); // server 멈춤
            try{
                server.join();      // 다 끝날때까지 server가 기다림
            } catch(InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
