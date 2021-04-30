package V2.Uppg2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class frame extends JFrame
{
    JPanel SearchButtonPanel = new JPanel();
    JPanel TextPanel = new JPanel();
    JPanel BottomPanel = new JPanel();
    JTextField searchInput = new JTextField("Input Search");
    JTextArea FilePreview = new JTextArea(10,40);

    JLabel infoFromAction = new JLabel("idle");

    JButton Open = new JButton("Open");
    JButton Save = new JButton("Save");
    JButton Print = new JButton("Print");
    JButton Exit = new JButton("Exit");


    public frame()
    {
        setLayout(new GridLayout(3,1));

        upperBar();

        TextPanel.add(FilePreview);
        TextPanel.setLayout(new FlowLayout());

        this.add(SearchButtonPanel);
        this.add(TextPanel);
        this.add(BottomPanel,CENTER_ALIGNMENT);
        BottomPanel.add(infoFromAction);

        setSize(500,300);
        setLocation(550,250);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void upperBar()
    {
        this.SearchButtonPanel.setLayout(new FlowLayout());


        this.SearchButtonPanel.add(searchInput);
        this.SearchButtonPanel.add(Open);
        this.SearchButtonPanel.add(Save);
        this.SearchButtonPanel.add(Print);
        this.SearchButtonPanel.add(Exit);

        searchInput.addActionListener(new upperBarAction());
        Open.addActionListener(new upperBarAction());
        Save.addActionListener(new upperBarAction());
        Print.addActionListener(new printFunction());
        Exit.addActionListener(new upperBarAction());

    }

    class upperBarAction implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == Open){
                ReadFile(searchInput.getText());
            }
            else if(e.getSource()   ==  Save){
                SaveFile(searchInput.getText());
            }
            else if(e.getSource()   ==  Exit)
            {
                System.exit(2);
            }

        }
    }


    class printFunction implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {

            try
            {
                boolean complete = FilePreview.print();
                if(complete){
                    infoFromAction.setText("Success");
                } else {
                    infoFromAction.setText("Ooops Canceled");
                }
            }
            catch(PrinterException pe)
            {
                infoFromAction.setText("Something really wrong happened here");
            }


        }
    }

    private void ReadFile(String name)
    {
        try{
            FileReader r = new FileReader(name);
            FilePreview.read(r,null);

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void SaveFile(String name)
    {
        try{
            FileWriter fw = new FileWriter(name);
            FilePreview.write(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
