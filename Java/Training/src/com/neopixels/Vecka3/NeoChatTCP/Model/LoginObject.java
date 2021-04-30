package Model;

import Server.ConnectedClientList;

public class LoginObject extends Request {
    private String userName;

    public LoginObject(String userName) {
        this.userName = userName;
        ConnectedClientList.setUserNameList(userName);
    }

    public String getUserName() {
        return userName;
    }


}
