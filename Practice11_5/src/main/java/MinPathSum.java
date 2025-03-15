public class MinPathSum {

    public int minPathSum(int[][] matrix){

        int n = matrix.length;
        int m = matrix[0].length;

        int[][] dp = new int[n+1][m+1];

        dp[0][0] = matrix[0][0];

        for(int i = 1; i < n; i++)
            dp[i][0] = matrix[i][0] + dp[i-1][0];

        for(int j = 1; j < m; j++)
            dp[0][j] = matrix[0][j] + dp[0][j-1];

        for(int i = 1; i <n; i++){
            for(int j = 1; j <m; j++){
                if(dp[i - 1][j] > dp[i][j - 1]){
                    dp[i][j] = matrix[i][j] +dp[i][j-1];
                }else{
                    dp[i][j] = matrix[i][j] +dp[i-1][j];
                }
            }

        }
        return dp[n - 1][m - 1];
    }


    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 9},
                {8, 1, 3, 4},
                {5, 0, 6, 1},
                {8, 8, 4, 0}
        };
        MinPathSum solution = new MinPathSum();

        System.out.println(solution.minPathSum(matrix));
    }

}
