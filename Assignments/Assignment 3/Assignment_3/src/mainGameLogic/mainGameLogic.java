package mainGameLogic;

import java.util.Scanner;
import java.util.UUID;
import java.util.HashMap;
import java.util.InputMismatchException;

import MapPackage.*;
import UserPackage.*;
import VehiclePackage.*;
import GUIPackage.*;

public class mainGameLogic {

    public GUI gameWindow;
    public Map map = new Map();
    public HashMap<Integer, Player> players = new HashMap<Integer, Player>();// = new ArrayList<Player>();
    public HashMap<Integer, AI> AIplayers = new HashMap<Integer, AI>();// = new ArrayList<AI>();
    // public HashMap<Integer, Intersection> intersections = new HashMap<Integer, Intersection>();
    Scanner input = new Scanner(System.in);
    HashMap<Integer, Boolean> usedPlayerIDList = new HashMap<Integer, Boolean>();
    HashMap<Integer, Boolean> usedIntersectionIDList = new HashMap<Integer, Boolean>();
    HashMap<Integer, Boolean> usedRoadIDList = new HashMap<Integer, Boolean>();
    HashMap<Integer, Boolean> usedVehicleIDList = new HashMap<Integer, Boolean>();

    /**
     * 
     * This method will handle getting the initial map set up and creating all the players and vehicles for them.
     * 
     * After everything has been created, it calls the game driver method which will handle moving vehicles and turning and traffic lights.
     * 
     */
    public mainGameLogic() {
        String ch;
        
        while (true) {
            // ---------------------------- GAME START ------------------------------------------------------------
            System.out.print("\n1: Create Intersection \n2: Create Road \n3: Continue\n0: Exit: ");
            ch = input.nextLine();

            // --------------------------- EXIT GAME -------------------------------------------------------------
            if (ch.equals("0")) {
                break;
            
            // --------------------------- ADD USERS -------------------------------------------------------------
            } else if (ch.equals("-1")) {
                addUsers();

            // --------------------------- CREATE INTERSECTIONS --------------------------------------------------
            } else if (ch.equals("1")) {
                createIntersection();

            // --------------------------- CONTINUTE GAME --------------------------------------------------------
            } else if (ch.equals("2")) {
                createRoad();

            }else if (ch.equals("3")) { // After the map has been created
                System.out.println("1:Create User \n2: List all players\n3: List all Intersections\n4: List all Roads\n5: Play Game\n");
                ch = input.nextLine();

                if (ch.equals("1")) {
                    addUsers();

                } else if (ch.equals("2")) { // prints all the players currently playing
                    players.entrySet().forEach(entry -> {
                        System.out.println("\nPlayer Name: " + entry.getValue().getUserName() + ", ID: " + entry.getKey());
                    
                    });

                } else if (ch.equals("3")) { // prints all the intersections on the map
                    map.getAllIntersection().forEach(entry -> {
                        System.out.println("\nIntersection Name: " + entry.getIntersectionName() + ", ID: " + entry.getIntersectionID());

                    });

                } else if (ch.equals("4")) { // prints all the roads on the map
                    map.getAllRoads().forEach(entry -> {
                        System.out.println("\nRoad name: " + entry.getRoadName() + ", ID: " + entry.getRoadID());

                    });

                } else if (ch.equals("5")) { // start the game
                    startGame();

                }

            }

        }

        // System.out.println(map.getAllIntersection());

        input.close();

    }

