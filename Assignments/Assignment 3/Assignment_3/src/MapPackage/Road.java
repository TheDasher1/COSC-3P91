package MapPackage;

import VehiclePackage.*;

public class Road {

    private String roadName;
    private int roadID, maxLanes, maxVehiclesPerLane;
    private Lane[] lanes;
    private Intersection topIntersection, bottomIntersection;
    private Vehicle[] vehiclesOnThisRoad;

    public Road(String RoadName, int RoadID, int maxNumOfLanes, int MAXVehiclesPerLane) {
        this.roadName = RoadName;
        this.roadID = RoadID;
        this.maxLanes = maxNumOfLanes;
        this.lanes = new Lane[this.maxLanes];
        this.maxVehiclesPerLane = MAXVehiclesPerLane;
        this.topIntersection = null;
        this.bottomIntersection = null;

        for (int i = 0; i < this.maxLanes; i ++) {
            lanes[i] = new Lane(this.maxVehiclesPerLane);

        }


    }

    public Road(String RoadName, int RoadID, int MaxLanes, int MAXVehiclesPerLane, Intersection TopIntersection, Intersection BottomIntersection) {
        this.roadName = RoadName;
        this.roadID = RoadID;
        this.maxLanes = MaxLanes;
        this.maxVehiclesPerLane = MAXVehiclesPerLane;
        this.lanes = new Lane[this.maxLanes];
        this.topIntersection = TopIntersection;
        this.bottomIntersection = BottomIntersection;

    }

    public String getRoadName() {
        return this.roadName;

    }

    public int getRoadID() {
        return this.roadID;

    }

    public int getMaxVehiclesPerLane() {
        return this.maxVehiclesPerLane;

    }

    public boolean canIntersectionBeAttached() {
        return (this.topIntersection == null || this.bottomIntersection == null) ? true : false;

    }

    public boolean setIntersection(Intersection intsect) {
        if (this.topIntersection == null) {
            this.topIntersection = intsect;

            return true;

        } else if (this.bottomIntersection == null) {
            this.bottomIntersection = intsect;

            return true;

        }

        return false;

    }

    public boolean isVehiclOnThisRoad(Vehicle vehicle) {
        // check <list> in this road if the vehicle exists
        for (Vehicle v : this.vehiclesOnThisRoad) {
            if (v == vehicle) {
                return true;

            }

        }
        return false;
    }

    public boolean addVehicle(Vehicle vehicle, int lane) {
        // check if vehicle exits in this road first with isVehicleOnThisRoad(), if not add the given vehicle, else return false
        if (!this.isVehiclOnThisRoad(vehicle)) {
            this.vehiclesOnThisRoad[this.vehiclesOnThisRoad.length] = vehicle;
            this.lanes[lane].addVehicle(vehicle);

            return true;

        }

        // add new vehicle in to <list> of vehicles on this road to keep track of where it is
        return false;
    }

    public boolean removeVehicle(Vehicle vehicle) {
        // check if vehicle exists on this road, if it does, delete it and return true to show sucessfull operation
        for (Vehicle v : this.vehiclesOnThisRoad) {
            if (v == vehicle) {
                v = null;

                return true;

            }

        }

        // false if vehicle isn't here
        return false;
    }

    public boolean setTopIntersection(Intersection intersection) {
        // check if the given intersection is equal to bottom intersection to prevent error
        if (this.bottomIntersection != intersection) {
            this.topIntersection = intersection;

            return true;

        }

        return false;
    }

    public Intersection getTopIntersection() {
        return this.topIntersection;
    }

    public boolean setBottomIntersection(Intersection intersection) {
        // check if the given intersection is equal to top intersection to prevent error
        if (this.topIntersection != intersection) {
            this.bottomIntersection = intersection;

            return true;

        }

        return false;
    }

    public Intersection getBottomIntersection() {
        return this.bottomIntersection;
    }

    public int getLaneSize() {
        return this.lanes[0].getLengthOfLane();

    }

    public Vehicle[] getAllVehiclesOnRoad() {
        return this.vehiclesOnThisRoad;
    }
    
}
