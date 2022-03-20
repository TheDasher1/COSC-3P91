package VehiclePackage;

import MapPackage.*;
import UserPackage.*;

public class Bus implements Vehicle {

    int topSpeed = 4, currSpeed = 0, health = 130, inLane = 1, vehicleID, currPos = 0;
    String vehicleColor, vehicleMake;
    Road currRoad;
    Intersection currIntersection;
    // Player[] passengers;
    private int currSpot = 0;

    public Bus(int vehicleID, String vehicleMake, String vehicleColor, int startSpeed, Road startingRoad, int startInLane) {
        this.vehicleID = vehicleID;
        this.vehicleMake = vehicleMake;
        this.vehicleColor = vehicleColor;
        this.currSpeed = startSpeed;
        this.currRoad = startingRoad;
        this.inLane = startInLane;
    
    }

    @Override
    public boolean increaseSpeed(int incBy) {
        // TODO Auto-generated method stub
        if (incBy < this.topSpeed & (this.currSpeed + incBy) < this.topSpeed) {
            this.currSpeed += incBy;

            return true;

        }

        return false;
    }

    @Override
    public boolean decreaseSpeed(int decBy) {
        // TODO Auto-generated method stub
        if (decBy > 0 & (this.currSpeed - decBy) > 0) {
            this.currSpeed -= decBy;

            return true;

        }

        return false;
    }

    @Override
    public int getCurrSpeed() {
        // TODO Auto-generated method stub
        return this.currSpeed;
    }

    @Override
    public int getHealth() {
        // TODO Auto-generated method stub
        return this.health;
    }

    @Override
    public int getLane() {
        // TODO Auto-generated method stub
        return this.inLane;
    }

    @Override
    public void changeLane(int laneNum) {
        // TODO Auto-generated method stub
        this.inLane = laneNum;
    }

    @Override
    public void changeVehicleColor(String newColor) {
        // TODO Auto-generated method stub
        this.vehicleColor = newColor;
    }

    @Override
    public void changeVehicleID(int newID) {
        // TODO Auto-generated method stub
        this.vehicleID = newID;
    }

    @Override
    public int getVehicleID() {
        // TODO Auto-generated method stub
        return this.vehicleID;
    }

    @Override
    public String getVehicleMake() {
        // TODO Auto-generated method stub
        return this.vehicleMake;
    }

    @Override
    public Road getCurrRoad() {
        // TODO Auto-generated method stub
        return this.currRoad;
    }

    @Override
    public void updateCurrRoad(Road newRoad) {
        // TODO Auto-generated method stub
        this.currRoad = newRoad;
    }

    @Override
    public Intersection getCurrIntersection() {
        // TODO Auto-generated method stub
        return this.currIntersection;
    }

    @Override
    public void updateCurrIntersection(Intersection newIntersection) {
        // TODO Auto-generated method stub
        
        this.currIntersection = newIntersection;

    }

    @Override
    public void decreaseHealth(int dmgTaken) {
        // TODO Auto-generated method stub
        this.health -= dmgTaken;
    }

    public boolean addPassanger(User newPassenger) {
        return false;
    }

    public User removePassenger(User passenger) {
        return null;
    }

    public boolean doesPassengerExist(User passenger) {
        return false;
    }

    @Override
    public String getVehicleColor() {
        // TODO Auto-generated method stub
        return this.vehicleColor;
    }

    @Override
    public void updateCurrPosition() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setCurrPosition(int newPos) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getCurrPosition() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
