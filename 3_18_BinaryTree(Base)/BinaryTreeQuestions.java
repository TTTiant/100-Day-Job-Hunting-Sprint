public class BinaryTreeQuestions {
        class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            public TreeNode(int val) {
                this.val = val;
                this.left = null;
                this.right = null;
            }
        }
       //对比两树是否相同
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) {   //边界检测
                return true;
            } else if (p == null || q == null) { //其一为空，返回false
                return false;
            } else if (p.val != q.val) { // 值不相等，返回false
                return false;
            } else {     //值相等，递归其对比左子节点和右子节点
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            }
        }
         // 求二叉树的深度
    public int maxDepth(TreeNode root) {  //递归
        if(root == null){return 0;} //边界检测
        int leftHeight = maxDepth(root.left); //递归左子树的长度
        int rightHeight = maxDepth(root.right);//递归右子树的长度

        return Math.max(leftHeight, rightHeight) + 1; //找出左右子树最大值+1

    }

    public TreeNode reverseTree(TreeNode root){
            if(root == null) return root;
            TreeNode left = reverseTree(root.left);
            TreeNode right = reverseTree(root.right);

            root.left = right;
            root.right = left;
            return root;
    }

    public boolean hasPathSum(TreeNode root, int targetSum){
        if (root == null) {
            return false;
        }

        // 如果当前节点是叶子节点，检查剩余的目标和是否等于当前节点的值
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        // 递归检查左子树和右子树
        // 在递归调用时，更新目标和为 targetSum - root.val
        boolean hasLeftPath = hasPathSum(root.left, targetSum - root.val);
        boolean hasRightPath = hasPathSum(root.right, targetSum - root.val);

        // 如果左子树或右子树中存在满足条件的路径，返回 true
        return hasLeftPath || hasRightPath;

    }
    }


