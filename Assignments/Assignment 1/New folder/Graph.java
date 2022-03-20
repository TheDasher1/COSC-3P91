import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {
    
    private Map<Vertex, List<Vertex>> adjVertex;
    
    public Graph() {
        adjVertex = new HashMap<Vertex, List<Vertex>>();

    }

    public void addVertex(String label) {
        adjVertex.putIfAbsent(new Vertex(label), new ArrayList<>());

    }

    public void removeVertex(String label) {
        Vertex v = new Vertex(label);
        
        adjVertex.values().stream().forEach(e -> e.remove(v));
        adjVertex.remove(new Vertex(label));

    }

    public void addEdgeBidirection(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);

        adjVertex.get(v1).add(v2);
        adjVertex.get(v2).add(v1);

    }

    public void addEdgeDirected(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);

        // if (adjVertex.containsKey(new Vertex(label2))) {
        //     List<Vertex> v2 = adjVertex.get(new Vertex(label2));

        // } else {
        //     Vertex v2 = new Vertex(label2);
        
        // }

        adjVertex.get(v1).add(v2);

    }

    public List<Vertex> getAdjVertex(String label) {
        return adjVertex.get(new Vertex(label));

    }

    public String[] getAllLocations() {
        String[] listOfLocation = new String[adjVertex.size()];
        int counter = 0;

        for (Entry<Vertex, List<Vertex>> loca : adjVertex.entrySet()) {
            listOfLocation[counter] = loca.getKey().getLabel();

            counter ++;
            
        }

        return listOfLocation;

    }

    public void removeEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);

        List<Vertex> eV1 = adjVertex.get(v1);
        List<Vertex> eV2 = adjVertex.get(v2);

        if (eV1 != null) eV1.remove(v2);
        
        if (eV2 != null) eV2.remove(v1);

    }

}
