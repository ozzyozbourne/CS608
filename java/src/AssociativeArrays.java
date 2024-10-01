
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
 *  1) https://www.geeksforgeeks.org/hashing-data-structure/# 
 *
 *  Assignment No.: 3
 *
 *  Problem -> To test the insertion and search performance of separate-chaining hash table
 *
 *  Input and Output -> Mentioned at the end of the file since it was too large 
 *
 *  OBSERVATIONS ->  
 *
 *  -----------------------------------------------------------------------------------
 *
 *
 *  Table with construction times(in nanoseconds) measured for different values of 'n'
 *
 *                       |  n = 10^2   |    n = 10^3   |    n = 10^4    |    n = 10^5      |  n = 10^6
 *
 *  Hypothical           |  72133      |    721332     |    7213323     |    72133233      |  721332330
 *  Actual               |  60083      |    2569625    |    1871041     |    13670209      |  112400458
 * 
 *
 *
 *  Table with search times(in nanoseconds) measured for different values of 'n'
 *
 *                       |  n = 10^2   |    n = 10^3   |    n = 10^4    |    n = 10^5      |  n = 10^6
 *
 *  Hypothical           |  12333      |    123329     |    1233285     |    12332850      |  123328503
 *  Actual               |  35917      |    135916     |    668708      |    3819209       |  16493625
 * 
 *
 *
 *
 *  Table of time and space complexities of search and construction operations
 *  
 *                     | Time Complexity          |  Space Complexity 
 *  Construction       |                          |  
 *  Search             |                          |    
 *
 *
 *
 *  ------------------------------------------------------------------------------------
 *  
 *  plot ->  https://colab.research.google.com/drive/1yfKNJ6nXibAz41b-98kencUYu1pM57GW?usp=sharing
 *
 *  Conclusion -> 
 *
 *
 *************************************************************************/

import java.util.Hashtable;

public final class AssociativeArrays {

    private AssociativeArrays() {
    }

    public static void main(final String... args) {
        // running for different value of n ie from 10^2 to 10^6
        for (final int n : new int[] { 100, 1000, 10000, 100000, 1000000 }) {
            // Creating a hash table of initial size 1000 with a load factor of 0.75 as
            // specified in the question
            final Hashtable<Integer, Integer> hashtable = new Hashtable<>(1000, 0.75f);

            // Testing construction time for different sizes of n
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("\nTesting construction and search time for for hashtable of size -> " + n + "\n");
            System.out.println("------------------------------------------------------------------------------");

            var startTime = System.nanoTime();
            for (int i = 0; i < n; i++)
                hashtable.put(i, i);
            var endTime = System.nanoTime();
            System.out.println("\nThe time take to construct a hashtable is -> " + (endTime - startTime) + "\n");

            // Testing search time for a key not the hashmap
            final int key = n + 1;
            System.out.println("\nSearching value that is not in the hashtable");

            startTime = System.nanoTime();
            final boolean found = hashtable.contains(key);
            endTime = System.nanoTime();
            System.out.println(
                    "\nThe time take to search value not in the hashtable is -> " + (endTime - startTime) + "\n");
            System.out.println("Is the value present -> " + found + "\n");

            System.out.println("------------------------------------------------------------------------------");
            System.out.println("\nTesting completed for input size of -> " + n);
            System.out.println("------------------------------------------------------------------------------");

        }
    }

}
/*******
 * ------------------------------------------------------------------------------
 * 
 * Testing construction and search time for for hashtable of size -> 100
 * 
 * ------------------------------------------------------------------------------
 * 
 * The time take to construct a hashtable is -> 60083
 * 
 * 
 * Searching value that is not in the hashtable
 * 
 * The time take to search value not in the hashtable is -> 35917
 * 
 * Is the value present -> false
 * 
 * ------------------------------------------------------------------------------
 * 
 * Testing completed for input size of -> 100
 * ------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------
 * 
 * Testing construction and search time for for hashtable of size -> 1000
 * 
 * ------------------------------------------------------------------------------
 * 
 * The time take to construct a hashtable is -> 2569625
 * 
 * 
 * Searching value that is not in the hashtable
 * 
 * The time take to search value not in the hashtable is -> 135916
 * 
 * Is the value present -> false
 * 
 * ------------------------------------------------------------------------------
 * 
 * Testing completed for input size of -> 1000
 * ------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------
 * 
 * Testing construction and search time for for hashtable of size -> 10000
 * 
 * ------------------------------------------------------------------------------
 * 
 * The time take to construct a hashtable is -> 1871041
 * 
 * 
 * Searching value that is not in the hashtable
 * 
 * The time take to search value not in the hashtable is -> 668708
 * 
 * Is the value present -> false
 * 
 * ------------------------------------------------------------------------------
 * 
 * Testing completed for input size of -> 10000
 * ------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------
 * 
 * Testing construction and search time for for hashtable of size -> 100000
 * 
 * ------------------------------------------------------------------------------
 * 
 * The time take to construct a hashtable is -> 13670209
 * 
 * 
 * Searching value that is not in the hashtable
 * 
 * The time take to search value not in the hashtable is -> 3819209
 * 
 * Is the value present -> false
 * 
 * ------------------------------------------------------------------------------
 * 
 * Testing completed for input size of -> 100000
 * ------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------
 * 
 * Testing construction and search time for for hashtable of size -> 1000000
 * 
 * ------------------------------------------------------------------------------
 * 
 * The time take to construct a hashtable is -> 112400458
 * 
 * 
 * Searching value that is not in the hashtable
 * 
 * The time take to search value not in the hashtable is -> 16493625
 * 
 * Is the value present -> false
 * 
 * ------------------------------------------------------------------------------
 * 
 * Testing completed for input size of -> 1000000
 * ------------------------------------------------------------------------------
 *****/
