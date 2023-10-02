package nc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class socketInOut {
    public static void main(String[] args) {
        // Quiz-05. Client socket을 server에 연결하여 server에서 보내오는 문자열을 출력하라.
        String host = "localhost";
        int port = 12345;
        try {

            // tag::newSocket[]
            Socket socket = new Socket(host, port);
            // end::newSocket[]
            // tag::connected[]
            System.out.println("서버에 연결되었습니다.");
            // end::connected[]
            // tag::getInputStream[]
            BufferedInputStream input = new BufferedInputStream(socket.getInputStream());
            // end::getInputStream[]
            // tag::getOutputStream[]
            BufferedOutputStream output = new BufferedOutputStream(socket.getOutputStream());
            // end::getOutputStream[]
            int readLength;
            // tag::createBuffer[]
            byte[] buffer = new byte[2048];
            // end::createBuffer[]

            // tag::inputRead[]
            while ((readLength = input.read(buffer)) > 0) {
                // end::inputRead[]

                // tag::exit[]
                if (new String(buffer, 0, readLength).trim().equals("exit")) {
                    break;
                }
                // end::exit[]

                System.out.println(new String(buffer, 0, readLength));
                // tag::outputWrite[]
                output.write(buffer, 0, readLength);
                output.flush();
                // end::outputWrite[]
            }
            // tag::socketClose[]
            socket.close();
            // end::socketClose[]
            // tag::IOException[]
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
