package MapPackage;

import java.util.LinkedList;
import java.util.List;

import VehiclePackage.*;

public class Lane {

    private int lengthOfLane;
    // private Vehicle[] vehicles;
    private List<Vehicle> listOfVehicles;

    /**
     * 
     * 
     * 
     * @param lengthofLane
     */
    public Lane(int lengthofLane) {
        this.lengthOfLane = lengthofLane;
        this.listOfVehicles = new LinkedList<Vehicle>();

    }

    /**
     * 
     * 
     * 
     * @return
     */
    public int getLengthOfLane() {
        return this.lengthOfLane;

    }

    /**
     * 
     * 
     * 
     * @param vehicle
     * @return
     */
    public boolean addVehicle(Vehicle vehicle) {
        //this.listOfVehicles.add(vehicle);
        if (!this.doesVehicleExistInThisLane(vehicle)) {
            this.listOfVehicles.add(vehicle);

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
    public boolean doesVehicleExistInThisLane(Vehicle vehicle) {
        // check linkedlist for given vehicle and return true or false based on existence
        return (this.listOfVehicles.contains(vehicle) ? true : false);
    }

    /**
     * 
     * 
     * 
     * @param vehicle
     * @return
     */
    public Vehicle removeVehicle(Vehicle vehicle) {

        return (this.doesVehicleExistInThisLane(vehicle)) ? this.listOfVehicles.remove(this.listOfVehicles.indexOf(vehicle)) : null;
    }

    // public Vehicle remoVehicle(int vehicleID) {

    //     return null;
    // }
    
}
