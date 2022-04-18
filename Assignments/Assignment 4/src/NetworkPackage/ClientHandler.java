package NetworkPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import UserPackage.Player;
import UserPackage.PlayerController;
import VehiclePackage.Car;
import VehiclePackage.Vehicle;
import mainGameLogic.mainGameLogic;

public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    public static mainGameLogic gameLogic = new mainGameLogic();
    
    private Socket sock;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientName;
    private Player player;
    private PlayerController playerController;
    private Vehicle playerVehicle;
    
    public ClientHandler(Socket socket) {
        int playerID, vID, vStartSpeed, vStartRoadID, vStartLane;

        try {
            this.sock = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.sock.getOutputStream()));

            this.bufferedWriter.write("Enter your player name: ");
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
            this.clientName = bufferedReader.readLine();

            this.bufferedWriter.write("Enter a unique ID for yourself: ");
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
            playerID = bufferedReader.read();

            while (this.gameLogic.players.containsKey(playerID)) {
                this.bufferedWriter.write("Enter a unique ID for yourself that is not already in use: ");
                this.bufferedWriter.newLine();
                this.bufferedWriter.flush();
                playerID = bufferedReader.read();

            }

            this.gameLogic.players.put(playerID, new Player(this.clientName, playerID));

            this.player = this.gameLogic.players.get(playerID);

            this.bufferedWriter.write("Enter a unique ID for your vehicle: ");
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
            vID = this.bufferedReader.read();

            while (this.gameLogic.map.doesPlayerVehicleExist(vID) | this.gameLogic.map.doesAIVehicleExist(vID)) {
                this.bufferedWriter.write("Enter a unique ID for vehicle that is not already in use: ");
                this.bufferedWriter.newLine();
                this.bufferedWriter.flush();
                vID = bufferedReader.read();

            }

            this.gameLogic.map.getAllRoads().forEach(entity -> {
                try {
                    this.bufferedWriter.write(entity.getRoadID());
                    this.bufferedWriter.newLine();
                    this.bufferedWriter.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                    
                }
                
            });

            this.bufferedWriter.write("Enter the ID of the road your vehicle start on: ");
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
            vStartRoadID = this.bufferedReader.read();

            this.bufferedWriter.write("Which lane on the road does your vehicle start on: ");
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
            vStartLane = this.bufferedReader.read();

            this.player.changeVehicle(new Car(vID, 0, this.gameLogic.map.getRoad(vStartRoadID), vStartLane, this.player));

            this.playerController = new PlayerController(this.player);

            this.clientHandlers.add(this);

            sendMSGToAllClients("SERVER: " + this.clientName + " has joined the game.");
        
        } catch (IOException e) {
            deleteClient(this.sock, this.bufferedReader, this.bufferedWriter);

        }

    }

    @Override
    public void run() {
        String msgFromClient;

        this.gameLogic.startGame();

        while (this.sock.isConnected()) {
            try {
                msgFromClient = this.bufferedReader.readLine();
                sendMSGToAllClients(msgFromClient);
                
            } catch (IOException e) {
                deleteClient(this.sock, this.bufferedReader, this.bufferedWriter);
                
                break;

            }

        }
        
    }

    public void sendMSGToAClient(String msg, int clientID) {
        for (ClientHandler e : this.clientHandlers) {
            try {
                if (e.player.getUserID() == clientID) {
                    e.bufferedWriter.write(msg);
                    e.bufferedWriter.newLine();
                    e.bufferedWriter.flush();

                }

            } catch (Exception ex) {
                removeClientHandler();

            }

        }

    }

    public void sendMSGToAllClients(String msg) {
        for (ClientHandler e : this.clientHandlers) {
            try {
                if (!e.clientName.equals(this.clientName)) {
                    e.bufferedWriter.write(msg);
                    e.bufferedWriter.newLine();
                    e.bufferedWriter.flush();

                }

            } catch (IOException ex) {
                deleteClient(this.sock, this.bufferedReader, this.bufferedWriter);
            }

        }

    }

    public void removeClientHandler() {
        this.clientHandlers.remove(this);

        sendMSGToAllClients("SERVER: " + this.clientName + " has left the chat.");

    }

    public void deleteClient(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
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
