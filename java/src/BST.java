
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
 *  OBSERVATIONS ->  
 *
 *  -----------------------------------------------------------------------------------
 *
 *
 *  Table with running times(in nanoseconds) measured for different values of 'n'
 *
 *                       |  n = 10^3   |    n = 10^4   |    n = 10^5    |    n = 10^6      |  n = 10^7
 *
 *  Right skewed BST     |  10542      |    97125      |    230042      |    1448583       |  19606750                  
 *  Balanced BST         |  333        |    250        |    333         |    708           |  3333                  
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
 *  Conclusion -> 
 *  As we increase the input size the right skewed tree search start to increase linearly 
 *  since it is behaving like a linked list, if we search a last element or a element that is 
 *  not present in the right skewed then it has to traverse the whole tree since linear time 
 *
 *  While the balance bst search time doesnt increase linearly as the input size increase 
 *  since at each step of find an element we are dividing our search space in half 
 *  hence due to this when searching for last value or a value that is not in the 
 *  the runtime is better since we are performing these operations in theta of log n 
 *  time.
 *
 *  when searching for a first value or a middle value in the right skewed tree 
 *  it performs is omega (1) for the first value since its the root of the bst 
 *  but for searching a middle value the average time complexcity is theta (n/2)
 *  so for smaller input size the 1/2 constant have a larger effect, as also shown in 
 *  the runtime data, right skewd tree is not significanly slower than the balanced bst 
 *  but as the input size grows the effect of the 1/2 constant diminishes and for the larger 
 *  input, finding the middle effect will take theta (n) time 
 *
 *  when searching for the first element in the balance bst the right skewed bst performs 
 *  better since for the right skewed bst it has to return the root so it is just a 
 *  constant operation for it. In contract the first element for the balance bst is pushed 
 *  down to the leaf node and as the size grows we have traverse the from the root to the leaf 
 *  node performing a log n time operation
 *  And for finding the middle element the balance bst is significanly better since for it 
 *  runtime is log n
 *
 *  
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

            searchValueInBST(rightSkewedBST, balancedBST, nodeCount + 1);

            System.out.println("------------------------------------------------------------------------------");
            System.out.println("\nTesting completed for skewed and balancedBST of size -> " + nodeCount + "\n");
            System.out.println("------------------------------------------------------------------------------");

        }
    }

    private static TreeNode generateBalancedBST(final int nodeCount) {
        TreeNode root = null;
        for (final int val : generateUniqueRandomNumbers(nodeCount)) {
            root = insertIntoBST(root, val);
        }
        return root;
    }

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

    public static List<Integer> generateUniqueRandomNumbers(final int n) {
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

    /****
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

    /****
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

    /**
     * This function searched key in the both the BST
     *
     * @param rightSkewedBST A right skewed BST
     * @param balancedBST    A balancesBST
     * @param key            value to be searched
     */
    private static void searchValueInBST(final TreeNode rightSkewedBST, final TreeNode balancedBST,
            final int key) {
        System.out.println("****************************************************\n");
        System.out.println("Searching value in the Right Skewed BST -> " + key);
        var startTime = System.nanoTime();
        var res = iterativeSearch(rightSkewedBST, key);
        var endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

        System.out.println("Searching value in the Balanced BST-> " + key);
        startTime = System.nanoTime();
        res = iterativeSearch(balancedBST, key);
        endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

    }

}
/*********************************************************
 ************* INPUT AND OUTPUT***************************
 *
 *
 *
 * ozzy@Mac src % java BST 1000 10000 100000 1000000 10000000
 * ------------------------------------------------------------------------------
 * 
 * Testing search time of skewed and balancedBST of size -> 1000
 * 
 * ------------------------------------------------------------------------------
 ****************************************************
 * 
 * Searching value in the Right Skewed BST -> 1001
 * Time taken in nano seconds -> 18042
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching value in the Balanced BST-> 1001
 * Time taken in nano seconds -> 958
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
 * Time taken in nano seconds -> 168833
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching value in the Balanced BST-> 10001
 * Time taken in nano seconds -> 458
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
 * Time taken in nano seconds -> 1138500
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching value in the Balanced BST-> 100001
 * Time taken in nano seconds -> 2958
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
 * Time taken in nano seconds -> 3435250
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching value in the Balanced BST-> 1000001
 * Time taken in nano seconds -> 1416
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
 * Time taken in nano seconds -> 46213833
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching value in the Balanced BST-> 10000001
 * Time taken in nano seconds -> 3791
 * Value found -> false
 ****************************************************
 * 
 * 
 * ------------------------------------------------------------------------------
 * 
 * Testing completed for skewed and balancedBST of size -> 10000000
 * 
 * ------------------------------------------------------------------------------
 * ozzy@Mac src %
 ****/
