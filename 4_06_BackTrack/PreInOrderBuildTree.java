import java.util.LinkedList;
import java.util.Queue;

public class PreInOrderBuildTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        return buildTreeHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd, int[] inorder,int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) return null;

        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        int inOrderRootVal = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                inOrderRootVal = i;
                break;
            }
        }
        int leftTreeSize = inOrderRootVal - inStart;
        root.left = buildTreeHelper( preorder,preStart +1, preStart +leftTreeSize,
                                     inorder,         inStart,      inOrderRootVal-1);
        root.right = buildTreeHelper(preorder, preStart+leftTreeSize+1, preEnd,
                                     inorder,   inOrderRootVal+1,        inEnd);
        return root;

    }

    public static void printLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        PreInOrderBuildTree tree = new PreInOrderBuildTree();
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode root = tree.buildTree(preorder,inorder);
        tree.printLevelOrder(root);
    }
}
