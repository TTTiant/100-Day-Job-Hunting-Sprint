public class OrdersBuildTree {
    class TreeNode{
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val){
            this.val = val;
        }
    }

    public TreeNode reConstructBinaryTree (int[] preOrder, int[] vinOrder) {
        // write code here
        if(preOrder == null || vinOrder == null){  //边界检测
            return null;
        }

        return reConstructBinaryTreeHelper(preOrder,0,preOrder.length - 1, vinOrder, 0 , vinOrder.length - 1);
    }

    public TreeNode reConstructBinaryTreeHelper(int[] preOrder, int preStart, int preEnd, int[] vinOrder, int vinStart, int vinEnd ){

        if(preStart > preEnd || vinStart > vinEnd){   //递归停止条件
            return null;
        }

        int rootVal = preOrder[preStart];      //前序遍历的第一个元素为根节点
        TreeNode root = new TreeNode(rootVal);

        int rootIndex = 0;
        for(int i = vinStart; i <= vinEnd; i++){   //找到根节点在中序遍历的索引位置
            if(vinOrder[i] == rootVal){
                rootIndex = i;
                break;
            }
        }

        int leftTreeSize = rootIndex - vinStart;  //通过根节点找出左右子树的范围

        root.left = reConstructBinaryTreeHelper(preOrder, preStart + 1, preStart + leftTreeSize, vinOrder, vinStart, rootIndex - 1);
        //递归
        root.right = reConstructBinaryTreeHelper(preOrder, preStart + leftTreeSize + 1, preEnd, vinOrder, rootIndex + 1 , vinEnd);


        return root;

    }
}



