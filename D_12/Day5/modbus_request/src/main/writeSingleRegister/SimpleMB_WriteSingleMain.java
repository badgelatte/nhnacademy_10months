package main.writeSingleRegister;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

import main.SimpleMB;

public class SimpleMB_WriteSingleMain  {
    public static void main(String[] args) throws IOException{
        try (Socket socket = new Socket("172.19.0.8",502);
            BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
            BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream())) {// 172.18.0.4
                // byte[] request = {0,1,0,0,0,6,1,3,0,0,0,5}; // fuction , address, counter
                int unitId = 1;
                int transactionId = 0;
                
                for (int i = 0; i < 10; i++) {                                      // value에 값을 넣어서 우리가 원하는 값을 보내도록 한다 값은 한개만 보내줄 수 있다
                    byte[] request = SimpleMB.addMBAP(++transactionId, unitId, SimpleMB_WriteSingleRegister.makeWriteSingleRegister(0, 7));
                    outputStream.write(request);
                    outputStream.flush();
                    
                    byte[] response = new byte[512];
                    int receivedLength = inputStream.read(response, 0, response.length);
                    
                    System.out.println(Arrays.toString(Arrays.copyOfRange(response, 0, receivedLength)));
                }
        } catch (UnknownHostException e) {
            System.err.println("Unknown host!!");
        }
        
    }
}