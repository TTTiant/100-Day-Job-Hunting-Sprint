public class numSquares {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int leastSquares = Integer.MAX_VALUE;
            for (int j = 0; j * j < i; j++) {
                leastSquares = Math.min(leastSquares, dp[i - j * j] + 1);
            }
            dp[i] = leastSquares + 1;
        }
        return dp[n];
    }

    public static void main(String[] args){
        numSquares obj = new numSquares();
        System.out.println(obj.numSquares(8));

    }

}
