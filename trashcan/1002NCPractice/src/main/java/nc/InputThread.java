package nc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

// - Quiz-05에서는 입력된 문자열을 전송하고, 이롤 돌려 받은 후 다음 문자열을 보낼 수 있다.
// - 본 프로그램에서는 보낸 과정과 받는 과정을 분리하여 개별 동작이 가능하도록 수정한다.
// - 두 동작의 개별 동작은 thread를 이용해 구현 가능하다.
// → output 받는 thread 만들고 input 받는 thread 만들어라
// input thread 받아서 찍는 거 만들기
// → run하면 input stream을 받아놓은 거 출력

public class InputThread {
    // 서버에서 내보낸 거 client socket에 받기
    static class Receiver implements Runnable{
        BufferedInputStream inputStream;
        public Receiver(BufferedInputStream inputStream) {
            this.inputStream = inputStream;
        }

        public void run() {
            try {
                byte[] buffer = new byte[2048];
                int line;

                while((line = inputStream.read(buffer))>= 0) {
                    System.out.print("From server - " + new String(buffer, 0, line));
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

    }


    public static void main(String[] args) {
        try (
            Socket socket = new Socket("localhost", 12345);

            BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());
            BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
        ){
            // server에서 보낸 거 client에서 받아서 띄우기
            Receiver receiver = new Receiver(inputStream);
            Thread receiverThread = new Thread(receiver);
            receiverThread.start();

            // receiverThread.join();

            // server에서 보낸거 다시 보내기
            byte[] buffer = new byte[2048];
            int line;
            while ((line = inputStream.read(buffer))> 0) {
                outputStream.write(buffer, 0, line);
                outputStream.flush();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
