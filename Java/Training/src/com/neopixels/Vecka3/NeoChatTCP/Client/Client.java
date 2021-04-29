package neopixels.Vecka3.NeoChatTCP.Client;

import neopixels.Vecka3.NeoChatTCP.Model.LoginObject;
import neopixels.Vecka3.NeoChatTCP.Model.MessageObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket server;
    private boolean connected = false;
    public Client() {

        try {
            this.server = new Socket("127.0.0.1", 5555);
            printMessage();
            ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(server.getInputStream());
            out.writeObject(new LoginObject());
            out.flush();
            this.connected=true;
            while(connected){

                Object incomingObject = in.readObject();
                if(incomingObject instanceof LoginObject)
                    System.out.println("Welcome To the Server");

                if(incomingObject instanceof MessageObject)
                {
                    System.out.println(((MessageObject) incomingObject).getMessage());
                }

                //out.writeObject(new LogoutObject());
                //out.flush();

            }
            server.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void printMessage() {
        System.out.println("Connected to " + server);
    }

    public static void main(String[] args)
    {
        new Client();
    }
}
