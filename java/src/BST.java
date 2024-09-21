
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
 *  1) https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/solutions/5791984/on-dsw-soln/ 
 *  2) https://leetcode.com/problems/binary-tree-preorder-traversal/solutions/5690881/o-n-morris-traversal-soln/
 *  3) https://leetcode.com/problems/binary-tree-inorder-traversal/solutions/5629922/o-n-morris-traversal-soln/
 *  4) https://leetcode.com/problems/binary-tree-postorder-traversal/solutions/5704500/o-n-morris-traveral-soln/
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
 *  First Value          |  n = 10^3   |    n = 10^4   |    n = 10^5    |    n = 10^6      |  n = 10^7
 *
 *  Right skewed BST     |  1000       |    125        |    208         |    416           |  750                  
 *  Balanced BST         |  791        |    416        |    583         |    1500          |  4167                  
 *
 *
 *
 *
 *
 *
 *
 *  Table with running times(in nanoseconds) measured for different values of 'n'
 *
 *  Middle value         |  n = 10^3   |    n = 10^4   |    n = 10^5    |    n = 10^6      |  n = 10^7
 *  
 *  Right skewed BST     |  5292       |    49375      |    413125      |    734625        |  39010209                   
 *  Balanced BST         |  375        |    333        |    500         |    5125          |  3541                  
 *
 *
 *
 *
 *
 *
 *
 *  Table with running times(in nanoseconds) measured for different values of 'n'
 *
 *  Last Value           |  n = 10^3   |    n = 10^4   |    n = 10^5    |    n = 10^6      |  n = 10^7
 *
 *  Right skewed BST     |  10125      |    97417      |    227250      |    1880459       |  21926750                  
 *  Balanced BST         |  542        |    416        |    625         |    1875          |  28500                  
 *
 *
 *
 *
 *
 *
 *
 *  Table with running times(in nanoseconds) measured for different values of 'n'
 *
 *  Value not in Tree    |  n = 10^3   |    n = 10^4   |    n = 10^5    |    n = 10^6      |  n = 10^7
 *
 *  Right skewed BST     |  10542      |    97125      |    230042      |    1448583       |  19606750                  
 *  Balanced BST         |  333        |    250        |    333         |    708           |  3333                  
 * 
 *
 *
 *
 *
 *
 *
 *  Table of time and space complexities of all approaches used
 *  
 *                     | Time Complexity |  Space Complexity 
 *  Right skewed BST   |                 |  
 *  Balanced BST       |                 |    
 *                     |                 |  
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
import java.util.Collections;

public final class BST {

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

            final TreeNode balancedBST = generateBalancedBST(generateRightSkewedBST(nodeCount), nodeCount);
            final TreeNode rightSkewedBST = generateRightSkewedBST(nodeCount);

            searchFirstValue(rightSkewedBST, balancedBST, 1);
            searchMiddleValue(rightSkewedBST, balancedBST, nodeCount / 2);
            searchLastValue(rightSkewedBST, balancedBST, nodeCount);
            searchValueNotInBST(rightSkewedBST, balancedBST, nodeCount + 1);

