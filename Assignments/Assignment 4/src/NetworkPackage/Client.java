package NetworkPackage;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import UserPackage.Player;
import IOPackage.Basic;

public class Client {

    private Socket sock;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String userName;
    private int userID;
    
    public Client(Socket socket) {
        try {
            this.sock = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.sock.getOutputStream()));
            // this.userName = userName;
            // this.userID = userID;

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

            input.close();

        } catch (IOException ex) {
            closeEverything(this.sock, this.bufferedReader, this.bufferedWriter);
            
        }

    }

    public void getCommand() {
        String tempCommand;

        try {
            this.bufferedWriter.write(this.userName);
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();

            Scanner input = new Scanner(System.in);

            while (this.sock.isConnected()) {
                tempCommand = input.nextLine();

                this.bufferedWriter.write(tempCommand);
                this.bufferedWriter.newLine();
                this.bufferedWriter.flush();

            }

            input.close();

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
        // Scanner input = new Scanner(System.in);
        Basic bs = new Basic();

        System.out.print("Enter the port number to join: ");
        int portNum = bs.getInt();

        // System.out.print("Enter a user name to use: ");
        // String userName = bs.getString();

        // System.out.print("Enter a user ID for yourself: ");
        // int uID = bs.getInt();

        Socket socket = new Socket("localhost", portNum);
        Client client = new Client(socket);

        client.listenForMessage();
        client.sendMessage();

    }
    
}
