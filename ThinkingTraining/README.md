# 算法题目解析

## 1. 最长连续序列

### 题目内容
给定一个未排序的整数数组，找出最长连续序列的长度。要求算法的时间复杂度为 O(n)。

### 解题思路
1. 使用HashSet存储所有数字，实现O(1)时间的查找
2. 遍历集合中的每个数字，只有当该数字是序列的起点时（即不存在num-1）才开始计算序列长度
3. 对于每个起点数字，不断检查num+1是否存在，计算当前序列长度
4. 更新最大长度

### 注意点
- 关键优化：只有当数字是序列起点时才进行计算，避免重复计算
- 使用HashSet去重，避免重复数字影响结果
- 时间复杂度要求严格，不能使用排序

### 代码
```java
import java.util.*;

public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<>();
        for(int num: nums){
            num_set.add(num);
        }

        int longest = 0;

        for(int num: num_set){
            if(!num_set.contains(num-1)) {
                int curLength = 1;
                int curNum = num;
                while(num_set.contains(curNum+1)){
                    curLength++;
                    curNum++;
                }
                longest = Math.max(longest, curLength);
            }
        }

        return longest;
    }
}
```

### 复杂度分析
- 时间复杂度：O(n)，每个数字最多被访问两次（一次在集合中，一次在序列检查中）
- 空间复杂度：O(n)，用于存储HashSet

## 2. 数组部分反转

### 题目内容
将数组的前m个元素移动到数组末尾。例如，数组[1,2,3,4]在m=3时变为[4,1,2,3]。

### 解题思路
1. 先反转整个数组
2. 然后反转前n-m个元素
3. 最后反转剩下的m个元素
4. 这种方法通过三次反转达到效果

### 注意点
- 需要处理m大于n的情况，使用m = m % n
- 当m为0或n的倍数时，数组不变
- 反转操作需要正确处理边界条件

### 代码
```java
import java.util.Arrays;

public class ReversePartNums {
    public int[] solve (int n, int m, int[] a) {
        if(m % n == 0 || a == null) return a;

        m = m % n;
        reverse(a,0,n-1);
        reverse(a,0,m-1);
        reverse(a,m,n-1);

        return a;
    }

    public void reverse(int[] a, int start, int end){
        while(start<end){
            int temp = a[start];
            a[start] = a[end];
            a[end] = temp;
            start++;
            end--;
        }
    }
}
```

### 复杂度分析
- 时间复杂度：O(n)，三次反转操作，每个元素被访问两次
- 空间复杂度：O(1)，原地操作，不需要额外空间

## 3. 只出现一次的数字

### 题目内容
给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

### 解题思路
1. 异或解法：利用异或运算的性质（a^a=0，a^0=a）
2. HashMap解法：统计每个数字出现的次数
3. HashSet解法：添加时遇到重复就删除，最后剩下的就是结果

### 注意点
- 异或解法最优雅且效率最高
- HashMap解法更通用，可以处理出现奇数次的情况
- HashSet解法需要注意实现细节

### 代码
```java
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SingleNumber {
    // 异或解法
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    // HashMap解法
    public int singleNumber2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }

    // HashSet解法
    public int singleNumber3(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if(!set.add(num)) {
                set.remove(num);
            }
        }
        return set.iterator().next();
    }
}
```

### 复杂度分析
1. 异或解法：
    - 时间复杂度：O(n)
    - 空间复杂度：O(1)
2. HashMap解法：
    - 时间复杂度：O(n)
    - 空间复杂度：O(n)
3. HashSet解法：
    - 时间复杂度：O(n)
    - 空间复杂度：O(n)

# 4.字母异位词分组

## 题目内容
给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

## 解题思路
1. **哈希表法**：使用排序后的字符串作为键，原始字符串作为值存储在哈希表中
2. **关键步骤**：
    - 对每个字符串进行排序，得到规范化的键
    - 将相同键的字符串分组到一起
    - 最后返回哈希表中所有的值集合

## 注意点
- 字符串排序是关键步骤，确保异位词有相同的键
- 使用`getOrDefault`方法简化代码，避免空指针检查
- 时间复杂度主要取决于排序操作

## 代码实现
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupsAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            // 将字符串转换为字符数组并排序
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            
            // 使用排序后的字符串作为键
            String key = new String(arr);
            
            // 获取或创建该键对应的列表
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            
            // 更新哈希表
            map.put(key, list);
        }
        
        // 返回所有分组
        return new ArrayList<List<String>>(map.values());
    }
}
```

## 复杂度分析
- **时间复杂度**：O(NKlogK)，其中N是字符串数组的长度，K是最长字符串的长度
- **空间复杂度**：O(NK)，需要存储所有字符串的排序版本和分组结果

