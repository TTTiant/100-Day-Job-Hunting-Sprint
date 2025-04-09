public class MoveZero {
    public void moveZeroes(int[] nums) {
        if(nums == null) return;

        int n = nums.length;
        int j = 0;

        for(int i = 0; i <n; i++){
            if(nums[i]!=0){
                int tmp = nums[i];
                nums[i] =nums[j];
                nums[j++] = tmp;
            }
        }

    }
}
