package neopixels.Vecka3.NeoChatTCP.Server;


import neopixels.Vecka3.NeoChatTCP.Model.LoginObject;
import neopixels.Vecka3.NeoChatTCP.Model.LogoutObject;
import neopixels.Vecka3.NeoChatTCP.Model.MessageObject;

import java.io.*;
import java.net.Socket;

public class ServerWorker extends Thread
{

    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private boolean logout=false;

    public ServerWorker(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
        this.start();
    }

    @Override
    public void run() {
        while (clientSocket.isConnected())
        {
            try {
                Object incomingObject = in.readObject();

                if(incomingObject instanceof LoginObject)
                {
                    System.out.println("Welcome motherfucker");
                    out.writeObject(new LoginObject());
                    out.flush();
                    //handleClientSocket();

                }
                if(incomingObject instanceof LogoutObject)
                {
                    this.logout = true;
                    clientSocket.close();
                    return;

                }
                handleClientSocket();


            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                //e.printStackTrace();
                try {
                    clientSocket.close();
                    System.out.println("Closed connection to : " + clientSocket);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                interrupted();
                break;
            }
        }
        try {
            interrupted();
            this.clientSocket.close();
            return;
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
    private void handleClientSocket() throws IOException, InterruptedException {


        for(int i = 0 ; i< 20; i++)
        {
            out.writeObject(new MessageObject("Hej"));
            out.flush();

            this.sleep(1000);
        }
        //clientSocket.close();
    }
}
