public class CompleteKnapsack {
    //和CoinChange思路一致，CoinChang求最小值，完全背包问题求最大值

    public int completeKnapsack(int W, int[] wt, int[] val) {
        int[] dp = new int[W + 1];
        for (int i = 0; i < wt.length; i++) {
            for (int w = wt[i]; w <= W; w++) { // 正序遍历，允许重复选
                dp[w] = Math.max(dp[w], dp[w - wt[i]] + val[i]);
            }
        }
        return dp[W];
    }

    public static void main(String[] args) {
        CompleteKnapsack completeKnapsack = new CompleteKnapsack();
        int[] wt = new int[]{2, 3, 5};
        int[] val = new int[]{10, 5, 1};
        int W = wt.length;
        System.out.println(completeKnapsack.completeKnapsack(W, wt, val));
    }

}
