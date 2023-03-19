public class Edge {
    public Node startingNode;
    public Node finishingNode;
    public int capacity;
    public int flow = 0;

    Edge(Node startingNode,Node finishingNode,int capacity){
        this.capacity = capacity;
        this.startingNode = startingNode;
        this.finishingNode = finishingNode;
    }




}
