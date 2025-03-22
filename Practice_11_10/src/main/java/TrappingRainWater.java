public class TrappingRainWater{

    public static int trap(int[] height) {
        //思路：木桶能装多少对取决于它最短的那块板
        //雨水问题可以看作左右有两块板的木桶，雨水量就等于两边的长度取最小值减去本身的高度
        //也就是 min（前缀最大值， 后缀最大值）- height

        int ans = 0;

        int n = height.length;
        int[] pre_max = new int[n];
        pre_max[0] = height[0];
        for (int i = 1; i < n; i++) {
            pre_max[i] = Math.max(pre_max[i - 1], height[i]);
        }

        int[] suf_max = new int[n];
        suf_max[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suf_max[i] = Math.max(suf_max[i + 1], height[i]);
        }

        for (int i = 0; i < n; i++) {
            ans += Math.min(pre_max[i],suf_max[i]) - height[i];
        }

        return ans;

        //时间复杂度O(n) 空间复杂度O(n)
    }

    public static void main(String[] args) {
        int[] example_one = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(TrappingRainWater.trap(example_one));
    }
}
