import java.lang.reflect.Array;
import java.util.*;
public class LongestCommonString {


    public String LCS_1 (String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int maxlength = 0;
        int endIndex = 0;

        int dp[][] = new int[m +1][n + 1]; //dp[i][j] 含义为两字符串到i和j（包括i和j）的最长公共字串


        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;

                    if(dp[i][j] > maxlength){
                        maxlength = dp[i][j];
                        endIndex = i;
                    }
                }else{

                    dp[i][j] = 0;
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));

        return str1.substring(endIndex - maxlength, endIndex);

    }





        public String LCS_2 (String str1, String str2) {
            int m = str1.length();
            int n = str2.length();

            int[][] dp = new int[m + 1][n + 1];
            int maxLen = 0;
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        maxLen = Math.max(maxLen, dp[i][j]);
                    }
                }
            }

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dp[i][j] == maxLen) {
                        return str1.substring(i - maxLen, i);
                    }
                }
            }
            return "";

        }

    public static void main(String[] args) {
        LongestCommonString solution1 = new LongestCommonString();
        LongestCommonString solution2 = new LongestCommonString();
        String str1 = "1AB2345CD";
        String str2 = "12345EF";

        System.out.println(solution1.LCS_1(str1,str2));
        System.out.println(solution2.LCS_2(str1,str2));
    }
    }




