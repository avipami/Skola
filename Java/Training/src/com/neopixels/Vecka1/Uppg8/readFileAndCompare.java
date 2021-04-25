package com.neopixels.Vecka1.Uppg8;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class readFileAndCompare {

    public static void main(String[] args)
    {

        String [] peoples = new String[6];
        Path readPath = Paths.get("src\\com\\neopixels\\Uppg8\\Personuppgifter.txt");
        Path savePath = Paths.get("src\\com\\neopixels\\Uppg8\\TallPeeps.txt");

        ArrayList<PERSON> person = new ArrayList<>();

        try(PrintWriter x = new PrintWriter(Files.newBufferedWriter(savePath));
            Scanner fileScanner = new Scanner(readPath); )
        {
            while (fileScanner.hasNext()){
                PERSON TempPerson = new PERSON();
                String firstLine;
                firstLine = fileScanner.nextLine();
                String firstLineArray[] = firstLine.split(",");
                peoples[0] = firstLineArray[0].trim();
                peoples[1] = firstLineArray[1].trim();
                peoples[2] = firstLineArray[2].trim();

                TempPerson.set_Name(firstLineArray[0].trim());
                TempPerson.set_StreetAddress(firstLineArray[1].trim());
                TempPerson.set_Address(firstLineArray[2].trim());

                if(fileScanner.hasNext()){
                    String secondLine;
                    secondLine = fileScanner.nextLine();
                    String secondLineArray[] = secondLine.split(",");

                    peoples[3] = secondLineArray[0].trim();
                    peoples[4] = secondLineArray[1].trim();
                    peoples[5] = secondLineArray[2].trim();

                    TempPerson.set_age(secondLineArray[0].trim());
                    TempPerson.set_Weight(secondLineArray[1].trim());
                    TempPerson.set_Height(secondLineArray[2].trim());

                    person.add(TempPerson);
                }
            }
            person.forEach((n)->
            {
                if(Integer.parseInt(n.get_Height())>199)
                {
                    x.println(n.get_Name()+" "+n.get_Height());
                    x.flush();
                }
            });
        }

        catch (FileNotFoundException ERROR)
        {
            System.out.println("ERROR FILE :"); ERROR.printStackTrace();
        }
        catch (IOException ERROR)
        {
            System.out.println("ERROR : "); ERROR.printStackTrace();
        }
        catch (Exception ERROR)
        {
            System.out.println("OOPS : "); ERROR.printStackTrace();
        }

    }

}


