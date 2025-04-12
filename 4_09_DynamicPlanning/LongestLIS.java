import java.util.Arrays;

public class LongestLIS {
    public static int longestLIS(int[] arr) {
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) { // 递归找i之前并且比i小的元素
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(longestLIS(new int[]{1,2,4,4,12,6,7,8,9,11}));
    }
}
