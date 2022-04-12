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

    /**
     * 
     * 
     * 
     * @param intersectionName
     * @param intersectionID
     */
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

    /**
     * 
     * 
     * 
     * @return
     */
    public String getIntersectionName() {
        return this.IntersectionName;

    }
    
    /**
     * 
     * 
     * 
     * @return
     */
    public int getIntersectionID() {
        return this.IntersectionID;
    }

    /**
     * 
     * 
     * 
     * @return
     */
    public TrafficLight getGoingTrafficLight() {
        if (this.traficLight1.getCurrentLight() == TrafficLightColor.GREEN) {
            return this.traficLight1;
            
        } else if (this.traficLight2.getCurrentLight() == TrafficLightColor.GREEN) {
            return this.traficLight2;

        } else {
            traficLight1.setLightColor(TrafficLightColor.GREEN);
            return this.traficLight1;

        }

    }

    /**
     * 
     * 
     * 
     */
    public void changeTrafficLight() {
        // also controls the current roads going
        if (this.traficLight1.getCurrentLight() == TrafficLightColor.RED) {
            // swap the lights being red and green
            this.traficLight1.changeLight();
            this.traficLight2.changeLight();

            // changes the roads that are going
            this.currRoadsGoing[0] = 0;//this.roadsAttachedTo.get(0);
            this.currRoadsGoing[1] = 1;//this.roadsAttachedTo.get(1);

        } else if (this.traficLight2.getCurrentLight() == TrafficLightColor.RED) {
            // swap the lights being red and green
            this.traficLight1.changeLight();
            this.traficLight2.changeLight();

            // changes the roads that are going
            this.currRoadsGoing[0] = 2;//this.roadsAttachedTo.get(0);
            this.currRoadsGoing[1] = 3;//this.roadsAttachedTo.get(1);

        }
        
    }

    /**
     * 
     * 
     * 
     * @param road
     * @return
     */
    public boolean attachRoad(Road road) {
        if (this.roadsAttachedTo.size() < 4) {
            // this.roadsAttachedTo[this.roadsAttachedTo.length] = road;
            this.roadsAttachedTo.add(road);
            
            return true;
        
        } else {
            return false;
        }

    }

    /**
     * 
     * 
     * 
     * @param road
     * @return
     */
    public boolean removeRoad(Road road) {
        return this.roadsAttachedTo.remove(road);
    }
    
    /**
     * 
     * 
     * 
     * @param roadID
     * @return
     */
    public boolean removeRoad(int roadID) {
        for (Road road : this.roadsAttachedTo) {
            if (road.getRoadID() == roadID) {
                this.roadsAttachedTo.remove(road);

                return true;

            }

        }
        
        return false;
    }

    /**
     * 
     * 
     * 
     * @param road
     * @return
     */
    public boolean isRoadAttached(Road road) {
        return this.roadsAttachedTo.contains(road);
    }

    /**
     * 
     * 
     * 
     * @param roadID
     * @return
     */
    public boolean isRoadAttached(int roadID) {
        for (Road road : this.roadsAttachedTo) {
            if (road.getRoadID() == roadID) {
                this.roadsAttachedTo.remove(road);

                return true;

            }
        }
        
        return false;
    }

    /**
     * 
     * 
     * 
     * @return
     */
    public boolean canARoadBeAttached() {
        return ((this.roadsAttachedTo.size() < 4) ? true : false);
        
    }

    /**
     * 
     * 
     * 
     * @return
     */
    public List<Road> getRoadsAttached() {
        return this.roadsAttachedTo;
    }

    /**
     * 
     * 
     * 
     * @param vehicle
     */
    public void addVehicle(Vehicle vehicle) {
        this.vehiclesHere[this.vehiclesHere.length] = vehicle;
        
    }

    /**
     * 
     * 
     * 
     * @param vehicle
     * @return
     */
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

    /**
     * 
     * 
     * 
     * @param vehicleID
     * @return
     */
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

    /**
     * 
     * 
     * 
     * @param vehicle
     * @return
     */
    public boolean isVehicleInThisSection(Vehicle vehicle) {
        for (int i = 0; i < this.vehiclesHere.length; i ++) {
            if (this.vehiclesHere[i] == vehicle) {
                return true;
                
            }

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
    public boolean isVehicleInThisSection(int vehicleID) {
        for (int i = 0; i < this.vehiclesHere.length; i ++) {
            if (this.vehiclesHere[i].getVehicleID() == vehicleID) {
                return true;

            }

        }

        return false;
    }

    /**
     * 
     * 
     * 
     * @return
     */
    public Vehicle[] getAllVehiclesHere() {
        return this.vehiclesHere;
    }
    
}
