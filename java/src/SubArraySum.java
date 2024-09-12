
/*************************************************************************
 *
 *  Pace University
 *  Fall 2024
 *  Algorithms and Computing Theory
 *
 *  Course: CS 608
 *  Team members:
 *  1) Osaid Khan
 *  2)
 *  3)
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
 *
 *  Input:
 *  Output:
 *
 *
 *
 * Problem 1.
 * PROVIDE YOUR WRITTEN RESPONSE. IF THE QUESTION IS A CODING QUESTION JUST PROVIDE YOUR CODE AS SHOWN BELOW.
 *
 * Problem 2.
 *                     	|n = 10^3| n = 10^4| n = 10^5| n = 10^6|
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

final public class SubArraySum {

    private record SumAndIndex(int l, int h, int sum) {
    }

    private int[] arr;

    private SubArraySum() {
    }

    private SumAndIndex divideAndConquer(int l, int h) {
        // base case
        if (l == h)
            return new SumAndIndex(l, h, arr[l]);

        // To avoid int overflow
        final int mid = l + (h - l) / 2;

        // calculate the left and right max recursively
        final var leftMaxSum = divideAndConquer(l, mid);
        final var rightMaxSum = divideAndConquer(mid + 1, h);
        // find the max sum of the crossing
        final var crossingMaxSum = maxCrossingSum(l, mid, h);

        // get the max of the left and right side of the mid
        final var maxOfLeftAndRight = leftMaxSum.sum > rightMaxSum.sum ? leftMaxSum : rightMaxSum;
        // return of max of leftRight and crossing
        return maxOfLeftAndRight.sum > crossingMaxSum.sum ? maxOfLeftAndRight : crossingMaxSum;

    }

    private SumAndIndex maxCrossingSum(final int l, final int m, final int h) {

        // calculate the sum from the mid to the left of the array
        // also get the index from where the starting from l to mid here we find the max
        // sum
        int sum = 0, lSum = Integer.MIN_VALUE, maxLeft = m;
        for (int i = m; i >= l; i--) {
            sum += this.arr[i];
            if (sum > lSum) {
                lSum = sum;
                maxLeft = i;
            }
        }

        // calculate the sum from mid to the right of the array
        sum = 0;
        int rSum = Integer.MIN_VALUE, maxRight = m + 1;
        for (int i = m + 1; i <= h; i++) {
            sum += this.arr[i];
            if (sum > rSum) {
                rSum = sum;
                maxRight = i;
            }
        }
        // the index and the sum the max crossing sum
        return new SumAndIndex(maxLeft, maxRight, lSum + rSum);

    }

    private SumAndIndex kadaneSlidingWindow() {
        int maxl = 0, maxr = 0, l = 0, currSum = 0, maxSum = this.arr[0];
        for (int r = 0; r < this.arr.length; r++) {
            if (currSum < 0) {
                currSum = 0;
                l = r; // shrinking the window back to r
            }
            currSum += this.arr[r];
            if (currSum > maxSum) {
                maxSum = currSum;
                maxl = l;
                maxr = r;
            }
        }
        return new SumAndIndex(maxl, maxr, maxSum);
    }

    private SumAndIndex bruteForce() {
        var maxSum = this.arr[0];
        int maxL = 0, maxR = 0;
        for (int i = 0; i < this.arr.length; i++) {
            int currSum = 0;
            for (int j = i; j < this.arr.length; j++) {
                currSum += this.arr[j];
                if (currSum > maxSum) {
                    maxSum = currSum;
                    maxL = i;
                    maxR = j;
                }
            }
        }
        return new SumAndIndex(maxL, maxR, maxSum);
    }

    private int kadane() {
        int maxSum = this.arr[0], currSum = 0;
        for (final int num : this.arr) {
            currSum = Math.max(0, currSum) + num;
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }

    public static void main(final String... args) {
        // setting up the array so run which custom n sizes
        final SubArraySum subArraySum = new SubArraySum();
        getSizeOfTheArray(subArraySum);
        fillArrayWithRandomNumbers(subArraySum, 200);

        System.out.println("Divide and conquer algo execution time");
        var startTime = System.nanoTime();
        var res = subArraySum.divideAndConquer(0, subArraySum.arr.length - 1);
        var endTime = System.nanoTime();
        System.out.println("Time take in nano seconds -> " + (endTime - startTime));
        System.out.println("the ouput is -> " + res);

        System.out.println("Brute force algo execution time");
        startTime = System.nanoTime();
        res = subArraySum.bruteForce();
        endTime = System.nanoTime();
        System.out.println("Time take in nano seconds -> " + (endTime - startTime));
        System.out.println("the ouput is -> " + res);

        System.out.println("Brute force algo execution time");
        startTime = System.nanoTime();
        res = subArraySum.kadaneSlidingWindow();
        endTime = System.nanoTime();
        System.out.println("Time take in nano seconds -> " + (endTime - startTime));
        System.out.println("the ouput is -> " + res);

    }

    // util functions start from here
    // ------------------------------------------------
    private static void getSizeOfTheArray(final SubArraySum main) {

        final Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array");

        final int size = sc.hasNextInt() ? sc.nextInt() : 0;
        System.out.println("The size of the array will be -> " + size);
        sc.close();

        main.arr = new int[size];
    }

    private static void fillArrayWithRandomNumbers(final SubArraySum main, final int rangeOfNumber) {
        final Random rand = new Random();
        for (int i = 0; i < main.arr.length; i++)
            main.arr[i] = rand.nextInt(rangeOfNumber) - 100;
        System.out.println("The input Array is -> ");
        for (final var num : main.arr)
            System.out.print(num + " ");
        System.out.println();
    }

}
