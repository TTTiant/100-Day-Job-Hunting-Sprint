import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    //组合总和
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<Integer> list = new ArrayList<>();
        backTrack(candidates, 0, target, list);
        return res;
    }

    private void backTrack(int[] candidates, int index, int target, List<Integer> list) {
        if(target == 0) {
            res.add(new ArrayList<>(list));
        }

        for(int i = index; i < candidates.length; i++) {
            if(candidates[i] > target) continue;
            list.add(candidates[i]);
            backTrack(candidates, i,  target - candidates[i], list);
            list.remove(list.size() - 1);
        }
    }
    public static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();
        System.out.println(combinationSum.combinationSum(new int[]{2,3,6,7}, 7));
    }
}
