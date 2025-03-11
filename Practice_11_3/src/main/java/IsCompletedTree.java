import java.util.*;

// 判断是不是完全二叉树
public class IsCompletedTree {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param root TreeNode类
     * @return bool布尔型
     */
    public boolean isCompleteTree (TreeNode root) {
        // write code here
        if(root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        boolean isNullPoint = true;

        while(!queue.isEmpty()){
            TreeNode node = queue.poll();

            if(node == null){
                isNullPoint = false;
            }else{
                if(isNullPoint == false){
                    return false;
                }
                queue.offer(node.left);
                queue.offer(node.right);
            }

        }
        return true;
    }
}