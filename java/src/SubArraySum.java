
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
 *  1) https://www.geeksforgeeks.org/maximum-subarray-sum-using-divide-and-conquer-algorithm/
 *  2) https://neetcode.io/courses/advanced-algorithms/0
 *
 *  Assignment No.: 1
 *
 *  Problem ->
 *  For an arbitrary input array of length n, two algorithms that compute
 *  the sum of the maximum subarray were discussed in class:
 *  (a) a brute-force algorithm that solves the problem in O(n2) steps, and
 *  (b) a divide-and- conquer algorithm that achieves O(n log n) running time.
 *  (c) a linear time O(n) using Kadane algo for extra credit 
 *
 *  Input and Output ->
 *  
 *  -----------------------------------------------------------------
 *  FIRST RUN WITH THE MAX INPUT SIZE IE N^7
 *
 *  ------------------------------------------------------------------- 
 *  Enter the size of the array
 *  10000000
 *  The size of the array will be -> 10000000
 *  ****************************************************
 *
 *  Brute Force method
 *  Time taken in nano seconds -> 27656933123708
 *  The output is -> SumAndIndex[l=2416491, h=2440620, sum=23055]
 *
 *  ****************************************************
 *
 *  Divide and conquer method
 *  Time taken in nano seconds -> 680475125
 *  The output is -> SumAndIndex[l=2416491, h=2440620, sum=23055]
 *
 *  ****************************************************
 *
 *  Kadane method
 *  Time taken in nano seconds -> 20994792
 *  The output is -> SumAndIndex[l=2416491, h=2440620, sum=23055]
 *  ---------------------------------------------------------------------- 
 *
 *  SECOND RUN WITH THE INPUT SIZE OF N^6
 *
 *  -----------------------------------------------------------------------
 *  Enter the size of the array
 *  1000000
 *  The size of the array will be -> 1000000
 *  ****************************************************
 *
 *  Brute Force method
 *  Time taken in nano seconds -> 320847910375
 *  The output is -> SumAndIndex[l=356582, h=421927, sum=19767]
 *
 *  ****************************************************
 *
 *  Divide and conquer method
 *  Time taken in nano seconds -> 71207583
 *  The output is -> SumAndIndex[l=356582, h=421927, sum=19767]
 *
 *  ****************************************************
 *
 *  Kadane method
 *  Time taken in nano seconds -> 5630833
 *  The output is -> SumAndIndex[l=356582, h=421927, sum=19767]
 *  ------------------------------------------------------------------------
 *  
 *  THIRD RUN WITH THE INPUT SIZE OF N^5
 *
 *  -------------------------------------------------------------------------
 *  Enter the size of the array
 *  100000
 *  The size of the array will be -> 100000
 *  ****************************************************
 *
 *  Brute Force method
 *  Time taken in nano seconds -> 3204621167
 *  The output is -> SumAndIndex[l=83475, h=94849, sum=8944]
 *
 *  ****************************************************
 *
 *  Divide and conquer method
 *  Time taken in nano seconds -> 9913500
 *  The output is -> SumAndIndex[l=83475, h=94849, sum=8944]
 *
 *  ****************************************************
 * 
 *  Kadane method
 *  Time taken in nano seconds -> 2099583
 *  The output is -> SumAndIndex[l=83475, h=94849, sum=8944]
 *  ------------------------------------------------------------------------------
 *  
 *  FOURTH RUN WITH INPUT SIZE OF N^4 
 *
 *  ------------------------------------------------------------------------------
 *  Enter the size of the array
 *  10000
 *  The size of the array will be -> 10000
 *  ****************************************************
 * 
 *  Brute Force method
 *  Time taken in nano seconds -> 45091667
 *  The output is -> SumAndIndex[l=1858, h=4762, sum=5889]
 *
 *  ****************************************************
 *
 *  Divide and conquer method
 *  Time taken in nano seconds -> 1555125
 *  The output is -> SumAndIndex[l=1858, h=4762, sum=5889]
 *
 *  ****************************************************
 * 
 *  Kadane method
 *  Time taken in nano seconds -> 293833
 *  The output is -> SumAndIndex[l=1858, h=4762, sum=5889]
 *  ----------------------------------------------------------------------------------
 *  
 *  FIFTH RUN WITH INPUT SIZE OF N^3
 *
 *  ----------------------------------------------------------------------------------
 *  Enter the size of the array
 *  1000
 *  The size of the array will be -> 1000
 *  ****************************************************
 * 
 *  Brute Force method
 *  Time taken in nano seconds -> 7241250
 *  The output is -> SumAndIndex[l=3, h=488, sum=2556]
 * 
 *  ****************************************************
 * 
 *  Divide and conquer method
 *  Time taken in nano seconds -> 729375
 *  The output is -> SumAndIndex[l=3, h=488, sum=2556]
 *
 *  ****************************************************
 * 
 *  Kadane method
 *  Time taken in nano seconds -> 32167
 *  The output is -> SumAndIndex[l=3, h=488, sum=2556]
 *  -----------------------------------------------------------------------------------
 *  
 *  OBSERVATIONS ->  
 *
 *  -----------------------------------------------------------------------------------
 *
 *  Table with running times(in nanoseconds) measured for different values of 'n'
 *
 *                           |  n = 10^3   |    n = 10^4   |    n = 10^5    |    n = 10^6      |  n = 10^7        |  n = 10^8
 *  Brute Force              |  7241250    |    45091667   |    3204621167  |    320847910375  |  27656933123708  |  N/A
 *  Divide and Conquer       |  729375     |    1555125    |    9913500     |    71207583      |  680475125       |  N/A
 *  Kadane                   |  32167      |    293833     |    2099583     |    5630833       |  20994792        | 98480625
 *  Kadane(less operations)  |  135833     |    541167     |    4628625     |    5304250       |  13314875        | 96493417
 *
 *  
 *  Table of time and space complexities of all three approached used to finding the max sub array sum 
 *  
 *                     | Time Complexity |  Space Complexity 
 *  Brute Force        | Theta (n^2)     |  Theta (1)
 *  Divide and Conquer | Theta (n logn)  |  Theta (log n)  
 *  Kadane             | Theta (n)       |  Theta (1) 
 *  ------------------------------------------------------------------------------------
 *  
 *  Plot Link -> https://colab.research.google.com/drive/1WHdEZiAfjpjjp99AaKnW2autX4mgyC0t?usp=sharing  
 *  
 *  Conclusion - 
 *  
 *  As the inoput size grows the time taken for the finding the max sub array increases
 *  
 *  Brute force technique -> worst performing since it checks every possible subarray. 
 *  Initially when the input size is less then this technique performs only a little bit worse than 
 *  divide and conquer since the for small input sizes the effect of number of constant 
 *  operations is greater, but as the input size increase, runtime grows exponentially 
 *  For larger input the effect of constant operations degrades and the size of input is the become 
 *  the main bottleneck increasing the runtime exponentially 
 * 
 *  Divide and conquer technique -> Performs better then brute force as the input size grows, since it 
 *  divides the problem into smaller problem and builds the solution recursively, this algo has the worst 
 *  space complexity since we are using recursive to keep tract of the solutions generated, space complexcity 
 *  depends on the height of the recursion generated (log n), but performs a lot better than brute force 
 *   
 *  Kadande technique -> Performs best of the three since it runs in linear time and has a constant space 
 *  complexcity, it uses the fact that we can keep on adding the to the sum if positive else make 
 *  the sum zero, it also has the least number of constant operations it need to perform hence 
 *  even for smaller input size it out performs the other two algorithms by a huge margin
 *  and we make it even faster by reducing the number of constant operations need, by only keeping 
 *  track of the maxsum and currsum
 *  
 *
 *************************************************************************/

