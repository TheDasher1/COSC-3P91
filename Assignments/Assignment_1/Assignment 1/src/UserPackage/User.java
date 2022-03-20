package UserPackage;

import VehiclePackage.*;
import MapPackage.*;

public interface User {
    
    public String getUserName();

    public int getUserID();

    public Vehicle getVehicle();

    public void changeVehicle(Vehicle newVehicle);

    public Vehicle[] lookAround(Road road);

    public Vehicle[] lookAround(Intersection intersection);

    public boolean changeLanes(Road roadName, int newLane);

    public boolean turnAtIntersection(Road roadToTurn);

    public boolean turnAtIntersection(int roadNum);

    public void gamble();

}
