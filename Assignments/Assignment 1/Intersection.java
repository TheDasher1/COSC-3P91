
public class Intersection {

    private String IntersectionName;
    private int IntersectionID, currRoadsGoing[];
    private Road[] roadsAttachedTo;
    private TrafficLight traficLight1, traficLight2;
    private Vehicle[] vehiclesHere;
    // private int currRoadsGoing;

    public Intersection(String intersectionName, int intersectionID) {
        this.IntersectionName = intersectionName;
        this.IntersectionID = intersectionID;
        this.currRoadsGoing = new int[2];
        this.roadsAttachedTo = new Road[4];
        // NumberofRoadSegments = numofRoadSegments;
        this.traficLight1 = new TrafficLight();
        this.traficLight2 = new TrafficLight();

    }

    public String getIntersectionName() {
        return this.IntersectionName;

    }
    
    public int getIntersectionID() {
        return this.IntersectionID;
    }

    public void changeTrafficLight() {
        // also controls the current roads going
    }

    public boolean attachRoad(Road road) {
        return false;
    }

    public Road removeRoad(Road road) {
        return null;
    }
    
    public Road removeRoad(int roadID) {
        return null;
    }

    public boolean isRoadAttached(Road road) {
        return false;
    }

    public boolean isRoadAttached(int roadID) {
        return false;
    }

    public Road[] getRoadsAttached() {
        return this.roadsAttachedTo;
    }

    public boolean addVehicle(Vehicle vehicle) {
        return false;
    }

    public Vehicle removeVehicle(Vehicle vehicle) {
        return null;
    }

    public Vehicle removeVehicle(int vehicleID) {
        return null;
    }

    public boolean isVehicleInThisSection(Vehicle vehicle) {
        return false;
    }
    
    public boolean isVehicleInThisSection(int vehicleID) {
        return false;
    }

    public Vehicle[] getAllVehiclesHere() {
        return this.vehiclesHere;
    }
    
}
