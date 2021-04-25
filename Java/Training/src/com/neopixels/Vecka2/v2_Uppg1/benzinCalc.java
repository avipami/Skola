package com.neopixels.Vecka2.v2_Uppg1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class benzinCalc extends JFrame
{

    JPanel CalculatorPanel = new JPanel();
    JPanel showAverage = new JPanel();

    JTextField currKmInput = new JTextField();
    JTextField kmOneYAgoInput = new JTextField();
    JTextField usedGasInput = new JTextField();

    Double meterAtOneYearAgo = 0.0;
    Double currentKmSince = 0.0;
    Double usedGasSince = 0.0;


    public benzinCalc()
    {
        showAverage.setLayout(new FlowLayout());

        JLabel CurrentKm = new JLabel("Please input your current  KM : ");
        JLabel KMatOneYearAgo = new JLabel("Please input your previous Distance : ");
        JLabel UsedGasoline= new JLabel("How much juice did your car Drink ? : ");

        kmOneYAgoInput.addActionListener(new meterOneYearAgo());
        currKmInput.addActionListener(new currentKm());
        usedGasInput.addActionListener(new usedGas());

        CalculatorPanel.add(CurrentKm);
        CalculatorPanel.add(KMatOneYearAgo);
        CalculatorPanel.add(UsedGasoline);

        CalculatorPanel.add(currKmInput);
        CalculatorPanel.add(kmOneYAgoInput);
        CalculatorPanel.add(usedGasInput);

        JLabel AvgConsumption = new JLabel("This is how thirsty your vechicle is : ");

        showAverage.add(AvgConsumption);

        CalculatorPanel.setLayout(new GridLayout(2,3));
        showAverage.setLayout(new FlowLayout());
        add(CalculatorPanel);
        add(showAverage);

        setLayout(new GridLayout(2,1));
        setSize(800,150);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }



    public static void calcProgram ()
    {
        benzinCalc b = new benzinCalc();

        int traveledDistance = (int)(b.meterAtOneYearAgo-b.currentKmSince);
        int usedGasPerKM = (int)(traveledDistance/b.usedGasSince);
        JLabel calculatedGas = new JLabel();
        b.showAverage.add(calculatedGas);


    }

    public static void main(String[] args)
    {

        calcProgram();

    }



    class meterOneYearAgo implements ActionListener
    {


        @Override
        public void actionPerformed(ActionEvent e)
        {
            meterAtOneYearAgo = Double.parseDouble(kmOneYAgoInput.getText());
            System.out.println(meterAtOneYearAgo);
        }
    }

    class currentKm implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            currentKmSince = Double.parseDouble(String.valueOf(currKmInput.getText()));
        }
    }

    class usedGas implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            usedGasSince = Double.parseDouble(String.valueOf(usedGasInput.getText()));
        }
    }
}
