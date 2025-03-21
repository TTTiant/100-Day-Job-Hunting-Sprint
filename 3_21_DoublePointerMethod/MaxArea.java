public class MaxArea {

    public int maxArea(int[] height) {
        int MaxArea = 0;
        int left = 0, right =height.length-1;
        while (left < right) {
            MaxArea = Math.max(MaxArea, (right - left) * Math.min(height[left], height[right]));
            if(height[right] < height[left]) {
                right--;
            }else{
                left++;
            }
        }
        return MaxArea;
    }
    public static void main(String[] args) {
        MaxArea maxArea = new MaxArea();
        System.out.println(maxArea.maxArea(new int[]{1, 6, 1, 2, 2, 5}));

    }

}
