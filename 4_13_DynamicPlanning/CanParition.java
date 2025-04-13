public class CanParition {
    //思路和01背包问题类似
    public boolean canPartition(int[] nums) {
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;
        sum = sum / 2;
        int n = nums.length;
        //dp[i][j]的含义是i代表物品数量，j代表背包容量
        boolean[][] dp = new boolean[n + 1][sum + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true; //i数量不管是多少，容量为0，都算是满的，所谓为true
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - nums[i - 1] < 0) { //背包容量不足不能装第i哥物品
                    dp[i][j] = dp[i - 1][j];
                }
                else {
                    //装入背包或不装入背包
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];
                }
            }
        }
        return dp[n][sum];
    }

    public static void main(String[] args) {
        CanParition cp = new CanParition();
        System.out.println(cp.canPartition(new int[]{1,2,3,4,5,6,7}));
    }
}
