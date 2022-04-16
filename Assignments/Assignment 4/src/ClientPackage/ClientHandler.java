package ClientPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    
    private Socket sock;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientName;
    
    public ClientHandler(Socket socket) {
        try {
            this.sock = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.sock.getOutputStream()));

            this.clientName = bufferedReader.readLine();

            this.clientHandlers.add(this);

            broadCastMessage("SERVER: " + this.clientName + " has entered the chat.");
        
        } catch (IOException e) {
            closeEverything(this.sock, this.bufferedReader, this.bufferedWriter);

        }

    }

    @Override
    public void run() {
        String msgFromClient;

        while (this.sock.isConnected()) {
            try {
                msgFromClient = this.bufferedReader.readLine();
                broadCastMessage(msgFromClient);
                
            } catch (IOException e) {
                closeEverything(this.sock, this.bufferedReader, this.bufferedWriter);
                
                break;

            }

        }
        
    }

    public void broadCastMessage(String msg) {
        for (ClientHandler e : this.clientHandlers) {
            try {
                if (!e.clientName.equals(this.clientName)) {
                    e.bufferedWriter.write(msg);
                    e.bufferedWriter.newLine();
                    e.bufferedWriter.flush();

                }

            } catch (IOException ex) {
                closeEverything(this.sock, this.bufferedReader, this.bufferedWriter);
            }

        }

    }

    public void removeClientHandler() {
        this.clientHandlers.remove(this);

        broadCastMessage("SERVER: " + this.clientName + " has left the chat.");

    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClientHandler();

        try {
            if (socket != null) socket.close();

            if (bufferedReader != null) bufferedReader.close();

            if (bufferedWriter != null) bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
            
        }

    }
    
}
