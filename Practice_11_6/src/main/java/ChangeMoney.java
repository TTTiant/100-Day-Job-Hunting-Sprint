import java.util.*;
public class ChangeMoney {
    public int minMoney (int[] arr, int aim) {

        if(aim < 1)
            return 0;

        int[] dp = new int[aim + 1];

        Arrays.fill(dp, aim + 1);
        dp[0] = 0;

        for(int i = 1; i <= aim; i++){

            for(int coin = 0; coin < arr.length; coin++){
                if(arr[coin] <= i)
                    dp[i] = Math.min(dp[i], dp[i - arr[coin]] + 1);
            }
        }
        if(dp[aim] > aim){
            return -1;
        }else {
            return dp[aim];
        }
    }
}
