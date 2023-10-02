package nc;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;

public class Quiz04_Me {
    public static void main(String[] args) {
        byte[] buffer = new  byte[2048];
        try (
            Socket socket = new Socket("localhost", 12345);
            ) {
            BufferedInputStream inputstream = new BufferedInputStream(socket.getInputStream());
            
            int line;

            // server에서 입력하면 보이기
            while ((line = inputstream.read(buffer))>0) {   // inputstream을 buffer에다가 넣어서 읽어야한다. 안그럼 내용물 못 읽음
                System.out.print("From Server: " + new String(buffer, 0, line));
                String exit = new String(buffer, 0, line).trim();
                
                // exit이라 치면 server 종료하기
                if(exit.equals("exit")){
                    System.out.println("server over");
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
    }
}
