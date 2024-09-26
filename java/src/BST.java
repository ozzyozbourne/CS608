
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
 *  1) https://www.geeksforgeeks.org/binary-tree-data-structure/ 
 *
 *  Assignment No.: 2
 *
 *  Problem ->
 *  The way that data is stored in a Binary Search Tree (BST) depends on the order in which the values are inserted.  
 *  Create one right skewed bst (S) and one balanced bst (R).  
 *  The purpose of this assignment is to highlight this striking difference in running time of searchig a value 
 *  skewed BST and in a balanced BST, both with a large number of nodes, and measuring the execution time 
 *  of search
 *
 *  Input and Output -> Mentioned at the end of the file since it was too large 
 *  
 *  ----------------------------------------------------------------------------------------------------
 *
 *  Hypothsis - 
 *
 *  Assumption for the Runtime as a function of input size ->
 *
 *      For Right Skewed BST ->  
 *              we are assuming that the time taken will be equal to the 
 *              to the size of input mutiplied by the mean scaling constant
 *              to accout for constant operations
 *
 *      For Balanced BST -> 
 *              we are assuming that the time taken will be equal to the log of 
 *              input size mutiplied by the mean scaling constant
 *              to accout for constant operations
 *
 *      Mean/Average scaling constant -> 
 *              Ideally the runtime equal to the size of input for right skewed tree
 *              and log of size of the input for the balanced BST but there are constant 
 *              operation always being perform that breaks this assumptions specially for 
 *              smaller inputs, we multiply over hypotical runtime time with the average of 
 *              sum of all actual runtime / sum of all hypothical runtime = scaling constant 
 *
 *              for instance we assume that the hypotical runtime for searching value not in 
 *              right skewed tree or an input of 1000 nodes will 1000 nanoseconds, 
 *              and we got an actual time of 17208 nanoseconds hence the scaling constant will be 
 *              sc = sum of actual / sum of expected which in this example will be 
 *              17208 / 1000
 *
 *  
 *  Formae -> 
 *
 *     skewed BST   =  (size of input/nodes * average scaling constant ) nanoseconds
 *     Balance BST  =  (log2 (size of the input/nodes) * average scaling constant) nanoseconds
 *
 *   Calculated Average scaling constant for skewed BST: 11
 *   Calculated Average scaling constant for balanced BST: 140
 *  
 *  HYPOTHICAL RUNTIME ->
 *
 *  Table with running times(in nanoseconds) hypotical for different value of n 
 *
 *                       |  n = 10^3   |    n = 10^4   |    n = 10^5    |    n = 10^6      |  n = 10^7
 *
 *  Right skewed BST     |  10,888      |    108,879   |    1,088,787   |    10,887,874    |  108,878,735
 *  Balanced BST         |  1,395       |    1,860     |    2,325       |    2,791         |  3,256

 *  Increase factors for skewed BST hypotical runtimes:
 *  From n^3 to n^4: 10.00
 *  From n^4 to n^5: 10.00
 *  From n^5 to n^6: 10.00
 *  From n^6 to n^7: 10.00
 *  
 *  Increase factors for balanced BST hypotical runtimes:
 *  From n^3 to n^4: 1.33
 *  From n^4 to n^5: 1.25
 *  From n^5 to n^6: 1.20
 *  From n^6 to n^7: 1.17
 *
 *  -----------------------------------------------------------------------------------
 *  Actual Observation -> 
 *
 *  plot -> https://colab.research.google.com/drive/1-0VEP6MW3T7LHpmYpEEnitphuSFRXQTW?usp=sharing   
 *
 *  ACTUAL RUNTIME
 *  Table with running times(in nanoseconds) measured for different values of 'n'
 *
 *                       |  n = 10^3   |    n = 10^4   |    n = 10^5    |    n = 10^6      |  n = 10^7
 *
 *  Right skewed BST     |  17208      |    180083     |    1075833     |    3897625       |  45671125
 *  Balanced BST         |  792        |    500        |    1667        |    6333          |  3833
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
 *  Increase factors for skewed BST runtimes: for a value not in the tree
 *  From n^3 to n^4: 10.47   -> indicating a growth rate of roughly theta (n) 
 *  From n^4 to n^5: 5.97    -> not exactly by a factor of 10, possible due be caching since could in laid 
 *                              linearly in memory like an array 
 *  From n^5 to n^6: 3.62    -> not exactly by a factor of 10, possible due be caching since could in laid 
 *                              linearly in memory like an array 
 *
 *  From n^6 to n^7: 11.72   -> indicating a growth rate of roughly theta (n)
 *
 *  Increase factors for balanced BST runtimes:
 *  From n^3 to n^4: 0.63    -> indicating a growth rate of roughly theta (log n).
 *  From n^4 to n^5: 3.33    -> indicating a growth rate of roughly theta (log n).
 *  From n^5 to n^6: 3.80    -> indicating a growth rate of roughly theta (log n).
 *  From n^6 to n^7: 0.61    -> indicating a growth rate of roughly theta (log n).
 *
 *
 *  ------------------------------------------------------------------------------------
 *  
 *
 *  Conclusion -> 
 *      
 *  As we increase the input size the right skewed tree search start to increase linearly 
 *  since it is behaving like a linked list, if we search a last element or a element that is 
 *  not present in the right skewed then it has to traverse the whole tree since linear time 
 *
 *  While in the balance bst the search time doesn't increase linearly as the input size increase 
 *  since at each step of find an element we are dividing our search space in half 
 *  hence due to this when searching for last value or a value that is not present in the balance bst 
 *  the runtime is better since we are performing these operations in theta of (log n) 
 *  time.
 *  
 *
 *************************************************************************/

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public final class BST {

    // class only contains static function no need to create an object of this class
    // hence private constructor
    private BST() {

    }

    /**
     * A class representing a node in Binary Tree
     */
    private static final class TreeNode {
        int val;
        TreeNode left, right;

        private TreeNode() {
        }

        private TreeNode(final int val) {
            this.val = val;
        }

        private TreeNode(final int val, final TreeNode left, final TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(final String... args) {
        final List<Integer> nodeCountList = getNodeCountList(args);
        for (final int nodeCount : nodeCountList) {
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("\nTesting search time of skewed and balancedBST of size -> " + nodeCount + "\n");
            System.out.println("------------------------------------------------------------------------------");

            final TreeNode balancedBST = generateBalancedBST(nodeCount);
            final TreeNode rightSkewedBST = generateRightSkewedBST(nodeCount);

            var key = nodeCount + 1;
            System.out.println("****************************************************\n");
            System.out.println("Searching value in the Right Skewed BST -> " + key);
            var startTime = System.nanoTime();
            var res = iterativeSearch(rightSkewedBST, key);
            var endTime = System.nanoTime();
            System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
            System.out.println("Value found -> " + res);
            System.out.println("\n****************************************************\n");

            final Random random = new Random();
            key = random.nextInt(nodeCount);
            System.out.println("Searching value in the Balanced BST-> " + key);
            startTime = System.nanoTime();
            res = iterativeSearch(balancedBST, key);
            endTime = System.nanoTime();
            System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
            System.out.println("Value found -> " + res);
            System.out.println("\n****************************************************\n");

            System.out.println("------------------------------------------------------------------------------");
            System.out.println("\nTesting completed for skewed and balancedBST of size -> " + nodeCount + "\n");
            System.out.println("------------------------------------------------------------------------------");

        }
    }

    /**
     * This function generates a balanced based using unique
     * random numbers
     *
     * @param nodeCount the number of nodes to be created
     * @return Balanced BST
     */
    private static TreeNode generateBalancedBST(final int nodeCount) {
        TreeNode root = null;
        for (final int val : generateUniqueRandomNumbers(nodeCount)) {
            root = insertIntoBST(root, val);
        }
        return root;
    }

    /**
     * This function insert a val in to an a bst
     * 
     * @param root tree for insertion
     * @param val  value for insertion
     * @return root of the new bst
     */
    private static TreeNode insertIntoBST(final TreeNode root, final int val) {
        if (root == null)
            return new TreeNode(val);

        var iterator = root;

        while (iterator != null) {
            if (val < iterator.val) {
                if (iterator.left == null) {
                    iterator.left = new TreeNode(val);
                    return root;
                }
                iterator = iterator.left;
            } else {
                if (iterator.right == null) {
                    iterator.right = new TreeNode(val);
                    return root;
                }
                iterator = iterator.right;
            }
        }
        return root;
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
     * This function searches a given key iterative in the bst
     *
     * @param root A bst
     * @param key  value to be searched in the bst
     * @return boolean
     */
    private static boolean iterativeSearch(TreeNode root, final int key) {
        while (root != null) {
            if (root.val == key)
                return true;
            else if (root.val > key)
                root = root.left;
            else
                root = root.right;
        }
        return false;
    }

    /**
     * This functions generates a right skewed tree with
     * Values from 1 to n inclusive
     *
     * @param nodeCount
     * @return A right skewed tree
     */
    private static TreeNode generateRightSkewedBST(final int nodeCount) {
        final TreeNode dummy = new TreeNode();
        var curr = dummy;
        for (int i = 1; i <= nodeCount; i++) {
            curr.right = new TreeNode(i);
            curr = curr.right;
        }
        return dummy.right;
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

}

/***********************************************************************
 ******************* INPUT AND OUTPUT
 *
 * ozzy@osaids-MacBook-Air src % java BST 1000 10000 100000 1000000 10000000
 * ------------------------------------------------------------------------------
 * 
 * Testing search time of skewed and balancedBST of size -> 1000
 * 
 * ------------------------------------------------------------------------------
 ****************************************************
 * 
 * Searching value in the Right Skewed BST -> 1001
 * Time taken in nano seconds -> 17208
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching value in the Balanced BST-> 736
 * Time taken in nano seconds -> 792
 * Value found -> false
 ****************************************************
 * 
 * 
 * ------------------------------------------------------------------------------
 * 
 * Testing completed for skewed and balancedBST of size -> 1000
 * 
 * ------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------
 * 
 * Testing search time of skewed and balancedBST of size -> 10000
 * 
 * ------------------------------------------------------------------------------
 ****************************************************
 * 
 * Searching value in the Right Skewed BST -> 10001
 * Time taken in nano seconds -> 180083
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching value in the Balanced BST-> 4059
 * Time taken in nano seconds -> 500
 * Value found -> false
 ****************************************************
 * 
 * 
 * ------------------------------------------------------------------------------
 * 
 * Testing completed for skewed and balancedBST of size -> 10000
 * 
 * ------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------
 * 
 * Testing search time of skewed and balancedBST of size -> 100000
 * 
 * ------------------------------------------------------------------------------
 ****************************************************
 * 
 * Searching value in the Right Skewed BST -> 100001
 * Time taken in nano seconds -> 1075833
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching value in the Balanced BST-> 28839
 * Time taken in nano seconds -> 1667
 * Value found -> false
 ****************************************************
 * 
 * 
 * ------------------------------------------------------------------------------
 * 
 * Testing completed for skewed and balancedBST of size -> 100000
 * 
 * ------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------
 * 
 * Testing search time of skewed and balancedBST of size -> 1000000
 * 
 * ------------------------------------------------------------------------------
 ****************************************************
 * 
 * Searching value in the Right Skewed BST -> 1000001
 * Time taken in nano seconds -> 3897625
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching value in the Balanced BST-> 350072
 * Time taken in nano seconds -> 6333
 * Value found -> false
 ****************************************************
 * 
 * 
 * ------------------------------------------------------------------------------
 * 
 * Testing completed for skewed and balancedBST of size -> 1000000
 * 
 * ------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------
 * 
 * Testing search time of skewed and balancedBST of size -> 10000000
 * 
 * ------------------------------------------------------------------------------
 ****************************************************
 * 
 * Searching value in the Right Skewed BST -> 10000001
 * Time taken in nano seconds -> 45671125
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching value in the Balanced BST-> 7110021
 * Time taken in nano seconds -> 3833
 * Value found -> false
 ****************************************************
 * 
 * 
 * ------------------------------------------------------------------------------
 * 
 * Testing completed for skewed and balancedBST of size -> 10000000
 * 
 * ------------------------------------------------------------------------------
 ******/
