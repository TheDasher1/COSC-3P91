package mainGameLogic;

import java.util.Scanner;
import java.util.UUID;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.Socket;
import java.util.HashMap;
import java.util.InputMismatchException;

import MapPackage.*;
import UserPackage.*;
import VehiclePackage.*;
import GUIPackage.*;
import NetworkPackage.*;

public class mainGameLogic {

    public GUI gameWindow;
    public final String filePath = "E:\\COSC 3P91\\Assignments\\Assignment 3\\Assignment_3\\mapLoader.xml";
    public Map map = new Map();
    public HashMap<Integer, Player> players = new HashMap<Integer, Player>();// = new ArrayList<Player>();
    public HashMap<Integer, AI> AIplayers = new HashMap<Integer, AI>();// = new ArrayList<AI>();
    // public HashMap<Integer, Intersection> intersections = new HashMap<Integer, Intersection>();
    Scanner input = new Scanner(System.in);
    
    // I now realise that having a seperate HashMap to just keep track of IDs that are already used is kind of redundant when I'm already storing existing players and stuff in a HashMap, my bad
    HashMap<Integer, Boolean> usedPlayerIDList = new HashMap<Integer, Boolean>();
    HashMap<Integer, Boolean> usedIntersectionIDList = new HashMap<Integer, Boolean>();
    HashMap<Integer, Boolean> usedRoadIDList = new HashMap<Integer, Boolean>();
    HashMap<Integer, Boolean> usedVehicleIDList = new HashMap<Integer, Boolean>();
    PlayerController player;

    /**
     * 
     * This method will handle getting the initial map set up and creating all the players and vehicles for them.
     * 
     * After everything has been created, it calls the game driver method which will handle moving vehicles and turning and traffic lights.
     * 
     */
    public mainGameLogic() {
        String ch = "";
        
        while (true) {
            // ---------------------------- GAME START ------------------------------------------------------------
            System.out.print("\n1: Create Map from File \n2: Create Intersection \n3: Create Road \n4: Continue \n0: Exit: ");
            // ch = input.nextLine();

            // --------------------------- EXIT GAME -------------------------------------------------------------
            if (ch.equals("0")) {
                break;
            
            // --------------------------- ADD USERS -------------------------------------------------------------
            } else if (ch.equals("-1")) {
                addUsers();

            // --------------------------- CREATE INTERSECTIONS --------------------------------------------------
            } else if (ch.equals("1")) {
                // fileMap();
                fileMapLoader();

            } else if (ch.equals("2")) {
                createIntersection();

            // --------------------------- CONTINUTE GAME --------------------------------------------------------
            } else if (ch.equals("3")) {
                createRoad();

            }else if (ch.equals("4")) { // After the map has been created
                break;
            
            }
        
        }

        while (true) {
            System.out.println("1:Create User \n2: List all players\n3: List all Intersections\n4: List all Roads\n5: Play Game\n");
            // ch = input.nextLine();

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
                // startGame();

                break;

            }

        }        
        
