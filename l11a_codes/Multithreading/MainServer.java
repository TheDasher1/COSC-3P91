/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multithreading;

import java.io.IOException;

/**
 *
 * @author robson
 */
public class MainServer {
    public static void main(String [] agrs) throws IOException{
        ServerMultithreaded newServer = new ServerMultithreaded(9191);
        new Thread(newServer).start();

        try {
            Thread.sleep(45 * 1000);
        } catch (InterruptedException e) {
        }
        System.out.println("Stopping newServer");
        newServer.stop();
    }
}