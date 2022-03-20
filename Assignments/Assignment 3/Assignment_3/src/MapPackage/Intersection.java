package MapPackage;

import java.util.LinkedList;
import java.util.List;

import MapPackage.TrafficLight.TrafficLightColor;
import VehiclePackage.*;

public class Intersection {

    private String IntersectionName;
    private int IntersectionID, currRoadsGoing[];
    private List<Road> roadsAttachedTo;
    private TrafficLight traficLight1, traficLight2;
    private Vehicle[] vehiclesHere;
    // private int currRoadsGoing;

    public Intersection(String intersectionName, int intersectionID) {
        this.IntersectionName = intersectionName;
        this.IntersectionID = intersectionID;
        this.currRoadsGoing = new int[2];
        // this.roadsAttachedTo = new Road[4];
        this.roadsAttachedTo = new LinkedList<Road>();
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

    public TrafficLight getTrafficLight() {
        if (this.traficLight1.CurrentLight == TrafficLightColor.GREEN) {
            return this.traficLight1;
            
        } else if (this.traficLight2.CurrentLight == TrafficLightColor.GREEN) {
            return this.traficLight2;

        } else {
            traficLight1.changeLight(TrafficLightColor.GREEN);
            return this.traficLight1;

        }

    }

    public void changeTrafficLight() {
        // also controls the current roads going
        
    }

    public boolean attachRoad(Road road) {
        if (this.roadsAttachedTo.size() < 4) {
            // this.roadsAttachedTo[this.roadsAttachedTo.length] = road;
            this.roadsAttachedTo.add(road);
            
            return true;
        
        } else {
            return false;
        }

    }

    public boolean removeRoad(Road road) {
        return this.roadsAttachedTo.remove(road);
    }
    
    public boolean removeRoad(int roadID) {
        for (Road road : this.roadsAttachedTo) {
            if (road.getRoadID() == roadID) {
                this.roadsAttachedTo.remove(road);

                return true;

            }

        }
        
        return false;
    }

    public boolean isRoadAttached(Road road) {
        return this.roadsAttachedTo.contains(road);
    }

    public boolean isRoadAttached(int roadID) {
        for (Road road : this.roadsAttachedTo) {
            if (road.getRoadID() == roadID) {
                this.roadsAttachedTo.remove(road);

                return true;

            }
        }
        
        return false;
    }

    public boolean canARoadBeAttached() {
        return ((this.roadsAttachedTo.size() < 4) ? true : false);
        
    }

    public List<Road> getRoadsAttached() {
        return this.roadsAttachedTo;
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehiclesHere[this.vehiclesHere.length] = vehicle;
        
    }

    public Vehicle removeVehicle(Vehicle vehicle) {
        Vehicle retVehicle = null;

        for (int i = 0; i < this.vehiclesHere.length; i ++) {
            if (this.vehiclesHere[i] == vehicle) {
                retVehicle = this.vehiclesHere[i];
                this.vehiclesHere[i] = null;

            }

        }
        
        return retVehicle;
    }

    public Vehicle removeVehicle(int vehicleID) {
        Vehicle retVehicle = null;

        for (int i = 0; i < this.vehiclesHere.length; i ++) {
            if (this.vehiclesHere[i].getVehicleID() == vehicleID) {
                retVehicle = this.vehiclesHere[i];
                this.vehiclesHere[i] = null;

            }

        }
        
        return retVehicle;
    }

    public boolean isVehicleInThisSection(Vehicle vehicle) {
        for (int i = 0; i < this.vehiclesHere.length; i ++) {
            if (this.vehiclesHere[i] == vehicle) {
                return true;
                
            }

        }
        
        return false;
    }
    
    public boolean isVehicleInThisSection(int vehicleID) {
        for (int i = 0; i < this.vehiclesHere.length; i ++) {
            if (this.vehiclesHere[i].getVehicleID() == vehicleID) {
                return true;

            }

        }

        return false;
    }

    public Vehicle[] getAllVehiclesHere() {
        return this.vehiclesHere;
    }
    
}
