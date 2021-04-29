package NeoChatTCP.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    private int portNr;
    static int chattersCounter;
    public Server (int portNr) throws IOException {
        if(portNr!=55555)
        {
            this.portNr=55555;
        }
        try(
                ServerSocket serverSocket = new ServerSocket(this.portNr);
                //PrintToConsole("Server initiated");
                Socket clientSocket = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());

                ){
            Object fromClientObject;
            String fromServer;




        }



    }


    public void PrintToConsole(String message)
    {
        System.out.println(message);
    }

}
