
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
 *  Hypothsis ->
 *   
 *  Insertion sort is the least optimal to sort an given array since we iterate through the array once, 
 *  and for each iteration, we choose the current as the key and sort every thing to its left make the runtime 
 *  n ^ 2.
 *
 *  Quick sort improves upon this by using a partition method that run once through the array sorting using a 
 *  pivot, and returning a new pivot, which is used by the recursve quick sort methods to break the array in two, 
 *  calling the partition method again, hence replacing outer loop by recursion to control the number of times 
 *  we call the partition method, this reduces the time complexity of the outer procedure to log n since we are using 
 *  a recursion, so the idea is the replace the outer loop by a recursion hence reducing the number of times 
 *  we have to call the partition function since its an expensive linear time opertion, so we have choose our pivot 
 *  skillfully inorde to sort the array by only calling the partition function log n time using recursion, hence making 
 *  quick sort n * log n operation by also makes in dependant on the way we choose pivot since if the pivot 
 *  is choosen naively ie always left or right, make the algorithm depandent on the arragment of the input since 
 *  now the recursive (outer loop) will end up calling the theta (n) partition function n time making it similar 
 *  to insertion with the added overhead of recursive stack space degrading its runtime worst than insertion sort   
 *
 *
 *  Assumption for the Runtime as a function of input size -> 
 *
 *  For Input array sorted in Ascending order -> 
 *
 *  Insertion sort -> 
 *  Since input array is already sorted hence the inner while loop will not run, making the runtime dependant 
 *  on the outer for loop ie theta (n) 
 * 
 *
 *  Quick sort (left pivot) -> 
 *  Since the array is already sorted the and pivot is also way the left most element hence the partition function 
 *  will return the pivot itself since the everyting to left's right is already sorted, hence the new return pivot 
 *  will be the left most element since recursion will then up calling the partition method n time making the runtime 
 *  theta(n^2)
 *                             
 *  Quick sort (median of random) -> 
 *  Inspite of the array being sorted, since we are choosing the pivot as random of median of three making it some 
 *  what invariant on the arrangment of input, due to the fact the median pivot might end being the middle element 
 *  of the low and high, hence reducing the depth of the recursion tree make the runtime theta (n log n)
 *
 *
 *  For Input array sorted in Descending order -> 
 *
 *  Insertion sort -> This will make the both the loops run making the time complexicty theta (n^2) 
 *
 *  Quick sort (left pivot) -> 
 *  Here the runtime will be theta (n^2) due to fact that we the partition method chooses the pivot as the the low 
 *  hence the partition method will end part swaping all the elements since, and returning the last element as the new pivot
 *  make the quick sort procedure to n recursive calls, hence the runtime will be n ^ 2 
 *
 *  Quick sort (median of random) -> 
 *  As mentioned above since we choose median of random of three, this makes the it invariant on the arrangement input 
 *  since there is a high change that pivot is middle element of low and high make the quick sort procedure calling 
 *  partition function log n times, ie theta (n log n) runtime
 *
 *
 *  For Input array in Random order -> 
 *
 *  Insertion sort -> 
 *  Since the array is randomly generated hence we can rule out the possiblity the array will be 
 *  completing in ascending order, the average, worst and best case of insertion sort have theta n^2 runtime 
 *  so the time complexcity will be theta (n^2)
 * 
 *
 *  Quick sort (left pivot) -> 
 *  Since the array is randomly generated hence we can rule out the possiblity the array will be completly 
 *  in ascending or descending order since we cannot have Big O n^2 as a runtime so since best worst average 
 *  cases are all same here the runtime will be theta (n log n)
 *  
 *  Quick sort (median of random) -> 
 *  As mentioned above since we choose median of random of three, this makes the it invariant on the arrangement input 
 *  since there is a high change that pivot is middle element of low and high make the quick sort procedure calling 
 *  partition function log n times, ie theta (n log n) runtime

 *
 *  -----------------------------------------------------------------------------------
 *  Actual Observation -> 
 *
 *
 *  ACTUAL RUNTIME
 *  Table with running times(in nanoseconds) for n = 1_000_000 
 *
 *                                    |  Sorted Increasing   |   Sorted Decreasing  |    Random   
 *
 *  1)  Insertion Sort                |  7044875             |   1876458            |    407248508167
 *  2a) Quick Sort (Left Pivot)       |  716004819500        |   719148505583       |    140445458 
 *  2b) Quick Sort (Median Pivot)     |  113454208           |   96932042           |    182321667   
 * 
 *
 *  Table of time and space complexities of sort operations
 *  
 *  Sorted Ascending                 | Time Complexity          |  Space Complexity 
 *  1)  Insertion Sort               | Theta(n)                 | theta(1)
 *  2a) Quick Sort (Left Pivot)      | Theta(n^2)               | theta(n) 
 *  2b) Quick Sort (Median Pivot)    | Theta(n log n)           | theta(log n)     
 * 
 *
 *  Sorted descending                | Time Complexity          |  Space Complexity 
 *  1)  Insertion Sort               | Theta(n^2)               | theta(1)
 *  2a) Quick Sort (Left Pivot)      | Theta(n^2)               | theta(n) 
 *  2b) Quick Sort (Median Pivot)    | Theta(n log n)           | theta(log n)     
 *
 *
 *  Random                           | Time Complexity          |  Space Complexity 
 *  1)  Insertion Sort               | theta(n^2)               | theta(1)
 *  2a) Quick Sort (Left Pivot)      | Theta(n log n)           | theta(log n)
 *  2b) Quick Sort (Median Pivot)    | Theta(n log n)           | theta(log n)     
 *
 *
 *
 *
 *  ------------------------------------------------------------------------------------
 *  
 *
 *  Conclusion -> 
 *
 *  For Input in Ascending order -> 
 *
 *  Insertion sort is the fastest amongst the three since it is simply iterating through the array in 
 *  theta (n) time due the inner while loop not activating
 *
 *  Quick Sort with left pivot has the highest runtime due the choice of pivot being fixed ie left, 
 *  resulting in unbalanced partition being formed making the quick sort recursion calls equal to n(size of the input array),  
 *  each of them calling partition function hence giving it a runtime of theta (n ^ 2) also having a recursive 
 *  call stack space overhead of n
 *
 *  Quick sort with randomized median pivot performs a lot better than Quick sort with left pivot, 
 *  due to choice of the median pivot, producing balanced partitions giving it a runtime of 
 *  theta (n log n)
 *
 *
 *  For input in Descending order -> 
 *
 *  Insertion sort is the fastest here despite having the runtime of theta (n ^ 2), this discrepence would be 
 *  due to CPU Branch prediction and caching, also insertion sort has no recursion so the JIT compilier is able 
 *  to optimize simply loops, improving performance further.
 *
 *  Quick sort with left pivot has the highest runtime since due to poor choice of pivot producing un-balanced 
 *  partitions giving it a runtime of theta (n ^ 2), also having a recursive call stack space overhead of n. 
 *
 *  Quick sort with randomized median pivot performs a lot better than Quick sort with left pivot, 
 *  due to choice of the median pivot, producing balanced partitions giving it a runtime of 
 *  theta (n log n)
 *
 *
 *  Randomized input 
 *   
 *  Insertion Sort performs the worst of the three due to the fact that it that it has theta (n ^ 2) 
 *  runtime agreeing with our Hypothsis
 *
 *  Quick Sort with left pivot performs fastest here since the input array is random making it a high 
 *  possiblity the on choosing the left element as pivot we would be able to produce balance 
 *  partitions without the need for a finding a median pivot, due to the nature of the input array
 *  hence the runtime here is theta (n log n )
 *
 *  Quick sort with randomized median pivot performs a lot better than insertion sort , but is slower by 
 *  10 ^ 6 nanoseconds in comparison to other quick since calculating the median is adding extra overhead 
 *  We can get away by choose a fixed right or left pivot here, due to the nature of the input array 
 *  being random. hence the runtime here is theta (n log n ) inspite being slower that other quick sort 
 *  since median of three calculation is a constant operation

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
     * This function implements the insertion sort algorithm
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
     * This function implements the quick sort using left pivot approch
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
     * This function implements the quick sort using median of random pivot approch
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
