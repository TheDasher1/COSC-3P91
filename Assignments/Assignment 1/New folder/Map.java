import java.util.List;

public class Map extends Graph {

    private Graph map;

    public Map() {

    }

    public Map(Graph Map) {
        this.map = Map;

    }

    public void addIntersection(String IntersectionName) {
        super.addVertex(IntersectionName);

    }

    public void addNewIntersectionToExistingIntersection(String ExistingIntersection, String NewIntersection, Boolean Bidirection) {
        if (Bidirection) {
            super.addVertex(NewIntersection);
            super.addEdgeBidirection(ExistingIntersection, NewIntersection);

        } else {
            super.addVertex(NewIntersection);
            super.addEdgeDirected(ExistingIntersection, NewIntersection);

        }

    }

    public void connectTwoExistingIntersections(String Intersection1, String Intersection2, Boolean Bidirection) {
        if (Bidirection) {
            super.addEdgeBidirection(Intersection1, Intersection2);

        } else {
            super.addEdgeDirected(Intersection1, Intersection2);

        }

    }

    public void removeIntersection(String Intersection) {
        super.removeVertex(Intersection);

    }

    public List<Vertex> getSurroundingIntersections(String CurrIntersection) {
        return super.getAdjVertex(CurrIntersection);

    }

    public void createRandomMap(int numIntersections) {
        for (int i = 0; i < numIntersections; i++) {


        }

    }

    public Graph getMap() {
        return this.map;

    }
    
}
