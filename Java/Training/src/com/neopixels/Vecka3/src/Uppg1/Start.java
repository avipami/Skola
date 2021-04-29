package Uppg1;

import java.io.IOException;

public class Start {


    public static void main(String[] args) throws IOException {
        TCPServer tcpServer = new TCPServer();
        DataAccessObjectClass test = new DataAccessObjectClass();
        test.getKompisar();
    }
}
