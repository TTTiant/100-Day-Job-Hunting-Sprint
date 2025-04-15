# 算法题解集

## 1. 组合总和 (Combination Sum)

### 题目内容
给定一个无重复元素的整数数组 `candidates` 和一个目标整数 `target`，找出 `candidates` 中所有可以使数字和为 `target` 的组合。`candidates` 中的数字可以无限制重复被选取。

### 解题思路
- **回溯算法**：通过递归尝试所有可能的组合。
- **剪枝**：当当前和超过 `target` 时停止递归。
- **避免重复组合**：通过控制遍历的起始位置（`start` 参数）来确保组合的唯一性。

### 需要注意的点
- 数组中的数字可以重复使用。
- 解集不能包含重复的组合。
- 需要对数组进行排序以优化剪枝过程（虽然本题代码中没有排序，但排序后可以提前终止无效路径）。

### 代码
```java
import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> list = new ArrayList<>();
        backTrack(candidates, 0, target, list);
        return result;
    }

    private void backTrack(int[] candidates, int index, int target, List<Integer> list) {
        if (target == 0) {
            result.add(new ArrayList<>(list));
        } else {
            for (int i = index; i < candidates.length; i++) {
                if (candidates[i] > target) continue;
                list.add(candidates[i]);
                backTrack(candidates, i, target - candidates[i], list);
                list.remove(list.size() - 1);
            }
        }
    }
}
```

### 复杂度分析
- **时间复杂度**：O(2^N)，其中 N 是 `candidates` 的长度。最坏情况下需要遍历所有子集。
- **空间复杂度**：O(target)，递归栈的深度取决于目标值。

---

## 2. 组合总和 II (Combination Sum II)

### 题目内容
给定一个可能有重复元素的整数数组 `candidates` 和一个目标整数 `target`，找出 `candidates` 中所有可以使数字和为 `target` 的组合。`candidates` 中的每个数字在每个组合中只能使用一次。

### 解题思路
- **回溯算法**：通过递归尝试所有可能的组合。
- **剪枝**：跳过重复元素和无效路径（当前和超过 `target`）。
- **避免重复组合**：排序后跳过相邻的重复元素。

### 需要注意的点
- 每个数字只能使用一次。
- 解集不能包含重复的组合。
- 必须对数组排序以跳过重复元素。

### 代码
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> state = new ArrayList<>();
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(state, target, candidates, 0, res);
        return res;
    }

    private void backtrack(List<Integer> state, int target, int[] choices, int start, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(state));
            return;
        }
        for (int i = start; i < choices.length; i++) {
            if (target - choices[i] < 0) break;
            if (i > start && choices[i] == choices[i - 1]) continue;
            state.add(choices[i]);
            backtrack(state, target - choices[i], choices, i + 1, res);
            state.remove(state.size() - 1);
        }
    }
}
```

### 复杂度分析
- **时间复杂度**：O(2^N)，其中 N 是 `candidates` 的长度。
- **空间复杂度**：O(N)，递归栈的深度取决于数组长度。

---

## 3. Pow(x, n) (MyPow)

### 题目内容
实现 `pow(x, n)`，即计算 `x` 的 `n` 次幂函数。

### 解题思路
- **快速幂算法**：通过二分法将幂次分解为更小的子问题。
- **处理负数幂**：将 `x` 取倒数，`n` 取绝对值。
- **位运算优化**：通过检查 `n` 的最低位来判断是否需要累乘。

### 需要注意的点
- 需要处理 `n` 为负数的情况。
- 注意整数溢出的问题（本题中 `n` 是 32 位整数）。

### 代码
```java
public class MyPow {
    public double myPow(double x, int n) {
        double result = 1;
        long absN = Math.abs((long)n); // 防止n=Integer.MIN_VALUE溢出
        if (n < 0) x = 1 / x;
        while (absN > 0) {
            if ((absN & 1) == 1) result *= x;
            x *= x;
            absN >>= 1;
        }
        return result;
    }
}
```

### 复杂度分析
- **时间复杂度**：O(log N)，每次将幂次减半。
- **空间复杂度**：O(1)，仅使用常数空间。

---

## 4. N皇后问题 (NQueens)

### 题目内容
在 N×N 的棋盘上放置 N 个皇后，使得它们互不攻击（即任意两个皇后不能在同一行、同一列或同一对角线上）。返回所有可能的棋盘布局。

### 解题思路
- **回溯算法**：逐行放置皇后，并检查当前位置是否合法。
- **合法性检查**：检查当前列、主对角线和副对角线是否有冲突。
- **构造解**：将合法的棋盘状态转换为字符串列表。

### 需要注意的点
- 棋盘用二维字符数组表示，`Q` 表示皇后，`.` 表示空位。
- 每次放置皇后后需要回溯恢复状态。

### 代码
```java
import java.util.ArrayList;
import java.util.List;

public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        backtrack(solutions, board, 0);
        return solutions;
    }

    private void backtrack(List<List<String>> solutions, char[][] board, int row) {
        if (row == board.length) {
            solutions.add(constructSolution(board));
            return;
        }
        for (int col = 0; col < board.length; col++) {
            if (isValid(board, row, col)) {
                board[row][col] = 'Q';
                backtrack(solutions, board, row + 1);
                board[row][col] = '.';
            }
        }
    }

    private boolean isValid(char[][] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    private List<String> constructSolution(char[][] board) {
        List<String> solution = new ArrayList<>();
        for (char[] row : board) {
            solution.add(new String(row));
        }
        return solution;
    }
}
```

### 复杂度分析
- **时间复杂度**：O(N!)，其中 N 是棋盘大小。最坏情况下需要遍历所有排列。
- **空间复杂度**：O(N^2)，用于存储棋盘状态。

--- 

以上是四道经典算法题的详细解析，涵盖了回溯、快速幂等常见算法思想。