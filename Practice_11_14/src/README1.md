```markdown
# 算法题解汇总 (快慢指针、二分查找、递归、动态规划)
来源：leetcode hot 100 && 牛客面试101

## 1. 寻找重复数 (Find Duplicate Number)
### 题目内容
给定一个包含 `n + 1` 个整数的数组 `nums`，其中每个整数都在 `[1, n]` 范围内。假设数组中只有一个重复的数字，找出这个重复的数。

### 解题思路
1. **二分查找法**：
   - 在 `[1, n]` 范围内进行二分查找。
   - 统计数组中 <= mid 的数的个数，若个数 > mid，说明重复数在左半部分，否则在右半部分。

2. **快慢指针法**：
   - 将数组视为链表，使用 Floyd 判圈算法。
   - 快指针每次走两步，慢指针每次走一步，相遇后慢指针回到起点，两指针同步移动，再次相遇点即为重复数。

### 注意的点
- 二分查找法的时间复杂度为 O(nlogn)，空间复杂度 O(1)。
- 快慢指针法的时间复杂度为 O(n)，空间复杂度 O(1)。

### 代码
```java
// 二分查找法
public int findDuplicate1(int[] nums) {
    int left = 1, right = nums.length - 1;
    while (left < right) {
        int mid = (left + right) / 2;
        int count = 0;
        for (int num : nums) {
            if (num <= mid) count++;
        }
        if (count > mid) right = mid;
        else left = mid + 1;
    }
    return left;
}

// 快慢指针法
public int findDuplicate2(int[] nums) {
    int slow = nums[0], fast = nums[0];
    do {
        slow = nums[slow];
        fast = nums[nums[fast]];
    } while (slow != fast);
    slow = nums[0];
    while (slow != fast) {
        slow = nums[slow];
        fast = nums[fast];
    }
    return slow;
}
```

### 复杂度分析
- 二分查找法：时间复杂度 O(nlogn)，空间复杂度 O(1)。
- 快慢指针法：时间复杂度 O(n)，空间复杂度 O(1)。

---

## 2. 二叉树的最近公共祖先 (Lowest Common Ancestor)
### 题目内容
给定一个二叉树和两个节点 p 和 q，找到它们的最近公共祖先。

### 解题思路
1. **普通二叉树**：
    - 递归遍历左右子树，若左右子树分别包含 p 和 q，则当前节点为 LCA。
    - 若只有一侧子树包含 p 或 q，则返回该子树的结果。

2. **二叉搜索树**：
    - 利用 BST 的性质，若 p 和 q 都小于当前节点值，则在左子树中查找；若都大于，则在右子树中查找；否则当前节点即为 LCA。

### 注意的点
- 普通二叉树的解法适用于任意二叉树。
- BST 的解法利用了 BST 的有序性质，效率更高。

### 代码
```java
// 普通二叉树
private TreeNode helper(TreeNode root, int p, int q) {
    if (root == null || root.val == p || root.val == q) return root;
    TreeNode left = helper(root.left, p, q);
    TreeNode right = helper(root.right, p, q);
    if (left == null) return right;
    if (right == null) return left;
    return root;
}

// 二叉搜索树
public int findCommonAncestorBST(TreeNode root, int p, int q) {
    if (root == null) return -1;
    if ((p >= root.val && q <= root.val) || (p <= root.val && q >= root.val)) return root.val;
    if (p <= root.val && q <= root.val) return findCommonAncestorBST(root.left, p, q);
    else return findCommonAncestorBST(root.right, p, q);
}
```

### 复杂度分析
- 普通二叉树：时间复杂度 O(n)，空间复杂度 O(n)。
- 二叉搜索树：时间复杂度 O(h)，空间复杂度 O(h)，其中 h 为树高。

---

## 3. 环形链表检测 (Cycle Linked List)
### 题目内容
给定一个链表，判断链表中是否有环，并找出环的起始节点。

### 解题思路
1. **检测环**：
    - 使用快慢指针，快指针每次走两步，慢指针每次走一步，若相遇则说明有环。

2. **找环的起始节点**：
    - 相遇后，将慢指针重置到链表头，快慢指针同步移动，再次相遇点即为环的起始节点。

### 注意的点
- 快指针需要检查 `fast != null && fast.next != null` 以避免空指针异常。

### 代码
```java
public boolean CycleDetect(ListNode head) {
    if (head == null || head.next == null) return false;
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (fast == slow) return true;
    }
    return false;
}

public ListNode findCycleNode(ListNode head) {
    if (head == null || head.next == null) return null;
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        fast = fast.next.next;
        slow = slow.next;
        if (fast == slow) break;
    }
    if (fast == null || fast.next == null) return null;
    slow = head;
    while (slow != fast) {
        fast = fast.next;
        slow = slow.next;
    }
    return slow;
}
```

### 复杂度分析
- 时间复杂度：O(n)。
- 空间复杂度：O(1)。

---

## 4. 颜色排序 (Sort Colors)
### 题目内容
给定一个包含红色、白色和蓝色的数组，原地对它们进行排序，使得相同颜色的元素相邻，且按照红、白、蓝的顺序排列。

### 解题思路
- 使用三指针法（荷兰国旗问题）：
    - `ptr0` 指向红色区域的末尾。
    - `ptr1` 指向白色区域的末尾。
    - 遍历数组，将 0 交换到 `ptr0`，将 1 交换到 `ptr1`。

### 注意的点
- 需要处理交换后的元素位置变化。

### 代码
```java
public void sortColors(int[] nums) {
    int ptr0 = 0, ptr1 = 0;
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] == 0) {
            swap(nums, i, ptr0);
            if (ptr0 < ptr1) swap(nums, i, ptr1);
            ptr0++;
            ptr1++;
        } else if (nums[i] == 1) {
            swap(nums, i, ptr1);
            ptr1++;
        }
    }
}

private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
```

### 复杂度分析
- 时间复杂度：O(n)。
- 空间复杂度：O(1)。

---

## 5. 完全平方数 (Perfect Squares)
### 题目内容
给定一个正整数 n，找到若干个完全平方数（如 1, 4, 9, 16, ...）使得它们的和等于 n。返回组成和的最少完全平方数的数量。

### 解题思路
- **动态规划**：
    - `dp[i]` 表示组成 i 的最少完全平方数的数量。
    - 对于每个 i，遍历所有可能的 j（j*j <= i），更新 `dp[i] = min(dp[i], dp[i - j*j] + 1)`。

### 注意的点
- 初始化 `dp[0] = 0`，其他为 `Integer.MAX_VALUE`。

### 代码
```java
public int numSquares(int n) {
    int[] dp = new int[n + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j * j <= i; j++) {
            dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
        }
    }
    return dp[n];
}
```

### 复杂度分析
- 时间复杂度：O(n√n)。
- 空间复杂度：O(n)。

---

## 6. 杨辉三角 (Pascal's Triangle)
### 题目内容
给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。

### 解题思路
- 动态生成每一行：
    - 每行的第一个和最后一个元素为 1。
    - 中间元素为上一行的相邻两个元素之和。

### 注意的点
- 需要处理边界条件（如 numRows = 0）。

### 代码
```java
public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> triangle = new ArrayList<>();
    for (int i = 0; i < numRows; i++) {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        for (int j = 1; j < i; j++) {
            row.add(triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j));
        }
        if (i > 0) row.add(1);
        triangle.add(row);
    }
    return triangle;
}
```

### 复杂度分析
- 时间复杂度：O(n²)。
- 空间复杂度：O(n²)。
```