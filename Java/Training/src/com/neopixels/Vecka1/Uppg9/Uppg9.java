package com.neopixels.Vecka1.Uppg9;

import java.net.MalformedURLException;
import java.net.URL;

/*
Uppgift 8–Inläsning/skrivning till fil
Ladda ner filen personuppgifter.txt från Nackademins portal.
Filen innehåller personuppgifter. Förvarje person står personens namn,
adress och på nästa rad personens ålder, längd och vikt.
Du ska läsa in filen i ditt program och hitta alla personer som är längre än 2 meter.
Skapa sedan en ny textfil som bara innehåller uppgifterna för de långa personerna.
Använd try-with-resourcesExempel på personpost i infilen: Kalle Nilsson, Xvägen 1, 12345 Ystad25, 80, 175
 */
public class Uppg9 {
/*
    public static void checkFile(ArrayList<PERSON> person)
    {

        try(BufferedReader bufIn = new BufferedReader(new FileReader("file.txt"));)
        {
        while (bufIn.readLine() && !Null)
            {

            }
            Path path = Path.get("src//Uppg9//text");


        }

        catch (IOException nf )
        {
            System.out.println("Error : ");
            nf.printStackTrace();
        }
    }
*/

    public static void main(String[] args) throws MalformedURLException {
        URL url=new URL("https://raw.githubusercontent.com/dwyl/english-words/master/words.txt");

    }


}
