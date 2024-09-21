
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
 *
 *  Assignment No.: 2
 *
 *  Problem ->
 *  Carry out the same experiments on the Java API class TreeMap. Compare the measurements with the skewed tree and random tree.
 *  Argue why the running time functions observed are (roughly) equal/different.
 *
 *  Input and Output ->
 *  
 *
 *
 *  OBSERVATIONS ->  
 *
 *  -----------------------------------------------------------------------------------
 *
 *  Table with running times(in nanoseconds) measured for different values of 'n'
 *
 *                           |  n = 10^3   |    n = 10^4   |    n = 10^5    |    n = 10^6      |  n = 10^7
 *  Right skewed TreeMap     |             |               |                |                  |                    
 *  Balanced TreeMap         |             |               |                |                  |                    
 *
 *
 *
 *
 *
 *
 *
 *  Table with running times(in nanoseconds) measured for different values of 'n'
 *
 *                           |  n = 10^3   |    n = 10^4   |    n = 10^5    |    n = 10^6      |  n = 10^7
 *  Right skewed TreeMap     |             |               |                |                  |                    
 *  Balanced TreeMap         |             |               |                |                  |                    
 *
 *
 *
 *
 *
 *
 *
 *  Table with running times(in nanoseconds) measured for different values of 'n'
 *
 *                           |  n = 10^3   |    n = 10^4   |    n = 10^5    |    n = 10^6      |  n = 10^7
 *  Right skewed TreeMap     |             |               |                |                  |                    
 *  Balanced TreeMap         |             |               |                |                  |                    
 *
 *
 *
 *
 *
 *
 *
 *  Table with running times(in nanoseconds) measured for different values of 'n'
 *
 *                        |  n = 10^3   |    n = 10^4   |    n = 10^5    |    n = 10^6      |  n = 10^7
 *  Right skewed TreeMap  |             |               |                |                  |                    
 *  Balanced TreeMap      |             |               |                |                  |                    
 * 
 *
 *
 *
 *
 *
 *
 *  Table of time and space complexities of all approaches used
 *  
 *                         | Time Complexity |  Space Complexity 
 *  Right skewed TreeMap   |                 |  
 *  Balanced TreeMap       |                 |    
 *    
 *
 *
 *
 *  ------------------------------------------------------------------------------------
 *  
 *  Conclusion - 
 *  
 *    
 *
 *************************************************************************/

import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;

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

            final TreeMap<Integer, Integer> balancedBST = generateRightSkewedBST(nodeCount);
            final TreeMap<Integer, Integer> rightSkewedBST = generateRightSkewedBST(nodeCount);

            searchFirstValue(rightSkewedBST, balancedBST, 1);
            searchMiddleValue(rightSkewedBST, balancedBST, nodeCount / 2);
            searchLastValue(rightSkewedBST, balancedBST, nodeCount);
            searchValueNotInBST(rightSkewedBST, balancedBST, nodeCount + 1);

            System.out.println("------------------------------------------------------------------------------");
            System.out.println("\nTesting completed for skewed and balanced TreeMap of size -> " + nodeCount + "\n");
            System.out.println("------------------------------------------------------------------------------");

        }

    }

    private static void searchLastValue(final TreeMap<Integer, Integer> rightSkewedBST,
            final TreeMap<Integer, Integer> balancedBST,
            final int key) {
        System.out.println("****************************************************\n");
        System.out.println("Searching last value in Right Skewed TreeMap -> " + key);
        var startTime = System.nanoTime();
        var res = rightSkewedBST.containsKey(key);
        var endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

        System.out.println("Searching last value in Balanced TreeMap -> " + key);
        startTime = System.nanoTime();
        res = balancedBST.containsKey(key);
        endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

    }

    private static void searchValueNotInBST(final TreeMap<Integer, Integer> rightSkewedBST,
            final TreeMap<Integer, Integer> balancedBST,
            final int key) {
        System.out.println("****************************************************\n");
        System.out.println("Searching a value not present in the Right Skewed TreeMap -> " + key);
        var startTime = System.nanoTime();
        var res = rightSkewedBST.containsKey(key);
        var endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

        System.out.println("Searching a value not present in the Balanced TreeMap -> " + key);
        startTime = System.nanoTime();
        res = balancedBST.containsKey(key);
        endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

    }

    private static void searchMiddleValue(final TreeMap<Integer, Integer> rightSkewedBST,
            final TreeMap<Integer, Integer> balancedBST,
            final int key) {
        System.out.println("****************************************************\n");
        System.out.println("Searching middle value of input range in Right Skewed TreeMap -> " + key);
        var startTime = System.nanoTime();
        var res = rightSkewedBST.containsKey(key);
        var endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

        System.out.println("Searching middle value of input range in the Balanced TreeMap -> " + key);
        startTime = System.nanoTime();
        res = balancedBST.containsKey(key);
        endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

    }

    private static void searchFirstValue(final TreeMap<Integer, Integer> rightSkewedBST,
            final TreeMap<Integer, Integer> balancedBST,
            final int key) {
        System.out.println("****************************************************\n");
        System.out.println("Searching first value of input range in Right Skewed TreeMap -> " + key);
        var startTime = System.nanoTime();
        var res = rightSkewedBST.containsKey(key);
        var endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

        System.out.println("Searching first value of input range in the Balanced TreeMap -> " + key);
        startTime = System.nanoTime();
        res = balancedBST.containsKey(key);
        endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

    }

    /****
     *
     *
     * @param args
     * @return
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
     *
     *
     * @param nodeCount
     * @return
     */
    private static TreeMap<Integer, Integer> generateRightSkewedBST(final int nodeCount) {
        final TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 1; i <= nodeCount; i++)
            treeMap.put(i, i);
        return treeMap;
    }

}

