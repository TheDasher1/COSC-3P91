package MapPackage;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import VehiclePackage.*;

public class Map {

    // private Graph map;
    // private Intersection[] intersection;
    // private List<Intersection> intersections;
    // private List<Road> roads;
    // private List<Vehicle> vehicles;
    private HashMap<Integer, Intersection> intersections;
    private HashMap<Integer, Road> roads;
    private HashMap<Integer, Vehicle> playerVehicles;
    private HashMap<Integer, Vehicle> aiVehicles;
    // private List<Intersection> intersections;

    public Map() {
        intersections = new HashMap<Integer, Intersection>();//new ArrayList<Intersection>();
        roads =  new HashMap<Integer, Road>();//new ArrayList<Road>();
        playerVehicles =  new HashMap<Integer, Vehicle>();//new ArrayList<Vehicle>();
        aiVehicles =  new HashMap<Integer, Vehicle>();//new ArrayList<Vehicle>();

    }

    // public boolean createIntersection(String intersName, int intersID) {
    //     if (intersections.containsKey(intersID)) {
    //         return false;

    //     } else {
    //         intersections.put(intersID, new Intersection(intersName, intersID));

    //         return true;
    //     }
    // }

    public Intersection createIntersection(String intersName, int intersID) {
        if (intersections.containsKey(intersID)) {
            return null;

        } else {
            Intersection tempSection = new Intersection(intersName, intersID);
            // intersections.put(intersID, new Intersection(intersName, intersID));
            intersections.put(intersID, tempSection);

            return tempSection;
        }
    }

    public Intersection createIntersection(String intersName, int intersID, Road roadAttachedTo) {
        if (intersections.containsKey(intersID)) {
            return null;
        
        } else {
            // intersections.put(intersID, new Intersection(intersName, intersID).attachRoad(roadAttachedTo));
            Intersection tempSection = new Intersection(intersName, intersID);

            tempSection.attachRoad(roadAttachedTo);

            intersections.put(intersID, tempSection);

            return tempSection;

        }
        
    }

    public boolean attachExistingIntersectionToRoad(Intersection intersect, Road roadToAttachTo) {
        // return false;

        if (intersect.canARoadBeAttached()) {
            return intersect.attachRoad(roadToAttachTo);
            // return true;

        } else {
            return false;
        
        }

        // return ((intersect.canARoadBeAttached()) ? intersect.attachRoad(roadToAttachTo) : false);

    }

    public Road createRoad(String roadName, int roadID, int maxLanes, int maxVehiclesPerLane) {
        if (this.roads.containsKey(roadID)) {
            return null;

        } else {
            Road tempRoad = new Road(roadName, roadID, maxLanes, maxVehiclesPerLane);

            roads.put(roadID, tempRoad);

            return tempRoad;//new Road(roadName, roadID, maxVehiclesPerLane);

        }

        // return (this.roads.containsKey(roadID) ? null : new Road(roadName, roadID, maxVehiclesPerLane));

    }

    public boolean createAttactedRoad(String roadName, int roadID, int maxLanes, int maxVehiclesPerLane, Intersection topIntersect) {//, Intersection bottomIntersect) {
        if (!this.roads.containsKey(roadID)) {
            Road tempRoad = new Road(roadName, roadID, maxLanes, maxVehiclesPerLane);
            topIntersect.attachRoad(tempRoad); 
            return true;

        }

        return false;
    }

    public boolean createPlayerCar(int vID, int vStartSpeed, Road vStartRoad, int vStartLane) {
        if (!this.playerVehicles.containsKey(vID)) {
            Vehicle tempVehicle = new Car(vID, vStartSpeed, vStartRoad, vStartLane);

            playerVehicles.put(tempVehicle.getVehicleID(), tempVehicle);

            return true;

        }

        return false;

    }

    public boolean doesPlayerVehicleExist(Vehicle vehicle) {

        return (this.playerVehicles.containsKey(vehicle.getVehicleID())) ? true : false;

    }

    public boolean addVehicle(Vehicle vehicle) {
        if (!this.playerVehicles.containsKey(vehicle.getVehicleID())) {
            playerVehicles.put(vehicle.getVehicleID(), vehicle);

            return true;

        }

        return false;

        // return this.vehicles.containsKey(vehicle.getVehicleID()) ? false : this.vehicles.put(vehicle.getVehicleID(), vehicle) != null;
        
    }

    public boolean removePlayerVehicle(Vehicle vehicle) {
        if (this.playerVehicles.containsKey(vehicle.getVehicleID())) {
            this.playerVehicles.remove(vehicle.getVehicleID());

            return true;

        }

        return false;
        
        // return false;
    }

    public boolean removePlayerVehicle(int vehicleID) {
        if (this.playerVehicles.containsKey(vehicleID)) {
            this.playerVehicles.remove(vehicleID);

            return true;

        }
        
        return false;
    }

    public int getNumberOfPlayerVehiclesOnMap() {
        return this.playerVehicles.size();
    }

    public Intersection getIntersection(int intersectionID) {
        return this.intersections.get(intersectionID);

    }

    public Road getRoad(int roadID) {
        return this.roads.get(roadID);

    }

    public Vehicle getPlayerVehicle(int vID) {
        return this.playerVehicles.get(vID);

    }

    public List<Vehicle> getAllPlayerVehiclesOnMap() {
        return new ArrayList<>(this.playerVehicles.values());
        // return this.vehicles;
    }

    public List<Intersection> getAllIntersection() {
        return new ArrayList<>(this.intersections.values());
        // return this.intersections;
    }

    public List<Road> getAllRoads() {
        return new ArrayList<>(this.roads.values());
        // return this.roads;
    }

    public boolean doesIntersectionExist(int intersectionID) {
        if (this.intersections.containsKey(intersectionID)) {
            return true;

        }
        
        return false;
    }
    
}
