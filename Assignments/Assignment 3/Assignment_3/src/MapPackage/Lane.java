package MapPackage;

import java.util.LinkedList;
import java.util.List;

import VehiclePackage.*;

public class Lane {

    private int lengthOfLane;
    // private Vehicle[] vehicles;
    private List<Vehicle> listOfVehicles;

    public Lane(int lengthofLane) {
        this.lengthOfLane = lengthofLane;
        this.listOfVehicles = new LinkedList<Vehicle>();

    }

    public int getLengthOfLane() {
        return this.lengthOfLane;
    }

    public boolean addVehicle(Vehicle vehicle) {
        //this.listOfVehicles.add(vehicle);
        if (!this.doesVehicleExistInThisLane(vehicle)) {
            this.listOfVehicles.add(vehicle);

            return true;
        }

        return false;

    }

    public boolean doesVehicleExistInThisLane(Vehicle vehicle) {
        // check linkedlist for given vehicle and return true or false based on existence
        return (this.listOfVehicles.contains(vehicle) ? true : false);
    }

    public Vehicle removeVehicle(Vehicle vehicleName) {

        return (this.doesVehicleExistInThisLane(vehicleName)) ? this.listOfVehicles.remove(this.listOfVehicles.indexOf(vehicleName)) : null;
    }

    // public Vehicle remoVehicle(int vehicleID) {

    //     return null;
    // }
    
}
