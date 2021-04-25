package V1_ÖvnUppg9_LetterCount;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class ÖvnUppg9_LetterCount {

    public ÖvnUppg9_LetterCount () {

        String temp;
        int[] fördelning = new int[100];

        try{
            URL url = new URL("https://raw.githubusercontent.com/dwyl/english-words/master/words.txt");
        
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()))) {
                while ((temp = in.readLine()) != null ){
                    int size = temp.length();
                    fördelning[size]++;
                }
        
                for (int i = 0; i < fördelning.length; i++){
                    if (fördelning[i] != 0){
                        System.out.println("Antal ord med "+ i +" bokstäver: "
                                +fördelning[i] );
                    }
                    
                }
            }
        }
        catch (MalformedURLException e){
            e.printStackTrace();
       }

        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        ÖvnUppg9_LetterCount ö = new ÖvnUppg9_LetterCount();
    }
}
