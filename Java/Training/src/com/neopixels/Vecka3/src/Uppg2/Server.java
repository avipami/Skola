package Uppg2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{

    TelephoneBook telefoneBok = new TelephoneBook();
    private int portNr = 12345;
    public Server() throws IOException, ClassNotFoundException {

        try (
                ServerSocket serverSocket = new ServerSocket(portNr);
                Socket clientSocket = serverSocket.accept();
                ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ){

            System.out.println("Server Activated");
            Object inputLine;
            String outputPerson;

            //Fuling, vi gör såhär eftersom klienten bara tar emot Person
            oos.writeObject(new Person("null", "Vems adress behöver du veta","null"));

            while ((inputLine = (String)ois.readObject()) != null) {

                outputPerson = telefoneBok.getTelefoneNumberFrom(((String)inputLine).trim());
                if (outputPerson == null){
                    //String n = "Denna person finns inte i databasen";
                    oos.writeObject(new Person("Null", "Denna person finns inte i databasen","null"));
                }
                else{
                    oos.writeObject(outputPerson);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
    Server server = new Server();
    }
}
