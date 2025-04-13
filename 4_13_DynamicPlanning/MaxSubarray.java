public class MaxSubarray {

    //动态规划解法
    //如果把dp[i]定义为到i的子数组最大值，会发现dp[i]和dp[i-1]，
    // 子问题没有关联性，无法写出状态转移方程。
    //所以要把dp[i]定义为 到i为结尾的子数组最大值，重点是i为结尾。

    public int maxSubarray(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for(int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
        }
        int res = Integer.MIN_VALUE;
        for(int i = 1; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;

    }

//    滑动窗口解法：
//    首先讨论一种特殊情况，就是 nums 中全是负数的时候，此时算法是可以得到正确答案的，
// 答案就是0
//    接下来讨论一般情况，nums 中有正有负，这种情况下元素和最大的那个子数组一定是以正数开头的
//    （以负数开头的话，把这个负数去掉，就可以得到和更大的子数组了，与假设相矛盾）。
//    那么此时我们需要穷举所有以正数开头的子数组，计算他们的元素和，找到元素和最大的那个子数组
    public int maxSubarray2(int[] nums) {
        int right = 0, left = 0;
        int windowSum = 0, maxSum = 0;
        while (right < nums.length) {
            windowSum += nums[right];
            right++;

            //判断最大值
            maxSum = windowSum > maxSum ? windowSum : maxSum;

            while(windowSum < 0){
                windowSum -= nums[left];
                left++;

            }
        }
        return maxSum;
    }


    public static void main(String[] args) {
        MaxSubarray maxSubarray = new MaxSubarray();
        int[] nums = {-2, 1,-3,5, -1,2, -5};
        System.out.println(maxSubarray.maxSubarray(nums));
        MaxSubarray maxSubarray2 = new MaxSubarray();
        System.out.println(maxSubarray2.maxSubarray2(nums));
    }
}

