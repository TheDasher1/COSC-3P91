/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThreadPoolServer;

import Multithreading.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author robson
 */

public class WorkerRunnable implements Runnable{
    protected Socket clntSocket = null;
    protected String txtFrmSrvr   = null;

    public WorkerRunnable(Socket clntSocket, String txtFrmSrvr) {
        this.clntSocket = clntSocket;
        this.txtFrmSrvr   = txtFrmSrvr;
    }
    public void run() {
        try {
            InputStream inputstrm  = clntSocket.getInputStream();
            OutputStream outputstrm = clntSocket.getOutputStream();
            long timetaken = System.currentTimeMillis();
            outputstrm.write(("OK\n\nWorkerRunnable: " + this.txtFrmSrvr + " - " +timetaken +"").getBytes());
            outputstrm.close();
            inputstrm.close();
            System.out.println("Your request has processed in time : " + timetaken);

            Thread.sleep(3000);

        } catch (IOException e) {           
            e.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(WorkerRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
