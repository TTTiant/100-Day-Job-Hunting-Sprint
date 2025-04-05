# 算法题
来源：leetcode + 牛客

## 1. 最长递增子序列 (TheLongestIncreSubsequence)

### 题目内容
给定一个无序的整数数组，找到其中最长严格递增子序列的长度。

### 解题思路

#### 方法一：动态规划
1. **定义状态**：dp[i]表示以nums[i]结尾的最长递增子序列长度
2. **状态转移**：对于每个i，遍历j从0到i-1，如果nums[i]>nums[j]，则dp[i] = max(dp[i], dp[j]+1)
3. **初始化**：每个dp[i]初始为1
4. **结果**：dp数组中的最大值

#### 方法二：贪心+二分查找
1. **维护tail数组**：tail[i]表示长度为i+1的递增子序列的最小末尾元素
2. **遍历数组**：
    - 如果当前数大于tail最后一个元素，直接追加
    - 否则二分查找第一个≥当前数的位置并替换
3. **结果**：tail数组的长度即为答案

### 注意事项
- 动态规划方法时间复杂度O(n²)，适合小规模数据
- 贪心方法时间复杂度O(nlogn)，适合大规模数据
- 二分查找时注意边界条件

### 代码
```java
// 动态规划实现
public int lengthOfLIS(int[] nums) {
    if(nums.length == 0) return 0;
    int[] dp = new int[nums.length];
    Arrays.fill(dp, 1);
    int res = 1;
    for(int i = 1; i < nums.length; i++){
        for(int j = 0; j < i; j++){
            if(nums[i] > nums[j]){
                dp[i] = Math.max(dp[i], dp[j]+1);
            }
        }
        res = Math.max(res, dp[i]);
    }
    return res;
}

// 贪心+二分实现
public int lengthOfLIS2(int[] nums) {
    int[] tail = new int[nums.length];
    int size = 0;
    for(int num : nums){
        int left = 0, right = size;
        while(left < right){
            int mid = left + (right-left)/2;
            if(tail[mid] < num) left = mid+1;
            else right = mid;
        }
        if(left == size) tail[size++] = num;
        else tail[left] = num;
    }
    return size;
}
```

### 复杂度分析
- 动态规划：
    - 时间：O(n²)
    - 空间：O(n)
- 贪心+二分：
    - 时间：O(nlogn)
    - 空间：O(n)

---

## 2. 最长公共前缀 (TheLongestCommonPrefix)

### 题目内容
查找字符串数组中的最长公共前缀。

### 解题思路
1. **纵向扫描**：
    - 以第一个字符串为基准
    - 逐列比较所有字符串的字符
    - 遇到不匹配或字符串长度不足时返回

### 注意事项
- 处理空数组情况
- 注意字符串越界问题
- 时间复杂度最坏情况下O(mn)，m为字符串平均长度，n为字符串数量

### 代码
```java
public String longestCommonPrefix(String[] strs) {
    if(strs == null || strs.length == 0) return "";
    for(int i = 0; i < strs[0].length(); i++){
        char c = strs[0].charAt(i);
        for(int j = 1; j < strs.length; j++){
            if(i == strs[j].length() || strs[j].charAt(i) != c){
                return strs[0].substring(0, i);
            }
        }
    }
    return strs[0];
}
```

### 复杂度分析
- 时间：O(mn)，m为最短字符串长度，n为字符串数量
- 空间：O(1)

---

## 3. 腐烂的橘子 (RottedOranges)

### 题目内容
计算网格中所有橘子腐烂所需的最少分钟数。

### 解题思路
1. **BFS**：
    - 统计新鲜橘子数量，腐烂橘子入队
    - 多源BFS，记录层数
    - 每次扩散到相邻新鲜橘子
2. **终止条件**：
    - 队列为空或没有新鲜橘子

### 注意事项
- 使用队列进行BFS
- 注意网格边界检查
- 每层扩散时间+1
- 最后检查是否还有新鲜橘子

