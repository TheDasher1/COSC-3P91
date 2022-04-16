package Chatter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {

    static String[] names = {"Alpha", "Beta", "Gamma", "Delta"};
    static int users = 0;
    
    private Socket client;
    private String clientName;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket client) {
        this.client = client;
        clientName = names[users++]; //assign the user a name
        try {
            in = new BufferedReader(new InputStreamReader(
                    client.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(
                    client.getOutputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getClientName() {
        return clientName;
    }

    public synchronized void sendMessage(String msg) {
        if (out != null) {
            out.println(msg);
            out.flush();
        }
    }

    public void broadcastMessage(String msg) {
        for (ClientHandler ch : ChatterServer.clients) {
            if (ch != this) {
                ch.sendMessage(msg);
            }
        }
    }

    @Override
    public void run() {
        if (in != null && out != null) {
            try {
                while (true) {
                    String str = in.readLine();
                    if (str == null || str.trim().equalsIgnoreCase("bye")) {
                        break;
                    }
                    broadcastMessage("From " + clientName + ": " + str);
                }
                broadcastMessage("User " + clientName + " has closed connection.");
                client.close();
                ChatterServer.clients.remove(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
