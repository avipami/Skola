package com.neopixels.Vecka3.NeoChatTCP.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port= 5555;
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            printServerOnline();

            while(true)
            {
                Socket clientSocket = serverSocket.accept();
                printClientConnected(clientSocket);

                    // Start a new thread everytime a new clients
                        //connect to the server
                ServerWorker worker = new ServerWorker(clientSocket);



            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printServerOnline(){
        System.out.println("Server is online and ready!");
    }

    public static void printClientConnected(Socket clientSocket){
        System.out.println("Client has now connected from " + clientSocket);
    }
}
