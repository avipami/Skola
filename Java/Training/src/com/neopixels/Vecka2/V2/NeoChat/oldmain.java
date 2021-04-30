package V2.NeoChat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;
import java.util.*;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;
import java.util.*;

public class oldmain
{

    public static void main(String[] args) throws IOException {
        ImageIcon logo = new ImageIcon("/Users/neopixel/Documents/Skola/Java/NeoChat/Gui-img/neoChatLogo3mini.png");
        String name = JOptionPane.showInputDialog(null,"Input Username","NeoChat",JOptionPane.QUESTION_MESSAGE,logo,null,"").toString();
        ChatGuiClient chat = new ChatGuiClient(name,"234.235.236.237",12540);
    }


}
