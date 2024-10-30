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
 *  1) https://www.geeksforgeeks.org/bucket-sort-2/
 *
 *  Assignment No.: 5
 *
 *  Problem -> Test quick and bucket sorting runtimes using a uniformly distributed random input
 *
 *  Input and Output -> Mentioned at the end of the file since it was too large 
 *
 *  Hypothesis -> 
 *
 *
 *
 *  Mean/Average scaling constant -> 
 *      Ideally the runtime equal to the size of input for the bucket sort since the 
 *      input is randomly distributed making the runtime theta (n + k) (k is the size 
 *      the buckets being generates) and theta (n log n) for the quick sort, hence to 
 *      account for the constant operation being formed we will used a Mean/Average
 *      scaling constant thst is a average of  sum the actual runtime divided by the 
 *      size of the input array.
 *
 *   Calculated Average scaling constant for Quick Sort: 137
 *   Calculated Average scaling constant for Bucket Sort: 234
 *
 *  Assumption for the runtimes as a function of the input size
 *
 *  HYPOTHICAL RUNTIME -> 
 *  Table with hypotical actual times(in nanoseconds) measured for different values of 'n'
 *
 *                       | n = 10^3   | n = 10^4   | n = 10^5    | n = 10^6      | n = 10^7
 *
 *  Quick Sort           | 1,361,162  | 18,148,827 | 226,860,333 | 2,722,323,999 | 31,760,446,653 
 *  Bucket Sort          | 234,188    | 2,341,877  | 23,418,766  | 234,187,661   | 2,341,876,607  
 *
 *
 *
 *  Increase Factor for Quick Sort hypotical search ->
 *  
 *  From n^3 to n^4: 13.33
 *  From n^4 to n^5: 12.50  -> Shows n log n sort time
 *  From n^5 to n^6: 12.00
 *  From n^6 to n^7: 11.67
 *
 *  Increase Factor for Bucket Sort hypotical search ->
 *  
 *  From n^3 to n^4: 10.00
 *  From n^4 to n^5: 10.00 -> Shows linear increase in sort time 
 *  From n^5 to n^6: 10.00 
 *  From n^6 to n^7: 10.00
 *
 *  -----------------------------------------------------------------------------------
 *
 *  OBSERVATIONS ->  
 *  
 *
 *  ACTUAL RUNTIME -> 
 *  Table with actual sort times(in nanoseconds) measured for different values of 'n'
 *
 *                       | n = 10^3   | n = 10^4   | n = 10^5    | n = 10^6      | n = 10^7
 *
 *  Quick Sort           | 268583     | 1170916    | 9458541     | 95680708      | 1069769333 
 *  Bucket Sort          | 599625     | 2664166    | 10598292    | 105192042     | 937217416 
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
 *  From n^3 to n^4: 4.36  ->  better than n logn increase in sort times possible due to caching
 *  From n^4 to n^5: 8.08  ->  shows approximately nlogn increase in sort time as expected
 *  From n^5 to n^6: 10.12 ->  shows approximately nlogn increase in sort time as expected
 *  From n^6 to n^7: 11.18 ->  shows approximately nlogn increase in sort time as expected 
 *  
 *  Increase Factor for Bucket Sort actual runtimes ->
 *
 *  From n^3 to n^4: 4.44 -> better since linear increase possible due to caching
 *  From n^4 to n^5: 3.98 -> better since linear increase possible due to caching
 *  From n^5 to n^6: 9.93 -> shows linear increase in time as expected
 *  From n^6 to n^7: 8.91 -> shows linear increase in time as expected
 *
 *  ------------------------------------------------------------------------------------
 *  
 *  plot -> https://colab.research.google.com/drive/1ab8eAEYDGmszOuRcChhNjbLZKO4qKOcx?usp=sharing  
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
import java.util.ArrayList;
import java.util.List;

public final class Assignment5 {

