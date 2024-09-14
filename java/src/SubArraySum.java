
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
 *
 *  -----------------------------------------------------------------------
 *  Enter the size of the array
 *  1000000
 *  The size of the array will be -> 1000000
 *  ****************************************************

 *  Brute Force method
 *  Time taken in nano seconds -> 320847910375
 *  The output is -> SumAndIndex[l=356582, h=421927, sum=19767]
 *
 *  ****************************************************

 *  Divide and conquer method
 *  Time taken in nano seconds -> 71207583
 *  The output is -> SumAndIndex[l=356582, h=421927, sum=19767]

 *  ****************************************************

 *  Kadane method
 *  Time taken in nano seconds -> 5630833
 *  The output is -> SumAndIndex[l=356582, h=421927, sum=19767]
 *  ------------------------------------------------------------------------
 *
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
 *
 *  -----------------------------------------------------------------------------------
 *
 *  Table with running times(in nanoseconds) measured for different values of 'n'
 *
 *                     |  n = 10^3  |    n = 10^4   |    n = 10^5    |    n = 10^6	|  n = 10^7
 *  Brute force        |  7241250   |    45091667   |    3204621167  |    320847910375  |  27656933123708  
 *  Divide and conquer |  729375    |    1555125    |    9913500     |    71207583      |  680475125
 *  Kadane             |  32167     |    293833     |    2099583     |    5630833       |  20994792
 *
 *
 *  ------------------------------------------------------------------------------------
 * Problem 1.
 * PROVIDE YOUR WRITTEN RESPONSE. IF THE QUESTION IS A CODING QUESTION JUST PROVIDE YOUR CODE AS SHOWN BELOW.
 *
 * Problem 2.
 *
 * Brute force
 * Divide and conquer
 *
 *
 * Problem 3.
 * PROVIDE YOUR WRITTEN RESPONSE. IF THE QUESTION IS A CODING QUESTION JUST PROVIDE YOUR CODE AS ASKED BELOW.
 *
 *
 *************************************************************************/

import java.util.Random;
import java.util.Scanner;

public final class SubArraySum {

    private static record SumAndIndex(int l, int h, int sum) {
    }

    private static int[] arr;

    private SubArraySum() {
    }

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

    private static SumAndIndex maxCrossingSum(final int l, final int m, final int h) {

        // calculate the sum from the mid to the left of the array
        // also get the index from where the starting from l to mid here we find the max
        // sum
        int sum = 0, lSum = Integer.MIN_VALUE, maxLeft = m;
        for (int i = m; i >= l; i--) {
            sum += arr[i];
            if (sum > lSum) {
                lSum = sum;
                maxLeft = i;
            }
        }

        // calculate the sum from mid to the right of the array
        sum = 0;
        int rSum = Integer.MIN_VALUE, maxRight = m + 1;
        for (int i = m + 1; i <= h; i++) {
            sum += arr[i];
            if (sum > rSum) {
                rSum = sum;
                maxRight = i;
            }
        }
        // the index and the sum the max crossing sum
        return new SumAndIndex(maxLeft, maxRight, lSum + rSum);

    }

    private static SumAndIndex kadane() {
        int maxl = 0, maxr = 0, l = 0, currSum = 0, maxSum = arr[0];
        for (int r = 0; r < arr.length; r++) {
            if (currSum < 0) {
                currSum = 0;
                l = r; // shrinking the window back to r
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

    }

    // util functions start from here
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
