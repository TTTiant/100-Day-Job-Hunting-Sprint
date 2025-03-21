import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;


public class TwoSums {
    // 方法 1: 暴力解法
    public static int[] solution1(int[] nums, int target) {
        // 实现代码
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }

            }
        }
        return new int[]{-1, -1};
    }

    // 方法 2: 哈希表解法
    public static int[] solution2(int[] nums, int target) {
        // 实现代码
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
        // 实现代码
        //建立个二维数组，存入索引和值
        int[][] indexedNums = new int[nums.length][2];

        for (int i = 0; i < nums.length; i++) {
            indexedNums[i][0] = nums[i];
            indexedNums[i][1] = i;
        }
        //给数组排序为新数组
        Arrays.sort(indexedNums, (a, b) -> Integer.compare(a[0], b[0]));


        //设置左右两个指针，同时向中间遍历数组。
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int sum = indexedNums[left][0] + indexedNums[right][0];
            if (sum == target) {
                // 返回原始索引
                return new int[]{indexedNums[left][1], indexedNums[right][1]};
            } else if (sum < target) {
                left++; // 和太小，左指针右移
            } else {
                right--; // 和太大，右指针左移
            }
        }
        return new int[0];

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution3(new int[]{2, 11, 7, 15}, 9)));
    }
}

