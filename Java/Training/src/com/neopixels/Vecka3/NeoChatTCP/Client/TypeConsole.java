package Client;

import Model.MessageObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class TypeConsole extends Thread {
    private String message;
    private ObjectOutputStream out;
    private String username;



    public TypeConsole(String username, ObjectOutputStream out) {
        this.out = out;
        this.username = username;
    }

    @Override
    public void run() {
        System.out.println(" ('^_^')/o ");
        Scanner scanner = new Scanner(System.in);
        message = scanner.nextLine();

        try {
            out.writeObject(new MessageObject(username + " : " + message));
            out.flush();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}
