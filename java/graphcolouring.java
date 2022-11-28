import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.Collections;

public class graphcolouring {
    static void addEdge(ArrayList<ArrayList<Integer>> temp, int u, int v) {
        temp.get(u).add(v);
        temp.get(v).add(u);
    }

    static void printGraph(ArrayList<ArrayList<Integer>> adj) {
        for (int i = 0; i < adj.size(); i++) {
            System.out.println("\nAdjacency list of vertex " + i);
            System.out.print("head");
            for (int j = 0; j < adj.get(i).size(); j++) {
                System.out.print(" -> " + adj.get(i).get(j));
            }
            System.out.println();
        }
    }

    static void colourGraph(ArrayList<ArrayList<Integer>> graph, int v) {
        int[] vertexColour = new int[v];
        ArrayList<Integer> colours = new ArrayList<Integer>();
        Arrays.fill(vertexColour, -1);
        int i = 0;

        vertexColour[i++] = 0;
        while (true) {
            if (IntStream.of(vertexColour).anyMatch(x -> x == -1)) {
                ArrayList<Integer> availcolours = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
                for (int elem : graph.get(i)) {
                    if (vertexColour[elem] != -1) {
                        colours.add(vertexColour[elem]);
                    }
                }
                availcolours.removeAll(colours);
                Collections.sort(availcolours);
                vertexColour[i] = availcolours.get(0);
                colours.clear();
                i++;
            } else {
                break;
            }
        }
        for (int a : vertexColour)
            System.out.println(a);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Number of Vertices");
        int vertices = sc.nextInt(), ch = 1;

        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(vertices);
        for (int i = 0; i < vertices; i++)
            graph.add(new ArrayList<Integer>());
        while (ch != 0) {
            System.out.println("Enter the two vertices of the edge");
            int n = sc.nextInt(), m = sc.nextInt();
            addEdge(graph, n, m);
            System.out.println("Enter 0 to stop input of edges\n1 to continue");
            ch = sc.nextInt();
        }

        // addEdge(graph, 0, 1);
        // addEdge(graph, 0, 4);
        // addEdge(graph, 1, 2);

        // addEdge(graph, 1, 4);
        // addEdge(graph, 2, 3);
        // addEdge(graph, 3, 4);
        colourGraph(graph, vertices);
        printGraph(graph);
        sc.close();
    }
}
