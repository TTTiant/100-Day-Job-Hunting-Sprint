import java.util.ArrayList;
import java.util.List;

public class FullPermutation {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        if(nums == null || nums.length == 0) return res;
        dfs(nums, new boolean[nums.length],list, res);
        return res;

    }

    private void dfs(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> res) {
        if(list.size() == nums.length){
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(!used[i]){
                list.add(nums[i]);
                used[i] = true;
                dfs(nums, used, list, res);
                list.remove(list.size() - 1);
                used[i] = false;
            }
        }
    }
    public static void main(String[] args) {
        FullPermutation f = new FullPermutation();
        System.out.println(f.permute(new int[]{1, 2, 3}));

    }

}
