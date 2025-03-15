import java.util.*;


public class DigitsToString {

    public int solve (String s) {
        //边界检测

        if(s.length() == 0 || s == null ||s.charAt(0) == '0'){
            return 0;
        }

        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;


        //状态转移方程
        for(int i = 2; i <=n; i++) {
            int oneDigit = Integer.parseInt(s.substring(i - 1, i));
            int twoDigit = Integer.parseInt(s.substring(i - 2, i));

            if (oneDigit <= 9 && oneDigit > 0) {
                dp[i] += dp[i - 1];
            }

            if (twoDigit <= 26 && twoDigit >= 10) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];

    }
}


