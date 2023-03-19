import java.util.ArrayList;

public class Node {
    public String name;
    public boolean checker;
    ArrayList<Edge> edges;


    public Node(String name){
        edges = new ArrayList<Edge>();
        this.name = name;
    }
    public void addEdge(Edge edge){
        edges.add(edge);
    }
    public Edge getEdge(int number){
        return edges.get(number);
    }

}
