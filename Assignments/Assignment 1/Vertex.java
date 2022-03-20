
public class Vertex {

    private String label;
    private Intersection intersection;

    public Vertex(String lable, int num) {
        this.label = lable;
        intersection = new Intersection(lable, num);

    }

    // public int hashCode() {
    //     final int prime = 31;
    //     int result = 1;

    //     result = prime * result + getOuterType().hashCode();

    // }

    // public Graph getOuterType() {
    //     return Graph.this;

    // }

    public String getLabel() {
        return this.label;

    }

    // public int getNumerofIntersections() {
    //     return this.intersection.getNumberofRoadSegements();
        
    // }
    
}
