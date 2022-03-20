
public interface Vehicle {

    // int topSpeed = 100, currSpeed = 0, health = 100, inLane = 1, vehicleID;
    // String vehicleColor, vehicleMake;
    // Road currRoad;
    // Intersection currIntersection;

    public boolean increaseSpeed(int incBy);

    public boolean decreaseSpeed(int decBy);

    public int getCurrSpeed();

    public int getHealth();

    public int getLane();

    public boolean changeLane(int laneNum);

    public void changeVehicleColor(String newColor);

    public void changeVehicleID(int newID);

    public int getVehicleID();

    public String getVehicleMake();

    public Road getCurrRoad();

    public void updateCurrRoad(Road newRoad);

    public Intersection getCurrIntersection();

    public void updateCurrIntersection(Intersection newIntersection);

    public void decreaseHealth(int dmgTaken);

}
