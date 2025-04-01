class SortColors {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int ptr = 0;
        for(int i = 0; i < n; i++){
            if(nums[i] == 0){
                swap(nums, i, ptr);
                ptr++;
            }
        }
        for(int i = ptr; i< n; i++){//从ptr位置开始遍历
            if(nums[i] == 1){
                swap(nums, i, ptr);
                ptr++;
            }
        }

    }

    public void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}