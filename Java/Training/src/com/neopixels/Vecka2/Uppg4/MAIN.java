package V2.Uppg4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class MAIN {

    public static void main(String[] args) throws IOException {

        getWeather gw = new getWeather();
        String weather = gw.returnStringWithWeatherData();
        System.out.println(weather);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        InetAddress toAdr = InetAddress.getLocalHost();
        int toPort = 56565;
        DatagramSocket socket = new DatagramSocket();

        System.out.println("?: ");

        if (weather!=null)
        {
            if(weather.equals("bye")) System.exit(0);

            byte[] data = weather.getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet = new DatagramPacket(data, data.length, toAdr, toPort);
            socket.send(packet);
            System.out.println("?: ");
        }System.exit(0);

    }
}
