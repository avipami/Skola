package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private ServerSocket serverSocket;

    //private ClientThreads worker;

    public Server() {
        int port = 5555;
        try {
            this.serverSocket = new ServerSocket(port);
            printServerOnline();

            while (true) {
                Socket clientSocket = this.serverSocket.accept();
                printClientConnected(clientSocket);

                // Start a new thread everytime a new clients
                //connect to the server
                ClientThreads worker = new ClientThreads(clientSocket);
                ConnectedClientList.GetInstance().AddClientThreadToList(worker);

                //for (ClientThreads ct : ConnectedClientList.GetInstance().getClientThreads()) {
               //     System.out.println(ct.getName());
                //}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void printServerOnline() {
        System.out.println("\nServer is online and ready!\n");
    }

    public static void printClientConnected(Socket clientSocket) {
        System.out.println("Client has now connected from " + clientSocket);
    }

    //Main
    public static void main(String[] args) {
        new Server();
    }
}
