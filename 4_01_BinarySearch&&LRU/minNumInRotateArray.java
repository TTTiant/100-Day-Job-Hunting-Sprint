public class minNumInRotateArray {

    public int minNumberInRotateArray (int[] nums) {
        // write code here
        if(nums.length == 0){
            return 0;
        }
        int i = 0;
        int j = nums.length - 1;
        while(i < j){
            int mid = (i + j) /2;
            if(nums[mid] > nums[j]) i = mid + 1;
            else if(nums[mid] < nums[j]) j = mid;

            else j--;

        }

        return nums[i];
    }

    public static void main(String[] args) {
        minNumInRotateArray minNumInRotateArray = new minNumInRotateArray();
        System.out.println(minNumInRotateArray.minNumberInRotateArray(
                new int[]{4,5,1,2,3}
        ));
    }
}
