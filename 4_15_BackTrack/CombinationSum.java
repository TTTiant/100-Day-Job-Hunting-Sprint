import java.util.ArrayList;
import java.util.List;


public class CombinationSum {
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> list  = new ArrayList<>();
        backTrack(candidates, 0, target, list);
        return result;
    }

    private void backTrack(int[] candidates, int index, int target, List<Integer> list){
        if(target == 0){
            result.add(new ArrayList<>(list));
        }else{
            for(int i = index; i < candidates.length; i++){
                if(candidates[i] == target) continue;
                list.add(candidates[i]);
                backTrack(candidates, i, target-candidates[i], list);
                list.remove(list.size() -1);
            }
        }
    }

}
