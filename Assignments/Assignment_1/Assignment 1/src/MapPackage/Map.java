package MapPackage;

import java.util.List;
import java.util.ArrayList;
import VehiclePackage.*;

public class Map {

    // private Graph map;
    // private Intersection[] intersection;
    private List<Intersection> intersections;
    private List<Road> roads;
    private List<Vehicle> vehicles;
    // private List<Intersection> intersections;

    public Map() {
        intersections = new ArrayList<Intersection>();
        roads = new ArrayList<Road>();
        vehicles = new ArrayList<Vehicle>();

    }

    public boolean createIntersection(String intersName, int intersID) {
        return false;
    }

    public boolean createIntersection(String intersName, int intersID, Road roadAttachedTo) {
        return false;
    }

    public boolean attachExistingIntersectionToRoad(Intersection intersName, Road roadToAttachTo) {
        return false;
    }

    public boolean createRoad(String roadName, int roadID, int maxVehiclesPerLane) {
        return false;
    }

    public boolean createRoad(String roadName, int roadID, int maxVehiclesPerLane, Intersection topIntersect, InternalError bottomIntersect) {
        return false;
    }

    public boolean addVehicle(Vehicle vehicle) {
        return false;
    }

    public boolean removeVehicle(Vehicle vehicle) {
        return false;
    }

    public boolean removeVehicle(String vehicleName, int vehicleID) {
        return false;
    }

    public int getNumberOfVehiclesOnMap() {
        return this.vehicles.size();
    }

    public List<Vehicle> getAllVehiclesOnMap() {
        return this.vehicles;
    }

    public List<Intersection> getAllIntersection() {
        return this.intersections;
    }

    public List<Road> getAllRoads() {
        return this.roads;
    }

    public boolean doesIntersectionExist(String intersectionName, int intersectionID) {
        return false;
    }
    
}