### 代码
```java
public int rottedOranges(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    Queue<int[]> q = new LinkedList<>();
    int fresh = 0;
    
    for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
            if(grid[i][j] == 1) fresh++;
            else if(grid[i][j] == 2) q.offer(new int[]{i,j});
        }
    }
    
    if(fresh == 0) return 0;
    
    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    int time = 0;
    
    while(!q.isEmpty() && fresh > 0){
        int size = q.size();
        for(int i = 0; i < size; i++){
            int[] cur = q.poll();
            for(int[] d : dirs){
                int x = cur[0]+d[0], y = cur[1]+d[1];
                if(x>=0 && y>=0 && x<m && y<n && grid[x][y]==1){
                    grid[x][y] = 2;
                    q.offer(new int[]{x,y});
                    fresh--;
                }
            }
        }
        time++;
    }
    
    return fresh == 0 ? time : -1;
}
```

### 复杂度分析
- 时间：O(mn)
- 空间：O(mn)

---

## 4. LFU缓存 (LFU)

### 题目内容
设计并实现最不经常使用(LFU)缓存的数据结构。

### 解题思路
1. **数据结构**：
    - keyToValue：存储键值对
    - keyToFreq：存储键的使用频率
    - freqToKeys：存储频率到键集合的映射(LinkedHashSet保持顺序)
2. **操作**：
    - get：更新频率，调整相关数据结构
    - put：新增或更新键值，容量满时淘汰最少使用的键

### 注意事项
- 频率更新时要调整三个数据结构
- 淘汰时选择最小频率中最久未使用的键
- 使用computeIfAbsent简化代码
- 注意边界条件处理

### 代码
```java
class LFUCache {
    private final int capacity;
    private final HashMap<Integer, Integer> keyToValue;
    private final HashMap<Integer, Integer> keyToFreq;
    private final HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
    private int minFreq;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.keyToValue = new HashMap<>();
        this.keyToFreq = new HashMap<>();
        this.freqToKeys = new HashMap<>();
        this.minFreq = 0;
    }

    public int get(int key) {
        if(!keyToValue.containsKey(key)) return -1;
        // 更新频率
        int freq = keyToFreq.get(key);
        freqToKeys.get(freq).remove(key);
        if(freq == minFreq && freqToKeys.get(freq).isEmpty()) minFreq++;
        freqToKeys.computeIfAbsent(freq+1, k->new LinkedHashSet<>()).add(key);
        keyToFreq.put(key, freq+1);
        return keyToValue.get(key);
    }

    public void put(int key, int value) {
        if(capacity <= 0) return;
        if(keyToValue.containsKey(key)){
            keyToValue.put(key, value);
            get(key); // 更新频率
            return;
        }
        if(keyToValue.size() >= capacity){
            int evict = freqToKeys.get(minFreq).iterator().next();
            freqToKeys.get(minFreq).remove(evict);
            keyToValue.remove(evict);
            keyToFreq.remove(evict);
        }
        keyToValue.put(key, value);
        keyToFreq.put(key, 1);
        freqToKeys.computeIfAbsent(1, k->new LinkedHashSet<>()).add(key);
        minFreq = 1;
    }
}
```

### 复杂度分析
- get/put操作：平均O(1)
- 空间：O(n)

---

## 5. 岛屿数量 (IslandNums)

### 题目内容
计算二维网格中岛屿的数量。

### 解题思路
1. **DFS**：
    - 遍历网格，遇到'1'时启动DFS
    - DFS将所有相连的'1'标记为'0'
    - 每次启动DFS计数+1
2. **方向处理**：上下左右四个方向

### 注意事项
- 先检查边界条件再访问数组
- 使用原地修改标记已访问
- 注意网格为null或空的情况

### 代码
```java
public int numIslands(char[][] grid) {
    if(grid == null || grid.length == 0) return 0;
    int count = 0;
    for(int i = 0; i < grid.length; i++){
        for(int j = 0; j < grid[0].length; j++){
            if(grid[i][j] == '1'){
                dfs(grid, i, j);
                count++;
            }
        }
    }
    return count;
}

private void dfs(char[][] grid, int i, int j){
    if(i<0 || j<0 || i>=grid.length || j>=grid[0].length || grid[i][j]!='1') return;
    grid[i][j] = '0';
    dfs(grid, i+1, j);
    dfs(grid, i-1, j);
    dfs(grid, i, j+1);
    dfs(grid, i, j-1);
}
```

### 复杂度分析
- 时间：O(mn)
- 空间：O(mn) (递归栈最坏情况)