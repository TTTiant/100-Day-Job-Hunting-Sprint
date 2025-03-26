```markdown
# 动态规划&&双指针

## 1. 最长递增子序列 (LIS)

### 题目内容
给定一个无序的整数数组，找到其中最长严格递增子序列的长度。

### 解题思路
使用动态规划：
1. 定义dp数组，dp[i]表示以arr[i]结尾的最长递增子序列长度
2. 初始化每个dp[i]=1，因为每个元素本身就是一个子序列
3. 对于每个i，遍历j从0到i-1：
   - 如果arr[j]<arr[i]，则说明arr[j]可以构成递增子序列的长度，
     则dp[i] = max(dp[i], dp[j]+1)
4. 最终结果是dp数组中的最大值

### 代码
```java
public int LIS(int[] arr) {
    if (arr == null || arr.length == 0) return 0;
    int[] dp = new int[arr.length];
    Arrays.fill(dp, 1);
    int res = 1;
    for(int i = 1; i < arr.length; i++){
        for(int j = 0; j < i; j++){
            if(arr[j] < arr[i]){
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        res = Math.max(res, dp[i]);
    }
    return res;
}
```

### 复杂度分析
- 时间复杂度：O(n²)，双重循环
- 空间复杂度：O(n)，dp数组空间

---

## 2. 打家劫舍II (Rob Two)

### 题目内容
房屋围成一圈，不能同时偷相邻的两家，求能偷到的最大金额。

### 解题思路
分两种情况动态规划：
1. 偷第一家就不能偷最后一家，计算0到n-2
2. 不偷第一家可以偷最后一家，计算1到n-1
   取两种情况的最大值

### 代码
```java
public int rob(int[] nums) {
    int n = nums.length;
    if(n == 1) return nums[0];
    
    int[] dp = new int[n + 1];
    // 偷第一家
    dp[1] = nums[0];
    for(int i = 2; i <=n; i++){
        dp[i] = Math.max(dp[i-1], nums[i-1]+ dp[i-2]);
    }
    int res = dp[n-1];
    
    // 不偷第一家
    Arrays.fill(dp,0);
    dp[1] = 0;
    for(int i = 2; i <=n; i++){
        dp[i] = Math.max(dp[i-1], nums[i-1]+ dp[i-2]);
    }
    return Math.max(res,dp[n]);
}
```

### 复杂度分析
- 时间复杂度：O(n)，两次线性遍历
- 空间复杂度：O(n)，dp数组空间

---

## 3. 打家劫舍I (Rob One)

### 题目内容
房屋排成一条直线，不能偷相邻的两家，求能偷到的最大金额。

### 解题思路
动态规划：
- dp[i]表示前i家能偷到的最大金额
- 状态转移：dp[i] = max(dp[i-1], nums[i-1] + dp[i-2])

### 代码
```java
public int rob(int[] nums) {
    if(nums == null) return 0;
    int n = nums.length;
    if(n == 1) return nums[0];
    
    int[] dp = new int[n + 1];
    dp[1] = nums[0];
    for(int i = 2; i <=n;i++){
        dp[i] = Math.max(dp[i-1], nums[i-1] + dp[i-2]);
    }
    return dp[n];
}
```

### 复杂度分析
- 时间复杂度：O(n)
- 空间复杂度：O(n)

---

## 4. 合并区间 (Merge Intervals)

### 题目内容
合并所有重叠的区间。

### 解题思路
1. 按区间起点排序
2. 遍历区间：
    - 如果当前区间与前一区间重叠，合并它们
    - 否则保留当前区间

### 代码
```java
public ArrayList<Interval> merge(ArrayList<Interval> intervals){
    if(intervals.size() == 0) return intervals;
    Collections.sort(intervals, (a,b) -> a.start - b.start);
    
    int i = 1;
    while(i < intervals.size()){
        Interval curr = intervals.get(i);
        Interval prev = intervals.get(i-1);
        if(curr.start <= prev.end){
            prev.end = Math.max(prev.end, curr.end);
            intervals.remove(i);
        }else{
            i++;
        }
    }
    return intervals;
}
```

### 复杂度分析
- 时间复杂度：O(nlogn)，排序耗时
- 空间复杂度：O(1)，原地修改

---

## 5. 验证回文串 (Is Palindrome)

### 题目内容
判断字符串是否是回文（忽略大小写和非字母数字字符）。

### 解题思路
双指针法：
- 左右指针向中间移动
- 跳过非字母数字字符
- 比较左右字符是否相同（忽略大小写）

### 代码
```java
public boolean isPalindrome(String s) {
    if (s.length() == 0) return true;
    int left = 0, right = s.length() - 1;
    while (left < right) {
        if(s.charAt(left)!= s.charAt(right)){
            return false;
        }
        left++;
        right--;
    }
    return true;
}
```

### 复杂度分析
- 时间复杂度：O(n)
- 空间复杂度：O(1)
```