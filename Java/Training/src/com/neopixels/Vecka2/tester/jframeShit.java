package com.neopixels.Vecka2.tester;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class jframeShit extends JFrame implements ActionListener{

    JLabel label = new JLabel("NEJ!!",SwingConstants.CENTER);
    JButton button = new JButton("PRESS ME PLZ");
    JTextField textInput = new JTextField();
    jframeShit(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,3));
        button.addActionListener(this);
        setSize(300,300);
        this.add(panel);
        label.setForeground(Color.white);
        panel.add(label);
        panel.add(textInput);


        panel.add(button).setBackground(Color.CYAN);
        panel.add(new JButton("Korkad?"));
        panel.add(new JLabel("BAJS",SwingConstants.CENTER));
        panel.setBackground(Color.MAGENTA.darker());
        this.setLocation(1000,500);
        this.setVisible(true);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        String input = textInput.getText();
    }
    public static void main(String[] args) {

        jframeShit shit = new jframeShit();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(textInput.getText() != null){

            String namn = textInput.getText();
            System.out.println(namn);
        }
        if(label.getText().equalsIgnoreCase("NEJ!!"))
        {
            label.setText("Hej");
        }

    }
}
