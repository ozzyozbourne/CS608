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
 *  Table with hypotical search times(in nanoseconds) measured for different values of 'n'
 *
 *                       |  n = 10^3   |    n = 10^4   |    n = 10^5    |    n = 10^6      |  n = 10^7
 *
 *  Quick Sort           |             |               |                |                  |  
 *  Bucket Sort          |             |               |                |                  |  
 *
 *
 *
 *  Increase Factor for Quick Sort search hypotical search ->
 *
 *
 *  Increase Factor for Bucket Sort search hypotical search ->
 * 
 *
 *  -----------------------------------------------------------------------------------
 *
 *  OBSERVATIONS ->  
 *  
 *
 *  ACTUAL RUNTIME -> 
 *  Table with hypotical search times(in nanoseconds) measured for different values of 'n'
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
 *  Table of time and space complexities of search and construction operations
 *  
 *                     | Time Complexity          |  Space Complexity 
 *  Quick Sort         | theta (n log n )         |  theta ( log n ) 
 *  Bucket Sort        | theta ()                 |  theta (1)
 *                        
 *
 *  Increase Factor for Quick Sort search actual runtimes ->
 *
 *
 *  Increase Factor for Bucket Sort search actual runtimes ->
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
public final class Assignment5 {

    private Assignment5() {

    }

    public static void main(final String... args) {
        for (final int n : new int[] { 1000, 10_000, 100_000, 1_000_000, 10_000_000 }) {
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
            } while (arr[low] < pivot);

            do {
                high -= 1;
            } while (arr[high] > pivot);

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

    private static double[] randomPoints(final int n) {
        final double[] res = new double[n];
        for (int i = 0; i < n; i++) {

        }
        return res;
    }
}
