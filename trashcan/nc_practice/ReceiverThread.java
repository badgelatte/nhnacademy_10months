import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

// Quiz-07. 프로그램에서 보내고 받는 과정에 별도로 동작하도록 구성하라
public class ReceiverThread {
    // Receiver로 받는 thread 만들기
    static class Receiver extends Thread {
        BufferedReader inputStream;
        public Receiver(BufferedReader inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public void run(){
            char[] buffer = new char[2048];
            int line;
            while (!Thread.currentThread().isInterrupted()) {
                // bufferedReader - char[] 사용 /  bufferedInputStream - byte[] 사용
                try {
                    line = inputStream.read(buffer);
                    System.out.print("From server - " + new String(buffer, 0, line));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        try (
            Socket socket = new Socket("localhost", 12345);

            // 내랑 서버랑 연결된 부분 - socket
            // 내가 받은 거 = 서버에서 나한테 보낸거
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 내한테서 나간거 = 서버한테 전송
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // 내가 적은 거
            BufferedReader terminalIn = new BufferedReader(new InputStreamReader(System.in));
        ){
            // 서버에 연결되었음을 알림
            System.out.println("서버에 연결하였습니다.");

            // server에서 보낸 거 client에서 받기
            Receiver receiver = new Receiver(input);
            receiver.start();

            String line;

            while ((line = terminalIn.readLine())!= null) {
                if(line.trim().equals("exit")){
                    break;
                }
                output.write("From client - " + line);
                output.newLine();
                output.flush();
            }
        } catch (Exception e) {
        }
    }
}
