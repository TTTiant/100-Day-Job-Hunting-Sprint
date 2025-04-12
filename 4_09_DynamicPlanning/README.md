```markdown
# 动态规划算法题解

## 1. 零钱兑换 (CoinChange)

### 题目内容
给定不同面额的硬币 `coins` 和总金额 `amount`，计算可以凑成总金额所需的最少硬币数。如果无法凑成，返回 -1。

### 解题思路
- **状态定义**：`dp[i]` 表示凑成金额 `i` 所需的最少硬币数。
- **初始化**：`dp[0] = 0`，其他初始化为 `amount + 1`（表示不可达）。
- **状态转移**：对于每个金额 `i` 和硬币 `coin`，如果 `i >= coin`，则 `dp[i] = min(dp[i], dp[i - coin] + 1)`。
- **遍历顺序**：正序遍历（完全背包问题，硬币可重复使用）。

### 代码
```java
public int coinChange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, amount + 1);
    dp[0] = 0;
    for (int i = 1; i <= amount; i++) {
        for (int coin : coins) {
            if (i >= coin) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
    }
    return dp[amount] > amount ? -1 : dp[amount];
}
```

### 复杂度分析
- **时间复杂度**：O(N × amount)，其中 N 是硬币种类数。
- **空间复杂度**：O(amount)。

---

## 2. 完全背包问题 (CompleteKnapsack)

### 题目内容
给定物品的重量 `wt`、价值 `val` 和背包容量 `W`，每种物品可无限选取，求背包能装的最大价值。

### 解题思路
- **状态定义**：`dp[w]` 表示容量为 `w` 的背包的最大价值。
- **初始化**：`dp[0] = 0`，其他初始为 0。
- **状态转移**：对于每个物品 `i` 和容量 `w`，`dp[w] = max(dp[w], dp[w - wt[i]] + val[i])`。
- **遍历顺序**：正序遍历（允许重复选取）。

### 代码
```java
public int completeKnapsack(int W, int[] wt, int[] val) {
    int[] dp = new int[W + 1];
    for (int i = 0; i < wt.length; i++) {
        for (int w = wt[i]; w <= W; w++) {
            dp[w] = Math.max(dp[w], dp[w - wt[i]] + val[i]);
        }
    }
    return dp[W];
}
```

### 复杂度分析
- **时间复杂度**：O(N × W)，其中 N 是物品数量。
- **空间复杂度**：O(W)。

---

## 3. 斐波那契数列 (Fibo)

### 题目内容
返回斐波那契数列的第 `n` 项。

### 解题思路
- **递归法**：直接递归，但效率低（O(2^n)）。
- **动态规划**：用数组存储中间结果，避免重复计算（O(n)）。

### 代码
```java
// 递归法
public int fibo(int n) {
    if (n <= 1) return n;
    return fibo(n - 1) + fibo(n - 2);
}

