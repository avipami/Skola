package V2.Uppg5;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MulticastSocket;
import java.net.SocketException;

public class DatagramReceiver
{
    public static void main(String[] args) throws SocketException, IOException {

        int minPort=56565;
        MulticastSocket socket = new MulticastSocket(minPort);
        byte[] data = new byte[256];
        while(true){
            DatagramPacket packet = new DatagramPacket(data,data.length);
            socket.receive(packet);
            System.out.println("Meddelande från : "
            + packet.getAddress().getHostAddress());
            String message = new String(packet.getData(),0,packet.getLength());
            System.out.println(message);
        }
    }
}