            System.out.println("------------------------------------------------------------------------------");
            System.out.println("\nTesting completed for skewed and balancedBST of size -> " + nodeCount + "\n");
            System.out.println("------------------------------------------------------------------------------");

        }
    }

    private static boolean recursiveSearch(final TreeNode root, final int key) {
        return switch (root) {
            case null -> false;
            default -> switch (Integer.compare(root.val, key)) {
                case 1 -> recursiveSearch(root.left, key);
                case -1 -> recursiveSearch(root.right, key);
                default -> true;
            };
        };
    }

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

    /**************************************************************************************************
     ************************** DSW IMPLEMENTATION BEGIN HERE *****************************************
     ***************************************************************************************************/

    /****
     *
     *
     * @param root
     * @param nodeCount
     * @return
     */
    private static TreeNode generateBalancedBST(final TreeNode root, final int nodeCount) {
        final TreeNode dummy = new TreeNode(-1, null, root);
        var perfectTreeNodeCount = getPerfectTreeNodeCount(nodeCount);
        rotateLeftByAmount(dummy, nodeCount - perfectTreeNodeCount);
        while (perfectTreeNodeCount > 1) {
            perfectTreeNodeCount /= 2;
            rotateLeftByAmount(dummy, perfectTreeNodeCount);
        }
        return dummy.right;
    }

    /****
     *
     *
     * @param count
     * @return
     */
    private static int getPerfectTreeNodeCount(final double count) {
        return (int) Math.pow(2, (Math.floor(Math.log(count + 1) / Math.log(2)))) - 1;
    }

    /****
     *
     *
     * @param parent
     * @param node
     */
    private static void rotateLeft(final TreeNode parent, final TreeNode node) {
        final TreeNode temp = node.right;
        node.right = temp.left;
        temp.left = node;
        parent.right = temp;
    }

    /****
     *
     *
     * @param root
     * @param amount
     */
    private static void rotateLeftByAmount(TreeNode root, final int amount) {
        for (int i = 0; i < amount; i++) {
            rotateLeft(root, root.right);
            root = root.right;
        }
    }

    /**************************************************************************************************
     ************************** DSW IMPLEMENTATION END HERE *******************************************
     ***************************************************************************************************/

    /**************************************************************************************************
     ************************** TREE TRAVERSAL FUNCTIONS BEGIN FROM HERE ******************************
     ******************** USING MORRIS TRAVERSAL TO OPTIMIZE SPACE COMPLEXCITY ************************
     ***************************************************************************************************/

    /****
     *
     *
     * @param root
     * @return
     */
    private static List<Integer> InorderTraversal(TreeNode root) {
        final List<Integer> res = new ArrayList<>();
        while (root != null) {
            if (root.left != null) {
                var pre = root.left;
                while (pre.right != null && pre.right != root)
                    pre = pre.right;
                if (pre.right != null) {
                    res.add(root.val);
                    pre.right = null;
                    root = root.right;
                } else {
                    pre.right = root;
                    root = root.left;
                }
            } else {
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }

    /****
     *
     *
     * @param root
     * @return
     */
    private static List<Integer> PreorderTraversal(TreeNode root) {
        final List<Integer> res = new ArrayList<>();
        while (root != null) {
            if (root.left != null) {
                var pre = root.left;
                while (pre.right != null && pre.right != root)
                    pre = pre.right;
                if (pre.right != null) {
                    pre.right = null;
                    root = root.right;
                } else {
                    res.add(root.val);
                    pre.right = root;
                    root = root.left;
                }
            } else {
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }

    /****
     *
     *
     * @param root
     * @return
     */
    private static List<Integer> PostorderTraversal(TreeNode root) {
        final List<Integer> res = new ArrayList<>();
        while (root != null) {
            if (root.right != null) {
                var pre = root.right;
                while (pre.left != null && pre.left != root)
                    pre = pre.left;
                if (pre.left != null) {
                    pre.left = null;
                    root = root.left;
                } else {
                    res.add(root.val);
                    pre.left = root;
                    root = root.right;
                }
            } else {
                res.add(root.val);
                root = root.left;
            }
        }
        Collections.reverse(res);
        return res;
    }

    /**************************************************************************************************
     ************************** TREE TRAVERSAL FUNCTIONS END HERE *************************************
     ***************************************************************************************************/

    /**************************************************************************************************
     ************************** UTILITY FUNCTIONS BEGING HERE *****************************************
     ***************************************************************************************************/

    /****
     *
     *
     * @param nodeCount
     * @return
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

    private static void searchLastValue(final TreeNode rightSkewedBST, final TreeNode balancedBST,
            final int key) {
        System.out.println("****************************************************\n");
        System.out.println("Searching last value in Right Skewed BST -> " + key);
        var startTime = System.nanoTime();
        var res = iterativeSearch(rightSkewedBST, key);
        var endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

        System.out.println("Searching last value in balanced BST-> " + key);
        startTime = System.nanoTime();
        res = iterativeSearch(balancedBST, key);
        endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

    }

    private static void searchValueNotInBST(final TreeNode rightSkewedBST, final TreeNode balancedBST,
            final int key) {
        System.out.println("****************************************************\n");
        System.out.println("Searching a value not present in the Right Skewed BST -> " + key);
        var startTime = System.nanoTime();
        var res = iterativeSearch(rightSkewedBST, key);
        var endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

        System.out.println("Searching a value not present in the Balanced BST-> " + key);
        startTime = System.nanoTime();
        res = iterativeSearch(balancedBST, key);
        endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

    }

    private static void searchMiddleValue(final TreeNode rightSkewedBST, final TreeNode balancedBST,
            final int key) {
        System.out.println("****************************************************\n");
        System.out.println("Searching middle value of input range in Right Skewed BST -> " + key);
        var startTime = System.nanoTime();
        var res = iterativeSearch(rightSkewedBST, key);
        var endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

        System.out.println("Searching middle value of input range in the Balanced BST-> " + key);
        startTime = System.nanoTime();
        res = iterativeSearch(balancedBST, key);
        endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

    }

    private static void searchFirstValue(final TreeNode rightSkewedBST, final TreeNode balancedBST,
            final int key) {
        System.out.println("****************************************************\n");
        System.out.println("Searching first value of input range in Right Skewed BST -> " + key);
        var startTime = System.nanoTime();
        var res = iterativeSearch(rightSkewedBST, key);
        var endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

        System.out.println("Searching first value of input range in the Balanced BST-> " + key);
        startTime = System.nanoTime();
        res = iterativeSearch(balancedBST, key);
        endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

    }

}

/*******************************************************************************
 * ********************** INPUT AND OUTPUT ************************************
 *
 *
 * ozzy@osaids-MacBook-Air src % java BST 1000 10000 100000 1000000 10000000
 * ------------------------------------------------------------------------------
 * 
 * Testing search time of skewed and balancedBST of size -> 1000
 * 
 * ------------------------------------------------------------------------------
 ****************************************************
 * 
 * Searching first value of input range in Right Skewed BST -> 1
 * Time taken in nano seconds -> 1000
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching first value of input range in the Balanced BST-> 1
 * Time taken in nano seconds -> 791
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching middle value of input range in Right Skewed BST -> 500
 * Time taken in nano seconds -> 5292
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching middle value of input range in the Balanced BST-> 500
 * Time taken in nano seconds -> 375
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching last value in Right Skewed BST -> 1000
 * Time taken in nano seconds -> 10125
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching last value in balanced BST-> 1000
 * Time taken in nano seconds -> 542
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching a value not present in the Right Skewed BST -> 1001
 * Time taken in nano seconds -> 10542
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching a value not present in the Balanced BST-> 1001
 * Time taken in nano seconds -> 333
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
 * Searching first value of input range in Right Skewed BST -> 1
 * Time taken in nano seconds -> 125
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching first value of input range in the Balanced BST-> 1
 * Time taken in nano seconds -> 416
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching middle value of input range in Right Skewed BST -> 5000
 * Time taken in nano seconds -> 49375
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching middle value of input range in the Balanced BST-> 5000
 * Time taken in nano seconds -> 333
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching last value in Right Skewed BST -> 10000
 * Time taken in nano seconds -> 97417
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching last value in balanced BST-> 10000
 * Time taken in nano seconds -> 416
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching a value not present in the Right Skewed BST -> 10001
 * Time taken in nano seconds -> 97125
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching a value not present in the Balanced BST-> 10001
 * Time taken in nano seconds -> 250
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
 * Searching first value of input range in Right Skewed BST -> 1
 * Time taken in nano seconds -> 208
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching first value of input range in the Balanced BST-> 1
 * Time taken in nano seconds -> 583
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching middle value of input range in Right Skewed BST -> 50000
 * Time taken in nano seconds -> 413125
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching middle value of input range in the Balanced BST-> 50000
 * Time taken in nano seconds -> 500
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching last value in Right Skewed BST -> 100000
 * Time taken in nano seconds -> 227250
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching last value in balanced BST-> 100000
 * Time taken in nano seconds -> 625
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching a value not present in the Right Skewed BST -> 100001
 * Time taken in nano seconds -> 230042
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching a value not present in the Balanced BST-> 100001
 * Time taken in nano seconds -> 333
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
 * Searching first value of input range in Right Skewed BST -> 1
 * Time taken in nano seconds -> 416
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching first value of input range in the Balanced BST-> 1
 * Time taken in nano seconds -> 1500
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching middle value of input range in Right Skewed BST -> 500000
 * Time taken in nano seconds -> 734625
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching middle value of input range in the Balanced BST-> 500000
 * Time taken in nano seconds -> 5125
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching last value in Right Skewed BST -> 1000000
 * Time taken in nano seconds -> 1880459
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching last value in balanced BST-> 1000000
 * Time taken in nano seconds -> 1875
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching a value not present in the Right Skewed BST -> 1000001
 * Time taken in nano seconds -> 1448583
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching a value not present in the Balanced BST-> 1000001
 * Time taken in nano seconds -> 708
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
 * Searching first value of input range in Right Skewed BST -> 1
 * Time taken in nano seconds -> 750
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching first value of input range in the Balanced BST-> 1
 * Time taken in nano seconds -> 4167
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching middle value of input range in Right Skewed BST -> 5000000
 * Time taken in nano seconds -> 39010209
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching middle value of input range in the Balanced BST-> 5000000
 * Time taken in nano seconds -> 3541
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching last value in Right Skewed BST -> 10000000
 * Time taken in nano seconds -> 21926750
 * Value found -> true
 ****************************************************
 * 
 * 
 * Searching last value in balanced BST-> 10000000
 * Time taken in nano seconds -> 28500
 * Value found -> true
 ****************************************************
 ****************************************************
 * 
 * 
 * 
 * Searching a value not present in the Right Skewed BST -> 10000001
 * Time taken in nano seconds -> 19606750
 * Value found -> false
 ****************************************************
 * 
 * 
 * Searching a value not present in the Balanced BST-> 10000001
 * Time taken in nano seconds -> 3333
 * Value found -> false
 ****************************************************
 * 
 * 
 * ------------------------------------------------------------------------------
 * 
 * Testing completed for skewed and balancedBST of size -> 10000000
 * 
 * ------------------------------------------------------------------------------
 ******************************************************************************/
