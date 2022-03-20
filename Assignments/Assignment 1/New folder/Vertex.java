
public class Vertex {

    private String label;

    public Vertex(String lable) {
        this.label = lable;

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
    
}
