
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
        final int size = 100;
        final int[] arr1 = generateUniqueRandomNumbersArray(size);
        final int[] arr2 = generateUniqueRandomNumbersArray(size);

        System.out.println("Before Sorting:");
        System.out.println("Array for Quick Sort (Left Pivot):");
        printArray(arr1);
        System.out.println("Array for Quick Sort (Median Pivot):");
        printArray(arr2);

        quickSortUsingLeftPivot(arr1, 0, size - 1);
        quickSortUsingMedianPivot(arr2, 0, size - 1);

        System.out.println("\nAfter Sorting:");
        System.out.println("Array sorted by Quick Sort (Left Pivot):");
        printArray(arr1);
        System.out.println("Array sorted by Quick Sort (Median Pivot):");
        printArray(arr2);
    }

    private static void printArray(final int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    private static void quickSortUsingLeftPivot(final int[] arr, final int low, final int high) {
        if (low < high) {
            final int p = partitionUsingLumotoScheme(arr, low, high);
            quickSortUsingLeftPivot(arr, low, p);
            quickSortUsingLeftPivot(arr, p + 1, high);
        }
    }

    private static void quickSortUsingMedianPivot(final int[] arr, final int low, final int high) {
        if (low < high) {
            final int p = partitionUsingHoareScheme(arr, low, high);
            quickSortUsingMedianPivot(arr, low, p);
            quickSortUsingMedianPivot(arr, p + 1, high);
        }
    }

    private static int partitionUsingLumotoScheme(final int[] arr, int low, int high) {
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

    private static int partitionUsingHoareScheme(final int[] arr, int low, int high) {

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

    private static void swap(final int[] arr, final int low, final int high) {
        final int temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;
    }

    /**
     * This function generated list of unique randoms number from 1 to n
     * 
     * @param n max random value
     * @return List of unique random number from 1 to n
     */
    private static int[] generateUniqueRandomNumbersArray(final int n) {
        final List<Integer> numbers = IntStream.rangeClosed(1, n).boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);
        final int[] res = new int[n];
        for (int i = 0; i < numbers.size(); i++)
            res[i] = numbers.get(i);
        return res;

    }

}
