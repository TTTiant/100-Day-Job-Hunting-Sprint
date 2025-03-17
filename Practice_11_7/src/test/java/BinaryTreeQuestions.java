public class BinaryTreeQuestions {

    class TreeNode{
        int val = 0;
        IsVliadBSTree.TreeNode right = null;
        IsVliadBSTree.TreeNode left = null;

        public TreeNode(int val){
            this.val = val;
        }
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            } else if (p == null || q == null) {
                return false;
            } else if (p.val != q.val) {
                return false;
            } else {
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            }
        }
    }


}
