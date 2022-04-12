package UserPackage;

import MapPackage.Map;
import VehiclePackage.Vehicle;

public class PlayerController {

    private Player player;
    private Map map;

    public PlayerController(Player player) {
        this.player = player;
        map = new Map();

    }

    public void lookAround() {
        // the player can only be either on a road or at an intersection, whatever the player is not on is set to null
        if (player.getVehicle().getCurrRoad() != null) { // if the player is on a road
            System.out.println("Player is on " + player.getVehicle().getCurrRoad().getRoadName() + " road.");
            System.out.println("Vehicle position: " + player.getVehicle().getCurrPosition());
            
            for(Vehicle v: player.getVehicle().getCurrRoad().getAllVehiclesOnRoad()) { // get all vehicles on the road the player is on
                if (v.getCurrPosition() == player.getVehicle().getCurrPosition() & v.getLane() != player.getVehicle().getLane()) { // if this vehicle is in the same position in a parallel lane (left side or right side of the player vehicle)
                    System.out.println(v.getOwner().getUserName() + " " + v.getOwner().getUserID() + " is next to you.");

                } else if ((v.getCurrPosition() - player.getVehicle().getCurrPosition()) == 1 & v.getLane() == player.getVehicle().getLane()) { // this vehicle is in front of player vehicle and in the same lane
                    System.out.println(v.getOwner().getUserName() + " " + v.getOwner().getUserID() + " is in front of you.");

                } else if ((v.getCurrPosition() - player.getVehicle().getCurrPosition()) == -1 & v.getLane() == player.getVehicle().getLane()) { // this vehicle is behind player vehicle and in the same lane
                    System.out.println(v.getOwner().getUserName() + " " + v.getOwner().getUserID() + " is behind you.");

                }
                
            }

        } else {
            System.out.println("Player is at " + player.getVehicle().getCurrIntersection().getIntersectionName() + " intersection.");

        }

    }

    public boolean changeLanes(int newLaneNum) {
        // if the side position in the given lane is empty, swap to it
        // make sure the given lane number is actually right beside the player's current lane
        
        if (newLaneNum <= player.getVehicle().getCurrRoad().getNumLanes()) {
            int leftLane, rightLane;
            
            leftLane = player.getVehicle().getLane() - 1; // this is the lane that is to the left of the player
            rightLane = player.getVehicle().getLane() + 1; // this is the lane that is to the right of the player

            // might need to change from 0 to 1 for array indexing ------------------------------------------
            if (newLaneNum == leftLane & leftLane >= 0) {// & !isVehicleOnSpot(newLaneNum)) { // if left lane is not outofbounds and new lane that player wants to switch into
                player.getVehicle().changeLane(newLaneNum);

                return true;

            } else if (newLaneNum == rightLane & rightLane <= player.getVehicle().getCurrRoad().getNumLanes()) {// & !isVehicleOnSpot(newLaneNum)) { // if player wants to switch to right lane and the right lane is not outofbounds
                player.getVehicle().changeLane(newLaneNum);

                return true;

            }

        }

        return false;

    }

    public boolean turnAtIntersection(int newRoadNum, int entryLane) {
        // make sure the player is at an intersection (or maybe the gamemanager should handle that)
        // make sure the given number of intersection to turn into is within limit
        // make sure the lane to enter on the road is within limit
        // turn if so (traffic light check stuff)

        if (newRoadNum <= player.getVehicle().getCurrIntersection().getRoadsAttached().size()) { // make sure the new road the player wants to turn into exists
            if (entryLane <= player.getVehicle().getCurrIntersection().getRoadsAttached().get(newRoadNum).getNumLanes()) {
                player.getVehicle().updateCurrRoad(player.getVehicle().getCurrIntersection().getRoadsAttached().get(newRoadNum));

                return true;

            }

        }
        
        return false;

    }

    public boolean isVehicleOnSpot(int spot) {
        for (Vehicle v : player.getVehicle().getCurrRoad().getAllVehiclesOnRoad()) {
            if (v.getCurrPosition() == spot) {
                
                return true; // there is a vehicle on this spot

            }

        }
        
        return false; // this spot is empty

    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public void playerView() { // lists everything about the player
        System.out.println("Name: " + player.getUserName() + "\tID: " + player.getUserID() + "\tReputation: " + player.getPlayerRep());

    }
    
}