    public void startGame() {
        int pChoice;
        // put the player/vehicle on the road its supposed to start on (held by the vehicle)
        // update all vehicle positions and decrease the timers on the traffic lights, which determine what roads are "going"

        for (Vehicle v : map.getAllPlayerVehiclesOnMap()) { // processes all the vehicles on the map
            if (v.getCurrPosition() > map.getRoad(v.getCurrRoad().getRoadID()).getMaxVehiclesPerLane()) { // player has reached the end of this road and has arrived at an intersection, should decide which road they should go into from this intersection
                // updates the map on where the vehicle is currently
                map.getRoad(v.getCurrRoad().getRoadID()).removeVehicle(v); // removes the vehicle from the road
                map.getRoad(v.getCurrRoad().getRoadID()).getTopIntersection().addVehicle(v); // adds this vehicle to the intersection above

                v.updateCurrRoad(null); // sets the vehicle's road to be null to signify its not on a road
                v.updateCurrIntersection(map.getPlayerVehicle(v.getVehicleID()).getCurrIntersection()); // set it's location to be on an intersection
                
                int rdChoise, lnChoice;

                System.out.print("\nThere are " + v.getCurrIntersection().getRoadsAttached().size() + " roads attached to this intersection, which to turn into: ");
                rdChoise = input.nextInt();

                System.out.print("\nWhich lane to turn into: ");
                lnChoice = input.nextInt();

                v.updateCurrIntersection(null); // sets the vehicle's intersection to null so signify it's out of the intersection
                v.updateCurrRoad(map.getRoad(rdChoise)); // sets the vehicle to be on the road

                map.getRoad(v.getCurrRoad().getRoadID()).addVehicle(v, lnChoice); // updates the road to hold the vehicle

                v.setCurrPosition(0);

            } else {
                System.out.println("1: Look around\n2: Keep moving: ");
                pChoice = justGetNum();

                switch (pChoice) {
                    case 1:
                        
                        break;
                
                    default:
                        v.updateCurrPosition();
                        break;
                }

                // v.updateCurrPosition(); // updates the vehicles to move forward

            }

        }

    }

    // --------------------------------------- CREATE INTERSECTIONS METHOD ---------------------------------------------
    public void createIntersection() {
        String iName, idChoice;
        int usedIds = 1, tempID;
        
        System.out.print("\nEnter name of the Intersection: ");
        iName = input.nextLine();
        System.out.print("\nEnter a unique ID or leave blank to use default: ");
        idChoice = input.nextLine();

        // ------------------------------ DEFAULT ID ------------------------------------------------------------------
        if (idChoice.equals("")) {
            while (this.usedIntersectionIDList.containsKey(usedIds)) {
                usedIds ++;

            }

            map.createIntersection(iName, usedIds); // creates an intersection with a default ID
            
            usedIntersectionIDList.put(usedIds, true); // adds the used ID number to prevent it from being used for another intersection

            System.out.println("\nNew Intersection: " + map.getIntersection(usedIds).getIntersectionName() + " with ID: " + map.getIntersection(usedIds).getIntersectionID() + " added.");
        
        // ------------------------------- USER GIVEN ID --------------------------------------------------------------
        } else {
            while (true) {
                // try {
                    // System.out.print("\nEnter a unique numerical ID that is not already in use: ");
                    tempID = getUniqueID();//input.nextInt();

                    if (!usedIntersectionIDList.containsKey(tempID)) { // makes sure the given ID is not in the list of used IDs
                        map.createIntersection(iName, tempID); // creates an intersection with the given ID

                        usedIntersectionIDList.put(tempID, true); // adds the user's given ID to the list of unusable list of IDs

                        System.out.println("\nNew Intersection: " + map.getIntersection(tempID).getIntersectionName() + " with ID: " + map.getIntersection(tempID).getIntersectionID() + " added.");

                        // input.next();
                        break;

                    } else {
                        System.out.println("This ID is already in use.");

                    }
                    
                // } catch (InputMismatchException err) {
                //     System.out.println("Invalid input.\n");
                //     input.next();

                // }
            }

        }

    }

