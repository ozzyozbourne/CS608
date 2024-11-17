
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
 *
 *
 * 
 *  Dfs runtime -> 
 *
 *
 *  Calculated constant value for the Dfs runtime ->  35917    
 *      
 *  HYPOTHICAL RUNTIME -> 
 *  Table with hypotical Dfs runtimes(in nanoseconds) measured for different values of 'V'
 *
 *                       | |E| = |V| - 1  | |E| = ⌊(|V| - 1)^(3/2)⌋  |  |E| = (|V| - 1)^2   |
 *           |V| = 10    |                |                          |                      |   
 *           |V| = 100   |                |                          |                      |
 *           |V| = 1000  |                |                          |                      |
 *
 *  Increase Factor of Dfs for hypotical runtimes ->
 *
 *  -----------------------------------------------------------------------------------
 *
 *  OBSERVATIONS ->  
 *  
 *
 *  ACTUAL RUNTIME -> 
 *  Table with Actual Dfs runtimes(in nanoseconds) measured for different values of 'V'
 *
 *                       | |E| = |V| - 1  | |E| = ⌊(|V| - 1)^(3/2)⌋  |  |E| = (|V| - 1)^2   |
 *           |V| = 10    |                |                          |                      |
 *           |V| = 100   |                |                          |                      |
 *           |V| = 1000  |                |                          |                      |
 *
 *
 *  Table of time and space complexities of search and construction operations
 *  
 *                     | Time Complexity  |  Space Complexity 
 *                DFS  |                  |        
 *
 *  Increase Factor of Dfs for Actual runtimes ->
 *
 *  ------------------------------------------------------------------------------------
 *  
 *  plot ->
 *
 *  Conclusion -> 
 */

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public final class Graphs {

    private Graphs() {
    }

    private static final List<Function<Integer, Integer>> densities = List.of(
            v -> v - 1, // Sparse
            v -> (int) Math.pow(v - 1, 1.5), // Medium
            v -> (v - 1) * (v - 1)); // Dense

    private static final Map<Integer, Integer> discoveryTimes = new HashMap<>();
    private static final Map<Integer, Integer> finishTimes = new HashMap<>();
    private static final Set<Integer> visited = new HashSet<>();
    private static int time = 0;

    public static void main(final String... args) {

        for (final int vertexCount : new int[] { 10, 100, 1000 }) {
            for (final Function<Integer, Integer> density : densities) {

                final int edgeCount = density.apply(vertexCount);
                final Map<Integer, List<Integer>> graph = generateDirectedGraph(vertexCount, edgeCount);

                System.out.printf("\n\nTesting dfs runtime on graph with -> |V| = %4d, |E| = %d\n\n",
                        vertexCount, edgeCount);

                final long startTime = System.nanoTime();
                for (final int vertex : graph.keySet()) {
                    if (!visited.contains(vertex))
                        dfsVisit(graph, vertex);
                }
                final long endTime = System.nanoTime();

                System.out.printf("\nRuntime: %d nanoseconds\n", endTime - startTime);
                System.out.printf("\n\nTesting completed\n\n");

            }
        }
    }

    private static void dfsVisit(final Map<Integer, List<Integer>> graph, final int vertex) {
        visited.add(vertex);
        time += 1;
        discoveryTimes.put(vertex, time);

        for (final int neighbor : graph.get(vertex)) {
            if (!visited.contains(neighbor))
                dfsVisit(graph, neighbor);
        }

        time += 1;
        finishTimes.put(vertex, time);
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
