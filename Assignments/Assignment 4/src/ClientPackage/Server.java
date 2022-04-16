package ClientPackage;

import java.io.*;
import java.net.*;

public class Server {

    private ServerSocket serveSocket;

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

    public static void main(String[] args) throws IOException, UnknownHostException {
        ServerSocket serveSock = new ServerSocket(9001);

        // Socket sock = serveSock.accept();

        // System.out.println("Client connected.");

        new Server(serveSock).start();
        
    }
    
}