import java.util.Random;
import java.util.Scanner;

public final class SubArraySum {

    // Record type to store the low, high and max-sum of the sub array for a input
    private static record SumAndIndex(int l, int h, int sum) {
    }

    // Global array declaration
    private static int[] arr;

    // This class contains only static functions hence no need to create object
    private SubArraySum() {
    }

    /**
     * This function calculates the max sub array sub using divide-and-conquer
     * strategy
     *
     * It uses the maxCrossingSum() function to get the max subarray that can be
     * formed in the region of low to mid to high ie in the 'crossing' region
     *
     * It calculate the max sum from the low to mid and
     * from mid + 1 to high and recursively and it return max
     * value that it get from the three regions
     * ie
     * max(low to mid, mid + 1 to high, low to mid to high)
     * 
     * @param l low index value of the max sub array
     * @param h high index value of the max sub array
     * @return SumAndIndex record containing the low, high index value and the max
     *         sub array sum
     */
    private static SumAndIndex divideAndConquer(final int l, final int h) {
        // base case
        if (l == h)
            return new SumAndIndex(l, h, arr[l]);

        // To avoid int overflow
        final int mid = l + (h - l) / 2;

        // calculate the left and right max recursively
        final SumAndIndex leftMaxSum = divideAndConquer(l, mid);
        final SumAndIndex rightMaxSum = divideAndConquer(mid + 1, h);
        // find the max sum of the crossing
        final SumAndIndex crossingMaxSum = maxCrossingSum(l, mid, h);

        // get the max of the left and right side of the mid
        final SumAndIndex maxOfLeftAndRight = leftMaxSum.sum > rightMaxSum.sum ? leftMaxSum : rightMaxSum;
        // return of max of leftRight and crossing
        return maxOfLeftAndRight.sum > crossingMaxSum.sum ? maxOfLeftAndRight : crossingMaxSum;

    }

