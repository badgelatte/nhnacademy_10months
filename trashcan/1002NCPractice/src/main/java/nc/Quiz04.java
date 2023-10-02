package nc;

import java.io.BufferedInputStream;
import java.net.Socket;

// Quiz-04. Client socket을 server에 연결하여 server에서 data를 보내고, exit가 입력되면 프로그램을 종료한다.
// -> socket, 즉 localhost에서 입력하거나 출력하는 건 없다
public class Quiz04 {
    public static void main(String[] args) {
        byte[] buffer = new byte[2048];
        try (
            // Client socket을 serversocket에 연결
            Socket socket = new Socket("localhost", 12345);
        ){
            // data 보내기
            BufferedInputStream sender = new BufferedInputStream(socket.getInputStream());
            // socket.getInputStream - 내 socket으로 들어온 거 = serversocket에서 내보낸 것을 내 socket으로 받아옴

            int line;
            while ((line = sender.read(buffer))>= 0) {
                // ->  BufferedInputStream = int형으로 read는 int가 return type이다.

                // 보낸 걸 내 terminal에 띄움 / socket에서 받아서 띄우는게 아니라 내가 입력한 걸 바로 띄우는 거임
                System.out.println(new String(buffer, 0, line) + ": 전송");

                String exit = new String(buffer, 0, line).trim();
                if(exit.equals("exit")) {
                    break;
                }
                System.out.println(exit + " - 전송");   // sysout-> 이 java를 사용하는 곳에 띄워준다는 말
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        // exit 누르면 프로그램 종료
    }
}
