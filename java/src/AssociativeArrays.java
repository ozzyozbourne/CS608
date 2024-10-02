
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
 *  Hypothesis -> 
 *
 *  Assumption for the runtimes as a function of the input size
 *
 *  Construction Time -> 
 *
 *      Here we assuming that time takes to create a hastable will be equal to the 
 *      size of the given array multiplied by an scaling constant, since by default 
 *      the size of the hashtable is 1000 with a load factor of the 75% this means that 
 *      for input size less than 750, only the insertion time will affect the creation time 
 *      but for input greater than 1000 there will be some overhead since hastable will have 
 *      allocate double the memory and then copy the existing items to the new memory locations 
 *      we are assuming that will be the main bottleneck as the size increase
 *
 * 
 *  Search Time -> 
 *
 *      Since the input element are unique and they mapping directly the indexes of the array 
 *      hence even a simple hash function that just does mod of the key with the size the array 
 *      will map one key to one slot, 
 *      Hence we have also assuming each bucket/linked-list of hash table will contain 
 *      only one element, and there will be no collosion, due to the fact that the 
 *      keys are directly mapping to array indexes and the most naive hash function 
 *      implementation should be able to avoid collosion, due to this for each 
 *      input size the search time for finding an key that is not in the hash-table 
 *      will have constant time
 *
 *      Hence we are assuming that the creation time will be theta (n) 
 *      and the search time will be theta (1)
 *      for all input sizes
 *
 *
 * 
 *  Calculated scaling constant for the hast-table creation -> 721
 *      This is calculated as the average sum of (actual runtimes / assumed runtime) 
 *      assume runtime is the number of input size for example for input size of 1000
 *      we are assuming that the creation time is 1000 nanoseconds.
 *      
 *
 *  Calculated constant value for the search time ->  35917    
 *      For runtime for search we assumed as constant so we take that as actual search time
 *      observed with input size of 100 
 *
 *      
 *  HYPOTHICAL RUNTIME -> 
 *  Table with hypotical construction and search times(in nanoseconds) measured for different values of 'n'
 *
 *                       |  n = 10^2   |    n = 10^3   |    n = 10^4    |    n = 10^5      |  n = 10^6
 *
 *  Construction         |  72133      |    721332   |    7213323   |    72133233   |  721332330
 *  Search               |  35917      |    35917    |    35917     |    35917      |  35917
 *
 *
 *
 *  Increase Factor for creation of hastable for hypotical runtimes ->
 *      From n^2 to n^3: 10.00 -> indicating a growth rate of roughly theta (n)
 *      From n^3 to n^4: 10.00 -> indicating a growth rate of roughly theta (n)
 *      From n^4 to n^5: 10.00 -> indicating a growth rate of roughly theta (n)
 *      From n^5 to n^6: 10.00 -> indicating a growth rate of roughly theta (n)
 * 
 *  No increase in factors for the search operation since we have assuming search time to be constant
 *  operation
 *
 *  -----------------------------------------------------------------------------------
 *
 *  OBSERVATIONS ->  
 *  
 *
 *  ACTUAL RUNTIME -> 
 *  Table with Actual construction and search times(in nanoseconds) measured for different values of 'n'
 *
 *                       |  n = 10^2   |    n = 10^3   |    n = 10^4    |    n = 10^5      |  n = 10^6
 *
 *  Construction         |  60083      |    2569625    |    1871041     |    13670209      |  112400458
 *  Search               |  35917      |    135916     |    668708      |    3819209       |  16493625
 *
 *
 *
 *
 *
 *  Table of time and space complexities of search and construction operations
 *  
 *                     | Time Complexity          |  Space Complexity 
 *  Construction       | theta (n)                |  theta (1) for input size 100 since the default size is 1000
 *                                                   then will be O(n) for input size greater than  750 since the laod
 *                                                   factor is 0.75 so for input from n^3 to n^6 
 *                                                   the space complexity will be O (n)
 *  Search             | theta (1)                |  theta (1)
 *
 *  Increase Factor for creation of hastable for Actual runtimes ->
 *      From n^2 to n^3: 42.77 -> not exactly factor of 10, possible due to alot of collision happening along
 *                                inefficient allocation of memory and copying to the new memory location overhead.      
 *
 *      From n^3 to n^4: 0.73  -> not exactly factor of 10, possible due to jvm anticapating memory allocation 
 *                                requirements and jitting the code to naive machine code for better performance 
 *                                along with not a alot of collision happening here, since no overhead for adding extra
 *                                nodes to the linked list(bucket) and traversing the linked list for a key that 
 *                                has a lot of collisions
 *
 *      From n^4 to n^5: 7.31  -> indicating a growth rate of roughly theta (n)
 *      From n^5 to n^6: 8.22  -> indicating a growth rate of roughly theta (n)
 *
 *  Increase factors for searching in hashtable for Actual runtimes ->
 *      From n^2 to n^3: 3.78  ->  This indicating a search time of roughly theta (1) for a value not in 
 *      From n^3 to n^4: 4.92  ->  in the hastable. Although is not doesn't remain constant as we size of 
 *      From n^4 to n^5: 5.71  ->  input increases indicating that the hashing function isn't mapping 
 *      From n^5 to n^6: 4.32  ->  a key to only one bucket, there are some collision happens which is causing 
 *                             ->  the search time to not remain constant.
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
