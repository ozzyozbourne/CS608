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
 *  Assignment No.: 5
 *
 *  Problem -> 
 *
 *  Input and Output -> Mentioned at the end of the file since it was too large 
 *
 *  Hypothesis -> 
 *
 *  Assumption for the runtimes as a function of the input size
 *
 *  HYPOTHICAL RUNTIME -> 
 *  Table with hypotical actual times(in nanoseconds) measured for different values of 'n'
 *
 *                       |  n = 10^3   |    n = 10^4   |    n = 10^5    |    n = 10^6      |  n = 10^7
 *
 *  Quick Sort           |             |               |                |                  |  
 *  Bucket Sort          |             |               |                |                  |  
 *
 *
 *
 *  Increase Factor for Quick Sort hypotical search ->
 *
 *
 *  Increase Factor for Bucket Sort hypotical search ->
 * 
 *
 *  -----------------------------------------------------------------------------------
 *
 *  OBSERVATIONS ->  
 *  
 *
 *  ACTUAL RUNTIME -> 
 *  Table with actual sort times(in nanoseconds) measured for different values of 'n'
 *
 *                       |  n = 10^3   |    n = 10^4   |    n = 10^5    |    n = 10^6      |  n = 10^7
 *
 *  Quick Sort           |             |               |                |                  |  
 *  Bucket Sort          |             |               |                |                  |  
 *
 *
 *
 *
 *
 *  Table of time and space complexities of sort operations
 *  
 *                     | Time Complexity          |  Space Complexity 
 *  Quick Sort         | theta (n log n )         |  theta ( log n ) 
 *  Bucket Sort        | theta ()                 |  theta (1)
 *                        
 *
 *  Increase Factor for Quick Sort actual runtimes ->
 *
 *
 *  Increase Factor for Bucket Sort actual runtimes ->
 *  ------------------------------------------------------------------------------------
 *  
 *  plot ->   
 *  
 *
 *  Conclusion ->  
 *
 *
 *
 *
 *
 */

import java.util.Random;

public final class Assignment5 {

    /*
     * Since here we are comparing floating point numbers so regular comparison
     * operators will not work properly since
     * hence
     * If on subtracting two numbers (a, b) thier Mod |difference| is less than 10^7
     * then are equal
     * If on subtracting two numbers (a, b) thier difference is greater than 10^7
     * then a is greater
     * If on subtracting two numbers (a, b) thier difference is less than -10^7
     * then a is smaller
     *
     */
    private static final double EPSILON = 1e-7;

    private Assignment5() {
    }

    public static void main(final String... args) {
        for (final int n : new int[] { 1000, 10_000, 100_000, 1_000_000, 10_000_000 }) {

            System.out.println("------------------------------------------------------------------------------");
            System.out.println("\nTesting sort time of Quick sort and Bucket Sort of size -> " + n + "\n");
            System.out.println("------------------------------------------------------------------------------");

            final double[] arr1 = generateUniformDistances(n);
            final double[] arr2 = generateUniformDistances(n);

            System.out.println("Sorting using Quick sort");
            var startTime = System.nanoTime();
            quickSortUsingLeftPivot(arr1, 0, n - 1);
            var endTime = System.nanoTime();
            System.out.println("Time taken in nano seconds -> " + (endTime - startTime));

            System.out.println();

            System.out.println("Sorting using Bucket sort");
            startTime = System.nanoTime();
            endTime = System.nanoTime();
            System.out.println("Time taken in nano seconds -> " + (endTime - startTime));

            System.out.println("------------------------------------------------------------------------------");
            System.out.println("\nTesting completed Quick sort and Bucket Sort of size -> " + n + "\n");
            System.out.println("------------------------------------------------------------------------------");

        }
    }

    /**
     * This function implements the quick sort using left pivot approch
     *
     * @param arr  input array
     * @param low  starting position
     * @param high ending position
     */
    private static void quickSortUsingLeftPivot(final double[] arr, final int low, final int high) {
        if (low < high) {
            final int p = partitionUsingHoareSchemeWithDefaultLeftPivot(arr, low, high);
            quickSortUsingLeftPivot(arr, low, p);
            quickSortUsingLeftPivot(arr, p + 1, high);
        }
    }

    /**
     * This function using hoarse partition using that uses a left movable pivot,
     * in this we move from both the direction of the pivot until the condition are
     * true being all to the left of pivot must be smaller and all to the right of
     * the pivot must be greater if this is not true then we swap the values
     *
     * @param arr  input array
     * @param low  1st position
     * @param high 2nd position
     */
    private static int partitionUsingHoareSchemeWithDefaultLeftPivot(final double[] arr, int low, int high) {
        final double pivot = arr[low];
        low -= 1;
        high += 1;
        while (true) {

            do {
                low += 1;
            } while (arr[low] - pivot < -EPSILON); // a < b becomes (a - b) < -EPSILON due to floating point arithmatic

            do {
                high -= 1;
            } while (arr[high] - pivot > EPSILON); // a > b becomes (a - b) > EPSILON due to floating point arithmatic
            if (low >= high)
                return high;

            swap(arr, low, high);
        }

    }

    /**
     * This function swaps the value of the given position in the array
     * 
     * @param arr  input array
     * @param low  1st position
     * @param high 2nd position
     */
    private static void swap(final double[] arr, final int low, final int high) {
        final double temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;
    }

    /**
     * This function generate a uniform distribution of distances within a unit
     * circle
     * 
     * @param n number of distances to be generated
     * @return array of n distances
     *
     */
    private static double[] generateUniformDistances(final int n) {
        final double[] distances = new double[n];
        final Random rand = new Random();

        for (int i = 0; i < n; i++) {
            // Generat random an angle in radians (ie get a random angle from 0.0
            // (inclusive) to 360(exclusive))
            final double t = 2 * Math.PI * rand.nextDouble();

            // Generate radius using the algorithm given
            final double u = rand.nextDouble() + rand.nextDouble();
            final double r = u > 1 ? 2 - u : u;

            // Converting polar co-ordinates to cartesian co-ordinates
            final double x = r * Math.cos(t);
            final double y = r * Math.sin(t);

            // Calculate distance from origin
            distances[i] = Math.sqrt(x * x + y * y);
        }
        return distances;
    }
}
