package V2.Uppg3;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private JPanel ButtonsLayoutPanel = new JPanel();

    private JTextArea textWindow = new JTextArea(10,40);

    private JTextField sendInput = new JTextField(10);

    private JButton sendButton = new JButton("Send");

    public GUI()
    {
        setTitle("ChatGui Neopixel 0.1");
        setFrameSettings();
        UpperGUI();
        LowerGui();

    }

    public void UpperGUI()
    {
        ButtonsLayoutPanel.setLayout(new FlowLayout());
        sendInput.setPreferredSize(new Dimension(80,30));
        sendButton.setPreferredSize(new Dimension(80,30));
        ButtonsLayoutPanel.add(sendInput);
        ButtonsLayoutPanel.add(sendButton);

        add(ButtonsLayoutPanel);
    }

    public void LowerGui()
    {
        this.textWindow.setLayout(new FlowLayout());
        textWindow.setPreferredSize(new Dimension(200,200));

        add(textWindow);
    }

    public void setFrameSettings()
    {
        setLayout(new FlowLayout());
        setVisible(true);
        setResizable(false);
        setSize(500,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
