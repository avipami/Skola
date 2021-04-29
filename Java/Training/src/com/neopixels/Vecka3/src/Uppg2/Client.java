package Uppg2;

import java.io.*;
import java.net.Socket;

public class Client {
    private int port = 12345;
    private String IpAddress = "127.0.0.1";
    public Client () throws IOException {

        try (
                Socket addressSocket = new Socket(IpAddress,port);
                ObjectInputStream ois = new ObjectInputStream(addressSocket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(addressSocket.getOutputStream());
                ){
            Object fromServer;
            String fromUser;

            BufferedReader bfr= new BufferedReader(new InputStreamReader(System.in));

            while ((fromServer = (Person)ois.readObject())!=null)
            {
                System.out.println("Server : " + fromServer);

                fromUser = bfr.readLine();
                if(fromUser != null)
                {
                    System.out.println("Client: "+fromUser);
                    oos.writeObject(fromUser);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();

    }
}
