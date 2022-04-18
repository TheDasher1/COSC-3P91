package MapPackage;

import java.util.List;

import UserPackage.User;

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

    /**
     * 
     * The default constructor.
     * 
     */
    public Map() {
        intersections = new HashMap<Integer, Intersection>();//new ArrayList<Intersection>();
        roads =  new HashMap<Integer, Road>();//new ArrayList<Road>();
        playerVehicles =  new HashMap<Integer, Vehicle>();//new ArrayList<Vehicle>();
        aiVehicles =  new HashMap<Integer, Vehicle>();//new ArrayList<Vehicle>();

    }

    /**
     * 
     * This method creates an Intersection.
     * 
     * @param intersName The Intersection name.
     * @param intersID The Intersection ID.
     * @return Returns the created Intersection or null if the ID is already used.
     */
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

    /**
     * 
     * This method creates an Intersection and attaches it to a Road.
     * 
     * @param intersName The Intersection name.
     * @param intersID The Intersection ID.
     * @param roadAttachedTo The Road to attach this Intersection to.
     * @return
     */
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

    /**
     * 
     * This method attaches an Intersection and a Road to eachother if there are open spots for them.
     * 
     * @param intersect The Intersection to attach.
     * @param roadToAttachTo The Road to attach.
     * @return True/False if attachement was successful.
     */
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

    /**
     * 
     * This method creates a Road and returns it if sucessful.
     * 
     * @param roadName The Road name.
     * @param roadID The Road ID.
     * @param maxLanes The number of lanes this Road has.
     * @param maxVehiclesPerLane The max number of vehicles there can be in any lane.
     * @return True/False if successful.
     */
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

    /**
     * 
     * This method creates a Road attached to an Intersection and returns T/F if sucessful.
     * 
     * @param roadName The Road name.
     * @param roadID The Road ID.
     * @param maxLanes The number of lanes this Road has.
     * @param maxVehiclesPerLane The max number of vehicles there can be in any lane.
     * @param topIntersect The top Intersection for this road to attach to.
     * @return True/False if successful.
     */
    public boolean createAttactedRoad(String roadName, int roadID, int maxLanes, int maxVehiclesPerLane, Intersection topIntersect) {//, Intersection bottomIntersect) {
        if (!this.roads.containsKey(roadID)) {
            Road tempRoad = new Road(roadName, roadID, maxLanes, maxVehiclesPerLane);
            
            if (topIntersect.attachRoad(tempRoad)) tempRoad.setIntersection(topIntersect);
            
            roads.put(roadID, tempRoad);

            return true;

        }

        return false;

    }

    /**
     * 
     * This method creates a Road attached between 2 Intersections and returns T/F if sucessful.
     * 
     * @param roadName The Road name.
     * @param roadID The Road ID.
     * @param maxLanes The number of lanes this Road has.
     * @param maxVehiclesPerLane The max number of vehicles there can be in any lane.
     * @param topIntersect The top Intersection for this road to attach to.
     * @param bottomIntersect The bottom Intersection for this road to attach to.
     * @return True/False if successful.
     */
    public boolean createAttactedRoad(String roadName, int roadID, int maxLanes, int maxVehiclesPerLane, Intersection topIntersect, Intersection bottomIntersect) {
        if (!this.roads.containsKey(roadID) && topIntersect.canARoadBeAttached() && bottomIntersect.canARoadBeAttached()) {
            Road tempRoad = new Road(roadName, roadID, maxLanes, maxVehiclesPerLane);
            
            if (topIntersect.attachRoad(tempRoad)) tempRoad.setIntersection(topIntersect);
            
            if (bottomIntersect.attachRoad(tempRoad)) tempRoad.setIntersection(bottomIntersect);

            roads.put(roadID, tempRoad);

            return true;

        }

        return false;
        
    }

    /**
     * 
     * 
     * 
     * @param vID
     * @param vStartSpeed
     * @param vStartRoad
     * @param vStartLane
     * @param vehicleOwner
     * @return
     */
    public boolean createPlayerCar(int vID, int vStartSpeed, Road vStartRoad, int vStartLane, User vehicleOwner) {
        if (!this.playerVehicles.containsKey(vID)) {
            Vehicle tempVehicle = new Car(vID, vStartSpeed, vStartRoad, vStartLane, vehicleOwner);

            playerVehicles.put(tempVehicle.getVehicleID(), tempVehicle);

            return true;

        }

        return false;

    }

    /**
     * 
     * 
     * 
     * @param vID
     * @param vStartSpeed
     * @param vStartRoad
     * @param vStartLane
     * @param vehicleOwner
     * @return
     */
    public boolean createAICar(int vID, int vStartSpeed, Road vStartRoad, int vStartLane, User vehicleOwner) {
        if (!this.aiVehicles.containsKey(vID)) {
            Vehicle tempVehicle = new Car(vID, vStartSpeed, vStartRoad, vStartLane, vehicleOwner);

            aiVehicles.put(tempVehicle.getVehicleID(), tempVehicle);

            return true;

        }

        return false;

    }

    /**
     * 
     * 
     * 
     * @param vehicle
     * @return
     */
    public boolean doesPlayerVehicleExist(Vehicle vehicle) {

        return (this.playerVehicles.containsKey(vehicle.getVehicleID())) ? true : false;

    }
    
    /**
     * 
     * @param vehicleID
     * @return
     */
    public boolean doesPlayerVehicleExist(int vehicleID) {

        return (this.playerVehicles.containsKey(vehicleID)) ? true : false;

    }

    /**
     * 
     * 
     * 
     * @param vehicle
     * @return
     */
    public boolean doesAIVehicleExist(Vehicle AIvehicle) {

        return (this.aiVehicles.containsKey(AIvehicle.getVehicleID())) ? true : false;

    }

    /**
     * 
     * 
     * 
     * @param AIvehicleID
     * @return
     */
    public boolean doesAIVehicleExist(int AIvehicleID) {

        return (this.aiVehicles.containsKey(AIvehicleID)) ? true : false;

    }

    /**
     * 
     * 
     * 
     * @param vehicle
     * @return
     */
    public boolean addVehicle(Vehicle vehicle) {
        if (!this.playerVehicles.containsKey(vehicle.getVehicleID())) {
            playerVehicles.put(vehicle.getVehicleID(), vehicle);

            return true;

        }

        return false;

        // return this.vehicles.containsKey(vehicle.getVehicleID()) ? false : this.vehicles.put(vehicle.getVehicleID(), vehicle) != null;
        
    }

    /**
     * 
     * 
     * 
     * @param spot
     * @return
     */
    public boolean isPlayerVehicleOnSpot(int spot) {
        for (Vehicle v : this.playerVehicles.values()) {
            if (v.getCurrPosition() == spot) {
                
                return true; // there is a vehicle on this spot

            }

        }
        
        return false; // this spot is empty

    }

    /**
     * 
     * 
     * 
     * @param spot
     * @return
     */
    public boolean isAIVehicleOnSpot(int spot) {
        for (Vehicle v : this.aiVehicles.values()) {
            if (v.getCurrPosition() == spot) {
                
                return true; // there is a vehicle on this spot

            }

        }
        
        return false; // this spot is empty

    }

    /**
     * 
     * 
     * 
     * @param vehicle
     * @return
     */
    public boolean removePlayerVehicle(Vehicle vehicle) {
        if (this.playerVehicles.containsKey(vehicle.getVehicleID())) {
            this.playerVehicles.remove(vehicle.getVehicleID());

            return true;

        }

        return false;
    
    }

    /**
     * 
     * 
     * 
     * @param vehicleID
     * @return
     */
    public boolean removePlayerVehicle(int vehicleID) {
        if (this.playerVehicles.containsKey(vehicleID)) {
            this.playerVehicles.remove(vehicleID);

            return true;

        }

        return false;
        
    }
    
    /**
     * 
     * 
     * 
     * @param vehicle
     * @return
     */
    public boolean removeAIVehicle(Vehicle vehicle) {
        if (this.aiVehicles.containsKey(vehicle.getVehicleID())) {
            this.aiVehicles.remove(vehicle.getVehicleID());

            return true;

        }

        return false;
    
    }

    /**
     * 
     * 
     * 
     * @param vehicleID
     * @return
     */
    public boolean removeAIVehicle(int vehicleID) {
        if (this.aiVehicles.containsKey(vehicleID)) {
            this.aiVehicles.remove(vehicleID);

            return true;

        }
        
        return false;
    }

    /**
     * 
     * 
     * 
     * @return
     */
    public int getNumberOfPlayerVehiclesOnMap() {
        return this.playerVehicles.size();

    }

    /**
     * 
     * 
     * 
     * @return
     */
    public int getNumberOfAIVehiclesOnMap() {
        return this.aiVehicles.size();

    }

    /**
     * 
     * 
     * 
     * @param intersectionID
     * @return
     */
    public Intersection getIntersection(int intersectionID) {
        return this.intersections.get(intersectionID);

    }

    /**
     * 
     * 
     * 
     * @param roadID
     * @return
     */
    public Road getRoad(int roadID) {
        return this.roads.get(roadID);

    }

    /**
     * 
     * 
     * 
     * @param vID
     * @return
     */
    public Vehicle getPlayerVehicle(int vID) {
        return this.playerVehicles.get(vID);

    }

    /**
     * 
     * 
     * 
     * @param vID
     * @return
     */
    public Vehicle getAIVehicle(int vID) {
        return this.aiVehicles.get(vID);

    }

    /**
     * 
     * 
     * 
     * @return
     */
    public List<Vehicle> getAllPlayerVehiclesOnMap() {
        return new ArrayList<>(this.playerVehicles.values());
        
    }

    /**
     * 
     * 
     * 
     * @return
     */
    public List<Vehicle> getAllAIVehiclesOnMap() {
        return new ArrayList<>(this.aiVehicles.values());
        
    }

    /**
     * 
     * 
     * 
     * @return
     */
    public List<Intersection> getAllIntersection() {
        return new ArrayList<>(this.intersections.values());
        
    }

    /**
     * 
     * 
     * 
     * @return
     */
    public List<Road> getAllRoads() {
        return new ArrayList<>(this.roads.values());
        
    }

    /**
     * 
     * 
     * 
     * @param intersectionID
     * @return
     */
    public boolean doesIntersectionExist(int intersectionID) {
        if (this.intersections.containsKey(intersectionID)) {
            return true;

        }
        
        return false;
    }
    
}
