import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSums {
    public List<List<Integer>> threeSum(int[] nums) {
          Arrays.sort(nums);
          List<List<Integer>> result = new ArrayList<>();   //存储结果
          for (int i = 0; i < nums.length - 2; i++) { //
              if (i > 0 && nums[i] == nums[i - 1]) continue;
              int left = i + 1, right = nums.length - 1;
              int target = -nums[i];

              while (left < right) {
                  int sum = nums[left] + nums[right];
                  if (sum == target) {
                      result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                      left++;
                      right--;
                      while (left < right && nums[left] == nums[left - 1]) left++; //跳过
                      while (left < right && nums[right] == nums[right + 1]) right--;
                  }else if (sum < target) {
                      left++;
                  }else{
                      right--;
                  }

              }

          }
return result;
    }

    public static void main(String[] args) {
        ThreeSums threeSums = new ThreeSums();
        List<List<Integer>> result = threeSums.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(result);
    }
}
