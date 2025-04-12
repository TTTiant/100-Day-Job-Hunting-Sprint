public class ZeroOneKnapsack {
    public int knapsack(int W, int N, int[] wt, int[] val){
        int[][] dp = new int[N+1][W+1];
        for (int w = 0; w <= W; w++) {
            dp[0][w] = 0;
        }
        for(int i = 1; i <= N; i++){ //数量
            for(int w = 1; w <= W; w++){ //重量
                if(w < wt[i-1]){ //总重量小于物体重量，不计算
                    dp[i][w] = dp[i-1][w];
                }else{
                    dp[i][w] = Math.max(
                            dp[i-1][w-wt[i-1]] + val[i-1], //加入新物品
                            dp[i-1][w]  //不加入新物品
                    );
                }
            }
        }
        return dp[N][W];
    }



    public static void main(String[] args) {
        ZeroOneKnapsack obj = new ZeroOneKnapsack();
        int W = 10;
        int N = 3;
        int[] wt = new int[]{1,2,3,4,5};
        int[] val = new int[]{1,5,1,4,5};
        System.out.println(obj.knapsack(W, N, wt, val));
    }

}
