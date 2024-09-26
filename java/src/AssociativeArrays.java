
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
 *  1)  
 *
 *  Assignment No.: 3
 *
 *  Problem ->
 *
 *  Input and Output -> Mentioned at the end of the file since it was too large 
 *
 *  OBSERVATIONS ->  
 *
 *  -----------------------------------------------------------------------------------
 *
 *
 *  Table with running times(in nanoseconds) measured for different values of 'n'
 *
 *                       |  n = 10^3   |    n = 10^4   |    n = 10^5    |    n = 10^6      |  n = 10^7
 *
 *  Right skewed BST     |  17208      |    180083     |    1075833     |    3897625       |  45671125
 *  Balanced BST         |  792        |    500        |    1667        |    6333          |  3833
 * 
 *
 *
 *
 *  Table of time and space complexities of search operations
 *  
 *                     | Time Complexity          |  Space Complexity 
 *  Right skewed BST   | best-case Omega (1)      |  theta (1)
 *                       item is root 
 *                       Worst-case O (n)
 *                       item is last element   
 *                       Average case theta (n)
 *  
 *  Balanced BST       |  theta (log n)          |  theta (1)  
 *
 *
 *
 *  ------------------------------------------------------------------------------------
 *  
 *  plot ->  
 *
 *  Conclusion -> 
 *
 *
 *************************************************************************/

import java.util.Hashtable;
import java.util.Random;

public final class AssociativeArrays {

    private AssociativeArrays() {

    }

    public static void main(final String... args) {
        // running for different value of n ie from 10^2 to 10^6
        for (final int n : new int[] { 100, 1000, 10000, 100000, 1000000 }) {
            // Creating a hash table of initial size 1000 with a load factor of 0.75 as
            // specified in the question
            final Hashtable<Integer, Integer> hashtable = new Hashtable<>(1000, 0.75f);
        }
    }

}
