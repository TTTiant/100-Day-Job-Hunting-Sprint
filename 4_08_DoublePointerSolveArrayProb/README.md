# 算法题目解析

## 1. 最长回文子串 (Longest Palindrome)

**题目内容**: 给定一个字符串 `s`，找到 `s` 中最长的回文子串。

**解题思路**:
- 使用中心扩展法，遍历字符串中的每个字符作为中心
- 考虑奇数长度和偶数长度的回文串情况
- 对每个中心向两边扩展，直到字符不匹配
- 比较并记录最长的回文子串

**注意的点**:
- 需要处理奇数长度和偶数长度的回文串
- 注意字符串索引不要越界
- 使用substring方法时注意区间是左闭右开

**代码**:
```java
public class LongestPalidrome {
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            // 以 s[i] 为中心的最长回文子串
            String s1 = palindrome(s, i, i);
            // 以 s[i] 和 s[i+1] 为中心的最长回文子串
            String s2 = palindrome(s, i, i + 1);
            // res = longest(res, s1, s2)
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }

    public String palindrome(String s, int l, int r) {
        // 防止索引越界
        while (l >= 0 && r < s.length()
                && s.charAt(l) == s.charAt(r)) {
            // 向两边展开
            l--;
            r++;
        }
        // 此时 [l+1, r-1] 就是最长回文串
        return s.substring(l + 1, r);
    }
}
```

**复杂度分析**:
- 时间复杂度: O(n²)，其中n是字符串长度。每个字符作为中心扩展需要O(n)时间。
- 空间复杂度: O(1)，只使用了常数空间。

---

## 2. 移动零 (Move Zeroes)

**题目内容**: 给定一个数组 `nums`，将所有 `0` 移动到数组的末尾，同时保持非零元素的相对顺序。

**解题思路**:
- 使用双指针法，一个指针`j`指向当前非零元素应该存放的位置
- 遍历数组，遇到非零元素就与`j`位置交换，然后`j`右移
- 这样所有非零元素都会被移动到前面，零自然留在后面

**注意的点**:
- 需要保持非零元素的原始顺序
- 交换时注意不要丢失数据
- 处理null输入的情况

**代码**:
```java
public class MoveZero {
    public void moveZeroes(int[] nums) {
        if(nums == null) return;

        int n = nums.length;
        int j = 0;

        for(int i = 0; i <n; i++){
            if(nums[i]!=0){
                int tmp = nums[i];
                nums[i] =nums[j];
                nums[j++] = tmp;
            }
        }
    }
}
```

**复杂度分析**:
- 时间复杂度: O(n)，只需遍历数组一次
- 空间复杂度: O(1)，原地操作，只使用了常数空间

---

## 3. 删除排序数组中的重复项 (Remove Duplicates from Sorted Array)

**题目内容**: 给定一个排序数组，在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

**解题思路**:
- 使用双指针法，`p`指向当前不重复元素的位置，`q`用于遍历数组
- 当`nums[p]`不等于`nums[q]`时，将`nums[q]`复制到`p+1`位置，然后`p`右移
- `q`始终右移

**注意的点**:
- 数组已排序，可以利用这一特性
- 需要在原地修改数组
- 返回的是新长度，不是数组本身

**代码**:
```java
public class RemoveDuplicateNums {
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int p =0;
        int q =1;
        while(q< nums.length){
            if(nums[p] != nums[q]){
                nums[p+1] = nums[q];
                p++;
            }
            q++;
        }
        return p+1;
    }
}
```

**复杂度分析**:
- 时间复杂度: O(n)，只需遍历数组一次
- 空间复杂度: O(1)，原地操作，只使用了常数空间

---

## 4. 移除元素 (Remove Element)

**题目内容**: 给定一个数组 `nums` 和一个值 `val`，原地移除所有数值等于 `val` 的元素，并返回新数组的长度。

**解题思路**:
- 使用双指针法，从数组两端向中间遍历
- 当遇到等于`val`的元素时，与末尾元素交换，并缩小右边界
- 这样可以减少不必要的交换操作

**注意的点**:
- 需要在原地修改数组
- 元素顺序可以改变
- 交换后需要检查新交换过来的元素

**代码**:
```java
import java.util.Arrays;

public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int j = nums.length -1;
        for(int i = 0; i<=j; i++){
            if(nums[i] == val){
                swap(nums, i--, j--);
            }
        }
        return j+1;
    }
    void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
```

**复杂度分析**:
- 时间复杂度: O(n)，最多遍历数组一次
- 空间复杂度: O(1)，原地操作，只使用了常数空间

---

## 5. 两数之和 II - 输入有序数组 (Two Sum II - Input Array Is Sorted)

**题目内容**: 给定一个已按升序排列的整数数组 `numbers`，从数组中找出两个数满足相加之和等于目标数 `target`。

**解题思路**:
- 使用双指针法，一个指向开头(left)，一个指向末尾(right)
- 计算两数之和，如果大于target则右指针左移，小于则左指针右移
- 直到找到等于target的两个数

**注意的点**:
- 数组已排序，可以利用这一特性
- 返回的索引从1开始
- 题目保证有唯一解

**代码**:
```java
import java.util.Arrays;

public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int left = 0;
        int right = n-1;
        while (left<right){
            int sum = numbers[left] + numbers[right];
            if(target> sum){
                left++;
            }else if(target<sum){
                right--;
            }else{
                return new int[] {left+1, right+1};
            }
        }
        return new int[] {-1,-1};
    }
}
```

**复杂度分析**:
- 时间复杂度: O(n)，最坏情况下遍历整个数组一次
- 空间复杂度: O(1)，只使用了常数空间