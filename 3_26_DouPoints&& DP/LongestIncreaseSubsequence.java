import java.util.*;


public class LongestIncreaseSubsequence {

    public int LIS (int[] arr) {
        // write code here
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);
        int res = 1;   // 最长升序子序列 至少为1
        for(int i = 1; i < arr.length; i++){
            for(int j = 0; j < i; j++){
                if(arr[j]< arr[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }

            }
            res = Math.max(res, dp[i]);
        }
        return res;

    }

    public static void main(String[] args) {
        LongestIncreaseSubsequence lis = new LongestIncreaseSubsequence();
        System.out.println(lis.LIS(new int[]{1,2,3,1,2,2,5,6,9,7}));
    }
}