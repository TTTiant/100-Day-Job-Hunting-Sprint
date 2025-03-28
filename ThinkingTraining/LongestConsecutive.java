import java.util.*;

public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        //本题要求时间复杂度O(n)
        //所以不能用Arrays.sort()对数组惊醒排序，此为O(nlogn)
        Set<Integer> num_set = new HashSet<>();
        for(int num: nums){
            num_set.add(num);
        }

        int longest = 0;

        for(int num: num_set){
            if(!num_set.contains(num+1)){ //这步很关键，可以省去遍历一些元素，从而使每个元素之遍历1遍，复杂度降为O(n)
                int curLength = 1;
                int curNum = num;
                while(num_set.contains(curNum-1)){
                    curLength++;
                    curNum--;
                }
                longest = Math.max(longest, curLength);
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        LongestConsecutive l = new LongestConsecutive();
        System.out.println(l.longestConsecutive(new int[]{1,100,5,4,4,3,2,12,7}));
    }
}
