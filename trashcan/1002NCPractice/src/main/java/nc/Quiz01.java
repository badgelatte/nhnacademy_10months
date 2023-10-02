package nc;


import java.net.Socket;

public class Quiz01 {
    public static void main(String[] args) {
        int startPort = 0;
        int endPort = 65535;

        for (int port = startPort; port < endPort; port++) {
            try {
                // tag::createSocket[]
                Socket socket = new Socket("localhost", port);
                System.out.println("Port " + port + " 열려 있습니다.");
                socket.close();
                // end::createSocket[]
            } catch (Exception ignore) {
                //
            }
        }
    }
}