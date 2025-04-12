import javax.swing.tree.TreeNode;

public class ShortestPathSum {
    public int longestPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        ShortestPathSum shortestPathSum = new ShortestPathSum();
        System.out.println(shortestPathSum.longestPathSum(new int[][]{
                {1,2,3,4,0},
                {1,2,3,4,1},
                {1,2,3,4,2},
                {1,2,3,4,3},
                {1,2,3,4,4},
        }));
    }

}
