public class RemoveDuplicateNums {

    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int p =0;
        int q =1;
        while(q< nums.length){
            if(nums[p] != nums[q]){
                nums[p+1] = nums[q];
                p++;
            }
            q++;
        }
        return p+1;
    }

    public static void main(String[] args) {
        RemoveDuplicateNums test = new RemoveDuplicateNums();
        System.out.println(test.removeDuplicates(new int[]{1,1,2}));
    }

}

