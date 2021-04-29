package com.neopixels.Vecka3.EgenUppgiftObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    private ArrayList<UserStatusListener> userStatusListeners = new ArrayList<>();

    public static void main(String argv[]) {new Client();}
    public Client(){
        Client client = new Client();
        client.addUserStatusListener(new UserStatusListener() {
            @Override
            public void online(String login) {
                System.out.println(" ONLINE : " + login);
            }

            @Override
            public void offline(String logout) {
                System.out.println(" OFFLINE : " + logout);
            }
        });
        try{
            Socket server = new Socket(InetAddress.getLocalHost(),1234);
            printMessage();

            ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(server.getInputStream());

            out.writeObject(new DateRequest());
            out.flush();
            System.out.println(in.readObject());

            out.writeObject(new MyCalculation(2));
            out.flush();
            System.out.println(in.readObject());

            server.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }

    }
    public void printMessage()
    {
        System.out.println("Client Connected");
    }

    public void addUserStatusListener(UserStatusListener listener)
    {
        userStatusListeners.add(listener);
    }
    public void removeUserStatusListener(UserStatusListener listener)
    {
        userStatusListeners.remove(listener);
    }

}
