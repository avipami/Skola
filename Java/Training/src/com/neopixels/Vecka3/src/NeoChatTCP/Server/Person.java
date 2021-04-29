package NeoChatTCP.Server;

import java.sql.Timestamp;

public class Person {
    private String nickName;
    private Timestamp loginTimestamp;

    public Person(String nickName, Timestamp loginTimestamp)
    {
        this.nickName = nickName;
        this.loginTimestamp = loginTimestamp;

    }

    public String getNickName()
    {
        return this.nickName;
    }


}
