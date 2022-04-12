package VehiclePackage;

import MapPackage.*;
import UserPackage.User;

public class Car implements Vehicle {

    // topSpeed = 5 means it goes 5 ticks every game "tick" (whever ever the game updates everything, the car has moved 5 ticks forward in its current lane with however long each lane is (eg. Lane is 20 ticks long))
    private int topSpeed = 5, currSpeed = 0, health = 100, inLane = 1, vehicleID, currPos = 0;
    private String vehicleColor, vehicleMake;
    private Road currRoad;
    private Intersection currIntersection;
    private int currSpot = 0;
    private User owner;

    public Car(int vehicleID, int startSpeed, Road startingRoad, int startInLane, User Owner) {
        this.vehicleID = vehicleID;
        // this.vehicleMake = vehicleMake;
        // this.vehicleColor = vehicleColor;
        this.currSpeed = startSpeed;
        this.currRoad = startingRoad;
        this.inLane = startInLane;
        this.owner = Owner;

    }

    @Override
    public boolean increaseSpeed(int incBy) {
        if (incBy < this.topSpeed & (this.currSpeed + incBy) < this.topSpeed) {
            this.currSpeed += incBy;

            return true;

        }

        return false;

    }

    public boolean setSpeed(int newSpeed) {
        if (newSpeed > 0 & newSpeed < this.topSpeed) {
            this.currSpeed = newSpeed;

            return true;

        }

        return false;

    }

    @Override
    public boolean decreaseSpeed(int decBy) {
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

    @Override
    public String getVehicleColor() {
        // TODO Auto-generated method stub
        return this.vehicleColor;
    }

    @Override
    public void updateCurrPosition() {
        // TODO Auto-generated method stub

        this.currPos += this.currSpeed;

        // if (this.currPos > this.currRoad.getLaneSize()) {
        //     // end of the road has been reached

        // }
        
    }

    @Override
    public void setCurrPosition(int newPos) {
        // TODO Auto-generated method stub

        this.currPos = newPos;
        
    }

    @Override
    public int getCurrPosition() {
        // TODO Auto-generated method stub
        return this.currPos;
    }

    @Override
    public User getOwner() {
        // TODO Auto-generated method stub
        return this.owner;
    }
    
}
