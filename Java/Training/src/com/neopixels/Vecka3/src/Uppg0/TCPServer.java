package Uppg0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    int port = 12345;
    public TCPServer() throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket s = serverSocket.accept();
             PrintWriter p = new PrintWriter(s.getOutputStream(),true);
             BufferedReader buf = new BufferedReader(new InputStreamReader(s.getInputStream()));)
        {

            String Response = " SKIT";
            String temp;
            while ((temp = buf.readLine())!=null)
            {
                System.out.println("MSG FROM CLIENT");
                System.out.println(temp.toUpperCase());
                p.println("HejFrServern");
                p.println(Response);

            }
        }


    }

    public static void main(String[] args) throws IOException {
        TCPServer tcpServer = new TCPServer();
    }
}
