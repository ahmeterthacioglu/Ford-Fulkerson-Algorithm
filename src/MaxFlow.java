import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class MaxFlow {
    boolean flag = false;
    ArrayList<Edge> edges = new ArrayList<Edge>();
    ArrayList<Node> unreachableNodes = new ArrayList<Node>();
    ArrayList<Node> reachableNodes = new ArrayList<Node>();

    public void findMaxFlow(Graph graph,Node source,Node target){
        int maxFlow = 0;
        LinkedList<Edge> path;
        LinkedList<Edge> path1;
        LinkedList<Edge> path2;
        while((path = findPath(graph,source,target)) != null){
            int minCapacity = Integer.MAX_VALUE;
            Node tempNode = source;
            for(Edge edge : path){
                int a = 0;
                if(edge.startingNode.equals(tempNode)){
                    a = edge.capacity - edge.flow;
                    tempNode = edge.finishingNode;
                }
                if(a<minCapacity)
                    minCapacity = a;

            }
            tempNode = source;
            for(Edge edge:path){
                if(edge.startingNode.equals(tempNode)){
                    edge.flow += minCapacity;
                    if(edge.capacity == edge.flow){
                        edge.capacity = 0;
                        edge.startingNode.checker = true;
                        edges.add(edge);
                        unreachableNodes.add(edge.finishingNode);
                        reachableNodes.add(edge.startingNode);

                    }
                    tempNode = edge.finishingNode;
                }
            }
        }
        for(int i = 0;i<source.edges.size();i++){
            maxFlow += source.getEdge(i).flow;
        }
        System.out.println(maxFlow);
        for(int i = 0;i<unreachableNodes.size();i++){
            if( !bfs1(graph, source, unreachableNodes.get(i)) && bfs1(graph, source, reachableNodes.get(i)) && edges.get(i).startingNode.checker == true && edges.get(i).finishingNode.checker == false ) {
                System.out.println(edges.get(i).startingNode.name+" "+edges.get(i).finishingNode.name);
            }
            path1 = null;
            if(!bfs1(graph, source, unreachableNodes.get(i)) && edges.get(i).startingNode.equals(source) ) { //&& edges.get(i).finishingNode.checker == false
                System.out.println(edges.get(i).finishingNode.name);
            }
        }

    }
    public boolean bfs1(Graph graph,Node source,Node target){
        HashMap<Node,Edge> parent = new HashMap<Node,Edge>();
        LinkedList<Node> oldPath = new LinkedList<Node>();

        parent.put(source,null);
        oldPath.add(source);
        flag = false;

        while ((!oldPath.isEmpty())){
            LinkedList<Node> newPath = new LinkedList<Node>();
            for(Node node : oldPath){
                for(int i = 0; i< node.edges.size();i++){
                    Edge edge = node.getEdge(i);
                    if(edge.startingNode.equals(node) && !parent.containsKey(edge.finishingNode) && edge.capacity != 0){
                        parent.put(edge.finishingNode,edge);
                        if(edge.finishingNode.equals(target)){
                            flag = true;
                            break;
                        }
                        newPath.add(edge.finishingNode);
                    }
                }
                if(flag)
                    break;

            }
            if(flag)
                break;
            oldPath = newPath;
        }
        if(oldPath.isEmpty())
            return false;
        return true;
    }
    public LinkedList<Edge> findPath(Graph graph,Node source,Node target){
        HashMap<Node,Edge> parent = new HashMap<Node,Edge>();
        LinkedList<Node> oldPath = new LinkedList<Node>();

        parent.put(source,null);
        oldPath.add(source);
        flag = false;

        while ((!oldPath.isEmpty())){
            LinkedList<Node> newPath = new LinkedList<Node>();
            for(Node node : oldPath){
                for(int i = 0; i< node.edges.size();i++){
                    Edge edge = node.getEdge(i);
                    if(edge.startingNode.equals(node) && !parent.containsKey(edge.finishingNode) && edge.flow < edge.capacity){
                        parent.put(edge.finishingNode,edge);
                        if(edge.finishingNode.equals(target)){
                            flag = true;
                            break;
                        }
                        newPath.add(edge.finishingNode);
                    }
                }
                if(flag)
                    break;

            }
            if(flag)
                break;
            oldPath = newPath;
        }
        if(oldPath.isEmpty())
            return null;
        Node node = target;
        LinkedList<Edge> path = new LinkedList<Edge>();
        while(!node.equals(source)){
            Edge edge1 = parent.get(node);
            path.addFirst(edge1);
            if(edge1.startingNode.equals(node)){
                node = edge1.finishingNode;
            }
            else
                node = edge1.startingNode;
        }

        return path;
    }

}
