import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public final class BST {

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

    private static int getPerfectTreeNodeCount(final double count) {
        return (int) Math.pow(2, (Math.floor(Math.log(count + 1) / Math.log(2)))) - 1;
    }

    private static void rotateLeft(final TreeNode parent, final TreeNode node) {
        final TreeNode temp = node.right;
        node.right = temp.left;
        temp.left = node;
        parent.right = temp;
    }

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

    private static TreeNode generateRightSkewedBST(final int nodeCount) {
        final TreeNode dummy = new TreeNode();
        var curr = dummy;
        for (int i = 1; i <= nodeCount; i++) {
            curr.right = new TreeNode(i);
            curr = curr.right;
        }
        return dummy.right;
    }

    private static int getNodeCount(final String... args) {
        if (args.length == 0)
            throw new RuntimeException("Please enter the range of x");
        return Integer.valueOf(args[0]);
    }

}
