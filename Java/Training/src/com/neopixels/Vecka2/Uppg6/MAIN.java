package V2.Uppg6;

import javax.swing.*;

public class MAIN
{
    public static void main(String[] args) {

        while(true)
      {
          String medName = JOptionPane.showInputDialog(null,"MedName ? ");
          if(medName.equalsIgnoreCase("die"))break;
          double interval = Double.parseDouble(JOptionPane.showInputDialog("How many times a Min ya need to take meds?"));
          MEDICINE med = new MEDICINE(medName,interval);
          med.start();

      }
        System.out.println("BYE");
    }
}
