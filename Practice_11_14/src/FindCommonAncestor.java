import javax.swing.tree.TreeNode;

public class FindCommonAncestor {
     private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
     //普通二叉树
    private int findCommonAncestor(TreeNode root, int p, int q) {
        return helper(root, p,q).val;
    }

    private TreeNode helper(TreeNode root, int p, int q) {
        if (root == null|| root.val == p || root.val == q) return root;
        TreeNode left = helper(root.left, p, q);
        TreeNode right = helper(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;

        return root;
    }

    //二叉搜索树的版本

//    public int findCommonAncestorBST(TreeNode root, int p, int q) {
//        if(root == null) return -1;
//        if((p >= root.val && q <= root.val) || (p <= root.val && q >= root.val)) return root.val; //两个节点分别在左右子树就返回root
//        if(p <= root.val && q <= root.val) return findCommonAncestorBST (root.left, p , q);
//
//        else return findCommonAncestorBST(root.right, p ,q);
//    }
}


