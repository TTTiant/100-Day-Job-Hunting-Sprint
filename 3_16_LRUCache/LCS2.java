import java.util.*;


public class LCS2 {


    public String LCS (String s1, String s2) {
        // write code here
        //dp[i][j] 记录的是表示字符串str1的前i个字符和字符串str2的前j个字符的最长公共子序列的长度。
        int m = s1.length();
        int n = s2.length();

        int[][] dp =new int[m + 1][n + 1];

        //状态转移方程， 如果s1[i-1] == s2[i-1], 那么dp[i][j] = dp[i-1][j-1] +1;
        // 如果不等于 dp[i][j] = max(dp[i-1][j], dp[i][j-1])

        for(int i = 1;  i <= m; i++){ //从int i = 1,开始因为从要计算i-1, i = 0 会越界
            for(int j = 1; j <= n; j++){
                if(s1.charAt(i - 1) == s2.charAt(j - 1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        if(dp[m][n] == 0){
            return "-1";
        }
        // 回溯 然后构建最长公共子序列字符串
        StringBuilder lcs = new StringBuilder();
        int i = m;
        int j = n;
        while(i > 0 && j > 0){
            if(s1.charAt(i - 1) == s2.charAt(j - 1)){
                lcs.append(s1.charAt(i - 1));
                i--;
                j--;
            }else if(dp[i-1][j] > dp[i][j-1]){
                i--;
            }else{
                j--;
            }
        }
        return lcs.reverse().toString();


    }
}