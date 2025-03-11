
import java.util.*;
//根据先序和中序遍历数组 构建二叉树
/*
 * public class TreeNode {
 *   int val = 0;
 *   TreeNode left = null;
 *   TreeNode right = null;
 *   public TreeNode(int val) {
 *     this.val = val;
 *   }
 * }
 */

public class ReconstructBinaryTree {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param preOrder int整型一维数组
     * @param vinOrder int整型一维数组
     * @return TreeNode类
     */
    public TreeNode reConstructBinaryTree (int[] preOrder, int[] vinOrder) {
        // write code here
        if(preOrder == null || vinOrder == null){
            return null;
        }

        return reConstructBinaryTreeHelper(preOrder,0,preOrder.length - 1, vinOrder, 0 , vinOrder.length - 1);
    }

    public TreeNode reConstructBinaryTreeHelper(int[] preOrder, int preStart, int preEnd, int[] vinOrder, int vinStart, int vinEnd ){

        if(preStart > preEnd || vinStart > vinEnd){
            return null;
        }

        int rootVal = preOrder[preStart];
        TreeNode root = new TreeNode(rootVal);

        int rootIndex = 0;
        for(int i = vinStart; i <= vinEnd; i++){
            if(vinOrder[i] == rootVal){
                rootIndex = i;
                break;
            }
        }

        int leftTreeSize = rootIndex - vinStart;
        root.left = reConstructBinaryTreeHelper(preOrder, preStart + 1, preStart + leftTreeSize, vinOrder, vinStart, rootIndex - 1);

        root.right = reConstructBinaryTreeHelper(preOrder, preStart + leftTreeSize + 1, preEnd, vinOrder, rootIndex + 1 , vinEnd);


        return root;

    }
}
