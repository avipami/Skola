package com.neopixels.Vecka1.Uppg6;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class change {
    private static String s = new String();
    private static int[] sedlarOmynt = {100,500,200,100,50,20,10,5,2,1};
    protected static String kronor = new String ("kronor");
    protected static String sedlar = new String("sedlar");

    public static void changeFunc(int valör, int amountOfValör)
    {
        String pengatyp = new String();
        if(valör>=20)
        {
            pengatyp = sedlar;
        }
        else {
            pengatyp = kronor;

        }
        if(amountOfValör>0)
        {
            s += "Antal "+valör+"-"+pengatyp+": "+amountOfValör+"\n";
        }

    }

    public static void main (String[] args)
    {
        System.out.println("Ange Pris : ");
        Scanner sc = new Scanner(System.in);
        try{
            int priceOfWare = sc.nextInt();
            System.out.println("Ange summa du betalade med: ");
            int amount = sc.nextInt();
            int change = amount-priceOfWare;

            if (change < 0)
            {
                System.out.println("För lite cash");
                System.exit(0);
            }
            System.out.println("Detta ska du få tillbaka : ");
            int amountOfValör;

            for(int i = 0; i < sedlarOmynt.length; i++)
            {
                amountOfValör = change / sedlarOmynt[i];
                changeFunc(sedlarOmynt[i],amountOfValör);
                change -=(amountOfValör*sedlarOmynt[i]);
            }
            System.out.println(s);
        }
        catch (InputMismatchException e) {
            System.out.println("Felaktigt tal!");
            sc.nextLine();//flushar input data så att inget ligger kvar
        }
        catch (NoSuchElementException e) {
            System.out.println("Indata saknas!");
            sc.nextLine();
        }
        catch (Exception e) {
            System.out.println("Ospecifierat fel inträffade, försök igen!");
            sc.nextLine();
        }
    }


}
