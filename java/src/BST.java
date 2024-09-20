
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
 *  -----------------------------------------------------------------
 *  FIRST RUN WITH THE MAX INPUT SIZE IE N^7
 *
 *  ------------------------------------------------------------------- 
 *  ---------------------------------------------------------------------- 
 *
 *  SECOND RUN WITH THE INPUT SIZE OF N^6
 *
 *  -----------------------------------------------------------------------
 *  ------------------------------------------------------------------------
 *  
 *  THIRD RUN WITH THE INPUT SIZE OF N^5
 *
 *  -------------------------------------------------------------------------
 *  ------------------------------------------------------------------------------
 *  
 *  FOURTH RUN WITH INPUT SIZE OF N^4 
 *
 *  ------------------------------------------------------------------------------
 *  ----------------------------------------------------------------------------------
 *  
 *  FIFTH RUN WITH INPUT SIZE OF N^3
 *
 *  ----------------------------------------------------------------------------------
 *  -----------------------------------------------------------------------------------
 *  
 *  OBSERVATIONS ->  
 *
 *  -----------------------------------------------------------------------------------
 *
 *  Table with running times(in nanoseconds) measured for different values of 'n'
 *
 *                       |  n = 10^3   |    n = 10^4   |    n = 10^5    |    n = 10^6      |  n = 10^7        |  n = 10^8
 *  Right skewed BST     |             |               |                |                  |                  |  
 *  Balanced BST         |             |               |                |                  |                  |  
 *                       |             |               |                |                  |                  | 
 *
 *  
 *  Table of time and space complexities of all approaches used
 *  
 *                     | Time Complexity |  Space Complexity 
 *  Right skewed BST   |                 |  
 *  Balanced BST       |                 |    
 *                     |                 |  
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
        final int nodeCount = getNodeCount(args);
        final TreeNode root = generateBalancedBST(generateRightSkewedBST(nodeCount), nodeCount);
        System.out.println("Inorder  -> " + InorderTraversal(root));
        System.out.println("Preorder -> " + PreorderTraversal(root));
        System.out.println("PreOrder -> " + PostorderTraversal(root));
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
    private static int getNodeCount(final String... args) {
        if (args.length == 0)
            throw new RuntimeException("Please enter the range of x");
        return Integer.valueOf(args[0]);
    }

}
