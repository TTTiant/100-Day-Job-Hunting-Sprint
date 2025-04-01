class FindDuplicate{
    public int findDuplicate1(int[] nums) {

        //二分查找，遍历[1,n]整数，因为题目要求
        int len = nums.length; // n+1 = len ; n = len - 1
        int left = 1;
        int right = len - 1;

        while(left < right){
            int mid = (left + right) / 2;
            // 计算<= mid的数有多少
            int count = 0;

            for(int num: nums){
                if(num <= mid){
                    count++;
                }
            }

            if(count > mid){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return left;

    }
    //时间复杂度 nlogn


    public int findDuplicate2(int[] nums) {
        int slow = 0, fast = 0;
        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while(slow != fast);

        slow = 0;
        while(fast != slow){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        FindDuplicate test1 = new FindDuplicate();
        FindDuplicate test2 = new FindDuplicate();
        int[] nums = {1,3,4,2,2};
        System.out.println(test1.findDuplicate1(nums));
        System.out.println(test2.findDuplicate2(nums));
    }
}