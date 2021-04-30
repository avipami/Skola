package Server;

import Model.LoginObject;
import Model.LogoutObject;
import Model.MessageObject;

import java.io.*;
import java.net.Socket;


public class ClientThreads extends Thread {

    private Socket clientConnectionSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String username;

    public ClientThreads(Socket clientConnectionSocket) throws IOException {
        this.clientConnectionSocket = clientConnectionSocket;
        out = new ObjectOutputStream(clientConnectionSocket.getOutputStream());
        in = new ObjectInputStream(clientConnectionSocket.getInputStream());
        this.start();
    }

    @Override
    public void run() {
        while (clientConnectionSocket.isConnected()) {
            try {
                Object incomingObject = in.readObject();

                if (incomingObject instanceof LoginObject) {
                    this.username = ((LoginObject) incomingObject).getUserName();
                    //ConnectedClientList.setUserNameList(this.username);
                    System.out.println("Welcome motherfucker " + this.username);
                    out.writeObject(new LoginObject(this.username));
                    out.flush();
                    Broadcast(this.username + " is connected");

                }

                if (incomingObject instanceof LogoutObject) {

                    clientConnectionSocket.close();
                    break;
                }
                if (incomingObject instanceof MessageObject) {
                    //System.out.println("From client : " + ((MessageObject) incomingObject).getMessage());
                    Broadcast(((MessageObject) incomingObject).getMessage());

                }


            } catch (IOException | ClassNotFoundException e) {
                try {
                    clientConnectionSocket.close();
                    System.out.println("Closed connection to : " + clientConnectionSocket);
                    ///DELETE THREAD FROM LIST
                    ConnectedClientList.GetInstance().getClientThreads().remove(this);
                   // ConnectedClientList.GetInstance().DeleteClientThreadFromList(this);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                interrupted();
                break;
            }
        }
        try {
            interrupted();
            this.clientConnectionSocket.close();

            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void Broadcast(String message) throws IOException {

        for (ClientThreads f : ConnectedClientList.GetInstance().getClientThreads()) {
            if (f.username != this.username) {
                f.out.writeObject(new MessageObject(message));
                System.out.println(message);
                out.flush();
            }
        }

        // }


    }
}
