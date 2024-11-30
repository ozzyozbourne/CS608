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
 *  References: None
 *
 *  Assignment No.: 6
 *
 *  Problem -> Evaluate the experimentally performance of a DFS alogorithm on different 
 *             different vertex count with different edge densities ie sparse, medium and
 *             dense
 *
 *  Input and Output -> mention at the end of the file since it was too large
 *
 *  Hypothesis  -> The runtime of the DFS depends linearly on the number of vertices + edge count, so 
 *                 as we double the count of vertices, the runtime should double. 
 *                 But for each of the vertices, we have three types of graph edge densities, 
 *                 i.e., sparse(V-1), medium((V-1)^1.5), and dense((V-1)^2). 
 *
 *                 Since here we are dealing with a directed graph that has no self-loops, as we increase 
 *                 the edge count (i.e., making the graph more connected), we will see a decrease in the runtime of DFS
 *                 upto certain density then the runtime we start to increase. 
 *
 *                 This is due to the fact that as we increase the edge it make the graph more connected,
 *                 so when outer for loop calls the recursive dfs function it is able to travel to all the 
 *                 vertex from the starting vertex.
 *
 *                 If is graph is sparsely connected then there are alot of disconnected components, hence the 
 *                 dfs is unable to travel to all the vertex from the starting node and the outer for loop calls 
 *                 dfs again and again since we fragmentation in the graph.
 *
 *                 If is graph is too densely connected the for loop is constant checking the visited set to see 
 *                 current vertex is tranvered or not adding a little extra over head 
 *
 *                 So we have assuming the when we keep the density (sparse, medium and densities) constant 
 *                 and we increase the vertex count the runtime will increase linearly, 
 *                 And when we keep the vertex count constant and increase the density (sparse -> medium -> dense)
 *                 the runtime will be highest for the spare graph and will drop drastically for medium graph 
 *                 and will increase a little bit for dense graph
 *
 *  Mean/Average scaling constant -> The density scaling constant is defined as taking the average of the 
 *                 actual runtime values as we increase the count of the vertex so we are going to have 
 *                 3 density constants.
 *
 *                 For the hypotical runtime we are assuming the number of vertex * density scaling constant to be 
 *                 the hypothetical runtime
 *  
 *  Formae -> 
 *
 *     hypotical runtime =  (number of vertex count * density scaling constant) ns
 *
 *
 *  Calculated scaling constants value for the Dfs runtime ->  
 *
 *              Density    |     Scaling Constant
 *              ----------------------------------------
 *              sparse     |            104148.00
 *              medium     |             17926.11
 *              dense      |             45509.44
 *
 *  HYPOTHICAL RUNTIME ->
 *  Table with hypotical Dfs runtimes(in nanoseconds) measured for different values of 'V'
 *
 *                       | |E| = |V| - 1  | |E| = ⌊(|V| - 1)^(3/2)⌋  |  |E| = (|V| - 1)^2   |
 *           |V| = 10    | 1,041,480.00   | 179,261.11               |  455,094.44          |
 *           |V| = 100   | 10,414,800.00  | 1,792,611.11             |  4,550,944.44        |
 *           |V| = 1000  | 104,148,000.00 | 17,926,111.1             |  45,509,444.44       |
 *
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
 *           |V| = 10    | 28333          | 5417                     |  3709                |
 *           |V| = 100   | 129666         | 16209                    |  17792               |
 *           |V| = 1000  | 779333         | 139709                   |  388084              |
 *
 *
 *  Table of time and space complexities of Dfs search 
 *
 *                     | Time Complexity  |  Space Complexity
 *                DFS  | O(V + E)         |  O(V)
 *
 *
 *  ------------------------------------------------------------------------------------
 *
 *  plot -> https://colab.research.google.com/drive/1mWxRUXbntiVi45AZPnlJqAzSraTq8N-E?usp=sharing
 *
 * ----------------------------------------------------------------------------------------------  
 *
 *  Increase in runtime as we increase the vertex count for sparse, medium and dense graph
 *
 *  Increase factors in sparse graph as we increase the vertex count :
 *      From n^1 to n^2: 4.58   |  ->  approx linear increase
 *      From n^2 to n^3: 6.01   |  -> 
 *
 *  Increase factors in medium graph as we increase the vertex count :
 *      From n^1 to n^2: 2.99   |  ->  approx linear increase
 *      From n^2 to n^3: 8.62   |  -> 
 *
 *  Increase factors in dense graph as we increase the vertex count :
 *      From n^1 to n^2: 4.80   |  ->  approx linear increase 
 *      From n^2 to n^3: 21.81  |  -> 
 *
 * ----------------------------------------------------------------------------------------------  
 *
 *  Increase in runtime as we increase the edge count for 10, 100 and 1000 vertex count graph
 *
 *  Increase factors in 10 vertex graph as we increase the edge count :
 *      From n^1 to n^2: 0.19  -> sharp decrease
 *      From n^2 to n^3: 0.68  -> only a little increase
 *
 *  Increase factors in 100 vertex graph as we increase the edge count :
 *      From n^1 to n^2: 0.13  -> sharp decrease
 *      From n^2 to n^3: 1.10  -> only a little increase
 *
 *  Increase factors in 1000 vertex graph as we increase the edge count :
 *      From n^1 to n^2: 0.18  -> sharp decrease
 *      From n^2 to n^3: 2.78  -> only a little increase
 *
 * ----------------------------------------------------------------------------------------------  
 *
 *  Conclusion -> As expected we see linear increase in the plots when we keep keep the densities 
 *                constant and increase the vertex count, and we increase the density while keepin the 
 *                vertex constant the dfs performance is best for medium density of egdes, this is due to 
 *                fact we dont have to call the recursive dfs function again and again the adjaceny list 
 *                is not for large as in the case of dense graph that we are checking the visted set more 
 *                times adding a small overhead.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public final class Graphs {

    private Graphs() {
    }

    /**
     * Graph types that can be generated:
     * RANDOM_DIRECTED: A directed graph that may or may not contain cycles
     * DAG: A Directed Acyclic Graph (no cycles)
     * CYCLIC: A directed graph guaranteed to contain at least one cycle
     */
    private enum GraphType {
        RANDOM_DIRECTED, // May or may not have cycles
        DAG, // Guaranteed no cycles
        CYCLIC // Guaranteed to have at least one cycle
    }

    private static final List<Function<Integer, Integer>> densities = List.of(
            v -> v - 1, // Sparse
            v -> (int) Math.pow(v - 1, 1.5), // Medium
            v -> (v - 1) * (v - 1)); // Dense
                                     //
    private static final Set<Integer> recursionStack = new HashSet<>();
    private static final Map<Integer, Integer> discoveryTimes = new HashMap<>();
    private static final Map<Integer, Integer> finishTimes = new HashMap<>();
    private static final Set<Integer> visited = new HashSet<>();
    private static int time = 0;

    public static void main(final String... args) {
        RandomGraphTest();
        ExtraCredit();
    }

    private static void RandomGraphTest() {
        for (final int vertexCount : new int[] { 10, 100, 1000 }) {
            for (final Function<Integer, Integer> density : densities) {
                final int edgeCount = density.apply(vertexCount);
                final Map<Integer, List<Integer>> graph = generateGraph(vertexCount, edgeCount,
                        GraphType.RANDOM_DIRECTED);

                System.out.printf("\n\nTesting dfs runtime on graph with -> |V| = %4d, |E| = %d\n\n", vertexCount,
                        edgeCount);

                final long startTime = System.nanoTime();
                for (final int vertex : graph.keySet()) {
                    if (!visited.contains(vertex))
                        dfsVisit(graph, vertex);
                }
                final long endTime = System.nanoTime();
                final int travelTime = Collections.max(finishTimes.values()) - 1; // start time is always one

                System.out.printf("Runtime: %10d ns DiscoveryTime - FinishTime: %d", endTime - startTime, travelTime);
                System.out.printf("\n\nTesting completed\n\n");
                resetCollections();
            }
        }

    }

    private static void ExtraCredit() {
        // Extra credit
        final int vertexCount = 1001;
        for (final GraphType type : List.of(GraphType.DAG, GraphType.CYCLIC)) {
            for (final Function<Integer, Integer> density : densities) {
                var hasCycle = true;

                final int edgeCount = density.apply(vertexCount);
                final Map<Integer, List<Integer>> graph = generateGraph(density.apply(vertexCount), edgeCount, type);

                System.out.printf("\n\nTesting dfs runtime on %7s graph with -> |V| = %4d, |E| = %d\n\n", type,
                        vertexCount,
                        edgeCount);
                final long startTime = System.nanoTime();
                for (final int vertex : graph.keySet()) {
                    if (!visited.contains(vertex) && hasCycle)
                        hasCycle = dfsDetectCycle(graph, vertex);
                }
                final long endTime = System.nanoTime();
                final int travelTime = Collections.max(finishTimes.values()) - 1;

                System.out.printf("Runtime: %10d ns DiscoveryTime - FinishTime: %d has cycle: %b", endTime - startTime,
                        travelTime, hasCycle);
                System.out.printf("\n\nTesting completed\n\n");
                resetCollections();
            }
        }

    }

    private static void resetCollections() {
        discoveryTimes.clear();
        finishTimes.clear();
        visited.clear();
        recursionStack.clear();
        time = 0;
    }

    private static void dfsVisit(
            final Map<Integer, List<Integer>> graph,
            final int vertex) {
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

    private static Map<Integer, List<Integer>> generateGraph(final int vertexCount, final int edgeCount,
            final GraphType type) {
        final Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        final Random rand = new Random();
        final Set<String> addedEdges = new HashSet<>();

        for (int i = 0; i < vertexCount; i++)
            adjacencyList.put(i, new ArrayList<>());

        var edgesAdded = 0;

        // For CYCLIC type, first create a guaranteed cycle
        if (type == GraphType.CYCLIC) {
            // First, add a guaranteed cycle
            final int cycleSize = 3 + rand.nextInt(5); // Random cycle size between 3 and 7
            for (int i = 0; i < cycleSize - 1; i++) {
                adjacencyList.get(i).add(i + 1);
                addedEdges.add(i + "," + (i + 1));
            }
            // Complete the cycle
            adjacencyList.get(cycleSize - 1).add(0);
            addedEdges.add((cycleSize - 1) + ",0");

            edgesAdded = cycleSize;
        }

        while (edgesAdded < edgeCount) {
            final int source = rand.nextInt(vertexCount);
            final int target = rand.nextInt(vertexCount);

            switch (type) {
                case RANDOM_DIRECTED, CYCLIC -> {
                    if (source == target)
                        continue;
                }
                case DAG -> {
                    if (source >= target)
                        continue;
                }
            }
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

    private static boolean dfsDetectCycle(final Map<Integer, List<Integer>> graph, final int vertex) {
        if (!visited.contains(vertex)) {
            visited.add(vertex);
            recursionStack.add(vertex);

            for (final int neighbor : graph.get(vertex)) {
                if (!visited.contains(neighbor) && dfsDetectCycle(graph, neighbor))
                    return true;
                else if (recursionStack.contains(neighbor))
                    return true;
            }
        }
        recursionStack.remove(vertex);
        return false;
    }
}
