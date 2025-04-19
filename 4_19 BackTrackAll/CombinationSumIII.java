import java.util.ArrayList;
import java.util.List;
// 有子数组长度限制，元素不能重复
public class CombinationSumIII {
     // 选择数字范围是[1,9]
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(1, k,n, list, res);

        return res;

    }

    public void dfs(int start, int k , int remain,  List<Integer> list , List<List<Integer>> res) {
        //  终止条件
        if (k == 0 && remain == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        //终止条件
        if( k == 0 || remain <= 0){ // 剪枝： k不为0，remain = 0， 说明数量不够； k = 0， remain < 0 说明当前数组和超过n
                                    // 由于是正序遍历1-9， 在往后遍历remain只会更小更负，所以后面答案一定不对。
            return;
        }

        for(int i = start; i <= 9; i++){
            if(remain < i) break; // remain 不足选择当前数组，剪枝
            if(9 - i + 1 < k) break; // 后面数组的数量就算选了也不能到k，剪枝
            list.add(i);
            dfs(i + 1, k-1, remain -i, list, res);
            list.removeLast();
        }
    }

    public static void main(String[] args) {
        CombinationSumIII combinationSumII = new CombinationSumIII();
        System.out.println(combinationSumII.combinationSum3(3, 7));
    }
}
