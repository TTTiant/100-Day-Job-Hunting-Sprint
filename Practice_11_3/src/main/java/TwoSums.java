import java.util.HashMap;
import java.util.Map;
import java.util.*;


public class TwoSums {

    //给一个不重复的数字数组，找出两个元素相加等于目标元素，例如[2,7,8,6], 返回个两数的下标

    public int[] twoSums(int[] list, int target) {
        Map<Integer, Integer> digits = new HashMap<>();

        for (int i = 0; i < list.length; i++) {
            digits.put(list[i], i);
        }

        for (int i = 0; i < list.length; i++) {
            int num1 = target - list[i];
            if (digits.containsKey(num1)&& digits.get(num1) != i) {
                return new int[]{digits.get(num1), i};
            }

        }
        return new int[]{-1,-1};
    }

    public static void main(String[] args) {
        int[] list = new int[] {2,7,8,6};
        int target = 11;

        TwoSums solution = new TwoSums();
        System.out.println(Arrays.toString(solution.twoSums(list, target)));
    }
}
