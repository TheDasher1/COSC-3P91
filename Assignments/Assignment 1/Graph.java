import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {
    
    // private Map<Vertex, List<Vertex>> adjVertex;
    private Map<Intersection, List<Intersection>> adjVertex;
    public int numOfIntersects;

    public Graph() {
        adjVertex = new HashMap<Intersection, List<Intersection>>();

    }
    
    public Graph(int numofsects) {
        // adjVertex = new HashMap<Vertex, List<Vertex>>();
        adjVertex = new HashMap<Intersection, List<Intersection>>();
        numOfIntersects = numofsects;

    }

    // public void addVertex(String label) {
    //     // adjVertex.putIfAbsent(new Vertex(label, numofsects), new ArrayList<>());
    //     adjVertex.putIfAbsent(new Intersection(label), new ArrayList<>());

    // }

    public void addVertex(String label, int numofsects) {
        // adjVertex.putIfAbsent(new Vertex(label, numofsects), new ArrayList<>());
        adjVertex.putIfAbsent(new Intersection(label, numofsects), new ArrayList<>());

    }

    public void removeVertex(String label) {
        // Vertex v = new Vertex(label, numofsects);
        Intersection v = new Intersection(label, numOfIntersects);
        
        adjVertex.values().stream().forEach(e -> e.remove(v));
        adjVertex.remove(new Intersection(label, numOfIntersects));

    }

    public void removeVertex(String label, int numofsects) {
        // Vertex v = new Vertex(label, numofsects);
        Intersection v = new Intersection(label, numofsects);
        
        adjVertex.values().stream().forEach(e -> e.remove(v));
        adjVertex.remove(new Intersection(label, numofsects));

    }

    public void addEdgeBidirection(String label1, String label2, int numofsects1, int numofsects2) {
        // Vertex v1 = new Vertex(label1, numofsects1);
        // Vertex v2 = new Vertex(label2, numofsects2);
        Intersection v1 = new Intersection(label1, numofsects1);
        Intersection v2 = new Intersection(label2, numofsects2);

        adjVertex.get(v1).add(v2);
        adjVertex.get(v2).add(v1);

    }

    public void addEdgeDirected(String label1, String label2, int numofsects1, int numofsects2) {
        // Vertex v1 = new Vertex(label1, numofsects1);
        // Vertex v2 = new Vertex(label2, numofsects2);
        Intersection v1 = new Intersection(label1, numofsects1);
        Intersection v2 = new Intersection(label2, numofsects2);

        // if (adjVertex.containsKey(new Vertex(label2))) {
        //     List<Vertex> v2 = adjVertex.get(new Vertex(label2));

        // } else {
        //     Vertex v2 = new Vertex(label2);
        
        // }

        adjVertex.get(v1).add(v2);

    }

    public List<Intersection> getAdjVertex(String label) {
        // return adjVertex.get(new Vertex(label, numofsects));
        return adjVertex.get(new Intersection(label, numOfIntersects));

    }

    public List<Intersection> getAdjVertex(Intersection name) {
        return adjVertex.get(name);

    }

    public boolean doesIntersectionExist(Intersection name) {
        if (adjVertex.containsKey(name)) {
            return true;

        } else {
            return false;

        }

    }

    public Intersection getIntersect(String label) {
        if (adjVertex.containsKey(new Intersection(label, numOfIntersects))) {
            return new Intersection(label, numOfIntersects);

        }

        return null;

    }

    public List<Intersection> getIntersection(Intersection label) {
        if (adjVertex.containsKey(label)) {
            return adjVertex.get(label);

        }
        
        return null;

    }

    public String[] getAllLocations() {
        String[] listOfLocation = new String[adjVertex.size()];
        int counter = 0;

        for (Entry<Intersection, List<Intersection>> loca : adjVertex.entrySet()) {
            listOfLocation[counter] = loca.getKey().getIntersectionName();

            counter ++;
            
        }

        return listOfLocation;

    }

    public void removeEdge(String label1, String label2, int numofsects1, int numofsects2) {
        // Vertex v1 = new Vertex(label1, numofsects1);
        // Vertex v2 = new Vertex(label2, numofsects2);
        Intersection v1 = new Intersection(label1, numofsects1);
        Intersection v2 = new Intersection(label2, numofsects2);

        List<Intersection> eV1 = adjVertex.get(v1);
        List<Intersection> eV2 = adjVertex.get(v2);

        if (eV1 != null) eV1.remove(v2);
        
        if (eV2 != null) eV2.remove(v1);

    }

}
