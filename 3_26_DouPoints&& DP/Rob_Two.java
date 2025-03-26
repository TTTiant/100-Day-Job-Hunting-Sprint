import java.util.*;


public class Rob_Two {

    public int rob (int[] nums) {
        // write code here
        int n = nums.length;
        if(n == 1) return nums[0];

        int[] dp = new int[n + 1];

        //偷第一家
        dp[1] = nums[0];

        for(int i = 2; i <=n; i++){
            dp[i] = Math.max(dp[i-1],nums[i-1]+ dp[i-2]);
        }
        int res = dp[n-1];

        //不偷第一家 偷最后一家
        Arrays.fill(dp,0);

        dp[1] = 0;
        for(int i = 2; i <=n; i++){
            dp[i] = Math.max(dp[i-1],nums[i-1]+ dp[i-2]);
        }

        return Math.max(res,dp[n]);
    }

    public static void main(String[] args) {
        Rob_Two robTwo = new Rob_Two();
        System.out.println(robTwo.rob(new int[]{5,2,3,4}));
    }
}