    /**
     * This function get the max sub array sum that can be formed from
     * low <- middle -> high
     * 
     * @param l low index value of the max sub array
     * @param h high index value of the max sub array
     * @param m middle index value of the max sub array
     * @return SumAndIndex record containing the low, high index value and the max
     *         sub array sum
     */
    private static SumAndIndex maxCrossingSum(final int l, final int m, final int h) {

        // Calculate the max sum value we see when going from
        // mid to low index of the array
        int sum = 0, lSum = Integer.MIN_VALUE, maxLeft = m;
        for (int i = m; i >= l; i--) {
            sum += arr[i];
            if (sum > lSum) {
                lSum = sum;
                maxLeft = i;
            }
        }

        // Calcute the max sum value we see when going from
        // mid + 1 to high index of the array
        sum = 0;
        int rSum = Integer.MIN_VALUE, maxRight = m + 1;
        for (int i = m + 1; i <= h; i++) {
            sum += arr[i];
            if (sum > rSum) {
                rSum = sum;
                maxRight = i;
            }
        }
        // the index and the sum of max crossing
        return new SumAndIndex(maxLeft, maxRight, lSum + rSum);

    }

    /**
     * This function get the max sub array sum and the indexes using the kadane
     * algo which is the most optimal way to calculate the max sub array sum
     * and it runs in linear time ie theta (n) since for given input of size n
     * the best and worst case are same due to the fact that it doesnt depend on
     * the arragement of the input
     * Kadane algo works relies on the premise that when ever the sum goes below
     * zero then there is no need to keep on adding the next element to the curr_sum
     * simply make the cursum zero
     * It behave like a sliding window where we keep on increasing the size of the
     * window if the sum of window stays positived as soon as the sliding window sum
     * goes below zero we collapse our window by make the left equal to the right
     * and at each step we compare our window sum to the max_sum to see if we have
     * greater value.
     * 
     * @return SumAndIndex record containing the low, high index value and the max
     *         sub array sum
     */
    private static SumAndIndex kadane() {
        int maxl = 0, maxr = 0, l = 0, currSum = 0, maxSum = arr[0];
        for (int r = 0; r < arr.length; r++) {
            if (currSum < 0) {
                currSum = 0;
                l = r; // shrinking the window
            }
            currSum += arr[r];
            if (currSum > maxSum) {
                maxSum = currSum;
                maxl = l;
                maxr = r;
            }
        }
        return new SumAndIndex(maxl, maxr, maxSum);
    }

    /**
     * This it the another kadane algo implementation but with less number of
     * constant opertations
     * as it keeps track only of the max and currsum
     *
     * @return maxSum
     */
    private static int kadaneLessOperations() {
        int maxSum = arr[0], currSum = 0;
        for (final int num : arr) {
            currSum = Math.max(currSum, 0) + num;
            maxSum = Math.max(currSum, maxSum);
        }
        return maxSum;
    }

    /**
     * This function get the max sub array sum and the indexes using the least
     * optimal way
     * 
     * @return SumAndIndex record containing the low, high index value and the max
     *         sub array sum
     */

    private static SumAndIndex bruteForce() {
        int maxSum = arr[0], maxL = 0, maxR = 0;
        for (int i = 0; i < arr.length; i++) {
            int currSum = 0;
            for (int j = i; j < arr.length; j++) {
                currSum += arr[j];
                if (currSum > maxSum) {
                    maxSum = currSum;
                    maxL = i;
                    maxR = j;
                }
            }
        }
        return new SumAndIndex(maxL, maxR, maxSum);
    }

    public static void main(final String... args) {
        // initialize the array
        initArray(200);

        System.out.println("****************************************************\n");
        System.out.println("Brute Force method");
        var startTime = System.nanoTime();
        var res = bruteForce();
        var endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("The output is -> " + res);
        System.out.println("\n****************************************************\n");

        System.out.println("Divide and conquer method");
        startTime = System.nanoTime();
        res = divideAndConquer(0, arr.length - 1);
        endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("The output is -> " + res);
        System.out.println("\n****************************************************\n");

        System.out.println("Kadane method");
        startTime = System.nanoTime();
        res = kadane();
        endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("The output is -> " + res);
        System.out.println("\n****************************************************\n");

        System.out.println("Kadane method less number of operations");
        startTime = System.nanoTime();
        int ans = kadaneLessOperations();
        endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("The output is -> " + ans);

    }

    /**
     * This function gets the size of the array from the user and initilizes the
     * globally declared array to the user input size, fills the array with random
     * numbers
     * 
     * @param rangeOfNumber Range of the number to generate random number from (0 to
     *                      range of number) - 100
     */
    private static void initArray(final int rangeOfNumber) {

        final Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array");

        final int size = sc.hasNextInt() ? sc.nextInt() : 0;
        System.out.println("The size of the array will be -> " + size);
        sc.close();

        arr = new int[size];
        final Random rand = new Random();
        for (int i = 0; i < arr.length; i++)
            arr[i] = rand.nextInt(rangeOfNumber) - 100;

    }

}
