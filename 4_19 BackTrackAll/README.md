# 回溯算法专题

回溯算法是一种通过探索所有可能的候选解来找出所有解的算法。如果候选解被确认不是一个解（或者至少不是最后一个解），回溯算法会通过在上一步进行一些变化来丢弃该解，即"回溯"并尝试其他可能性。

## 1. 组合问题

### 题目：组合 (77. Combinations)

**题目内容**：
给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。可以按任何顺序返回答案。

**解题思路**：
1. 使用回溯法生成所有可能的组合
2. 通过维护起始位置避免重复组合
3. 当组合长度达到k时，将其加入结果集

**代码实现**：
```java
import java.util.ArrayList;
import java.util.List;

public class Combination {
    public List<List<Integer>> combination(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(1, n, k, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int start, int n, int k, List<Integer> list, List<List<Integer>> res) {
        if (k == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= n; i++) {
            list.add(i);
            dfs(i + 1, n, k - 1, list, res);
            list.remove(list.size() - 1);
        }
    }
}
```

**复杂度分析**：
- 时间复杂度：O(C(n,k) * k)，其中C(n,k)是组合数
- 空间复杂度：O(k)，递归栈的深度

## 2. 组合总和问题

### 题目：组合总和 (39. Combination Sum)

**题目内容**：
给定一个无重复元素的数组 candidates 和一个目标数 target，找出 candidates 中所有可以使数字和为 target 的组合。candidates 中的数字可以无限制重复被选取。

**解题思路**：
1. 回溯法遍历所有可能的组合
2. 允许重复选择同一个元素
3. 通过排序和剪枝优化

**代码实现**：
```java
import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    public void dfs(int[] candidates, int target, int start, List<Integer> list, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(list));
        } else {
            for (int i = start; i < candidates.length; i++) {
                if(target < candidates[i]) continue;
                list.add(candidates[i]);
                dfs(candidates, target - candidates[i], i, list, res);
                list.remove(list.size() - 1);
            }
        }
    }
}
```

**复杂度分析**：
- 时间复杂度：O(N^(T/M + 1))，其中N是候选数数量，T是目标值，M是最小候选数
- 空间复杂度：O(T/M)，递归栈的深度

## 3. 组合总和II (40. Combination Sum II)

**题目内容**：
给定一个可能有重复元素的数组 candidates 和一个目标数 target，找出 candidates 中所有可以使数字和为 target 的组合。candidates 中的每个数字在每个组合中只能使用一次。

**解题思路**：
1. 先排序数组
2. 回溯过程中跳过重复元素
3. 每个元素只能使用一次

**代码实现**：
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    public void dfs(int[] candidates, int target, int start, List<Integer> list, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (target - candidates[i] < 0) break;
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            list.add(candidates[i]);
            dfs(candidates, target - candidates[i], i+1, list, res);
            list.remove(list.size() - 1);
        }
    }
}
```

**复杂度分析**：
- 时间复杂度：O(2^N)
- 空间复杂度：O(N)

## 4. 全排列问题 (46. Permutations)

**题目内容**：
给定一个不含重复数字的数组 nums，返回其所有可能的全排列。

**解题思路**：
1. 回溯法生成所有排列
2. 使用used数组标记已使用的元素
3. 每次递归都从数组开头开始，跳过已使用的元素

**代码实现**：
```java
import java.util.ArrayList;
import java.util.List;

public class FullPermutation {
    List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> permute(int[] nums) {
        if(nums == null || nums.length == 0) return res;
        dfs(nums, new boolean[nums.length], new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> res) {
        if(list.size() == nums.length){
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(!used[i]){
                list.add(nums[i]);
                used[i] = true;
                dfs(nums, used, list, res);
                list.remove(list.size() - 1);
                used[i] = false;
            }
        }
    }
}
```

**复杂度分析**：
- 时间复杂度：O(N*N!)
- 空间复杂度：O(N)

## 5. 全排列II (47. Permutations II)

**题目内容**：
给定一个可包含重复数字的序列 nums，按任意顺序返回所有不重复的全排列。

**解题思路**：
1. 先排序数组
2. 回溯过程中跳过重复元素
3. 使用used数组标记已使用的元素

**代码实现**：
```java
import java.util.ArrayList;
import java.util.List;

public class FullPermutatuonII {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        boolean[] used = new boolean[nums.length];
        dfs(nums, used, new ArrayList<>(), res);
        return res;
    }