    // ------------------------------ CREATE ROAD METHOD ----------------------------------------------------------------
    public void createRoad() {
        int tempID, idChoice;

        // --------------------------------- CREATE ROAD PART ------------------------------------------------------------
        String rName;
        int rID = 1, numLanes = 0, maxVehicles = 0;

        System.out.print("Enter name of the road: ");
        rName = input.nextLine();

        System.out.print("\nMax number of vehicles per lane on this road: ");
        maxVehicles = input.nextInt();

        System.out.print("\nNumber of Lanes this road has: ");
        numLanes = input.nextInt();

        System.out.print("\nEnter a unique ID or 0 to use default: ");
        idChoice = input.nextInt();
        
        if (idChoice == 0) {
            while (this.usedRoadIDList.containsKey(rID)) { // if the default ID is in the list of used IDs, keep incrementing it until it's not
                rID ++;

            }

            map.createRoad(rName, rID, numLanes, maxVehicles); // --------------- ROAD WITH DEFAULT ID CREATED ---------------

            usedRoadIDList.put(rID, true);

            System.out.println("\nNew Road: " + map.getRoad(rID).getRoadName() + " with ID: " + map.getRoad(rID).getRoadID() + " which allows a max of " + map.getRoad(rID).getMaxVehiclesPerLane() + " vehicles per lane added.");
            
            attachRoadToIntsect(rID); // sets the road to be attached to an intersection
            
            // ------------------------------- USER GIVEN ID --------------------------------------------------------------
            } else {
                tempID = idChoice;//input.nextInt();
    
                if (!usedRoadIDList.containsKey(tempID)) { // makes sure the given user ID is not in the list of used IDs
                    map.createRoad(rName, tempID, numLanes, maxVehicles); // -------- ROAD WITH USER ID CREATED ---------------

                    usedRoadIDList.put(tempID, true); // create road with given ID

                    System.out.println("\nNew Road: " + map.getRoad(tempID).getRoadName() + " with ID: " + map.getRoad(tempID).getRoadID() + " which allows a max of " + map.getRoad(tempID).getMaxVehiclesPerLane() + " vehicles per lane added.");

                    attachRoadToIntsect(tempID); // sets the road to be attached to an intersection

                } else {
                    System.out.println("This ID is already in use.");

                }
                    
            }

        // }

    }

    // --------------------------------------- ATTACH ROAD TO INTERSECTION(S) METHOD ---------------------------------------------
    public void attachRoadToIntsect(int ID) {
        int rdAttch, rAID;

        // while (true) {
        //     try {
                System.out.print("\nRoad has to be attached to an intersection\n1: connect this road to only 1 Intersection\n2: connect 2 intersections with this road\n");
                rdAttch = input.nextInt();

                // ------------------------------------ ATTACH ONLY 1 INTERSECTIONS ---------------------------------------------------
                if (rdAttch == 1) {
                    while (true) {
                        System.out.println("All intersections on map.");
                        map.getAllIntersection().forEach(entry -> {
                            System.out.println("\nIntersection Name: " + entry.getIntersectionName() + ", ID: " + entry.getIntersectionID());

                        });

                        System.out.print("\n\nEnter the unique ID of the intersection to attach to: ");
                        rAID = getUniqueID();//input.nextInt();

                        if (map.doesIntersectionExist(rAID)) { // makes sure the intersection exists
                            if (map.getIntersection(rAID).canARoadBeAttached()) {//} && map.getRoad(ID).canIntersectionBeAttached()) {
                                map.getIntersection(rAID).attachRoad(map.getRoad(ID)); // sets the intersection to be attched to this road

                                map.getRoad(ID).setIntersection(map.getIntersection(rAID)); // sets the road to be attached to this intersection
                                
                                // input.next();
                                break;

                            }
                        } else {
                            System.out.println("Intersection does not exist.");
                            
                        }
                    }
                
                // ------------------------------------ ATTACH ROAD TO 2 INTERSECTIONS -------------------------------------------
                } else if (rdAttch == 2) {
                    while (true) {
                        int rAID1, rAID2;
                        System.out.print("\nEnter the unique ID of the 1st intersection to attach to: ");
                        rAID1 = getUniqueID();//input.nextInt();

                        System.out.print("\nEnter the unique ID of the 2nd intersection to attach to: ");
                        rAID2 = getUniqueID();//input.nextInt();

                        // attaches the road to between 2 intersections
                        if (map.getIntersection(rAID1).canARoadBeAttached() && map.getIntersection(rAID2).canARoadBeAttached()) {
                            map.getIntersection(rAID1).attachRoad(map.getRoad(ID));
                            map.getIntersection(rAID2).attachRoad(map.getRoad(ID));

                            map.getRoad(ID).setIntersection(map.getIntersection(rAID1));
                            map.getRoad(ID).setIntersection(map.getIntersection(rAID2));

                            System.out.println("\nIntersection " + map.getIntersection(rAID1).getIntersectionName() + " attached to " + map.getIntersection(rAID2).getIntersectionName());
                            System.out.println("\nRoad has top intersection: " + map.getRoad(ID).getTopIntersection().getIntersectionName() + " and Bottom Intersection: " + map.getRoad(ID).getBottomIntersection().getIntersectionName());
                            
                            // input.next();
                            break;

                        } else {
                            System.out.println("The Intersection does not exist or it can not have any additional roads attached.");

                        }
                    }

                }

        //     } catch (InputMismatchException err) {
        //         System.out.println("Invalid input.");
        //         input.next();

        //     }

        // }

    }

