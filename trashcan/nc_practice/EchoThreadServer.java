import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;



// main이 돌면서 1번 serversocket이 열림
// 2번 현재 스레드가 열려있다면
// 3번 열린 serversocket에 socket이 연결됨
// -> EchoServer에 socket 값을 준다(serversocket과 연결되어있는 socket에 대한 정보)
// EchoServer는 받은 socket 값으로 Thread를 하나 만든다 -> 고로 socket 하나당 Thread 하나

// socket에서 input이 들어오면 계속 내보낸다
// socket이 처음 연결된 것(socket1)과 이후에 연결된 socket(socket2)은 서로 각자 한개씩 thread를 가지므로 
// socket1과 socket2 각각 연결된 thread1과 thread2는 다른 것이다.
// => 그래서 socket 여러개를 연결해도 각각 다 사용이 가능한 것이다.
public class EchoThreadServer extends Thread{
    Socket socket;

    public EchoThreadServer (Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {
            while(!Thread.currentThread().isInterrupted()) {

                writer.write(reader.readLine() + "\n");
                writer.flush();
            }
        } catch (Exception e) { // Thread가 끊어졌을때
            System.out.println(Thread.currentThread().getName() +" 서버 연결 끊어졌습니다.");
        }
        try {
            socket.close();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        List<EchoThreadServer> serverlist = new LinkedList<>();
        try (
            ServerSocket server = new ServerSocket(1232);
            ) {
                System.out.println(Thread.currentThread().getName());

                while (!Thread.currentThread().isInterrupted()) {   // main이 돌 때
                    Socket socket = server.accept();
        
                    EchoThreadServer echoThreadServer = new EchoThreadServer(socket);
                    echoThreadServer.start();
        
                    serverlist.add(echoThreadServer);
                    
                }
        } catch (Exception e) {
            System.out.println(e);
        }

        for (EchoThreadServer server : serverlist) {
            server.interrupt();
            try {
                server.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
