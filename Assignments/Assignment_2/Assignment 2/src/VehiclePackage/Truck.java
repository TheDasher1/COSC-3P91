package VehiclePackage;

import MapPackage.*;

public class Truck implements Vehicle {

    int topSpeed = 100, currSpeed = 0, health = 100, inLane = 1, vehicleID;
    String vehicleColor, vehicleMake;
    Road currRoad;
    Intersection currIntersection;

    @Override
    public boolean increaseSpeed(int incBy) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean decreaseSpeed(int decBy) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public int getCurrSpeed() {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public int getHealth() {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public int getLane() {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public boolean changeLane(int laneNum) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public void changeVehicleColor(String newColor) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void changeVehicleID(int newID) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public int getVehicleID() {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public String getVehicleMake() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Road getCurrRoad() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void updateCurrRoad(Road newRoad) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public Intersection getCurrIntersection() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void updateCurrIntersection(Intersection newIntersection) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void decreaseHealth(int dmgTaken) {
        // TODO Auto-generated method stub
        
    }

    public void attachTrailer(Vehicle trailer) {
        
    }
    
}
