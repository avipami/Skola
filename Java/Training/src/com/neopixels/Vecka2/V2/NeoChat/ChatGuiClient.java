package V2.NeoChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ChatGuiClient extends JFrame implements ActionListener {
    private String userName;
    private InetAddress IPAddress;
    private int port;
    private NetworkInterface networkInterfaceName;
    private InetSocketAddress group;

    MulticastSocket mcSocket;
    JTextArea chatWindow = new JTextArea();
    JTextField chatInputField = new JTextField();
    JScrollPane autoScrollerChatWindow = new JScrollPane(chatWindow);

    Font font = new Font("Roboto",Font.BOLD,13);
    JButton exit = new JButton("Exit Chat");
    ImageIcon icon = new ImageIcon("/Users/neopixel/Documents/Skola/Java/NeoChat/Gui-img/neoChatLogo3mini.png");
    JLabel iconLabel = new JLabel(icon);

        //constructor
    public ChatGuiClient(String userName, String IpAddress, int portNr) throws IOException
    {
        this.userName = userName;


            //Network Shits
            // An inetSocketAddress needs an ipaddress and the port combined
        this.group = new InetSocketAddress(IpAddress,port);
            //Needed an interfaceName for the new JoinGroup function.
        this.networkInterfaceName = NetworkInterface.getByName("en0");
        this.IPAddress = InetAddress.getByName(IpAddress);
        this.port = portNr;
        this.chatInputField.setToolTipText("Chat Input");

            //create socket/connection
        this.mcSocket = new MulticastSocket(this.port);
        this.mcSocket.joinGroup(this.group,this.networkInterfaceName);

            //activate Receive class
        new Receive(this.mcSocket,this.chatWindow,this.userName);
        sendMsg("Connected");

            //For the autoscroll and div function
        this.chatWindow.setCaretPosition(chatWindow.getDocument().getLength());
        this.chatWindow.setEditable(false);
        this.chatWindow.setBackground(new java.awt.Color(13, 95, 102));
        this.chatWindow.setFont(font);
        this.chatWindow.setForeground(Color.white);

            //add to frame

        add(this.iconLabel, BorderLayout.WEST);
        add(this.exit, BorderLayout.NORTH);
        add(this.autoScrollerChatWindow, BorderLayout.CENTER);
        add(this.chatInputField,BorderLayout.SOUTH);

            //actionListener Links
        this.exit.addActionListener(this::Exit);
        this.chatInputField.addActionListener(this);

            //set various stuff on frame
        getContentPane().setBackground(new java.awt.Color(13, 95, 102));
        setIconImage(this.icon.getImage());
        setTitle("NeoChat" + this.userName);
        setSize(400,600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public void sendMsg(String msg)
    {
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH.mm.ss");
        //simpleDateFormat.setDateFormatSymbols("");
        String timeStamp = new SimpleDateFormat("HH.mm.ss").format(new Date());

        byte[] data = (timeStamp + " : " + this.userName + "\n " +msg).getBytes(StandardCharsets.UTF_8);
        DatagramPacket packet = new DatagramPacket(data, data.length,this.IPAddress,this.port);
        try { this.mcSocket.send(packet); }
        catch (IOException ie) { System.out.println("error : " + ie); }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //if(e.getSource()==this.chatInputField){
            sendMsg(this.chatInputField.getText());
            this.chatInputField.setText("");
       // }
       /* else if(e.getSource() == this.exit){

            sendMsg("Disconnected");
            System.out.println("DISSAD");
            dispose();
            System.exit(0);
        }*/
    }

    public void Exit(ActionEvent e)
    {
        sendMsg("Disconnected");
        dispose();
        System.exit(0);
    }

}