    /*
     * Since here we are comparing floating point numbers so regular comparison
     * operators will not work properly since
     * hence
     * 
     * If on subtracting two numbers (a, b) their Mod |difference| is less than 10^7
     * then are equal
     * 
     * If on subtracting two numbers (a, b) their difference is greater than 10^7
     * then a is greater
     * 
     * If on subtracting two numbers (a, b) their difference is less than -10^7
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
            final double[] arr2 = arr1.clone();

            System.out.println("Sorting using Quick sort");
            var startTime = System.nanoTime();
            quickSortUsingLeftPivot(arr1, 0, n - 1);
            var endTime = System.nanoTime();
            System.out.println("Time taken in nano seconds -> " + (endTime - startTime));

            System.out.println();

            System.out.println("Sorting using Bucket sort");
            startTime = System.nanoTime();
            bucketSort(arr2);
            endTime = System.nanoTime();
            System.out.println("Time taken in nano seconds -> " + (endTime - startTime));

            System.out.println("------------------------------------------------------------------------------");
            System.out.println("\nTesting completed Quick sort and Bucket Sort of size -> " + n + "\n");
            System.out.println("------------------------------------------------------------------------------");

        }
    }

    /**
     * This function implements bucket sort for sorting distances in range [0,1]
     * using √n buckets and quicksort to sort individual buckets
     * 
     * @param arr input array of distances
     */
    private static void bucketSort(final double[] arr) {

        // Number of buckets will be square root of n
        final int numBuckets = (int) Math.sqrt(arr.length);

        final List<List<Double>> buckets = new ArrayList<>(numBuckets);

        // Initialize all buckets
        for (int i = 0; i < numBuckets; i++)
            buckets.add(new ArrayList<>());

        // Find the maximum and minimum values to know the range
        double maxValue = arr[0], minValue = arr[0];
        for (double value : arr) {
            if (value - maxValue > EPSILON)
                maxValue = value;

            if (minValue - value > EPSILON)
                minValue = value;
        }

        final double interval = (maxValue - minValue + 1) / numBuckets;

        // Distribute input array values into buckets
        for (final double value : arr) {
            var bucketIndex = (int) ((value - minValue) / interval);
            // Handle edge case where value == maxValue
            if (bucketIndex == numBuckets)
                bucketIndex--;
            buckets.get(bucketIndex).add(value);
        }

        // Sort individual buckets and concatenate them
        var index = 0;
        for (List<Double> bucket : buckets) {
            final double[] temp = new double[bucket.size()];
            for (int i = 0; i < bucket.size(); i++)
                temp[i] = bucket.get(i);
            quickSortUsingLeftPivot(temp, 0, temp.length - 1);
            for (final double value : temp) {
                arr[index] = value;
                index += 1;
            }
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

/*
 * ozzy@Mac src % java -Xss100m Assignment5
 * -----------------------------------------------------------------------------
 *
 * 
 * Testing sort time of Quick sort and Bucket Sort of size -> 1000
 * 
 * -----------------------------------------------------------------------------
 *
 * Sorting using Quick sort
 * Time taken in nano seconds -> 268583
 * 
 * Sorting using Bucket sort
 * Time taken in nano seconds -> 599625
 * -----------------------------------------------------------------------------
 *
 * 
 * Testing completed Quick sort and Bucket Sort of size -> 1000
 * 
 * -----------------------------------------------------------------------------
 *
 * -----------------------------------------------------------------------------
 * 
 * 
 * Testing sort time of Quick sort and Bucket Sort of size -> 10000
 * 
 * -----------------------------------------------------------------------------
 *
 * Sorting using Quick sort
 * Time taken in nano seconds -> 1170916
 * 
 * Sorting using Bucket sort
 * Time taken in nano seconds -> 2664166
 * -----------------------------------------------------------------------------
 *
 * 
 * Testing completed Quick sort and Bucket Sort of size -> 10000
 * 
 * -----------------------------------------------------------------------------
 *
 * -----------------------------------------------------------------------------
 *
 * 
 * Testing sort time of Quick sort and Bucket Sort of size -> 100000
 * 
 * -----------------------------------------------------------------------------
 *
 * Sorting using Quick sort
 * Time taken in nano seconds -> 9458541
 * 
 * Sorting using Bucket sort
 * Time taken in nano seconds -> 10598292
 * -----------------------------------------------------------------------------
 *
 * 
 * Testing completed Quick sort and Bucket Sort of size -> 100000
 * 
 * -----------------------------------------------------------------------------
 *
 * -----------------------------------------------------------------------------
 *
 * 
 * Testing sort time of Quick sort and Bucket Sort of size -> 1000000
 * 
 * -----------------------------------------------------------------------------
 *
 * Sorting using Quick sort
 * Time taken in nano seconds -> 95680708
 * 
 * Sorting using Bucket sort
 * Time taken in nano seconds -> 105192042
 * -----------------------------------------------------------------------------
 *
 * 
 * Testing completed Quick sort and Bucket Sort of size -> 1000000
 * 
 * -----------------------------------------------------------------------------
 *
 * -----------------------------------------------------------------------------
 * 
 * 
 * Testing sort time of Quick sort and Bucket Sort of size -> 10000000
 * 
 * -----------------------------------------------------------------------------
 * 
 * Sorting using Quick sort
 * Time taken in nano seconds -> 1069769333
 * 
 * Sorting using Bucket sort
 * Time taken in nano seconds -> 937217416
 * -----------------------------------------------------------------------------
 * 
 * 
 * Testing completed Quick sort and Bucket Sort of size -> 10000000
 * 
 * -----------------------------------------------------------------------------
 *
 * ozzy@Mac src %
 */
