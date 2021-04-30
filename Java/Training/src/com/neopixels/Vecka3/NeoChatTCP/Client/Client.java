package Client;

import Model.LoginObject;
import Model.MessageObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Client extends Thread{
    private Socket server;

    private String userName;
    private boolean connected = false;

    public Client(String userName) throws IOException, InterruptedException {
        this.userName = userName;
        while (connected == false)
        {
            try {
                this.server = new Socket("127.0.0.1", 5555);
                printMessage();
                ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(server.getInputStream());
                out.writeObject(new LoginObject(userName));
                out.flush();
                TypeConsole tc = new TypeConsole(userName, out);
                tc.start();

                connected = true;
                while (connected) {

                    Object incomingObject = in.readObject();

                    if (incomingObject instanceof LoginObject)
                    {
                        WelcomeMessage(incomingObject);
                    }


                    if (incomingObject instanceof MessageObject)
                    {
                        System.out.println(((MessageObject) incomingObject).getMessage());
                    }

                }
                server.close();
            } catch (ClassNotFoundException | IOException e)
            {
                if (server != null) server.close();
                System.out.println("Server not online for the moment");
                sleep(2000);
                connected = false;

                e.printStackTrace();
            }

        }
    }

    private void printMessage()
    {
        System.out.println("Connected to " + server);
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        new Client("v");
    }

    public void WelcomeMessage(Object incomingLoginMessage)
    {
        System.out.println("Welcome To the Server !\n" + ((LoginObject) incomingLoginMessage).getUserName());
        System.out.println("You can type anytime you want!\n Everyone will be able to listen.\n");
    }

}
