import java.util.Arrays;

public class FindCheapestPrice {
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // n城市总数
        // flights为路径起点终点和费用
        // src为起点
        // dst为终点
        // k为中转次数，航班次数=k+1

        //dp[t][i] 为中转t到达i的最小价格
        //如果允许 K 次中转，则最多可以乘坐 K + 1 次航班。
        int dp[][]  = new int[k+2][n];

        //赋值初始值为无限大费用，为都不可达
        for(int[] row : dp){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dp[0][src] = 0; //地点和终点相等，费用为0

        for(int t = 1; t <= k+1; t++){
            dp[t][src] = 0; //起点到起点，不管几次始终可达
            for(int[] flight : flights){
                int s = flight[0], d = flight[1], cost = flight[2];
                if(dp[t-1][s] != Integer.MAX_VALUE){ //如果上次的终点，为不可达就不能计算下一次，所以要判断
                    dp[t][d] = Math.min(dp[t-1][s]+ cost, dp[t][d]); //t次不同路线到d终点的最小花费
                }
            }
        }
        //找出小于k+1次中转的最小值
        int minCost = Integer.MAX_VALUE;
        for(int t = 0; t <= k+1; t++){
            if(dp[t][dst] < minCost){
                minCost = dp[t][dst];
            }
        }
        return minCost;
    }
    public static void main(String[] args) {
        FindCheapestPrice fp = new FindCheapestPrice();
        int[][] flights = new int[][]{
                {0,1,100},
                {1,2,100},
                {2,0,100},
                {1,3,150},
                {2,3,200}
        };
        System.out.println(fp.findCheapestPrice(5, flights, 1, 3, 2));
    }
}
