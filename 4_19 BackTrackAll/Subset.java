import java.util.ArrayList;
import java.util.List;

public class Subset {

    List<List<Integer>> res = new ArrayList<>();//存储结果

    public List<List<Integer>> subsets(int[] nums) {

        List<Integer> list = new ArrayList<>();//存储子集
        backTrack(nums, 0, list);
        return res;
    }

    public void backTrack(int[]nums , int nums_index, List<Integer> list){

        res.add(new ArrayList<>(list));

        for(int i = nums_index; i < nums.length; i++){
            list.add(nums[i]);
            backTrack(nums, i+1, list);
            list.remove(list.size()-1);
        }
    }
}
