import java.util.*;
// 根据前序遍历和中序遍历建立二叉树，再找出二叉树的右视图。
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public int[] solve(int[] preOrder, int[] inOrder) {
        // write code here
        TreeNode root = buildTree(preOrder, inOrder);
        List<Integer> levelNodeList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                if (i == levelSize - 1) {
                    levelNodeList.add(currentNode.val);
                }
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }

        int[] res = new int[levelNodeList.size()];
        for (int i = 0; i < levelNodeList.size(); i++) {
            res[i] = levelNodeList.get(i);
        }
        return res;
    }

    public TreeNode buildTree(int[] preList, int[] inList) {
        if (preList == null || inList == null) {
            return null;
        }
        return buildTreeHelper(preList, 0, preList.length - 1, inList, 0, inList.length - 1);
    }

    public TreeNode buildTreeHelper(int[] preList, int preStart, int preEnd, int[] inList, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        int rootVal = preList[preStart];
        TreeNode root = new TreeNode(rootVal);
        int inIndex = 0;

        for (int i = inStart; i <= inEnd; i++) {
            if (inList[i] == rootVal) {
                inIndex = i;
                break;
            }
        }
        int leftTreeSize = inIndex - inStart;
        root.left = buildTreeHelper(preList, preStart + 1, preStart + leftTreeSize, inList, inStart, inIndex - 1);
        root.right = buildTreeHelper(preList, preStart + leftTreeSize + 1, preEnd, inList, inIndex + 1, inEnd);

        return root;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取前序遍历数组
        System.out.println("前序遍历数组（以空格分隔）：");     // 12453
        String[] preOrderStr = scanner.nextLine().split(" ");
        int[] preOrder = new int[preOrderStr.length];
        for (int i = 0; i < preOrderStr.length; i++) {
            preOrder[i] = Integer.parseInt(preOrderStr[i]);
        }

        // 读取中序遍历数组
        System.out.println("中序遍历数组（以空格分隔）：");  //  42513
        String[] inOrderStr = scanner.nextLine().split(" ");
        int[] inOrder = new int[inOrderStr.length];
        for (int i = 0; i < inOrderStr.length; i++) {
            inOrder[i] = Integer.parseInt(inOrderStr[i]);
        }

        // 调用solve方法
        Solution solution = new Solution();
        int[] result = solution.solve(preOrder, inOrder);

        // 输出结果
        System.out.println("右视图：");
        for (int value : result) {
            System.out.print(value + " ");
        }
    }
}