    public void dfs(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> res) {
        if(list.size() == nums.length){
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(used[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            list.add(nums[i]);
            used[i] = true;
            dfs(nums, used, list, res);
            list.remove(list.size() - 1);
            used[i] = false;
        }
    }
}
```

**复杂度分析**：
- 时间复杂度：O(N*N!)
- 空间复杂度：O(N)

## 6. 子集问题 (78. Subsets)

**题目内容**：
给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

**解题思路**：
1. 回溯法生成所有子集
2. 每次递归都从当前位置的下一个开始
3. 每次递归调用前都保存当前路径

**代码实现**：
```java
import java.util.ArrayList;
import java.util.List;

public class Subset {
    List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> subsets(int[] nums) {
        backTrack(nums, 0, new ArrayList<>());
        return res;
    }

    public void backTrack(int[] nums, int nums_index, List<Integer> list){
        res.add(new ArrayList<>(list));
        for(int i = nums_index; i < nums.length; i++){
            list.add(nums[i]);
            backTrack(nums, i+1, list);
            list.remove(list.size()-1);
        }
    }
}
```

**复杂度分析**：
- 时间复杂度：O(N*2^N)
- 空间复杂度：O(N)

## 7. 子集II (90. Subsets II)

**题目内容**：
给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。解集不能包含重复的子集。

**解题思路**：
1. 先排序数组
2. 回溯过程中跳过重复元素
3. 每次递归调用前都保存当前路径

**代码实现**：
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetII {
    public List<List<Integer>> subset3(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] nums, int start, List<Integer> list, List<List<Integer>> res) {
        res.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);
            dfs(nums, i + 1, list, res);
            list.remove(list.size() - 1);
        }
    }
}
```

**复杂度分析**：
- 时间复杂度：O(N*2^N)
- 空间复杂度：O(N)

## 8. 组合总和III (216. Combination Sum III)

**题目内容**：
找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
- 只使用数字1到9
- 每个数字最多使用一次

**解题思路**：
1. 回溯法在1-9范围内搜索
2. 通过剪枝优化效率
3. 同时满足长度k和和n两个条件

**代码实现**：
```java
import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(1, k, n, new ArrayList<>(), res);
        return res;
    }

    public void dfs(int start, int k, int remain, List<Integer> list, List<List<Integer>> res) {
        if (k == 0 && remain == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        if(k == 0 || remain <= 0) return;

        for(int i = start; i <= 9; i++){
            if(remain < i) break;
            if(9 - i + 1 < k) break;
            list.add(i);
            dfs(i + 1, k-1, remain -i, list, res);
            list.remove(list.size() - 1);
        }
    }
}
```

**复杂度分析**：
- 时间复杂度：O(C(9,k) * k)
- 空间复杂度：O(k)

## 不同类型题解题思路总结

### 组合问题
1. **特点**：顺序不重要，[1,2]和[2,1]是相同的组合
2. **解题关键**：
    - 维护起始位置，避免重复
    - 可能需要剪枝优化
3. **典型题目**：组合(77)、组合总和(39,40)、组合总和III(216)

### 排列问题
1. **特点**：顺序重要，[1,2]和[2,1]是不同的排列
2. **解题关键**：
    - 使用used数组标记已使用的元素
    - 处理重复元素时需要排序和剪枝
3. **典型题目**：全排列(46)、全排列II(47)

### 子集问题
1. **特点**：需要所有可能的子集
2. **解题关键**：
    - 每次递归都保存当前路径
    - 处理重复元素时需要排序和剪枝
3. **典型题目**：子集(78)、子集II(90)

### 通用回溯模板
```java
void backtrack(参数) {
    if (终止条件) {
        存放结果;
        return;
    }
    
    for (选择：本层集合中的元素) {
        处理节点;
        backtrack(路径，选择列表); // 递归
        回溯，撤销处理结果
    }
}
```

### 剪枝技巧
1. **排序**：处理重复元素问题时，先排序数组
2. **跳过条件**：
    - 当前元素与前一个相同且前一个未被使用
    - 剩余元素不足以满足要求
    - 当前路径已经不可能达到目标

通过掌握这些基本模式和剪枝技巧，可以解决大多数回溯问题。关键是根据题目特点调整回溯策略和剪枝条件。