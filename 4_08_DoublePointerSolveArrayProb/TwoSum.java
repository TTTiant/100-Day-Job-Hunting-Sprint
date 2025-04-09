import java.util.Arrays;

public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int left = 0;
        int right = n-1;
        while (left<right){
            int sum = numbers[left] + numbers[right];
            if(target> sum){
                left++;
            }else if(target<sum){
                right--;
            }else{
                return new int[] {left+1, right+1};
            }
        }
        return new int[] {-1,-1};
    }

    public static void main(String[] args) {
        int[] ans = new TwoSum().twoSum(new int[]{2,7,11,15}, 9);
        System.out.println(Arrays.toString(ans));

    }
}

