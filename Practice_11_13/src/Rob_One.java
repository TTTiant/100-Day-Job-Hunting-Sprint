import java.util.*;

public class Rob_One {

    public int rob (int[] nums) {
        // write code here
        if(nums == null) return 0;
        int n = nums.length;
        if(n == 1) return nums[0];

        int[] dp = new int[n + 1]; //dp含义为长度为i的数组最多能偷多少钱

        dp[1] = nums[0];

        for(int i = 2; i <=n;i++){
            dp[i] = Math.max(dp[i-1], nums[i-1] + dp[i-2]) ;
        }

        return dp[n];
    }

    public static void main(String[] args) {
        Rob_One robOne = new Rob_One();
        System.out.println(robOne.rob(new int[]{1,2,3,1}));
    }
}

