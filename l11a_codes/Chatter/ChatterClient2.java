/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Kyle
 */
public class ChatterClient2 {

    public static void main(String[] args) throws IOException {
       
        String hostName= "localhost";
        int port = 10997;
        
        try (
            Socket echoSocket = new Socket(hostName, port);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader( new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader( new InputStreamReader(System.in))
        ) {
            String userInput;
                        
            new Thread( () -> {
                String message;
                try{
                    while((message = in.readLine()) != null){System.out.println(message);}
                }
                catch(Exception e){}
            }).start();
            

            for ( int i = 0; i < 100; i++ ) {
                out.println( "Sending message number " + i );
                Thread.sleep(1000);
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } catch (Exception e) {
            System.err.println("An error happenned " + hostName);
            e.printStackTrace();
            System.exit(1);
        } 
    }
}
