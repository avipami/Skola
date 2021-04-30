package Model;

import Server.ConnectedClientList;

import java.util.List;

public class MessageObject extends Request {
    private String message;
    private String userName;


    public MessageObject(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public String getUserName(){return this.userName;}
}
