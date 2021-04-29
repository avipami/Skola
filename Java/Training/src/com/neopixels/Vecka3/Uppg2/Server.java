package com.neopixels.Vecka3.Uppg2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    Database db = new Database();
    private int connectionPort = 55555;

    public Server () throws IOException {
        try(
        ServerSocket serverSocket = new ServerSocket(connectionPort);
        Socket clientSocket = serverSocket.accept();
        ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
        ){
            System.out.println("Connection Granted");
            Object inputLine;
            Person outputPerson;


        }


    }
    public static void main(String[] args) {

    }
}