    // ------------------------------------ ADD USERS METHOD ------------------------------------------------------------
    public void addUsers() {
        String pName, idChoice;
        int usedIDs = 1, tempID;

        System.out.print("\nEnter name for the player: ");
        pName = input.nextLine();
        System.out.print("\nEnter a unique ID or leave blank to use default: ");
        idChoice = input.nextLine();

        // ------------------------------ DEFAULT ID ------------------------------------------------------------------
        if (idChoice.equals("")) {
            while (usedPlayerIDList.containsKey(usedIDs)) { // increments the usedIDs until it is no longer in the list of used IDs
                usedIDs ++;

            }

            players.put(usedIDs, new Player(pName, usedIDs)); // creates the player and holds it in a HashMap to be easily usable

            usedPlayerIDList.put(usedIDs, true); // adds the used ID to prevernt it from being used again

            System.out.println("\nNew user: " + players.get(usedIDs).getUserName() + " with ID: " + players.get(usedIDs).getUserID() + " added.");

            VehicleMaker(usedIDs); // calls this method to make a vehicle for the user
            
        } else {
            while (true) {
                tempID = getUniqueID();//input.nextInt();

                if (!usedPlayerIDList.containsKey(tempID)) {
                    players.put(tempID, new Player(pName, tempID));

                    usedPlayerIDList.put(tempID, true);

                    System.out.println("\nNew user: " + players.get(tempID).getUserName() + " with ID: " + players.get(tempID).getUserID() + " added.");
                    
                    VehicleMaker(tempID);

                    break;

                } else {
                    System.out.println("This ID is already in use.");

                }
                               
            }
            
        }

        // VehicleMaker();

    }

    /**
     * 
     * This method creates a vehicle for the user
     * 
     * @param pID
     */
    public void VehicleMaker(int pID) {
        int vID, vStartSpeed, vStartRoadID, vStartLane;
        String vMake, vColor;
        int usedIDs = 1;

        System.out.println("The user needs a car.\n");

        try {
            System.out.print("\nEnter the ID of the road to start on: ");
            vStartRoadID = input.nextInt();

            System.out.print("\nEnter the lane number to start on: ");
            vStartLane = input.nextInt();

            System.out.print("\nEnter the starting speed of the vehicle: ");
            vStartSpeed = input.nextInt();
            
            System.out.print("\nEnter a unique ID of your vehicle or 0 to have one generated: ");
            vID = input.nextInt();

            if (vID == 0) {
                while (usedVehicleIDList.containsKey(usedIDs)) {
                    usedIDs ++;
    
                }

                map.createPlayerCar(usedIDs, vStartSpeed, map.getRoad(vStartRoadID), vStartLane);

                this.usedVehicleIDList.put(usedIDs, true);

                players.get(pID).changeVehicle(map.getPlayerVehicle(usedIDs));
                
            
            } else {
                if (!this.usedVehicleIDList.containsKey(vID)) {
                    map.createPlayerCar(vID, vStartSpeed, map.getRoad(vStartRoadID), vStartLane);

                    this.usedVehicleIDList.put(vID, true);

                    players.get(pID).changeVehicle(map.getPlayerVehicle(vID));

                } else {
                    System.out.println("This ID might already be in use.");

                }

            }

        } catch (InputMismatchException err) {
            System.out.println("Invalid input.");

        }

    }

    public void loadItem(int ICase) {
        switch (ICase) {
            case 0:

                break;
            
            case 1:

                break;

            case 2:

                break;

        }

    }

    public int justGetNum() {
        int temp;

        while (true) {
            if (input.hasNextInt()) {
                temp = input.nextInt();

            } else {
                input.next();

                continue;

            }

            break;

        }

        return temp;

    }

    public int getUniqueID() {
        int tempID;

        while (true) {
            System.out.print("\nEnter a unique numerical ID that is not already in use: ");

            if (input.hasNextInt()) {
                tempID = input.nextInt();

            } else {
                input.next();

                continue;

            }

            break;

        }

        return tempID;

    }

    public static void main(String[] args) throws Exception {
        new mainGameLogic();
    }
}
