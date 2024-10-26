
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
 * Before sorting -> [7, 4, 5, 2, 8, 3]
 * Before sorting -> [3, 6, 4, 3, 5, 6, 3, 2, 4, 5, 67, 78, 6, 5, 4, 3, 2, 4, 5, 6, 2, 4, 43, 3]
 *
 *
 * After sorting -> [2, 3, 4, 5, 7, 8]
 * After sorting -> [2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 43, 67, 78] 
 *
 * Conclusion -> library is O(n log n)(best and average case) implement of insertion sort where we replace the linear search 
 *               to insert the key into its correct position by binary search, and it uses a rebalancing 
 *               mechanism to make the array less dense. 
 *               The whole point of libray sort to the reduce the time taken in swaping the elements 
 *               so to implement this we use an array that is larger that the input array and we begin our 
 *               insertion, we keep track of the gaps present in array if during insertion we find that the 
 *               insertion position already has an element present meaning we need swap then instead of 
 *               doing that we rebalance the array ie introduce gaps to the left and right indexes of the 
 *               each element hence, making the insertion possible without the need of swapping.
 *
 * 
 ********************************************************************************/

import java.util.Arrays;

public final class LibrarySort {

    private LibrarySort() {
    }

    public static void librarySort(final int[] index, final int n) {
        int lib_size = 1, index_pos = 1, target_lib = 0;

        final int[] gaps = new int[n + 1];
        final int[][] library = new int[2][];
        final boolean[] numbered = new boolean[n + 1];

        for (int i = 0; i < 2; i++)
            library[i] = new int[n];

        library[target_lib][0] = index[0];

        while (index_pos < n) {

            int insert = Arrays.binarySearch(library[target_lib], 0, lib_size, index[index_pos]);

            if (insert < 0)
                insert = -insert - 1;

            if (numbered[insert]) {
                int prov_size = 0;
                final int next_target_lib = 1 - target_lib;

                for (int i = 0; i <= n; i++) {
                    if (numbered[i]) {
                        library[next_target_lib][prov_size] = gaps[i];
                        prov_size += 1;
                        numbered[i] = false;
                    }

                    if (i <= lib_size) {
                        library[next_target_lib][prov_size] = library[target_lib][i];
                        prov_size += 1;
                    }
                }

                target_lib = next_target_lib;
                lib_size = prov_size - 1;
            } else {
                numbered[insert] = true;
                gaps[insert] = index[index_pos];
                index_pos += 1;
            }
        }

        int index_pos_for_output = 0;
        for (int i = 0; index_pos_for_output < n; i++) {
            if (numbered[i]) {
                index[index_pos_for_output] = gaps[i];
                index_pos_for_output += 1;
            }

            if (i < lib_size) {
                index[index_pos_for_output] = library[target_lib][i];
                index_pos_for_output += 1;
            }
        }
    }

    public static void main(final String... args) {

        var input_A = new int[] { 7, 4, 5, 2, 8, 3 };
        var input_B = new int[] { 3, 6, 4, 3, 5, 6, 3, 2, 4, 5, 67, 78, 6, 5, 4, 3, 2, 4, 5, 6, 2, 4, 43, 3 };

        System.out.println("Before sorting -> " + Arrays.toString(input_A));
        System.out.println("Before sorting -> " + Arrays.toString(input_B));
        System.out.println();

        librarySort(input_A, input_A.length);
        librarySort(input_B, input_B.length);

        System.out.println();
        System.out.println("After sorting -> " + Arrays.toString(input_A));
        System.out.println("After sorting -> " + Arrays.toString(input_B));

    }
}
