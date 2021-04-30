package Server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ConnectedClientList {
    // Create and main list of active clients based on their host name / ip address.
    //Use the concurrent hashmap and maintain your list of clients in that.
    // The concurrent hashmap is safe and you won't need to use synchronization while adding / iterating / removing
    public static Set<ClientThreads> clientThreads = new HashSet<>();
    public static List<String> userNameList = new ArrayList<>();
    private static ConnectedClientList single_instance = null;

    private ConnectedClientList() {
    }

    public static ConnectedClientList GetInstance() {
        if (single_instance == null) {
            single_instance = new ConnectedClientList();
        }
        return single_instance;
    }


    public static void setUserNameList (String userName)
    {
        userNameList.add(userName);
    }


    public Set<ClientThreads> getClientThreads ()
    {
        return this.clientThreads;
    }

    public void AddClientThreadToList( ClientThreads clientToAdd) {

        this.clientThreads.add(clientToAdd);
    }

    public void DeleteClientThreadFromList(ClientThreads threadToClose)
    {
        for (ClientThreads f : clientThreads)
        {
            if(f ==threadToClose)
            {
                clientThreads.remove(f);
            }
        }
    }

}
