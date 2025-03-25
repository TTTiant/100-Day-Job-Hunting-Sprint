# 回溯 BackTrack && Review

## 1. 子集问题 (Subsets)

### 题目内容
给定一个不含重复元素的整数数组 `nums`，返回所有可能的子集（幂集）。

### 代码实现
```java
import java.util.*;

public class Subset {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> list = new ArrayList<>();
        backTrack(nums, 0, list);
        return res;
    }

    public void backTrack(int[] nums, int start, List<Integer> list) {
        res.add(new ArrayList<>(list));
        
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            backTrack(nums, i + 1, list);
            list.remove(list.size() - 1);
        }
    }
}
```

### 解题思路
1. 回溯算法：通过递归遍历所有可能的组合
2. 关键点：
    - 每次递归都先添加当前子集到结果中
    - 从当前索引开始遍历，避免重复
    - 递归后撤销选择（回溯）

### 复杂度分析
- 时间复杂度：O(N×2^N)，共有2^N个子集，每个子集平均需要O(N)时间
- 空间复杂度：O(N)，递归栈的深度

---

## 2. 全排列问题 (Permutations)

### 题目内容
给定一个不含重复数字的数组 `nums`，返回所有可能的全排列。

### 代码实现
```java
import java.util.ArrayList;
import java.util.LinkedList;

public class Permute {
    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    LinkedList<Integer> list = new LinkedList<>();

    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        if(num == null || num.length == 0) return res;
        boolean[] used = new boolean[num.length];
        backTrack(num, used, list);
        return res;
    }

    public void backTrack(int[] num, boolean[] used, LinkedList<Integer> list) {
        if(list.size() == num.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = 0; i < num.length; i++) {
            if(used[i]) continue;
            used[i] = true;
            list.add(num[i]);
            backTrack(num, used, list);
            list.removeLast();
            used[i] = false;
        }
    }
}
```

### 解题思路
1. 回溯算法：通过递归生成所有排列
2. 关键点：
    - 使用`used`数组标记已使用的元素
    - 当路径长度等于数组长度时，保存结果
    - 递归后撤销选择（回溯）

### 复杂度分析
- 时间复杂度：O(N×N!)，共有N!种排列，每种排列需要O(N)时间
- 空间复杂度：O(N)，递归栈深度和`used`数组

---

## 3. 全排列II (Unique Permutations)

### 题目内容
给定一个可包含重复数字的序列 `nums`，返回所有不重复的全排列。

### 代码实现
```java
import java.util.ArrayList;
import java.util.Arrays;

public class UniPermute {
    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    ArrayList<Integer> list = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> uniquePermute(int[] num) {
        if (num.length == 0 || num == null) return res;
        Arrays.sort(num);
        boolean[] used = new boolean[num.length];
        backTrack(num, used, list, res);
        return res;
    }

    public void backTrack(int[] num, boolean[] used, ArrayList<Integer> list, 
                         ArrayList<ArrayList<Integer>> res) {
        if (list.size() == num.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < num.length; i++) {
            if (used[i]) continue;
            if(i > 0 && num[i] == num[i-1] && !used[i-1]) continue;
            list.add(num[i]);
            used[i] = true;
            backTrack(num, used, list, res);
            list.remove(list.size()-1);
            used[i] = false;
        }
    }
}
```

### 解题思路
1. 回溯+剪枝：
    - 先排序数组以便检测重复
    - 跳过重复元素和已使用元素
2. 关键点：
    - `!used[i-1]`确保相同元素按顺序使用
    - 其他与普通全排列类似

### 复杂度分析
- 时间复杂度：O(N×N!)，最坏情况下
- 空间复杂度：O(N)

---

## 4. 用栈实现队列 (Stack-based Queue)

### 题目内容
使用两个栈实现一个队列的下列操作：
- push(x)
- pop()
- peek()
- empty()

### 代码实现
```java
import java.util.Stack;

public class Stacks2Queue<T> {
    private Stack<T> stack1; // 入队栈
    private Stack<T> stack2; // 出队栈

    public Stacks2Queue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(T x) {
        stack1.push(x);
    }

    public T pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.isEmpty()) throw new RuntimeException("Queue is empty!");
        return stack2.pop();
    }

    public T peek() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.isEmpty()) throw new RuntimeException("Queue is empty!");
        return stack2.peek();
    }

    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
```

### 解题思路
1. 双栈法：
    - stack1专门用于入队
    - stack2专门用于出队
    - 当stack2为空时，将stack1所有元素倒入stack2

### 复杂度分析
- 入队：O(1)
- 出队：摊还O(1)
- 空间：O(N)

---

## 5. 零钱兑换 (Coin Change)

### 题目内容
给定不同面额的硬币和一个总金额，计算可以凑成总金额所需的最少硬币数。

### 代码实现
```java
import java.util.Arrays;

public class GetChange {
    public int GetChange(int[] arr, int aim) {
        if(aim < 1) return 0;
        if(arr == null || arr.length == 0) return -1;
        
        int[] dp = new int[aim+1];
        Arrays.fill(dp, aim+1);
        dp[0] = 0;
        
        for(int coin : arr) {
            for(int i = coin; i <= aim; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[aim] > aim ? -1 : dp[aim];
    }
}
```

### 解题思路
1. **动态规划**：
    - dp[i]表示金额i所需最少硬币数
    - 初始化dp数组为最大值
    - 遍历每种硬币，更新dp数组

### 复杂度分析
- 时间复杂度：O(M×N)，M是金额，N是硬币种类
- 空间复杂度：O(M)

---

## 6. 腐烂的橘子 (Rotting Oranges)

### 题目内容
在给定的网格中，每个单元格可以有以下三个值之一：
- 0 代表空单元格
- 1 代表新鲜橘子
- 2 代表腐烂的橘子
  每分钟，任何与腐烂的橘子相邻的新鲜橘子都会腐烂。返回直到没有新鲜橘子为止所必须经过的最小分钟数。

### 代码实现
```java
import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;
        
        // 初始化队列和新鲜橘子计数
        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < cols; c++) {
                if(grid[r][c] == 2) queue.offer(new int[]{r, c});
                else if(grid[r][c] == 1) fresh++;
            }
        }
        
        if(fresh == 0) return 0;
        
        int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        int minutes = 0;
        
        while(!queue.isEmpty() && fresh > 0) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                for(int[] dir : dirs) {
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];
                    if(nr >= 0 && nc >= 0 && nr < rows && nc < cols && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;
                        queue.offer(new int[]{nr, nc});
                        fresh--;
                    }
                }
            }
            if(!queue.isEmpty()) minutes++;
        }
        return fresh == 0 ? minutes : -1;
    }
}
```

### 解题思路
1. 多源BFS：
    - 初始将所有腐烂橘子入队
    - 进行层级遍历，每分钟处理一层
    - 统计感染所有新鲜橘子的时间

### 复杂度分析
- 时间复杂度：O(M×N)
- 空间复杂度：O(M×N)