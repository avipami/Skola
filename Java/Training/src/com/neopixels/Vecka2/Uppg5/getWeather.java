package V2.Uppg5;

import javax.swing.*;

public class getWeather extends JOptionPane
{
    private String inDataLocation;
    private String inDataTemp;
    private String send;


    public getWeather ()
    {
            this.inDataLocation = JOptionPane.showInputDialog("Current City");
            this.inDataTemp = JOptionPane.showInputDialog("Current Temp");
    }

    public String returnStringWithWeatherData()
    {
        send = inDataLocation + " " + inDataTemp;
        return send;
    }

}
