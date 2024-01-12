import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

class Graph {
    private int V;
    private Map<String, List<String>> adj;
    String availbaleColors ="Blue,Red,Green,Yellow,Black,White,Violet,Orange,Brown,Grey,Sky Blue,Pink,Biege,Purple";
    String[] colors = availbaleColors.split(",");

    public Graph(int v) {
        this.V = v;
        adj = new HashMap<>();
    }

    public void addVertex(String v) {
        if (adj.get(v) == null)
            adj.put(v, new ArrayList<>());
    }
    public void addEdge(String v, String w) {
        adj.get(v).add(w);
        adj.get(w).add(v);
    }
    public void Coloring() {
        Map<String,Integer> verticesColors = new HashMap<>();
        List<String> neighbours = new ArrayList<>();
        for (String node : adj.keySet()) {
            int[] check = new int[V];
            neighbours = adj.get(node);
            for (String neighbour : neighbours) {
                if (verticesColors.containsKey(neighbour)){
                    int indexOfColor = verticesColors.get(neighbour);
                    check[indexOfColor]=1;
                }
            }
            for (int i=0;i<V;i++){
                if (check[i]==0){
                    verticesColors.put(node,i);
                    break;
                }
            }
        }
        for (String vertex : adj.keySet()){
            System.out.println(vertex+" - "+colors[(verticesColors.get(vertex))]);
        }
    }
}

public class ClassScheduleOptimization {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Classes: ");
        String[] classes = sc.nextLine().split(", ");
        Graph schedule = new Graph(classes.length);
        for (String vertex : classes){
            schedule.addVertex(vertex);
        }

        System.out.println("Conflicting classes(cannot occur simultaneously):");
        String line;
        while(sc.hasNextLine() && !(line = sc.nextLine()).isEmpty()) {
            String[] conflictingClasses = line.split("-");
            schedule.addEdge(conflictingClasses[0], conflictingClasses[1]);
        }
        sc.close();

        schedule.Coloring();
    }
}