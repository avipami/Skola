/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neopixel.neochatwithgui;

/**
 *
 * @author neopixel
 */

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receive implements Runnable
{
    Thread threads = new Thread( this);
    MulticastSocket mcSocket;
    JTextArea chatWindow;

    public Receive(MulticastSocket mcSocket,JTextArea chatWindow){
        this.mcSocket = mcSocket;
        this.chatWindow = chatWindow;
        threads.start();

    }

    @Override
    public void run() {
        byte[] data = new byte[1024];
        while (true)
        {
            try{
                //skapa för inkommande data
                DatagramPacket datagPack = new DatagramPacket(data, data.length);
                //väntar på inkommande data
                mcSocket.receive(datagPack);

                String message = new String(data,0,datagPack.getLength());
                //visas i textfönstret
                chatWindow.append(message +"\n");

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