// 动态规划
public int fibo2(int n) {
    int[] dp = new int[n + 2];
    dp[0] = 0; dp[1] = 1;
    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
}
```

### 复杂度分析
- **递归法**：O(2^n)。
- **动态规划**：O(n)，空间 O(n)。

---

## 4. 最低票价 (FindCheapestPrice)

### 题目内容
给定航班信息 `flights`（起点、终点、价格），求从 `src` 到 `dst` 最多经过 `k` 次中转的最低价格。

### 解题思路
- **状态定义**：`dp[t][i]` 表示经过 `t` 次中转到达城市 `i` 的最低价格。
- **初始化**：`dp[0][src] = 0`，其他初始为 `Integer.MAX_VALUE`。
- **状态转移**：对于每个航班 `(s, d, cost)`，`dp[t][d] = min(dp[t][d], dp[t-1][s] + cost)`。
- **遍历顺序**：按中转次数 `t` 从小到大遍历。

### 代码
```java
public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    int[][] dp = new int[k + 2][n];
    for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
    dp[0][src] = 0;
    for (int t = 1; t <= k + 1; t++) {
        dp[t][src] = 0;
        for (int[] flight : flights) {
            int s = flight[0], d = flight[1], cost = flight[2];
            if (dp[t - 1][s] != Integer.MAX_VALUE) {
                dp[t][d] = Math.min(dp[t][d], dp[t - 1][s] + cost);
            }
        }
    }
    int minCost = Integer.MAX_VALUE;
    for (int t = 0; t <= k + 1; t++) {
        minCost = Math.min(minCost, dp[t][dst]);
    }
    return minCost == Integer.MAX_VALUE ? -1 : minCost;
}
```

### 复杂度分析
- **时间复杂度**：O(K × E)，其中 K 是中转次数，E 是航班数。
- **空间复杂度**：O(K × n)。

---

## 5. 最长递增子序列 (LongestLIS)

### 题目内容
给定数组 `arr`，返回最长严格递增子序列的长度。

### 解题思路
- **状态定义**：`dp[i]` 表示以 `arr[i]` 结尾的最长递增子序列长度。
- **初始化**：`dp[i] = 1`（每个元素本身是一个子序列）。
- **状态转移**：对于每个 `i`，遍历 `j < i`，如果 `arr[i] > arr[j]`，则 `dp[i] = max(dp[i], dp[j] + 1)`。

### 代码
```java
public int longestLIS(int[] arr) {
    int[] dp = new int[arr.length];
    Arrays.fill(dp, 1);
    for (int i = 1; i < arr.length; i++) {
        for (int j = 0; j < i; j++) {
            if (arr[i] > arr[j]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
    }
    int res = 0;
    for (int num : dp) res = Math.max(res, num);
    return res;
}
```

### 复杂度分析
- **时间复杂度**：O(n^2)。
- **空间复杂度**：O(n)。

---

## 6. 最小路径和 (ShortestPathSum)

### 题目内容
给定二维数组 `grid`，从左上角到右下角，每次只能向右或向下移动，求路径和的最小值。

### 解题思路
- **状态定义**：`dp[i][j]` 表示从 `(0,0)` 到 `(i,j)` 的最小路径和。
- **初始化**：第一行和第一列直接累加。
- **状态转移**：`dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]`。

### 代码
```java
public int minPathSum(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int[][] dp = new int[m][n];
    dp[0][0] = grid[0][0];
    for (int i = 1; i < m; i++) dp[i][0] = dp[i - 1][0] + grid[i][0];
    for (int j = 1; j < n; j++) dp[0][j] = dp[0][j - 1] + grid[0][j];
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
        }
    }
    return dp[m - 1][n - 1];
}
```

### 复杂度分析
- **时间复杂度**：O(m × n)。
- **空间复杂度**：O(m × n)。

---

## 7. 0-1 背包问题 (ZeroOneKnapsack)

### 题目内容
给定物品的重量 `wt`、价值 `val` 和背包容量 `W`，每种物品只能选一次，求背包能装的最大价值。

### 解题思路
- **状态定义**：`dp[i][w]` 表示前 `i` 个物品中容量为 `w` 的最大价值。
- **初始化**：`dp[0][w] = 0`。
- **状态转移**：
    - 不选当前物品：`dp[i][w] = dp[i-1][w]`。
    - 选当前物品：`dp[i][w] = dp[i-1][w-wt[i-1]] + val[i-1]`。
- **遍历顺序**：逆序遍历（防止重复选取）。

### 代码
```java
public int knapsack(int W, int N, int[] wt, int[] val) {
    int[][] dp = new int[N + 1][W + 1];
    for (int i = 1; i <= N; i++) {
        for (int w = 1; w <= W; w++) {
            if (w < wt[i - 1]) {
                dp[i][w] = dp[i - 1][w];
            } else {
                dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - wt[i - 1]] + val[i - 1]);
            }
        }
    }
    return dp[N][W];
}
```

### 复杂度分析
- **时间复杂度**：O(N × W)。
- **空间复杂度**：O(N × W)。

---

## 完全背包 vs. 0-1 背包

| 特性                | 完全背包                          | 0-1 背包                          |
|---------------------|----------------------------------|----------------------------------|
| **物品选择次数**     | 无限次                           | 最多一次                         |
| **遍历顺序**         | 正序（允许重复）                 | 逆序（防止重复）                 |
| **状态转移方程**     | `dp[w] = max(dp[w], dp[w-wt]+val)` | `dp[w] = max(dp[w], dp[w-wt]+val)` |
| **典型问题**         | 零钱兑换                         | 经典背包问题                     |
```