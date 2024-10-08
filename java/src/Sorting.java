
/*************************************************************************
 *
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
 *  1) https://www.geeksforgeeks.org/insertion-sort-algorithm/
 *  2) https://www.geeksforgeeks.org/quick-sort-algorithm/
 *  3) https://www.geeksforgeeks.org/hoares-vs-lomuto-partition-scheme-quicksort/
 *
 *  Assignment No.: 4
 *
 *  Problem -> To evaluate the perfomance of Quick Sort for different methods of choosing pivot 
 *             and comparing quick sort with the insertion sort for a input of size of 1000000
 *             on the three input arrangement ascending, decending and random
 *
 *  Input and Output -> Mentioned at the end of the file since it was too large 
 *  
 *  ----------------------------------------------------------------------------------------------------
 *
 *  Hypothsis - 
 *
 *  Assumption for the Runtime as a function of input size ->
 *
 *  
 *  HYPOTHICAL RUNTIME ->
 *
 *  Table with running times(in nanoseconds) hypotical for n = 1000000 
 *
 *                                    |  Sorted Increasing   |   Sorted Decreasing  |    Random   
 *
 *  1)  Insertion Sort     
 *  2a) Quick Sort (Left Pivot)      
 *  2b) Quick Sort (Median Pivot)          
 *
 *  -----------------------------------------------------------------------------------
 *  Actual Observation -> 
 *
 *  plot ->   
 *
 *  ACTUAL RUNTIME
 *  Table with running times(in nanoseconds) hypotical for n = 1000000 
 *
 *                                    |  Sorted Increasing   |   Sorted Decreasing  |    Random   
 *
 *  1)  Insertion Sort     
 *  2a) Quick Sort (Left Pivot)      
 *  2b) Quick Sort (Median Pivot)          
 * 
 *
 *  Table of time and space complexities of search operations
 *  
 *                                   | Time Complexity          |  Space Complexity 
 *  1)  Insertion Sort               |                          |
 *  2a) Quick Sort (Left Pivot)      |                          | 
 *  2b) Quick Sort (Median Pivot)    |                          |      
 * 
 *
 *
 *
 *  ------------------------------------------------------------------------------------
 *  
 *
 *  Conclusion -> 
 *      
 *  
 *
 *************************************************************************/

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.List;
import java.util.Collections;

public final class Sorting {

    private Sorting() {
    }

    public static void main(final String... args) {
        final int size = 1_000_000;
        testRuntimeForAscendingArray(size);
        testRuntimeForRandomlyGeneratedArray(size);
        testRuntimeForDescendingArray(size);
    }

    /**
     * This function generates an radom array of given size and tests the runtimes
     * of the three sorting algorithms
     *
     * @param size size of the array
     */
    private static void testRuntimeForRandomlyGeneratedArray(final int size) {

        System.out.println("------------------------------------------------------------------------------");
        System.out.println("\nTesting sort time of Randomly generated array");
        System.out.println("------------------------------------------------------------------------------");

        final int[] arr1 = generateUniqueRandomNumbersArray(size);
        final int[] arr2 = generateUniqueRandomNumbersArray(size);
        final int[] arr3 = generateUniqueRandomNumbersArray(size);

        var startTime = System.nanoTime();
        insertionSort(arr1);
        var endTime = System.nanoTime();
        System.out.println("Time taken for insertion Sort-> " + (endTime - startTime));

        startTime = System.nanoTime();
        quickSortUsingLeftPivot(arr2, 0, size - 1);
        endTime = System.nanoTime();
        System.out.println("Time taken for quick Sort using fixed left pivot -> " + (endTime - startTime));

        startTime = System.nanoTime();
        quickSortUsingMedianPivot(arr3, 0, size - 1);
        endTime = System.nanoTime();
        System.out.println("Time taken for quick Sort using median pivot -> " + (endTime - startTime));

        System.out.println("------------------------------------------------------------------------------");
        System.out.println("\nTesting completed for Randomly generated array");
        System.out.println("------------------------------------------------------------------------------");

    }

