import java.util.Arrays;

public class GetChange {

    public int GetChange(int[] arr, int aim) {
        if(aim < 1){
            return 0;
        }
        //dp每个元素代表张数，索引代表所要兑换的钱数
        if(arr == null || arr.length == 0){
            return -1;
        }
        int dp[] = new int[aim+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for(int coin: arr){
            for(int i = coin; i <= aim; i++){
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        if(dp[aim]==-1){
            return -1;
        }else{
            return dp[aim];
        }

    }



}
