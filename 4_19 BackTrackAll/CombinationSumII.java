import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(candidates);
        int start = 0;
        dfs(candidates, target, start, new ArrayList<>(), res);


        return res;
    }

    public void dfs(int[] candidates, int target, int start, List<Integer> list, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (target - candidates[i] < 0) break;
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            list.add(candidates[i]);
            dfs(candidates, target - candidates[i], i+1, list, res);
            list.remove(list.size() - 1);


        }

    }
    public static void main(String[] args) {
        CombinationSumII c = new CombinationSumII();
        List<List<Integer>> res = c.combinationSum2(new int[]{1,2,3,5,6,7}, 9);
        System.out.println(res);
    }
}
