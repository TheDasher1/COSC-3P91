package UserPackage;

import MapPackage.*;
import VehiclePackage.*;

public class Player implements User {

    private String playerName, playerSID;
    private int playerID, repuation;
    private Vehicle playerVehicle;

    public Player(String PlayerName, int PlayerID) {
        this.playerName = PlayerName;
        this.playerID = PlayerID;
        this.repuation = 100;
        this.playerVehicle = null;

    }
    
    public Player(String PlayerName, int PlayerId, Vehicle PlayerVehicle) {
        this.playerName = PlayerName;
        this.playerID = PlayerId;
        this.repuation = 100;
        this.playerVehicle = PlayerVehicle;

    }

    @Override
    public String getUserName() {
        return this.playerName;
    }

    @Override
    public int getUserID() {
        return this.playerID;
    }

    @Override
    public Vehicle getVehicle() {
        return this.playerVehicle;
    }

    @Override
    public void changeVehicle(Vehicle newVehicle) {
        this.playerVehicle = newVehicle;
        
    }

    @Override
    public Vehicle[] lookAround(Road road) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vehicle[] lookAround(Intersection intersection) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean changeLanes(Road roadName, int newLane) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean turnAtIntersection(Road roadToTurn) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean turnAtIntersection(int roadNum) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void gamble() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getPlayerRep() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
