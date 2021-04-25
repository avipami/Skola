package com.neopixels.Vecka1.Uppg7;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.Scanner;
import java.util.DoubleSummaryStatistics;

public class readTempFromFile {

    public static void main(String[] args) {
        boolean loop = true;
        while (loop)
        {
            try
            {
                Scanner inputChoice = new Scanner(System.in);
                System.out.println("Wanna read the file ? y/n : ");

                if(inputChoice.nextLine().equalsIgnoreCase("n"))
                {
                    loop = false;
                    break;
                }

                File myObj = new File("file.txt");
                if (myObj.createNewFile())
                {
                    System.out.println("File created " + myObj.getName());
                }

                ArrayList<Integer> temps = new ArrayList<Integer>();
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                    temps.add(Integer.parseInt(data));
                }



                Optional<Integer> maxNumber = temps.stream().max((i, j)-> i.compareTo(j));
                System.out.println("HIGHEST FUCKING READ NUMBER : "+maxNumber.get());

                Optional<Integer> minNumber = temps.stream().min((i, j)-> i.compareTo(j));
                System.out.println("LOWEST FUCKING READ NUMBER : "+minNumber.get());

                DoubleSummaryStatistics floatAverageblablabla = temps.stream().mapToDouble((x)->x).summaryStatistics();
                System.out.println(floatAverageblablabla);
                System.out.println("övre e floatie");
                IntSummaryStatistics average = temps.stream().mapToInt((x)->x).summaryStatistics();
                System.out.println(average);


                System.out.println("Wanna add something to the file? y/n : ");
                if(inputChoice.next().equals("n"))
                {
                    loop = false;

                }

                FileWriter addToFile = new FileWriter("file.txt");
                System.out.println(" Please input what you Would like to insert to the File : \n");
                String whatToInput = inputChoice.nextLine();
                addToFile.write(whatToInput);
                addToFile.close();
                myReader.close();
            }
            catch (IOException nf )
            {
                System.out.println("Error : ");
                nf.printStackTrace();
            }

        }
        System.out.println("goodBye!");
        System.exit(0);
    }


}
