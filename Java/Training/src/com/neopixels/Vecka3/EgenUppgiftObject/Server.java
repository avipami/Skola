package com.neopixels.Vecka3.EgenUppgiftObject;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    public static void main( String argv[] ) throws IOException
    {
        ServerSocket ss = new ServerSocket( 1234 );
        while ( true ) {
            new ServerConnection(ss.accept()).start();
            printMessage();
        }
    }
    public static void printMessage()
    {
        System.out.println("Server online");
    }

} // end of class Server

class ServerConnection extends Thread
{
    Socket client;
    ServerConnection ( Socket client ) throws SocketException
    {
        this.client = client;
        setPriority( NORM_PRIORITY - 1 );
    }

    public void run( )
    {
        try
        {
            ObjectInputStream in = new ObjectInputStream( client.getInputStream( ) );
            ObjectOutputStream out = new ObjectOutputStream( client.getOutputStream( ) );
            while ( true ) {
                out.writeObject( processRequest( in.readObject( ) ) );
                out.flush( );
            }
        } catch ( EOFException e3 ) { // Normal EOF
            try {
                client.close( );
            } catch ( IOException e ) { }
        } catch ( IOException e ) {
            System.out.println( "I/O error " + e ); // I/O error
        } catch ( ClassNotFoundException e2 ) {
            System.out.println( e2 ); // unknown type of request object
        }
    }

    private Object processRequest( Object request )
    {
        if ( request instanceof DateRequest )
            return new java.util.Date( );
        else if ( request instanceof WorkRequest )
            return ((WorkRequest)request).execute( );
        else
            return null;
    }


}