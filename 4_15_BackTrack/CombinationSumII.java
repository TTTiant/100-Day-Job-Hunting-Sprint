import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> state = new ArrayList<>();
        Arrays.sort(candidates);
        int start = 0; //遍历起始点
        List<List<Integer>> res = new ArrayList<>();

        backtrack(state, target, candidates, start, res);
        return res;
    }

    private void backtrack(List<Integer> state, int target, int[] choices, int start,List<List<Integer>> res){
        // 子集和等于target时，记录解
        if(target == 0){
            res.add(new ArrayList<>(state));
            return;
        }

        for(int i = start; i < choices.length; i++){
            if(target - choices[i] < 0) break;
            if(i > start && choices[i] == choices[i-1]) continue;
            state.add(choices[i]);
            backtrack(state, target-choices[i], choices,i+1,res);
            state.remove(state.size()-1);
        }

    }
}
