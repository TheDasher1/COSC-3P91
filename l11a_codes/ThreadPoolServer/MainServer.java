/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThreadPoolServer;

import Multithreading.*;
import java.io.IOException;

/**
 *
 * @author robson
 */
public class MainServer {
    public static void main(String [] agrs) throws IOException{
        ThreadPooledServer server = new ThreadPooledServer(9000);
        new Thread(server).start();

        try {
            Thread.sleep(45 * 1000);
        } catch (InterruptedException e) {
        }
        System.out.println("Stopping newServer");
        server.stop();
    }
}