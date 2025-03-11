import java.util.*;

public class BinTreeRightViewSight {

    public int[]  rightViewSight(TreeNode root){
        ArrayList<Integer> levelNodes = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                if (i == levelSize - 1) {
                    levelNodes.add(currentNode.val);
                }
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }
        int[] res = new int[levelNodes.size()];

        for (int i = 0; i < levelNodes.size(); i++) {
            res[i] = levelNodes.get(i);
        }
        return res;
    }

}
