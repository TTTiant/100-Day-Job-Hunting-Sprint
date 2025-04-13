# 动态规划经典问题解析

## 1. 最大子数组和 (Max Subarray)

### 题目内容
给定一个整数数组 `nums`，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

### 解题思路
#### 动态规划解法
- **状态定义**：`dp[i]` 表示以 `nums[i]` 结尾的最大子数组和
- **状态转移**：
    - `dp[i] = max(dp[i-1] + nums[i], nums[i])`
    - 要么延续前面的子数组，要么从当前元素重新开始
- **初始化**：`dp[0] = nums[0]`
- **结果**：`max(dp[0...n-1])`

#### 滑动窗口解法
- 维护一个窗口和 `windowSum`
- 当 `windowSum < 0` 时，重置窗口（因为负数会拖累后续和）
- 始终保持记录最大和 `maxSum`

### 需要注意的点
1. 动态规划解法中 `dp[i]` 必须是以 `nums[i]` 结尾的子数组
2. 滑动窗口解法需要处理全负数的情况

### 代码
```java
// 动态规划解法
public int maxSubarray(int[] nums) {
    int n = nums.length;
    if(n == 0) return 0;
    int[] dp = new int[n];
    dp[0] = nums[0];
    for(int i = 1; i < n; i++) {
        dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
    }
    int res = Integer.MIN_VALUE;
    for(int num : dp) {
        res = Math.max(res, num);
    }
    return res;
}

// 滑动窗口解法
public int maxSubarray2(int[] nums) {
    int left = 0, right = 0;
    int windowSum = 0, maxSum = Integer.MIN_VALUE;
    while (right < nums.length) {
        windowSum += nums[right];
        maxSum = Math.max(maxSum, windowSum);
        while(windowSum < 0) {
            windowSum -= nums[left];
            left++;
        }
        right++;
    }
    return maxSum;
}
```

### 复杂度分析
- 时间复杂度：O(n)
- 空间复杂度：
    - 动态规划：O(n)（可优化为O(1)）
    - 滑动窗口：O(1)

---

## 2. 最长回文子序列 (Longest Palindromic Subsequence)

### 题目内容
给定一个字符串 `s`，找到其中最长的回文子序列的长度。

### 解题思路
- **状态定义**：`dp[i][j]` 表示 `s[i...j]` 的最长回文子序列长度
- **状态转移**：
    - 如果 `s[i] == s[j]`：`dp[i][j] = dp[i+1][j-1] + 2`
    - 否则：`dp[i][j] = max(dp[i+1][j], dp[i][j-1])`
- **初始化**：`dp[i][i] = 1`（单个字符）
- **遍历顺序**：`i` 从后向前，`j` 从 `i+1` 开始

### 需要注意的点
1. 必须反向遍历 `i` 以保证子问题先计算
2. `j` 从 `i+1` 开始不会越界，因为 `j < n`

### 代码
```java
public int longestPalindrome(String s) {
    int n = s.length();
    int[][] dp = new int[n][n];
    for(int i = 0; i < n; i++) {
        dp[i][i] = 1;
    }
    for(int i = n-1; i >= 0; i--) {
        for(int j = i+1; j < n; j++) {
            if(s.charAt(i) == s.charAt(j)) {
                dp[i][j] = dp[i+1][j-1] + 2;
            } else {
                dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
            }
        }
    }
    return dp[0][n-1];
}
```

### 复杂度分析
- 时间复杂度：O(n²)
- 空间复杂度：O(n²)

---

## 3. 编辑距离 (Edit Distance)

### 题目内容
给定两个单词 `word1` 和 `word2`，计算出将 `word1` 转换成 `word2` 所使用的最少操作数（插入、删除、替换）。

### 解题思路
#### DP Table 方法
- **状态定义**：`dp[i][j]` 表示 `word1[0...i-1]` 和 `word2[0...j-1]` 的最小编辑距离
- **状态转移**：
    - 字符相等：`dp[i][j] = dp[i-1][j-1]`
    - 字符不等：`min(插入、删除、替换) + 1`
- **初始化**：
    - `dp[i][0] = i`（全删除）
    - `dp[0][j] = j`（全插入）

#### 备忘录递归方法
- 递归终止条件：
    - `i == -1`：返回 `j+1`（插入剩余字符）
    - `j == -1`：返回 `i+1`（删除剩余字符）

### 需要注意的点
1. DP Table 方法中索引偏移1位处理边界
2. 备忘录方法需要正确处理递归终止条件

### 代码
```java
// DP Table 方法
public int minDistance(String word1, String word2) {
    int n1 = word1.length(), n2 = word2.length();
    int[][] dp = new int[n1+1][n2+1];
    for(int i = 0; i <= n1; i++) dp[i][0] = i;
    for(int j = 0; j <= n2; j++) dp[0][j] = j;
    
    for(int i = 1; i <= n1; i++) {
        for(int j = 1; j <= n2; j++) {
            if(word1.charAt(i-1) == word2.charAt(j-1)) {
                dp[i][j] = dp[i-1][j-1];
            } else {
                dp[i][j] = 1 + Math.min(
                    Math.min(dp[i-1][j], dp[i][j-1]),
                    dp[i-1][j-1]
                );
            }
        }
    }
    return dp[n1][n2];
}

// 备忘录递归方法
private int[][] memo;
public int minDistance2(String word1, String word2) {
    int n1 = word1.length(), n2 = word2.length();
    memo = new int[n1][n2];
    for(int[] row : memo) Arrays.fill(row, -1);
    return dp(word1, n1-1, word2, n2-1);
}

private int dp(String word1, int i, String word2, int j) {
    if(i == -1) return j+1;
    if(j == -1) return i+1;
    if(memo[i][j] != -1) return memo[i][j];
    
    if(word1.charAt(i) == word2.charAt(j)) {
        memo[i][j] = dp(word1, i-1, word2, j-1);
    } else {
        memo[i][j] = 1 + Math.min(
            Math.min(dp(word1, i-1, word2, j), dp(word1, i, word2, j-1)),
            dp(word1, i-1, word2, j-1)
        );
    }
    return memo[i][j];
}
```

### 复杂度分析
- 时间复杂度：O(mn)
- 空间复杂度：O(mn)

---

## 4. 分割等和子集 (Partition Equal Subset Sum)

### 题目内容
给定一个只包含正整数的非空数组，判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

### 解题思路
- 转化为0-1背包问题：
    - 背包容量为 `sum/2`
    - 物品重量和价值均为 `nums[i]`
- **状态定义**：`dp[i][j]` 表示前 `i` 个物品能否凑出和 `j`
- **状态转移**：
    - 不选当前物品：`dp[i][j] = dp[i-1][j]`
    - 选当前物品：`dp[i][j] = dp[i-1][j-nums[i-1]]`
- **初始化**：`dp[i][0] = true`

### 需要注意的点
1. 总和必须为偶数
2. 空间可以优化为一维数组

### 代码
```java
public boolean canPartition(int[] nums) {
    int sum = 0;
    for(int num : nums) sum += num;
    if(sum % 2 != 0) return false;
    sum /= 2;
    
    boolean[] dp = new boolean[sum+1];
    dp[0] = true;
    for(int num : nums) {
        for(int j = sum; j >= num; j--) {
            dp[j] = dp[j] || dp[j-num];
        }
    }
    return dp[sum];
}
```

### 复杂度分析
- 时间复杂度：O(n×sum)
- 空间复杂度：O(sum)