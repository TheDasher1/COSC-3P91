package NetworkPackage;

import java.io.*;
import java.net.*;
import IOPackage.Basic;

public class Server {

    private ServerSocket serveSocket;
    // private Basic bs;

    public Server(ServerSocket serverSocket) {
        this.serveSocket = serverSocket;

    }

    public void start() {
        try {
            while (!this.serveSocket.isClosed()) {
                Socket sock = this.serveSocket.accept();

                System.out.println("A new user has connected.");

                ClientHandler clientHandler = new ClientHandler(sock);

                Thread thread = new Thread(clientHandler);

                thread.start();

            }
            
        } catch (IOException ex) {
            ex.printStackTrace();

        }

    }

    public static void main(String[] args) throws IOException {
        // ServerSocket serveSock = new ServerSocket(9001);
        Basic bs = new Basic();
        int portNum;

        // Socket sock = serveSock.accept();

        // System.out.println("Client connected.");

        // new Server(serveSock).start();

        while (true) {
            System.out.print("Enter a port number to start a server on( > 9000)(0 to exit): ");
            portNum = bs.getInt();

            if (portNum == 0) {
                break;

            } else if (portNum > 9000) {
                ServerSocket newServerSocket = new ServerSocket(portNum);

                new Server(newServerSocket).start();

            }

        }
        
    }
    
}