    /**
     * This function generates an Ascending array of given size and tests the
     * runtimes of the three sorting algorithms
     * 
     * @param size size of the array
     */
    private static void testRuntimeForAscendingArray(final int size) {

        System.out.println("------------------------------------------------------------------------------");
        System.out.println("\nTesting sort time of Ascending array");
        System.out.println("------------------------------------------------------------------------------");

        final int[] arr1 = generateAscendingArray(size);
        final int[] arr2 = generateAscendingArray(size);
        final int[] arr3 = generateAscendingArray(size);

        var startTime = System.nanoTime();
        insertionSort(arr1);
        var endTime = System.nanoTime();
        System.out.println("Time taken for insertion Sort-> " + (endTime - startTime));

        startTime = System.nanoTime();
        quickSortUsingLeftPivot(arr2, 0, size - 1);
        endTime = System.nanoTime();
        System.out.println("Time taken for quick Sort using fixed left pivot -> " + (endTime - startTime));

        startTime = System.nanoTime();
        quickSortUsingMedianPivot(arr3, 0, size - 1);
        endTime = System.nanoTime();
        System.out.println("Time taken for quick Sort using median pivot -> " + (endTime - startTime));

        System.out.println("------------------------------------------------------------------------------");
        System.out.println("\nTesting completed for Ascending array");
        System.out.println("------------------------------------------------------------------------------");

    }

    /**
     * This function generates an decending array of given size and tests the
     * runtimes of the three sorting algorithms
     * 
     * @param size size of the array
     */
    private static void testRuntimeForDescendingArray(final int size) {

        System.out.println("------------------------------------------------------------------------------");
        System.out.println("\nTesting sort time of descending array");
        System.out.println("------------------------------------------------------------------------------");

        final int[] arr1 = generateDescendingArray(size);
        final int[] arr2 = generateDescendingArray(size);
        final int[] arr3 = generateDescendingArray(size);

        var startTime = System.nanoTime();
        insertionSort(arr1);
        var endTime = System.nanoTime();
        System.out.println("Time taken for insertion Sort-> " + (endTime - startTime));

        startTime = System.nanoTime();
        quickSortUsingLeftPivot(arr2, 0, size - 1);
        endTime = System.nanoTime();
        System.out.println("Time taken for quick Sort using fixed left pivot -> " + (endTime - startTime));

        startTime = System.nanoTime();
        quickSortUsingMedianPivot(arr3, 0, size - 1);
        endTime = System.nanoTime();
        System.out.println("Time taken for quick Sort using median pivot -> " + (endTime - startTime));

        System.out.println("------------------------------------------------------------------------------");
        System.out.println("\nTesting completed for decending array");
        System.out.println("------------------------------------------------------------------------------");

    }

