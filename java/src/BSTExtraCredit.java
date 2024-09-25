
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
 *  
 *  References:
 *  1) https://www.geeksforgeeks.org/treemap-in-java/
 *
 *  Assignment No.: 2
 *
 *  Problem ->
 *  Carry out the same experiments on the Java API class TreeMap. Compare the measurements with the skewed tree and random tree.
 *  Argue why the running time functions observed are (roughly) equal/different.
 *
 *  Input and Output -> Mentioned at the end of the file since it was too large 
 *  
 *
 *
 *  OBSERVATIONS ->  
 *
 *  -----------------------------------------------------------------------------------
 *
 *  Table with running times(in nanoseconds) measured for different values of 'n'
 *
 *                        |  n = 10^3   |    n = 10^4   |    n = 10^5    |    n = 10^6      |  n = 10^7
 *  Right skewed TreeMap  |  4958       |    2334       |    3333        |    6542          |  11542
 *  Balanced TreeMap      |  1750       |    1500       |    4584        |    4041          |  5500
 * 
 *
 *  Table of time and space complexities of search operations
 *  
 *                         | Time Complexity |  Space Complexity 
 *  Right skewed TreeMap   | theta (log n)   |  theta (1)
 *  Balanced TreeMap       | theta (log n)   |  theta (1)
 *    
 *
 *  ------------------------------------------------------------------------------------
 *  
 *  plot link -> https://colab.research.google.com/drive/1DJqn4feCyVHUMihJAeWLiTEIuzZiHtd-?usp=sharing
 *
 *  Conclusion -> On creating the TreeMap by using the same procedures used in the BST assignment, 
 *                that is creating two TreeMap using sorted ascending input from 1 to n, and creating another 
 *                TreeMap using unique random values, then applying the same search methodoligies 
 *                we, see that the search times for both treemap almost comparable to each other, this 
 *                is due to the fact that java treemaps are red black trees meaning they are 
 *                self balancing bst's so this makes them invarient on the arragment of the input 
 *                if create treemap using sorted input in ascending, desending or randomly arragment
 *                input, the bst formed in all tree cases will be same, since after each insertion it 
 *                balances itself hence the height of the tree will also be the same regardless of the 
 *                input arragment make the search time theta of log n
 *
 *************************************************************************/

import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Random;
import java.util.Collections;

public final class BSTExtraCredit {

    // this class contains only static fuctions no need to create an object
    private BSTExtraCredit() {
    }

    public static void main(final String... args) {
        final List<Integer> nodeCountList = getNodeCountList(args);
        for (final int nodeCount : nodeCountList) {

            System.out.println("------------------------------------------------------------------------------");
            System.out.println("\nTesting search time of skewed and balanced TreeMap of size -> " + nodeCount + "\n");
            System.out.println("------------------------------------------------------------------------------");

            final TreeMap<Integer, Integer> balancedBST = generateBalancedBST(nodeCount);
            final TreeMap<Integer, Integer> rightSkewedBST = generateRightSkewedBST(nodeCount);

            var key = nodeCount + 1;
            System.out.println("****************************************************\n");
            System.out.println("Searching value in the Right Skewed TreeMap -> " + key);
            var startTime = System.nanoTime();
            var res = rightSkewedBST.containsKey(key);
            var endTime = System.nanoTime();
            System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
            System.out.println("Value found -> " + res);
            System.out.println("\n****************************************************\n");

            final Random random = new Random();
            key = random.nextInt(nodeCount);
            System.out.println("Searching value in the Balanced TreeMap -> " + key);
            startTime = System.nanoTime();
            res = balancedBST.containsKey(key);
            endTime = System.nanoTime();
            System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
            System.out.println("Value found -> " + res);
            System.out.println("\n****************************************************\n");

            System.out.println("------------------------------------------------------------------------------");
            System.out.println("\nTesting completed for skewed and balanced TreeMap of size -> " + nodeCount + "\n");
            System.out.println("------------------------------------------------------------------------------");

        }

    }

