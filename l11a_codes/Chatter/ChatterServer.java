package Chatter;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
public class ChatterServer {

    public static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        try {
            ServerSocket server = new ServerSocket(10997);
            while (true) {
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                clients.add(clientHandler);
                clientHandler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
