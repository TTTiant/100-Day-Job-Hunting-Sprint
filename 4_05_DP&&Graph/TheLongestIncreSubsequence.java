public class TheLongestIncreSubsequence {

    public int lengthOfLIS(int[] nums) {
        // 边界检测
        if(nums.length == 0) return 0;

        //dp[i] 为到i为止 最长递增子序列的长度
        int[] dp = new int [nums.length + 1];
        //双层遍历，外层i为dp[i]赋值，内层j找到比dp[i]小的dp[j],即dp[i] = dp[j]+1
        dp[0] = 1;
        int res = 1;  // 设置最长递增子序列长度

        for(int i = 1; i < nums.length; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if (nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;

    }

    public int lengthOfLIS2(int[] nums) {

//        tail[i] 表示长度为 i+1 的所有递增子序列中，最小的末尾元素。
        int[] tail = new int[nums.length];
        int size = 0;
        for(int num : nums){
            int left = 0;
            int right = size;
            while(left < right){
                int mid = left + (right - left) / 2;
                if(tail[mid] < num){
                    left = mid + 1;
                }else{
                    right = mid;
                }
            }
            if(left == size){
                tail[size++] = num;
            }else{
                tail[left] = num;
            }
        }
        return size;

    }

    public static void main(String[] args) {
        TheLongestIncreSubsequence obj = new TheLongestIncreSubsequence();
        System.out.println(obj.lengthOfLIS(new int[]{3,5,4,6,1,9,}));
        System.out.println(obj.lengthOfLIS2(new int[]{3,5,4,1,2,9}));
    }
}

