import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class project5 {
    public static void main(String[] args) throws FileNotFoundException {
        //long startTime = System.nanoTime();

        PrintStream fileOut = null;
        try {
            fileOut = new PrintStream(args[1]);
            //fileOut = new PrintStream("C:\\Users\\ahmet\\eclipse-workspace\\FordFulkerson\\output.txt");
        }catch(FileNotFoundException e1){
            e1.printStackTrace();
        }
        System.setOut(fileOut);

        File file = new File(args[0]);
        //File file = new File("C:\\Users\\ahmet\\eclipse-workspace\\FordFulkerson\\input\\input_50.txt");

        ArrayList<String> input = new ArrayList<>();
        Scanner data = new Scanner(file);

        HashMap<String, Node> totalNodes = new HashMap<>();
        Graph graph = new Graph();
        MaxFlow maxFlow = new MaxFlow();


        while(data.hasNextLine()) {
            input.add(data.nextLine());
        }
        int numberOfCity = Integer.parseInt(input.get(0));

        Node r0 = new Node("r0");
        Node r1 = new Node("r1");
        Node r2 = new Node("r2");
        Node r3 = new Node("r3");
        Node r4 = new Node("r4");
        Node r5 = new Node("r5");
        Node source1 = new Node("source");

        totalNodes.put("r0", r0);
        totalNodes.put("r1", r1);
        totalNodes.put("r2", r2);
        totalNodes.put("r3", r3);
        totalNodes.put("r4", r4);
        totalNodes.put("r5", r5);





        String troops[] = input.get(1).split(" ");

        graph.addEdge(source1, r0, Integer.parseInt(troops[0]));
        graph.addEdge(source1, r1, Integer.parseInt(troops[1]));
        graph.addEdge(source1, r2, Integer.parseInt(troops[2]));
        graph.addEdge(source1, r3, Integer.parseInt(troops[3]));
        graph.addEdge(source1, r4, Integer.parseInt(troops[4]));
        graph.addEdge(source1, r5, Integer.parseInt(troops[5]));

        for(int index=2;index<numberOfCity+8;index++){
            String[] info = input.get(index).split(" ");
            String startNodeName = info[0];
            if(totalNodes.get(startNodeName) == null) {
                totalNodes.put(startNodeName, new Node(startNodeName));
            }
            for(int j = 1; j<info.length;j+=2) {
                String relatedNodeName = info[j];
                if(relatedNodeName.equals("KL") && totalNodes.get(relatedNodeName)==null) {
                    Node finishingNode = new Node(relatedNodeName);
                }
                if(totalNodes.get(relatedNodeName)==null) {
                    totalNodes.put(relatedNodeName, new Node(relatedNodeName));
                }
                int capacity = Integer.parseInt(info[j+1]);
                graph.addEdge(totalNodes.get(startNodeName), totalNodes.get(relatedNodeName), capacity);
            }
        }

        maxFlow.findMaxFlow(graph, source1, totalNodes.get("KL"));  //HashMap<Edge, Integer> flow =
        //System.out.println(maxFlow.getFlowSize(graph, source1)); //flow,

        //long endTime = System.nanoTime();
        //double duration = Double.valueOf(endTime - startTime) /1000000;
        //System.out.println(duration);


    }
}