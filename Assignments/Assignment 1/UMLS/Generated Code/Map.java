public class Map {

  private ArrayList<Intersection> intersections;

  private ArrayList<Road> roads;

  private ArrayList<Vehicle> vehicles;

  public boolean createIntersection(String intersName, int intersID) {
  return false;
  }

  public boolean createIntersection(String intersName, int intersID, Road roadAttachedTo) {
  return false;
  }

  public boolean attachExistingIntersectionToRoad(Intersection intersName, Road roadToAttachTo) {
  return false;
  }

  public boolean createRoad(Road roadName, int roadID, int maxVehiclesPerLane) {
  return false;
  }

  public boolean createRoad(Road roadName, int roadID, int maxVehiclesPerLane, Intersection topIntersect, Intersection botIntersect) {
  return false;
  }

  public boolean addVehicle(Vehicle vehicle) {
  return false;
  }

  public boolean removeVehicle(Vehicle vehicle) {
  return false;
  }

  public boolean removeVehicle(String vehicleName, int vehiclID) {
  return false;
  }

  public int getNumberOfVehiclesOnMap() {
  return 0;
  }

  public ArrayList<Vehicle> getAllVehiclesOnMap() {
  return null;
  }

  public ArrayList<Intersection> getAllIntersectionsOnMap() {
  return null;
  }

  public ArrayList<Road> getAllRoadsOnMap() {
  return null;
  }

  public void getBoardState():Intersection, Road() {
  }

  public boolean doesIntersectionExist(String intersectName, int intersectionID) {
  return false;
  }

}