    /**
     *
     * @param arr input array
     */
    private static void insertionSort(final int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            final int key = arr[i];
            var j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j -= 1;
            }
            arr[j + 1] = key;
        }
    }

    /**
     *
     * @param arr  input array
     * @param low  starting position
     * @param high ending position
     */
    private static void quickSortUsingLeftPivot(final int[] arr, final int low, final int high) {
        if (low < high) {
            final int p = partitionUsingHoareSchemeWithDefaultLeftPivot(arr, low, high);
            quickSortUsingLeftPivot(arr, low, p);
            quickSortUsingLeftPivot(arr, p + 1, high);
        }
    }

    /**
     *
     * @param arr  input array
     * @param low  starting position
     * @param high ending position
     */
    private static void quickSortUsingMedianPivot(final int[] arr, final int low, final int high) {
        if (low < high) {
            final int p = partitionUsingHoareSchemeWithMedianPivot(arr, low, high);
            quickSortUsingMedianPivot(arr, low, p);
            quickSortUsingMedianPivot(arr, p + 1, high);
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
    private static int partitionUsingHoareSchemeWithDefaultLeftPivot(final int[] arr, int low, int high) {
        final int pivot = arr[low];
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
     * This function using hoarse partition using that uses a median movable pivot,
     * in this we move from both the direction of the pivot until the condition are
     * true being all to the left of pivot must be smaller and all to the right of
     * the pivot must be greater if this is not true then we swap the values
     * 
     * @param arr  input array
     * @param low  1st position
     * @param high 2nd position
     */
    private static int partitionUsingHoareSchemeWithMedianPivot(final int[] arr, int low, int high) {

        final int pivotIndex = medianOfThree(arr, low, high);
        final int pivot = arr[pivotIndex];

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
     * This function generate the median index out of three index
     * chossen at random from low to high
     * 
     * @param arr  input array
     * @param low  1st position
     * @param high 2nd position
     */
    private static int medianOfThree(final int[] arr, final int low, final int high) {
        final Random random = new Random();

        final int index1 = low + random.nextInt(high - low);
        final int index2 = low + random.nextInt(high - low);
        final int index3 = low + random.nextInt(high - low);

        final int value1 = arr[index1];
        final int value2 = arr[index2];
        final int value3 = arr[index3];

        if ((value1 >= value2 && value1 <= value3) || (value1 <= value2 && value1 >= value3))
            return index1;
        else if ((value2 >= value1 && value2 <= value3) || (value2 <= value1 && value2 >= value3))
            return index2;
        else
            return index3;
    }

    /**
     * This function swaps the value of the given position in the array
     * 
     * @param arr  input array
     * @param low  1st position
     * @param high 2nd position
     */
    private static void swap(final int[] arr, final int low, final int high) {
        final int temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;
    }

    /**
     * This function generates an array unique randoms number from 1 to n
     * 
     * @param n max random value
     * @return Array of unique random number from 1 to n
     */
    private static int[] generateUniqueRandomNumbersArray(final int n) {
        final List<Integer> numbers = IntStream.rangeClosed(1, n).boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);
        final int[] res = new int[n];
        for (int i = 0; i < numbers.size(); i++)
            res[i] = numbers.get(i);
        return res;

    }

    /**
     * This function generate an array of ascending values from 0 to n - 1
     * 
     * @param n max random value
     * @return Array in ascending order from 0 to n - 1
     */
    private static int[] generateAscendingArray(final int n) {
        final int[] res = new int[n];
        for (int i = 0; i < n; i++)
            res[i] = i;
        return res;
    }

    /**
     * This function generates an array of desceding values from n - 1 to 0
     * 
     * @param n max random value
     * @return Array in descending order from n - 1 to 0
     */
    private static int[] generateDescendingArray(final int n) {
        final int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--)
            res[i] = i;
        return res;
    }

}

/*******
 * ozzy@Mac src % java -Xss100m Sorting
 * ------------------------------------------------------------------------------
 * 
 * Testing sort time of Ascending array
 * ------------------------------------------------------------------------------
 * Time taken for insertion Sort-> 7044875
 * Time taken for quick Sort using fixed left pivot -> 716004819500
 * Time taken for quick Sort using median pivot -> 113454208
 * ------------------------------------------------------------------------------
 * 
 * Testing completed for Ascending array
 * ------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------
 * 
 * Testing sort time of Randomly generated array
 * ------------------------------------------------------------------------------
 * Time taken for insertion Sort-> 407248508167
 * Time taken for quick Sort using fixed left pivot -> 140445458
 * Time taken for quick Sort using median pivot -> 182321667
 * ------------------------------------------------------------------------------
 * 
 * Testing completed for Randomly generated array
 * ------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------
 * 
 * Testing sort time of descending array
 * ------------------------------------------------------------------------------
 * Time taken for insertion Sort-> 1876458
 * Time taken for quick Sort using fixed left pivot -> 719148505583
 * Time taken for quick Sort using median pivot -> 96932042
 * ------------------------------------------------------------------------------
 * 
 * Testing completed for decending array
 * ------------------------------------------------------------------------------
 **/
