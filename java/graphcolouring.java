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
        int[] vertexColour = new int[v]; // array to store the colour of each vertex
        String[] colours = { "red", "blue", "yellow", "purple", "green", "orange" };
        Arrays.fill(vertexColour, -1); // initialise the array to -1
        ArrayList<Integer> usedColours = new ArrayList<Integer>(); // stores the colours that have been used in previous
        // vertices
        ArrayList<Integer> availcolours = new ArrayList<Integer>(v); // stores the colours that are available to use
        int i = 0;
        vertexColour[i++] = 0; // assign the first vertex the first colour
        while (IntStream.of(vertexColour).anyMatch(x -> x == -1)) { // run until all vertices have been assigned a
                                                                    // colour
            for (int j = 0; j < v; j++) // initialise available colours with all possible colours
                availcolours.add(j);
            for (int elem : graph.get(i)) { // add every colour used in adjacent vertices
                if (vertexColour[elem] != -1) {
                    usedColours.add(vertexColour[elem]);
                }
            }
            availcolours.removeAll(usedColours); // remove the used colours from the list of available colours
            Collections.sort(availcolours); // sort remaining available colours
            vertexColour[i] = availcolours.get(0); // assign the smallest available colour to the current vertex
            usedColours.clear(); // clear the variables
            availcolours.clear();
            i++; // next vertex
        }
        i = 0;
        for (int a : vertexColour) // prints the colour of each vertex
            System.out.println("Colour of vertex " + i++ + " is " + colours[a]);
        System.out.println("Chromatic number is " + (Arrays.stream(vertexColour).max().getAsInt() + 1));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Number of Vertices");
        int vertices = sc.nextInt(), ch = 1;

        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(vertices);
        for (int i = 0; i < vertices; i++)
            graph.add(new ArrayList<Integer>());
        // while (ch != 0) {
        // System.out.println("Enter the two vertices of the edge");
        // int n = sc.nextInt(), m = sc.nextInt();
        // addEdge(graph, n, m);
        // System.out.println("Enter 0 to stop input of edges\n1 to continue");
        // ch = sc.nextInt();
        // }

        addEdge(graph, 0, 1);
        addEdge(graph, 0, 2);
        // addEdge(graph, 0, 6);
        // addEdge(graph, 0, 7);
        addEdge(graph, 1, 2);
        addEdge(graph, 1, 3);
        // addEdge(graph, 1, 6);
        // addEdge(graph, 1, 7);
        addEdge(graph, 2, 3);
        // addEdge(graph, 2, 5);
        // addEdge(graph, 2, 6);
        // addEdge(graph, 2, 7);
        addEdge(graph, 3, 4);
        // addEdge(graph, 3, 5);
        // addEdge(graph, 3, 6);
        // addEdge(graph, 3, 7);
        colourGraph(graph, vertices);
        // printGraph(graph);
        sc.close();
    }
}
