
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
 *  Conclusion - 
 *  
 *    
 *
 *************************************************************************/

import java.util.List;
import java.util.ArrayList;

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

    /**************************************************************************************************
     ************************** DSW IMPLEMENTATION BEGIN HERE
     * *****************************************
     ***************************************************************************************************/

    /****
     * This function is driver function for the DSW implementation
     * This taken in a right skewed bst ie Vine or backbone tree then performs
     * neccessary left rotation make the a balanced bst
     * This uses a dummy node right pointer as the attachment from which to performs
     * a left rotation
     * Since in a balanced BST the different in the height of the tree
     * cannot exceed 1
     * So first we get the count of the perfect tree ie we get the node count
     * of the maximum level that we can fill for instance if the node count is 7
     * that we can form a perfect tree of level 3 ie all leafs are at the same
     * height, but that might not be the case since say for the 4 level to be
     * completed filled we will need 8 more leafs ie log2 (nodecount + 1) = 4
     * but if we have 10 leaves the last level will have 3 leaves nodes, will the
     * height diff of the leaves of the bst will 1
     * This building the level of the tree is done by the function
     * rotateleftbyamount
     * this function is run a loop and it take the amount count then is does the
     * left rotationn by that amount so, each cal to this function build level
     * by performing the left rotation from the parent and the parents right
     * and moving the parent to is right node and does the left rotation where
     * fist we do the left rotation by the difference of total node - perfect node
     * if this difference is not zero, then the generated bst will have a height
     * diff of
     * 1 and then in the while loop will build the perferct tree levels
     * and since the each time we do the rotation from the dummy and dummy,right
     * since the balanced bst is attached to dummies right
     *
     * @param root
     * @param nodeCount
     * @return A balanced BST
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
     * calculate the amount of nodes present in the perfect trees for eg
     * say we have the node count as 10 then this function will first calculate
     * log2 of the nodecount + 1 since numbers of node in the tree is off by 1
     * due to fact the root is 1
     * so first we get the floor of log2 of nodecount + 1 ie log2 floor (11)
     * and this will be 3 since 2 ^ 3 = 8
     * so now we now the 3 level of the tree will completed completely
     * ie level 0, 1 and 3 will have full nodes since the total number of
     * node in this example is 10
     * Now we know the max filled level, so now calculate the number of nodes in
     * these level which will 2 ^ 3 -1 (since root is 1)
     * so we get the perfect node count as 7
     * hence the fourth level will have 3 node
     * so we return the perfect node count ie 7 since we already have the
     * total number of nodes
     *
     * @param count number of nodes in the perfect tree
     * @return count of the nodes that will be present in the perfect tree
     */
    private static int getPerfectTreeNodeCount(final double count) {
        return (int) Math.pow(2, (Math.floor(Math.log(count + 1) / Math.log(2)))) - 1;
    }

    /****
     * This function does the left rotation
     * the visual for this is
     * say we have a tree like
     * ****** dummy
     * *********** \
     * ************ node1
     * ***************** \
     * ****************** node2
     * so what is function it holds the dummy node
     * and push the node1.right ie node2 upwards
     * the best to think if we take a rorasy bead that is right skewed
     * and we hold it from top (dummy) and push it from the bottom (node2 )
     * then the node 1 will be pushed to the left node2
     *
     * so applying this function to the above tree now the tree will look
     * like
     * ****************dummy
     * *********************\
     * **********************node2
     * ******************** /*****\
     * ******************node1
     *
     * so we attahch the dummyies right to the node2
     * and node2 left will have node1 and
     * since and subtree present in the node2 left
     * will now be attached to node1.right
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
     * this function builds a tree level the amount of rotation done by
     * this function will generated the same number of nodes that level
     * for example we have number of nodes as 10 so there will be 3 nodes
     * in the last level so we will do three left rotation alternatively
     * ie first will be done from dummy.right
     * and now dummy will have a new right
     * so we move down to the right and then do rotation there
     * and so on..
     *
     * @param root   holder root from there the rotation will happen ie
     *               it right->right will now be its right and its prevous
     *               right now will be attach to the right->right.left
     * @param amount the number of left rotations to be performed
     * 
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
     ************************** UTILITY FUNCTIONS BEGING HERE *****************************************
     ***************************************************************************************************/

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
     * @param key            Last value of the input sequence
     */
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

    /**
     * This function searched key in the both the BST
     *
     * @param rightSkewedBST A right skewed BST
     * @param balancedBST    A balancesBST
     * @param key            value not in input the sequence ie (n + 1)
     */
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

    /**
     * This function searched key in the both the BST
     *
     * @param rightSkewedBST A right skewed BST
     * @param balancedBST    A balancesBST
     * @param key            middle value of the input Sequence
     */
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

    /**
     * This function searched key in the both the BST
     *
     * @param rightSkewedBST A right skewed BST
     * @param balancedBST    A balancesBST
     * @param key            first value of the input Sequence
     */
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
