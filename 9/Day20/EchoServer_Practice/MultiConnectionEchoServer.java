
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

// EchoServer 서비스 돌림
public class MultiConnectionEchoServer extends Thread{
    static List<MultiConnectionEchoServer> serverList = new LinkedList<>();
    Socket socket;
    BufferedWriter writer;

    //socket을 주면 동작을 하겠다
    public MultiConnectionEchoServer(Socket socket) {
        this.socket = socket;
        serverList.add(this);
    }

    // 다른 client들에게 메세지 보내기
    public void send(String message) throws IOException{
        // message를 받으면 output으로 보내라
        writer.write(message);
        writer.flush();
    }
    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {
            this.writer = writer;
            while(!Thread.currentThread().isInterrupted()) {
                String line = reader.readLine() + "\n";     // ID:A

                // 아이디 등록 과정
                System.out.println(getName() + " - " + line);
                String[] tokens = line.trim().split(":");       // ID, A로 나눔
                if(tokens.length > 1) {
                    // equasIgnoreCase - 같은 영어일까 ? 근데 소문자 대문자 상관없이 비교해줌
                    if(tokens[0].equalsIgnoreCase("ID")) {
                        setName(tokens[1]);                     // A를 이름으로 지정
                    }
                    else if(tokens[0].equalsIgnoreCase("who")) {
                        writer.write(getName() + "\n");
                        writer.flush();
                        // tokens[1].charAt(0) == '@'tokens[1].equals("@*")
                    } else if(tokens[0].charAt(0) == '@' && (tokens[0].length() > 1)) {    // @B:Hello! => tokens의 0번째의 1번째 거가 @인가
                        // tokens[0].length() > 1 -> tokens[0]이 @만 있을 때를 거를라고
                        String targetId = tokens[0].substring(1, tokens[0].length());       // substring 0부터 세는데 1번부터 length까지 - ex.Hello -> e부터 o까지이다. (1~4) length = 5
                        if(targetId.equals("@")) {  // 모두에게 보내기
                            for(MultiConnectionEchoServer server: MultiConnectionEchoServer.serverList ){
                                server.send("#" + getName() + ":" + tokens[1]);                 // tokens[1] = Hello!
                            }
                        }else {
                            for(MultiConnectionEchoServer server : serverList) {
                                if(server.getName().equals(targetId)) {
                                    server.send("#" + getName() + ":" + tokens[1]);                 // tokens[1]
                                    break;
                                }
                            }
                        }
                    }

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
                MultiConnectionEchoServer server = new MultiConnectionEchoServer(socket);
                server.start();

            }
            
        } catch (IOException e) {
            System.out.println(e);
        }

        for(MultiConnectionEchoServer server : serverList) {
            server.interrupt(); // server 멈춤
            try{
                server.join();      // 다 끝날때까지 server가 기다림
            } catch(InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void write(String line) {
    }
}
