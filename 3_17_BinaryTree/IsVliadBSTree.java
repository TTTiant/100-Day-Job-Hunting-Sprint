import com.sun.source.tree.Tree;
/*
* 二叉搜索树（BST）是一种特殊的二叉树，满足以下条件：

左子树：所有节点的值小于根节点。

右子树：所有节点的值大于根节点。

递归性：左右子树也必须是二叉搜索树。
*
* */
public class IsVliadBSTree {
    class TreeNode{
        int val = 0;
        TreeNode right = null;
        TreeNode left = null;

        public TreeNode(int val){
            this.val = val;
        }

    }

    public boolean isValidBSTHelper(TreeNode node, long lower, long upper){

        if(node == null){
            return true;
        }
        int val = node.val;

        if(val < lower || val > upper){
            return false;

        }
        return isValidBSTHelper(node.left,lower, val) && isValidBSTHelper(node.right, val, upper);

    }




    public boolean IsValidBST(TreeNode root){
        if(root != null && root.left == null && root.right == null){
        return true;
    }
    // write code here
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
}

}



