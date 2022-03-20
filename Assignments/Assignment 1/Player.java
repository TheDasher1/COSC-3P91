
public class Player implements User {

    private String playerName;
    private int playerID;
    private Vehicle playerVehicle;
    // Input playerInput;

    @Override
    public String getUserName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getUserID() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void changeVehicle(Vehicle newVehicle) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Vehicle[] lookAround(Road road) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vehicle[] lookAround(Intersection intersection) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean changeLanes(Road roadName, int newLane) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean turnAtIntersection(Road roadToTurn) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void gamble() {
        // TODO Auto-generated method stub
        
    }

    public boolean makeMove() {
        return false;
    }

    @Override
    public Vehicle getVehicle() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