    /**
     * This function generates a TreeMap using the same technique
     * of the unique randowm number generation used in creating
     * balanced BST for assignment 2, since treemap is red black
     * tree ie a self balancing bst it is invarient on the arragment of
     * the input ie we could have entered unique number in ascending order
     * would have made no difference since after each insertion the tree
     * balances itself.
     * 
     * @param nodeCount the number of nodes to be created
     * @return TreeMap
     */
    private static TreeMap<Integer, Integer> generateBalancedBST(final int nodeCount) {
        final TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (final int val : generateUniqueRandomNumbers(nodeCount))
            treeMap.put(val, val);
        return treeMap;

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

    /**
     * This function get the user input node count number
     * and stores then in a list
     *
     * @param args Arguments passed to the main method
     * @return A list of nodes for which we have to test search
     */
    private static List<Integer> getNodeCountList(final String... args) {
        if (args.length == 0)
            throw new RuntimeException("Please enter the range of x");
        final List<Integer> input = new ArrayList<>();
        for (final String arg : args)
            input.add(Integer.parseInt(arg));
        return input;
    }

    /****
     * This functions insert value from 1 to n in ascending order to
     * simulate the same steps that were taked to create the
     * right skewed tree, but since we are using a tree map
     * which is a red black tree ie a self balancing bst
     * the order in which the input is arranged won't matter since after each
     * insertions it used rotation to balance it self
     *
     * @param nodeCount
     * @return TreeMap
     */
    private static TreeMap<Integer, Integer> generateRightSkewedBST(final int nodeCount) {
        final TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 1; i <= nodeCount; i++)
            treeMap.put(i, i);
        return treeMap;
    }

}

/**
 * ozzy@Mac src % java BSTExtraCredit 1000 10000 100000 1000000 10000000
 * ------------------------------------------------------------------------------
 * 
 * Testing search time of skewed and balanced TreeMap of size -> 1000
 * 
 * ------------------------------------------------------------------------------
 ****************************************************
 * 
 * Searching value in the Right Skewed TreeMap -> 1001
 * Time taken in nano seconds -> 4958
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching value in the Balanced TreeMap -> 505
 * Time taken in nano seconds -> 1750
 * Value found -> false
 ****************************************************
 * 
 * 
 * ------------------------------------------------------------------------------
 * 
 * Testing completed for skewed and balanced TreeMap of size -> 1000
 * 
 * ------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------
 * 
 * Testing search time of skewed and balanced TreeMap of size -> 10000
 * 
 * ------------------------------------------------------------------------------
 ****************************************************
 * 
 * Searching value in the Right Skewed TreeMap -> 10001
 * Time taken in nano seconds -> 2334
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching value in the Balanced TreeMap -> 7894
 * Time taken in nano seconds -> 1500
 * Value found -> false
 ****************************************************
 * 
 * 
 * ------------------------------------------------------------------------------
 * 
 * Testing completed for skewed and balanced TreeMap of size -> 10000
 * 
 * ------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------
 * 
 * Testing search time of skewed and balanced TreeMap of size -> 100000
 * 
 * ------------------------------------------------------------------------------
 ****************************************************
 * 
 * Searching value in the Right Skewed TreeMap -> 100001
 * Time taken in nano seconds -> 3333
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching value in the Balanced TreeMap -> 56902
 * Time taken in nano seconds -> 4584
 * Value found -> false
 ****************************************************
 * 
 * 
 * ------------------------------------------------------------------------------
 * 
 * Testing completed for skewed and balanced TreeMap of size -> 100000
 * 
 * ------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------
 * 
 * Testing search time of skewed and balanced TreeMap of size -> 1000000
 * 
 * ------------------------------------------------------------------------------
 ****************************************************
 * 
 * Searching value in the Right Skewed TreeMap -> 1000001
 * Time taken in nano seconds -> 6542
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching value in the Balanced TreeMap -> 189293
 * Time taken in nano seconds -> 4041
 * Value found -> false
 ****************************************************
 * 
 * 
 * ------------------------------------------------------------------------------
 * 
 * Testing completed for skewed and balanced TreeMap of size -> 1000000
 * 
 * ------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------
 * 
 * Testing search time of skewed and balanced TreeMap of size -> 10000000
 * 
 * ------------------------------------------------------------------------------
 ****************************************************
 * 
 * Searching value in the Right Skewed TreeMap -> 10000001
 * Time taken in nano seconds -> 11542
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching value in the Balanced TreeMap -> 2989716
 * Time taken in nano seconds -> 5500
 * Value found -> false
 ****************************************************
 * 
 * 
 * ------------------------------------------------------------------------------
 * 
 * Testing completed for skewed and balanced TreeMap of size -> 10000000
 * 
 * ------------------------------------------------------------------------------
 * ozzy@Mac src %
 **/
