package ClientPackage;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    private Socket sock;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String userName;

    public Client(Socket socket, String userName) {
        try {
            this.sock = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.sock.getOutputStream()));
            this.userName = userName;
        
        } catch (IOException ioe) {
            closeEverything(this.sock, this.bufferedReader, this.bufferedWriter);

        }

    }

    public void sendMessage() {
        try {
            this.bufferedWriter.write(this.userName);
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();

            Scanner input = new Scanner(System.in);

            while (this.sock.isConnected()) {
                String sendMsg = input.nextLine();

                this.bufferedWriter.write(this.userName + ": " + sendMsg);
                this.bufferedWriter.newLine();
                this.bufferedWriter.flush();

            }

        } catch (IOException ex) {
            closeEverything(this.sock, this.bufferedReader, this.bufferedWriter);
            
        }

    }

    public void listenForMessage() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                String msgFromGroup;

                while (sock.isConnected()) {
                    try {
                        msgFromGroup = bufferedReader.readLine();
                        System.out.println(msgFromGroup);

                    } catch (Exception e) {
                        closeEverything(sock, bufferedReader, bufferedWriter);

                    }

                }
                
            }
            
        }).start();

    }
    
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (socket != null) socket.close();

            if (bufferedReader != null) bufferedReader.close();

            if (bufferedWriter != null) bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
            
        }

    }

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a user name to use: ");
        String userName = input.nextLine();

        Socket socket = new Socket("localhost", 9001);
        Client client = new Client(socket, userName);

        client.listenForMessage();
        client.sendMessage();

    }
    
}
