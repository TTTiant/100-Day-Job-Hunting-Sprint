# 算法题目解析
来源 LeetCode 热题100 双指针部分

## 1. 两数之和 (Two Sum)

### 题目内容
给定一个整数数组 `nums` 和一个目标值 `target`，请你在该数组中找出和为目标值的那两个整数，并返回它们的数组下标。

### 代码实现
```java
import java.util.HashMap;

public class TwoSums {
    // 方法 1: 暴力解法
    public static int[] solution1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    // 方法 2: 哈希表解法
    public static int[] solution2(int[] nums, int target) {
        HashMap<Integer, Integer> hashtable = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    // 方法 3: 双指针解法
    public static int[] solution3(int[] nums, int target) {
        int[][] indexedNums = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            indexedNums[i][0] = nums[i];
            indexedNums[i][1] = i;
        }
        Arrays.sort(indexedNums, (a, b) -> Integer.compare(a[0], b[0]));

        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = indexedNums[left][0] + indexedNums[right][0];
            if (sum == target) {
                return new int[]{indexedNums[left][1], indexedNums[right][1]};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[0];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution3(new int[]{2, 11, 7, 15}, 9)));
    }
}
```

### 问题思路
1. 暴力解法：通过双重循环遍历数组，检查每一对元素的和是否等于目标值。
2. 哈希表解法：使用哈希表存储数组元素及其索引，遍历数组时检查 `target - nums[i]` 是否在哈希表中。
3. 双指针解法：将数组元素与索引一起存储，排序后使用双指针从两端向中间遍历，寻找满足条件的两个数。

### 时间复杂度与空间复杂度
- 暴力解法：
    - 时间复杂度：O(n²)，其中 n 是数组的长度。
    - 空间复杂度：O(1)。
- 哈希表解法：
    - 时间复杂度：O(n)，只需遍历数组一次。
    - 空间复杂度：O(n)，用于存储哈希表。
- 双指针解法：
    - 时间复杂度：O(n log n)，主要是排序的时间复杂度。
    - 空间复杂度：O(n)，用于存储带索引的数组。

总结： 暴力解法空间复杂度最低，而哈希表算法时间复杂度最低。

## 2. 盛最多水的容器 (Max Area)

### 题目内容
给定一个长度为 `n` 的整数数组 `height`，其中 `height[i]` 表示第 `i` 条垂直线的高度。找出两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

### 代码实现
```java
public class MaxArea {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            maxArea = Math.max(maxArea, (right - left) * Math.min(height[left], height[right]));
            if (height[right] < height[left]) {
                right--;
            } else {
                left++;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        MaxArea maxArea = new MaxArea();
        System.out.println(maxArea.maxArea(new int[]{1, 6, 1, 2, 2, 5}));
    }
}
```

### 问题思路
使用双指针法，从数组的两端向中间移动，每次移动高度较小的指针，计算当前容器的面积并更新最大值。

### 时间复杂度与空间复杂度
- 时间复杂度：O(n)，只需遍历数组一次。
- 空间复杂度：O(1)。

---

## 3. 三数之和 (Three Sum)

### 题目内容
给定一个包含 `n` 个整数的数组 `nums`，判断数组中是否存在三个元素 `a`、`b`、`c`，使得 `a + b + c = 0`？请找出所有满足条件且不重复的三元组。

### 代码实现
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSums {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1, right = nums.length - 1;
            int target = -nums[i];
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ThreeSums threeSums = new ThreeSums();
        List<List<Integer>> result = threeSums.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(result);
    }
}
```

### 问题思路
1. 首先对数组进行排序。
2. 使用三重循环的思路，但通过双指针优化为两重循环。
3. 固定一个数，然后使用双指针在剩余数组中寻找两个数，使得三数之和为零。

### 时间复杂度与空间复杂度
- 时间复杂度：O(n²)，其中 n 是数组的长度。
- 空间复杂度：O(1)（不考虑结果存储空间）。

---

## 4. 接雨水 (Trapping Rain Water)

### 题目内容
给定 `n` 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

### 代码实现
```java
public class HoldRainWater {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int result = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    result += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    result += rightMax - height[right];
                }
                right--;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        HoldRainWater solution = new HoldRainWater();
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(solution.trap(height)); // 输出: 6
    }
}
```

### 问题思路
使用双指针法，从数组的两端向中间移动，记录左右两端的最大高度，计算每个位置的雨水量。

双指针法思路

初始化指针和变量：
使用两个指针 left 和 right，分别指向数组的起始和末尾。
使用两个变量 leftMax 和 rightMax 来记录从左到右和从右到左的最大高度。
使用一个变量 result 来记录总的雨水量。

移动指针并计算雨水量：
当 left 指针小于 right 指针时，执行以下步骤：
比较 height[left] 和 height[right] 的大小：
如果 height[left] < height[right]，则处理 left 指针：
如果 height[left] >= leftMax，更新 leftMax 为 height[left]。
否则，计算 leftMax - height[left] 并加到 result 中。
将 left 指针向右移动一位。
否则，处理 right 指针：
如果 height[right] >= rightMax，更新 rightMax 为 height[right]。
否则，计算 rightMax - height[right] 并加到 result 中。
将 right 指针向左移动一位。

返回结果：
当 left 和 right 指针相遇时，返回 result 作为最终的雨水量。

### 时间复杂度与空间复杂度
- 时间复杂度：O(n)，只需遍历数组一次。
- 空间复杂度：O(1)。


