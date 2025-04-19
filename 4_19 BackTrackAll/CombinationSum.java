import java.util.ArrayList;
import java.util.List;

// 回溯算法判断： 子数组长度限制，元素能不能重复选。

public class CombinationSum {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {



        dfs(candidates, target,0, new ArrayList<>(), res);
        return res;
    }

    public void dfs(int[] candidates, int target, int start, List<Integer> list,  List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(list));
        }else{
            for (int i = start; i < candidates.length; i++) {
                if(target < candidates[i]) continue;

                list.add(candidates[i]);
                dfs(candidates, target - candidates[i], i, list, res);

                list.remove(list.size() - 1);
            }
        }

    }

    public static void main(String[] args) {
        CombinationSum c = new CombinationSum();
        System.out.println(c.combinationSum(new int[]{2,3,6,7}, 7));

    }
}