/**************************************************************************
 ************************ INPUT AND OUTPUT **************************
 * 
 * ozzy@osaids-MacBook-Air src % java BSTExtraCredit 1000 10000 100000 1000000
 * 10000000
 * ------------------------------------------------------------------------------
 * 
 * Testing search time of skewed and balanced TreeMap of size -> 1000
 * 
 * ------------------------------------------------------------------------------
 ****************************************************
 * 
 * Searching first value of input range in Right Skewed TreeMap -> 1
 * Time taken in nano seconds -> 2333
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching first value of input range in the Balanced TreeMap -> 1
 * Time taken in nano seconds -> 1583
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching middle value of input range in Right Skewed TreeMap -> 500
 * Time taken in nano seconds -> 1167
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching middle value of input range in the Balanced TreeMap -> 500
 * Time taken in nano seconds -> 1333
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching last value in Right Skewed TreeMap -> 1000
 * Time taken in nano seconds -> 1708
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching last value in Balanced TreeMap -> 1000
 * Time taken in nano seconds -> 1209
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching a value not present in the Right Skewed TreeMap -> 1001
 * Time taken in nano seconds -> 1292
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching a value not present in the Balanced TreeMap -> 1001
 * Time taken in nano seconds -> 1042
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
 * Searching first value of input range in Right Skewed TreeMap -> 1
 * Time taken in nano seconds -> 1083
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching first value of input range in the Balanced TreeMap -> 1
 * Time taken in nano seconds -> 1208
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching middle value of input range in Right Skewed TreeMap -> 5000
 * Time taken in nano seconds -> 750
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching middle value of input range in the Balanced TreeMap -> 5000
 * Time taken in nano seconds -> 1000
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching last value in Right Skewed TreeMap -> 10000
 * Time taken in nano seconds -> 958
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching last value in Balanced TreeMap -> 10000
 * Time taken in nano seconds -> 1458
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching a value not present in the Right Skewed TreeMap -> 10001
 * Time taken in nano seconds -> 1000
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching a value not present in the Balanced TreeMap -> 10001
 * Time taken in nano seconds -> 917
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
 * Searching first value of input range in Right Skewed TreeMap -> 1
 * Time taken in nano seconds -> 6375
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching first value of input range in the Balanced TreeMap -> 1
 * Time taken in nano seconds -> 1584
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching middle value of input range in Right Skewed TreeMap -> 50000
 * Time taken in nano seconds -> 1541
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching middle value of input range in the Balanced TreeMap -> 50000
 * Time taken in nano seconds -> 6625
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching last value in Right Skewed TreeMap -> 100000
 * Time taken in nano seconds -> 2166
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching last value in Balanced TreeMap -> 100000
 * Time taken in nano seconds -> 8083
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching a value not present in the Right Skewed TreeMap -> 100001
 * Time taken in nano seconds -> 1125
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching a value not present in the Balanced TreeMap -> 100001
 * Time taken in nano seconds -> 1000
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
 * Searching first value of input range in Right Skewed TreeMap -> 1
 * Time taken in nano seconds -> 4083
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching first value of input range in the Balanced TreeMap -> 1
 * Time taken in nano seconds -> 1417
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching middle value of input range in Right Skewed TreeMap -> 500000
 * Time taken in nano seconds -> 2000
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching middle value of input range in the Balanced TreeMap -> 500000
 * Time taken in nano seconds -> 1208
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching last value in Right Skewed TreeMap -> 1000000
 * Time taken in nano seconds -> 1750
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching last value in Balanced TreeMap -> 1000000
 * Time taken in nano seconds -> 2667
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching a value not present in the Right Skewed TreeMap -> 1000001
 * Time taken in nano seconds -> 1250
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching a value not present in the Balanced TreeMap -> 1000001
 * Time taken in nano seconds -> 1208
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
 * Searching first value of input range in Right Skewed TreeMap -> 1
 * Time taken in nano seconds -> 5166
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching first value of input range in the Balanced TreeMap -> 1
 * Time taken in nano seconds -> 1625
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching middle value of input range in Right Skewed TreeMap -> 5000000
 * Time taken in nano seconds -> 29542
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching middle value of input range in the Balanced TreeMap -> 5000000
 * Time taken in nano seconds -> 2834
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching last value in Right Skewed TreeMap -> 10000000
 * Time taken in nano seconds -> 1833
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching last value in Balanced TreeMap -> 10000000
 * Time taken in nano seconds -> 10916
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching a value not present in the Right Skewed TreeMap -> 10000001
 * Time taken in nano seconds -> 1709
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching a value not present in the Balanced TreeMap -> 10000001
 * Time taken in nano seconds -> 3583
 * Value found -> false
 ****************************************************
 * 
 * 
 * ------------------------------------------------------------------------------
 * 
 * Testing completed for skewed and balanced TreeMap of size -> 10000000
 * 
 * ------------------------------------------------------------------------------
 *********************************************************************************/
