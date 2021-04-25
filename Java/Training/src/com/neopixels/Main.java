package com.neopixels;

import com.neopixels.Vecka1.Uppg3.*;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        /*
        System.out.println("SHoeFactory");
        ArrayList <skor> shoes = new ArrayList<>();
        shoes.add(new skor(44,"Runner","White"));
        shoes.add(new skor(43,"Finskor", "Svarta"));
        printfordon();
*/
        double meterAtOneYearAgo;
        double drivenKmSince ;
        double usedGasSince;
        Scanner car = new Scanner(System.in);
        try {
            System.out.println("Enter driven distance 1 year ago : ");
            meterAtOneYearAgo = car.nextDouble();
            System.out.println("Enter todays total distance : ");
            drivenKmSince = car.nextDouble();
            System.out.println("Enter used gas since 1 year ago : ");
            usedGasSince = car.nextDouble();
        } catch (Exception a) {
            System.out.println("Did ya really input the correct data format ? ");
        }

       // var uppg4Bil = new avgCalculator(drivenKmSince, meterAtOneYearAgo, usedGasSince);
        //uppg4Bil.PrintAvg();

        printfordon();
    }

    public static ArrayList<NeoFordon> skapaFordon() {

        var fordon = new ArrayList<NeoFordon>();
        fordon.add(new Bil(100, 1000, 5, 4));
        fordon.add(new Båt(203, 33, 2000));
        fordon.add(new Tåg(202020, 222, 10));
        fordon.add(new Cykel(10, 60, 26, 21));
        fordon.add(new Cykel(12, 22, 22));
        return fordon;
    }

    public static void printfordon() {
        var fordonsLista = skapaFordon();
        fordonsLista.forEach((n) ->
        {
            n.printMe();
        });

    }



}
