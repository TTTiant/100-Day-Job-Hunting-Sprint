import java.util.ArrayList;
import java.util.List;

public class BinaryTreeBase {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class InOrderTraversal {

        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            inorder(root, res);
            return res;
        }

        public void inorder(TreeNode root, List<Integer> res) {
            if (root == null) {
                return;
            }
            inorder(root.left, res);
            res.add(root.val);
            inorder(root.right, res);
        }
    }

    class maxDepth {
        public int maxDepth(TreeNode root) {
            if (root == null) return 0;
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            return Math.max(left, right) + 1;
        }
    }

    class reverseTree {
        public TreeNode reverseTree(TreeNode root) {
            if (root == null) return root;
            TreeNode left = reverseTree(root.left);
            TreeNode right = reverseTree(root.right);
            root.left = right;
            root.right = left;
            return root;
        }

    class IsSymmetric {
        public boolean isSymmetric(TreeNode root) {
            return check(root.left, root.right);
        }

        public boolean check(TreeNode q, TreeNode p) {
            if (p == null || q == null) return true;
            if (p == null && q == null) return false;

            return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
        }
    }

    class Diameter{
            int ans = 0;
            public int diameterOfBinaryTree(TreeNode root) {
                ans = 1;
                depth(root);
                return ans;

            }
            public int depth(TreeNode root) {
                if (root == null) return 0;
                int left = depth(root.left);
                int right = depth(root.right);
                ans = Math.max(ans, left + right);
                return Math.max(left, right) + 1;
            }
    }

    }
}
