```markdown
# 算法题解汇总

## 1. 归并排序 (Merge Sort)

### 题目内容
实现归并排序算法对一个整数数组进行排序

### 注意事项
- 需要额外的O(n)空间用于合并操作
- 注意数组边界条件处理
- 使用System.arraycopy提高数组合并效率

### 代码实现
```java
public void mergeSort(int[] arr) {
    if(arr == null || arr.length <= 1) return;
    int[] temp = new int[arr.length];
    mergeSort(arr, 0, arr.length-1, temp);
}

private void mergeSort(int[] arr, int left, int right, int[] temp) {
    if(left < right) {
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid, temp);
        mergeSort(arr, mid+1, right, temp);
        merge(arr, left, mid, right, temp);
    }
}
```

### 复杂度分析
- 时间复杂度：O(nlogn)
- 空间复杂度：O(n)

---

## 2. 快速排序 (Quick Sort)

### 题目内容
实现快速排序算法

### 注意事项
- 随机选择pivot提高平均性能
- 注意递归终止条件
- 处理相等元素的情况

### 代码实现
```java
public void quickSort(int[] arr) {
    if(arr == null || arr.length <= 1) return;
    quickSort(arr, 0, arr.length-1);
}

private void quickSort(int[] arr, int left, int right) {
    if(left >= right) return;
    int pivot = partition(arr, left, right);
    quickSort(arr, left, pivot-1);
    quickSort(arr, pivot+1, right);
}
```

### 复杂度分析
- 平均时间复杂度：O(nlogn)
- 最坏时间复杂度：O(n²)
- 空间复杂度：O(logn) (递归栈空间)

---

## 3. 买卖股票最佳时机 (Best Time to Buy and Sell Stock)

### 题目内容
给定股票价格数组，计算一次交易能获得的最大利润

### 注意事项
- 只能买卖一次
- 注意空数组和单元素数组处理
- 双指针解法更高效

### 代码实现
```java
public int maxProfit(int[] prices) {
    if(prices == null || prices.length < 2) return 0;
    int minPrice = prices[0];
    int maxProfit = 0;
    
    for(int price : prices) {
        minPrice = Math.min(minPrice, price);
        maxProfit = Math.max(maxProfit, price - minPrice);
    }
    return maxProfit;
}
```

### 复杂度分析
- 时间复杂度：O(n)
- 空间复杂度：O(1)

---

## 4. N皇后问题 (N-Queens)

### 题目内容
在N×N棋盘上放置N个皇后，使其互不攻击

### 注意事项
- 使用回溯算法
- 注意对角线检查
- 棋盘表示方式

### 代码实现
```java
private void backtrack(List<List<String>> solutions, char[][] board, int row) {
    if(row == board.length) {
        solutions.add(constructSolution(board));
        return;
    }
    
    for(int col = 0; col < board.length; col++) {
        if(isValid(board, row, col)) {
            board[row][col] = 'Q';
            backtrack(solutions, board, row+1);
            board[row][col] = '.';
        }
    }
}
```

### 复杂度分析
- 时间复杂度：O(N!)
- 空间复杂度：O(N²)

---

## 5. 二叉树遍历 (Binary Tree Traversal)

### 题目内容
实现二叉树的前序、中序、后序遍历

### 注意事项
- 递归和迭代两种实现
- 栈的使用技巧
- 空节点处理

### 代码实现
```java
// 中序遍历递归实现
public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    inorder(root, res);
    return res;
}

private void inorder(TreeNode root, List<Integer> res) {
    if(root == null) return;
    inorder(root.left, res);
    res.add(root.val);
    inorder(root.right, res);
}
```

### 复杂度分析
- 时间复杂度：O(n)
- 空间复杂度：O(h) (h为树高)

---

## 6. 二叉树最大深度 (Maximum Depth of Binary Tree)

### 题目内容
计算二叉树的最大深度

### 注意事项
- 空树深度为0
- 递归计算左右子树深度

### 代码实现
```java
public int maxDepth(TreeNode root) {
    if(root == null) return 0;
    return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
}
```

### 复杂度分析
- 时间复杂度：O(n)
- 空间复杂度：O(h)

---

## 7. 对称二叉树 (Symmetric Tree)

### 题目内容
判断二叉树是否对称

### 注意事项
- 镜像比较
- 空节点处理

### 代码实现
```java
public boolean isSymmetric(TreeNode root) {
    return check(root.left, root.right);
}

private boolean check(TreeNode left, TreeNode right) {
    if(left == null && right == null) return true;
    if(left == null || right == null) return false;
    return left.val == right.val 
        && check(left.left, right.right) 
        && check(left.right, right.left);
}
```

### 复杂度分析
- 时间复杂度：O(n)
- 空间复杂度：O(h)

---

## 8. 二叉树直径 (Diameter of Binary Tree)

### 题目内容
计算二叉树的最大直径(任意两节点间最长路径)

### 注意事项
- 直径可能不经过根节点
- 需要同时计算深度和直径

### 代码实现
```java
int ans = 0;
public int diameterOfBinaryTree(TreeNode root) {
    depth(root);
    return ans;
}

private int depth(TreeNode node) {
    if(node == null) return 0;
    int left = depth(node.left);
    int right = depth(node.right);
    ans = Math.max(ans, left + right);
    return Math.max(left, right) + 1;
}
```

### 复杂度分析
- 时间复杂度：O(n)
- 空间复杂度：O(h)
```