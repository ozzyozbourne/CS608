
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
 *  2a) Quick Sort (Fixed Pivot)      
 *  2b) Quick Sort (Random Pivot)          
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
 *  2a) Quick Sort (Fixed Pivot)      
 *  2b) Quick Sort (Random Pivot)          
 * 
 *
 *  Table of time and space complexities of search operations
 *  
 *                                   | Time Complexity          |  Space Complexity 
 *  1)  Insertion Sort               |                          |
 *  2a) Quick Sort (Fixed Pivot)     |                          | 
 *  2b) Quick Sort (Random Pivot)    |                          |      
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
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public final class Sorting {

    private Sorting() {
    }

    public static void main(final String... args) {

    }

    /**
     * This function generated list of unique randoms number from 1 to n
     * each multipled by a random constant
     *
     * @param n max random value
     * @return List of unique random number from 1 to n each mutiplied by random
     *         constant
     */
    private static List<Integer> generateUniqueRandomNumbers(final int n) {
        final Random random = new Random();
        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= n; i++)
            numbers.add(i * random.nextInt(100000));
        Collections.shuffle(numbers);
        return numbers;
    }

}
