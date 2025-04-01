public class findPeakElement {

    public int findPeakElement(int[] nums) {
        if(nums==null||nums.length==0) return -1;
        if(nums.length==1) return 0;
        int left = 0;
        int right = nums.length-1;
        while(left<right){
            int mid = left+(right-left)/2;  //防止整数溢出
            if(nums[mid]<nums[mid+1]){
                left = mid+1; // 峰值在右侧
            }else{
                right = mid; // 峰值在左侧
            }
        }
        return left;
    }

    public static void main(String[] args) {
        findPeakElement peakElement = new findPeakElement();
        System.out.println(peakElement.findPeakElement(
                new int[]{2,4,1,2,7,8,6}
        ));
    }
}
