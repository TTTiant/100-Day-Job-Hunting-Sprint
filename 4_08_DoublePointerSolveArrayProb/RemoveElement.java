import java.util.Arrays;

public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int j = nums.length -1;
        for(int i = 0; i<=j; i++){
            if(nums[i] == val){
                swap(nums, i--, j--);
            }
        }
        return j+1;
    }
    void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;

    }

    public static void  main(String[] args) {
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int val = 2;
        RemoveElement test = new RemoveElement();
        System.out.println(test.removeElement(nums, val));
        System.out.println(Arrays.toString(nums));
    }
}
