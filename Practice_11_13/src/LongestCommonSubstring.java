public class LongestCommonSubstring {

    public String longestCommonSubstring(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        int maxLength = 0;
        int endIndex = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        endIndex = i-1;
                    }
                }else{
                    dp[i][j] = 0;
                }
            }
        }
        return text1.substring(endIndex - maxLength +1,endIndex+1);

    }

    public static void main(String[] args) {
        LongestCommonSubstring longestCommonSubstring = new LongestCommonSubstring();
        String text1 = "abcabcbb";
        String text2 = "abvabcbbbb";
        System.out.println(longestCommonSubstring.longestCommonSubstring(text1, text2));

    }

}
