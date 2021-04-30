package V2.NeoChat;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.concurrent.atomic.AtomicBoolean;

public class Receive implements Runnable
{
    Thread threads = new Thread( this);
    MulticastSocket mcSocket;
    JTextArea chatWindow;
    String Name;
    JScrollPane scroller = new JScrollPane(chatWindow);


    public Receive(MulticastSocket mcSocket,JTextArea chatWindow,String name){
        this.mcSocket = mcSocket;
        this.chatWindow = chatWindow;
        this.Name = name;

            // Start the threads and run the run run method
        this.threads.start();

    }

    @Override
    public void run() {

        byte[] incomingData = new byte[1024];
        while (!Thread.interrupted())
        {
            try{
                    //create dataReceiveStorage
                DatagramPacket datagramPacket = new DatagramPacket(incomingData, incomingData.length);
                    //waiting for incomingData
                this.mcSocket.receive(datagramPacket);

                String message = new String(incomingData,0,datagramPacket.getLength());

                    //append into the chatWindow and autoscroll
                this.chatWindow.setCaretPosition(this.chatWindow.getDocument().getLength());
                this.chatWindow.append(message +"\n\n");

                    //when shit happens
            } catch (InterruptedIOException e) {
                System.out.println(" Disconnected");
                this.mcSocket.disconnect();
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