        input.close();

    }

    /**
     * 
     * This method is the main game manager. It moves all the vehicles on the map and presents the player with options on what to do 
     * in between each step. When the player vehicle reaches an intersection, it handles the vehicle turning into the correct road and lane.
     * @throws IOException
     * 
     */
    public void startGame(Socket sock) throws IOException {
        int pChoice;
        // put the player/vehicle on the road its supposed to start on (held by the vehicle)
        // update all vehicle positions and decrease the timers on the traffic lights, which determine what roads are "going"
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        BufferedWriter bufferWriter = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));

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
                System.out.print("\n1: Look around\n2: Change Lanes\n3: Change speed\n4: Gamble\n5: Keep moving\n> ");
                pChoice = justGetNum();

                switch (pChoice) {
                    case 1:
                        // System.out.println("Location on road: " + v.getCurrPosition());
                        player.lookAround();
                        
                        break;
                    
                    case 2:
                        int newLane;

                        System.out.print("\nEnter number of which lane to change to");
                        newLane = justGetNum();

                        if (player.changeLanes(newLane)) {
                            player.getPlayer().changeReputation(5);
                        
                        } else {
                            player.getPlayer().changeReputation(-5);
                        
                        }

                        break;

                    case 3:
                        int newSpeed;

                        System.out.print("\nNew speed to change to (- in front to decrease)");
                        newSpeed = justGetNum();

                        if (player.getPlayer().getVehicle().increaseSpeed(newSpeed)) {
                            player.getPlayer().changeReputation(5);

                        } else {
                            player.getPlayer().changeReputation(-5);

                        }

                        break;

                    case 4:

                        break;
                
                    default:
                        v.updateCurrPosition(); // updates all the vehicles to move forward based on what speed they're currently traveling at
                        break;
                }

                // v.updateCurrPosition(); // updates the vehicles to move forward

            }

        }

    }

    // --------------------------------------- CREATE INTERSECTIONS METHOD ---------------------------------------------
    /**
     * 
     * This method creates an Intersection. It takes the appropriate values values and creates an intersection with a default ID or
     * takes an ID from the the user and creates an Intersection from that.
     * 
     */
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
    /**
     * 
     * This method creates a Road. The road is created with either a default ID or a user given ID.
     * 
     */
    public void createRoad() {
        int tempID, idChoice;

        // --------------------------------- CREATE ROAD PART ------------------------------------------------------------
        String rName;
        int rID = 1, numLanes = 0, maxVehicles = 0;

        System.out.print("Enter name of the road: ");
        rName = input.nextLine();

        System.out.print("\nMax number of vehicles per lane on this road: "); // this also doubles as the length of the lane
        maxVehicles = justGetNum();//input.nextInt();

        System.out.print("\nNumber of Lanes this road has: ");
        numLanes = justGetNum();//input.nextInt();

        System.out.print("\nEnter a unique ID or 0 to use default: ");
        idChoice = justGetNum();//input.nextInt();
        
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
    /**
     * 
     * This method will attach a Road to an Intersection. It takes an Intersection's ID as parameter. It makes sure that the Intersection
     * can have the Road attached to it and will not exit until the Road is attached to some Intersection.
     * 
     * @param ID the ID of the Intersection to attach Road to
     */
    public void attachRoadToIntsect(int ID) {
        int rdAttch, rAID;

        // while (true) {
        //     try {
                System.out.print("\nRoad has to be attached to an intersection\n1: connect this road to only 1 Intersection\n2: connect 2 intersections with this road");
                rdAttch = justGetNum();//input.nextInt();

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
                        rAID1 = justGetNum();//getUniqueID();//input.nextInt();

                        System.out.print("\nEnter the unique ID of the 2nd intersection to attach to: ");
                        rAID2 = justGetNum();//getUniqueID();//input.nextInt();

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
    /**
     * 
     * This method will handle creating a user with either a default ID or a user given ID.
     * 
     */
    public void addUsers() {
        String pName, idChoice;
        int usedIDs = 1, tempID;
        Player tempPlayer;

        System.out.print("\nEnter name for the player: ");
        pName = input.nextLine();
        System.out.print("\nEnter a unique ID or leave blank to use default: ");
        idChoice = input.nextLine();

        // ------------------------------ DEFAULT ID ------------------------------------------------------------------
        if (idChoice.equals("")) {
            while (usedPlayerIDList.containsKey(usedIDs)) { // increments the usedIDs until it is no longer in the list of used IDs
                usedIDs ++;

            }

            tempPlayer = new Player(pName, usedIDs);

            players.put(usedIDs, tempPlayer);//new Player(pName, usedIDs)); // creates the player and holds it in a HashMap to be easily usable

            usedPlayerIDList.put(usedIDs, true); // adds the used ID to prevernt it from being used again

            System.out.println("\nNew user: " + players.get(usedIDs).getUserName() + " with ID: " + players.get(usedIDs).getUserID() + " added.");

            VehicleMaker(tempPlayer);//usedIDs); // calls this method to make a vehicle for the user
            
        } else {
            while (true) {
                tempID = getUniqueID();//input.nextInt();

                if (!usedPlayerIDList.containsKey(tempID)) {
                    tempPlayer = new Player(pName, tempID);
                    
                    players.put(tempID, tempPlayer);//new Player(pName, tempID));

                    usedPlayerIDList.put(tempID, true);

                    System.out.println("\nNew user: " + players.get(tempID).getUserName() + " with ID: " + players.get(tempID).getUserID() + " added.");
                    
                    VehicleMaker(tempPlayer);//tempID);

                    break;

                } else {
                    System.out.println("This ID is already in use.");

                }
                               
            }
            
        }

        player = new PlayerController(tempPlayer);

        // VehicleMaker();

    }

    /**
     * 
     * This method creates a vehicle for the user.
     * 
     * @param pID The Player to create the vehicle for.
     */
    public void VehicleMaker(User playertemp) {//int pID) {
        int vID, vStartSpeed, vStartRoadID, vStartLane;
        // String vMake, vColor;
        int usedIDs = 1;

        System.out.println("The user needs a car.\n");

        try {
            System.out.print("\nEnter the ID of the road to start on: ");
            vStartRoadID = justGetNum();//input.nextInt();

            System.out.print("\nEnter the lane number to start on: ");
            vStartLane = justGetNum();//input.nextInt();

            System.out.print("\nEnter the starting speed of the vehicle: ");
            vStartSpeed = justGetNum();//input.nextInt();
            
            System.out.print("\nEnter a unique ID of your vehicle or 0 to have one generated: ");
            vID = justGetNum();//input.nextInt();

            if (vID == 0) {
                while (usedVehicleIDList.containsKey(usedIDs)) {
                    usedIDs ++;
    
                }

                map.createPlayerCar(usedIDs, vStartSpeed, map.getRoad(vStartRoadID), vStartLane, playertemp);

                this.usedVehicleIDList.put(usedIDs, true);

                // players.get(pID).changeVehicle(map.getPlayerVehicle(usedIDs));
                players.get(playertemp.getUserID()).changeVehicle(map.getPlayerVehicle(usedIDs));
                
            
            } else {
                if (!this.usedVehicleIDList.containsKey(vID)) {
                    map.createPlayerCar(vID, vStartSpeed, map.getRoad(vStartRoadID), vStartLane, playertemp);

                    this.usedVehicleIDList.put(vID, true);

                    // players.get(pID).changeVehicle(map.getPlayerVehicle(vID));
                    players.get(playertemp.getUserID()).changeVehicle(map.getPlayerVehicle(vID));

                } else {
                    System.out.println("This ID might already be in use.");

                }

            }

        } catch (InputMismatchException err) {
            System.out.println("Invalid input.");

        }

    }

    /**
     * 
     * This method creates the map from an XML file.
     * 
     */
    public void fileMapLoader() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        Document doc;
        NodeList list;
        Node node;
        Element element;

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            db = dbf.newDocumentBuilder();

            doc = db.parse(new File(this.filePath));

            doc.getDocumentElement().normalize();

            list = doc.getElementsByTagName("Intersection");

            for(int i = 0; i < list.getLength(); i ++) {
                node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    element = (Element)node;

                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());

                    if (!usedIntersectionIDList.containsKey(id)) {
                        map.createIntersection(name, id);

                    } else {
                        System.out.println("Intersection ID: " + id + " is already in use.");

                    }

                }

            }

            list = doc.getElementsByTagName("Road");

            for (int i = 0; i < list.getLength(); i ++) {
                node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    element = (Element)node;

                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    int maxLanes = Integer.parseInt(element.getElementsByTagName("maxLanes").item(0).getTextContent());
                    int maxVehiclesPerLane = Integer.parseInt(element.getElementsByTagName("maxVehiclesPerLane").item(0).getTextContent());
                    int topIntersectionID = Integer.parseInt(element.getElementsByTagName("topIntersection").item(0).getTextContent());
                    int bottomIntersectionID = Integer.parseInt(element.getElementsByTagName("bottomIntersection").item(0).getTextContent());

                    if (!usedRoadIDList.containsKey(id)) {
                        map.createAttactedRoad(name, id, maxLanes, maxVehiclesPerLane, map.getIntersection(topIntersectionID), map.getIntersection(bottomIntersectionID));

                    } else {
                        System.out.println("Road ID: " + id + " is already in use.");

                    }

                }

            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();

        }

    }

    /**
     * 
     * 
     * 
     */
    public void fileMap() {
        String line[], Iname, IID;

        try {
            // Reader file = new FileReader(filePath);
            File file = new File(filePath);
            // Reader fileReader = new FileReader(file);
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNext()) {
                
                line = fileReader.nextLine().split(" ");

                if (line[0].equals("roadsect")) break;

                // Iname = line[0];//line.split(" ")[0];
                // IID = line[1];//line.split(" ")[1];

                if (!usedIntersectionIDList.containsKey(Integer.parseInt(line[1]))) {
                    map.createIntersection(line[0], Integer.parseInt(line[1]));

                } else {
                    System.out.println("This Intersection ID is already in use.");

                }

            }

            while (fileReader.hasNext()) {
                line = fileReader.nextLine().split(" ");

                if (!usedRoadIDList.containsKey(Integer.parseInt(line[1]))) {
                    if (line.length == 4) {
                        map.createRoad(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]));

                    } else if (line.length == 5) {
                        map.createAttactedRoad(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), map.getIntersection(Integer.parseInt(line[4])));

                    } else if (line.length == 6) {
                        boolean temp = map.createAttactedRoad(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), map.getIntersection(Integer.parseInt(line[4])), map.getIntersection(Integer.parseInt(line[5])));

                    }

                } else {
                    System.out.println("This Road ID is already in use.");

                }
                
            }

            fileReader.close();            
        
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
            e.printStackTrace();
            
        }

        // fileReader.close();
        // file.close();

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

    /**
     * 
     * This method will safely get a number input from the user.
     * 
     * @return The number the user entered.
     */
    public int justGetNum() {
        int temp;

        while (true) {
            System.out.print("\n>");

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

    /**
     * 
     * This method will handle properly promting the user for a unique ID and returning it.
     * 
     * @return The unique ID entered by the user.
     */
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
