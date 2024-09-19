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
        final int range = getRange(args);
        final TreeNode root = generateRightSkewedBST(range);
        System.out.println(InorderTraversal(root));
        System.out.println(PreorderTraversal(root));
        System.out.println(PostorderTraversal(root));

    }

    private static TreeNode generateRightSkewedBST(final int range) {
        final TreeNode dummy = new TreeNode();
        var curr = dummy;
        for (int i = 1; i <= range; i++) {
            curr.right = new TreeNode(i);
            curr = curr.right;
        }
        return dummy.right;
    }

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

    private static int getRange(final String... args) {
        if (args.length == 0)
            throw new RuntimeException("Please enter the range of x");
        return Integer.valueOf(args[0]);
    }

}
