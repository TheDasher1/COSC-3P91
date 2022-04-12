package VehiclePackage;

import MapPackage.*;
import UserPackage.User;

public interface Vehicle {

    public boolean increaseSpeed(int incBy);

    public boolean decreaseSpeed(int decBy);

    public int getCurrSpeed();

    public int getHealth();

    public int getLane();

    public void changeLane(int laneNum);

    public String getVehicleColor();

    public void changeVehicleColor(String newColor);

    public void changeVehicleID(int newID);

    public int getVehicleID();

    public String getVehicleMake();

    public Road getCurrRoad();

    public void updateCurrRoad(Road newRoad);

    public Intersection getCurrIntersection();

    public void updateCurrIntersection(Intersection newIntersection);

    public void decreaseHealth(int dmgTaken);

    public int getCurrPosition();

    public void setCurrPosition(int newPos);
    
    public void updateCurrPosition();

    public User getOwner();
    
}
