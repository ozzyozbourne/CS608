import java.util.Random;
import java.util.Scanner;

final public class Main {

    private record SumAndIndex(int l, int h, int sum){}
    private int[] arr;
    private Main() {}

    private SumAndIndex maxSubArraySum(int l, int h) {
        //base case
        if (l == h) return new SumAndIndex(l, h, arr[l]);

        // To avoid int overflow
        final int mid = l + (h - l) / 2;

        // calculate the left and right max recursively
        final var leftMaxSum = maxSubArraySum(l, mid);
        final var rightMaxSum = maxSubArraySum(mid + 1, h);
        // find the max sum of the crossing
        final var crossingMaxSum =maxCrossingSum(l, mid, h);

        // get the max of the left and right side of the mid
        final var maxOfLeftAndRight = leftMaxSum.sum > rightMaxSum.sum ? leftMaxSum : rightMaxSum;
        // return of max of leftRight and crossing
        return maxOfLeftAndRight.sum > crossingMaxSum.sum ? maxOfLeftAndRight : crossingMaxSum;

    }

    private SumAndIndex maxCrossingSum(final int l, final int m, final int h) {

        //calculate the sum from the mid to the left of the array
        // also get the index from where the starting from l to mid here we find the max sum
        int sum = 0, lSum = Integer.MIN_VALUE, maxLeft = m;
        for (int i = m; i >= l; i--) {
            sum += this.arr[i];
            if (sum > lSum){
                lSum = sum;
                maxLeft = i;
            }
        }

        //calculate the sum from mid to the right of the array
        sum = 0;
        int rSum = Integer.MIN_VALUE, maxRight = m + 1;
        for (int i = m + 1; i <= h; i++) {
            sum += this.arr[i];
            if (sum > rSum){
                rSum = sum;
                maxRight = i;
            }
        }
        // the index and the sum the max crossing sum
        return new SumAndIndex(maxLeft, maxRight, lSum+ rSum);

    }

    public static void main(final String... args) {
        final Main mainTest1 = new Main();

        // this also works for negative number
        mainTest1.arr = new int[]{-1, -2, -3, -4, -5};
        System.out.println(mainTest1.maxSubArraySum(0, mainTest1.arr.length - 1));

//        getSizeOfTheArray(mainTest1);
//        fillArrayWithRandomNumbers(mainTest1, 200);
//        System.out.println(mainTest1.maxSubArraySum(0, mainTest1.arr.length - 1));

    }

    // util functions start from here ------------------------------------------------
    private static void getSizeOfTheArray(final Main main){
        final Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array");

        final int size = sc.hasNextInt() ? sc.nextInt() : 0;
        System.out.println("The size of the array will be -> " + size);
        sc.close();

        main.arr = new int[size];
    }

    private static void fillArrayWithRandomNumbers(final Main main, final int rangeOfNumber) {
        final Random rand = new Random();
        for (int i = 0; i < main.arr.length; i++) main.arr[i] = rand.nextInt(rangeOfNumber) - 100;
        System.out.println("The input Array is -> ");
        for(final var num : main.arr) System.out.print(num + " ");
        System.out.println();
    }


    private int kadane(){
        var maxSum = this.arr[0];
        var currSum = Integer.MIN_VALUE;
        for(final int num : this.arr) {
            currSum += Math.max(0, currSum) + num;
            maxSum = Math.max(maxSum, currSum);
        }return maxSum;
    }

    private SumAndIndex kadaneSliding(){
        int maxl = 0, maxr = 0, l = 0, currSum = 0, maxSum = this.arr[0];
        for(int r = 0; r < this.arr.length; r++) {
            if (currSum > maxSum){
                maxSum = currSum;
                maxl = l;
                maxr = r;
            }
            currSum += Math.max(0, currSum) + this.arr[r];

        }return new SumAndIndex(maxl, maxr, maxSum);
    }

}