import java.util.HashMap;
import java.util.LinkedList;

public class Graph {
    public HashMap<String,Node> allNodes = new HashMap<String,Node>();
    public LinkedList<Edge> allEdges = new LinkedList<Edge>();

    public void addEdge(Node startNode, Node endNode, int capacity) {
        if(!allNodes.containsKey(startNode.name))
            allNodes.put(startNode.name, startNode);
        if(!allNodes.containsKey(endNode.name))
            allNodes.put(endNode.name, endNode);

        Edge edge = new Edge(startNode,endNode,capacity);
        startNode.addEdge(edge);
        allEdges.add(edge);
    }
    public Node getNode(String nodeName){
        return allNodes.get(nodeName);
    }
    public LinkedList<Edge> getAllEdges(){
        return allEdges;
    }

}
