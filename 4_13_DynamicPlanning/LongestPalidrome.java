public class LongestPalidrome {
    public int longestPalindrome(String word) {

        int n = word.length();
        if(n == 0 || n == 1) return n;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        //遍历顺序要注意，从状态转移方程知道要想知道dp[i][j],需要知道dp[i-1][j-1]，dp[i+1][j]和dp[i][j-1]，
        //所以根据dp table 要向右上方遍历，i逆序，j要大于i且不能超过n。
        for(int i = n -1; i >= 0; i--) {
            for(int j = i + 1; j < n; j++) {
                if(word.charAt(i) == word.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else{
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }

    public static void main(String[] args) {
        LongestPalidrome lp = new LongestPalidrome();
        System.out.println(lp.longestPalindrome("abbaabbaa"));
    }


}
