
/*********************************************************************************
 *
 * Pace University
 * Fall 2024
 * Algorithms and Computing Theory
 *
 * Course: CS 608
 * Team members:
 * 1) Adam Miftahelidrissi
 * 2) Marlen Cuevas Duarte
 * 3) Osaid Khan
 *
 * Other collaborators: None.
 * 
 * References:
 * 1) https://en.wikipedia.org/wiki/Library_sort
 *
 * Assignment No.: 4EC
 *
 * Problem -> Implement Library Sort
 *
 * Input and Output ->
 * 
 * Conclusion ->
 *
 * 
 ********************************************************************************/

import java.util.Arrays;

public final class LibrarySort {

    private LibrarySort() {
    }

    private static int binarySearch(final int[] arr, final int key, final int validLength) {
        int low = 0, high = validLength - 1;

        while (low <= high) {
            final int mid = (low + high) / 2;

            if (arr[mid] == -1 || arr[mid] > key) {
                high = mid - 1;
            } else if (arr[mid] < key) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return low;
    }

    private static void rebalance(final int[] arr, final int validLength) {
        int[] newArr = new int[arr.length];
        Arrays.fill(newArr, -1);

        var index = 0;
        for (int i = 0; i < validLength; i++) {
            newArr[index] = arr[i];
            index += 2;
        }

        System.arraycopy(newArr, 0, arr, 0, arr.length);
    }

    public static int[] librarySort(final int[] arr) {
        final int[] sortedArr = new int[(int) ((1 + 0.5) * arr.length)];
        Arrays.fill(sortedArr, -1);

        sortedArr[0] = arr[0];
        int validLength = 1;

        for (int i = 1; i < arr.length; i++) {
            if (validLength >= sortedArr.length)
                rebalance(sortedArr, validLength);

            int pos = binarySearch(sortedArr, arr[i], validLength);
            for (int j = validLength; j > pos; j--)
                sortedArr[j] = sortedArr[j - 1];

            sortedArr[pos] = arr[i];
            validLength += 1;
        }

        return Arrays.stream(sortedArr).filter(x -> x != -1).toArray();

    }

    public static void main(final String... args) {

        var input_A = new int[] { 7, 4, 5, 2, 8, 3 };
        var input_B = new int[] { 3, 6, 4, 3, 5, 6, 3, 2, 4, 5, 67, 78, 6, 5, 4, 3, 2, 4, 5, 6, 2, 4, 43, 3 };

        System.out.println("Before sorting -> " + Arrays.toString(input_A));
        System.out.println("Before sorting -> " + Arrays.toString(input_B));
        System.out.println();

        input_A = librarySort(input_A);
        input_B = librarySort(input_B);

        System.out.println();
        System.out.println("After sorting -> " + Arrays.toString(input_A));
        System.out.println("After sorting -> " + Arrays.toString(input_B));

    }
}
