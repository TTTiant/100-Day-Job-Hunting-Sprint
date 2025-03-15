import java.util.*;


public class LCS_2 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * longest common substring
     * @param str1 string字符串 the string
     * @param str2 string字符串 the string
     * @return string字符串
     */
    public String LCS (String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int maxLenth = 0;   //记录最长字符串的长度
        int endIndex = 0;   //记录最长字符串的最后一位的索引位置

        int[][] dp = new int[n + 1][m + 1];  //记录最长字符串的长度

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                    if (dp[i][j] > maxLenth) {
                        maxLenth = dp[i][j];
                        endIndex = i;    //记录最后的索引位置
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return str1.substring(endIndex - maxLenth, endIndex);
    }
}



