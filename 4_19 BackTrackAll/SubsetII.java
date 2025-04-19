import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetII {
    public List<List<Integer>> subset3(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        return res;
    }

    private void dfs(int[] nums, int start, List<Integer> list, List<List<Integer>> res) {
        res.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            //跳过重复元素
            if (i > start && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);
            dfs(nums, i + 1, list, res);
            list.remove(list.size() - 1);

        }
    }
}
