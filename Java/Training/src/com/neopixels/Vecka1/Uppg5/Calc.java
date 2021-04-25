package com.neopixels.Vecka1.Uppg5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calc {

    public static void main(String[] args)
    {

        System.out.println("Skriv in ditt enkla matteproblem : ");
        Scanner scanInput = new Scanner(System.in);


        List<String> calcList = new ArrayList<>();
        calcList.add(scanInput.next());
        calcList.add(scanInput.next());
        calcList.add(scanInput.next());

        System.out.println("Ditt tal blir : " + calculatorFunc.calculate(calcList));


    }
}