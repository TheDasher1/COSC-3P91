import java.util.List;
import java.util.ArrayList;

public class Map extends Graph {

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

    // public Map(Graph Map) {
    //     this.map = Map;

    // }

    // public void addIntersection(String IntersectionName) {
    //     super.addVertex(IntersectionName, numOfIntersects);

    // }

    // public void addNewIntersectionToExistingIntersection(String ExistingIntersection, int numofsects1, String NewIntersection, int numofsects2, Boolean Bidirection) {
    //     if (Bidirection) {
    //         super.addVertex(NewIntersection, numofsects2);
    //         super.addEdgeBidirection(ExistingIntersection, NewIntersection, numofsects1, numofsects2);

    //     } else {
    //         super.addVertex(NewIntersection, numofsects2);
    //         super.addEdgeDirected(ExistingIntersection, NewIntersection, numofsects1, numofsects2);

    //     }

    // }

    // public void connectTwoExistingIntersections(String Intersection1, int numofsects1, String Intersection2, int numofsects2, Boolean Bidirection) {
    //     if (Bidirection) {
    //         super.addEdgeBidirection(Intersection1, Intersection2, numofsects1, numofsects2);

    //     } else {
    //         super.addEdgeDirected(Intersection1, Intersection2, numofsects1, numofsects2);

    //     }

    // }

    // public void removeIntersection(String Intersection) {
    //     super.removeVertex(Intersection);

    // }

    // public List<Intersection> getSurroundingIntersections(String CurrIntersection) {
    //     return super.getAdjVertex(CurrIntersection);

    // }

    // public void createRandomMap(int numIntersections) {
    //     for (int i = 0; i < numIntersections; i++) {


    //     }

    // }

    // public Graph getMap() {
    //     return this.map;

    // }
    
}
