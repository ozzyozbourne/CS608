
/*
 *  Pace University
 *  Fall 2024
 *  Algorithms and Computing Theory
 *
 *  Course: CS 608
 *  Team members:
 *  1) Adam Miftahelidrissi
 *  2) Marlen Cuevas Duarte
 *  3) Osaid Khan
 *
 *  Other collaborators: None.
 *  References:
 *
 *  Assignment No.: 6
 *
 *  Problem ->
 *
 *  Input and Output ->
 *
 *  Hypothesis -> 
 *
 *  Assumption for the runtimes as a function of the input size
 *
 *  Construction Time -> 
 *
 *
 * 
 *  Search Time -> 
 *
 * 
 *  Calculated scaling constant for the hast-table creation -> 721
 *
 *  Calculated constant value for the search time ->  35917    
 *      
 *  HYPOTHICAL RUNTIME -> 
 *  Table with hypotical construction and search times(in nanoseconds) measured for different values of 'n'
 *
 *                       |  n = 10^2   |    n = 10^3   |    n = 10^4    |    n = 10^5      |  n = 10^6
 *
 *
 *
 *
 *  Increase Factor for creation of hastable for hypotical runtimes ->
 *
 *  -----------------------------------------------------------------------------------
 *
 *  OBSERVATIONS ->  
 *  
 *
 *  ACTUAL RUNTIME -> 
 *  Table with Actual construction and search times(in nanoseconds) measured for different values of 'n'
 *
 *                       |  n = 10^2   |    n = 10^3   |    n = 10^4    |    n = 10^5      |  n = 10^6
 *
 *
 *  Table of time and space complexities of search and construction operations
 *  
 *                     | Time Complexity          |  Space Complexity 
 *
 *  Increase Factor for creation of hastable for Actual runtimes ->
 *
 *  Increase factors for searching in hashtable for Actual runtimes ->
 *
 *  ------------------------------------------------------------------------------------
 *  
 *  plot ->
 *
 *  Conclusion -> 
 */

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public final class Graphs {

    private Graphs() {
    }

    public static void main(final String... args) {

        enum EdgeDensity {
            SPARSE, // |E| = |V| - 1
            MEDIUM, // |E| = ⌊ (|V| - 1)^(3/2) ⌋
            DENSE // |E| = (|V| - 1)^2
        }

        for (final int vertexCount : new int[] { 10, 100, 1000 }) {
            for (final EdgeDensity density : EdgeDensity.values()) {
                final int edgeCount = switch (density) {
                    case SPARSE -> vertexCount - 1;
                    case MEDIUM -> (int) Math.pow(vertexCount - 1, 1.5);
                    case DENSE -> (vertexCount - 1) * (vertexCount - 1);
                };

                final Map<Integer, List<Integer>> graph = generateDirectedGraph(vertexCount, edgeCount);

                System.out.printf("\n\nTesting dfs runtime on graph with -> |V| = %4d, density = %6s, |E| = %d\n\n",
                        vertexCount, density, edgeCount);

                System.out.printf("\n\nTesting completed\n\n");

            }
        }
    }

    public static Map<Integer, List<Integer>> generateDirectedGraph(final int vertexCount, final int edgeCount) {
        final Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        final Random rand = new Random();
        final Set<String> addedEdges = new HashSet<>();

        for (int i = 0; i < vertexCount; i++)
            adjacencyList.put(i, new ArrayList<>());

        var edgesAdded = 0;

        while (edgesAdded < edgeCount) {
            final int source = rand.nextInt(vertexCount);
            final int target = rand.nextInt(vertexCount);

            // Skip self-loops
            if (source == target)
                continue;

            // Check if edge already exists
            final String edge = source + "," + target;
            if (!addedEdges.contains(edge)) {
                adjacencyList.get(source).add(target);
                addedEdges.add(edge);
                edgesAdded += 1;
            }
        }
        return adjacencyList;
    }
}
