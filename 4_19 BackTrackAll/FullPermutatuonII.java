import java.util.ArrayList;
import java.util.List;

public class FullPermutatuonII {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        boolean[] used = new boolean[nums.length];
        dfs(nums, used, new ArrayList<>(), res);
        return res;
    }

    public void dfs(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> res) {
        if(list.size() == nums.length){
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            // 如果当前数字已被使用，跳过
            if(used[i]) continue;

            // 如果当前数字与前一个数字相同，并且前一个数字未被使用，跳过（剪枝）
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            used[i] = true;
            dfs(nums, used, list, res);
            list.remove(list.size() - 1);
            used[i] = false;
        }
    }
    public static void main(String[] args) {
        FullPermutatuonII f = new FullPermutatuonII();
        System.out.println(f.permute(new int[]{1, 1, 3}));
    }

}
