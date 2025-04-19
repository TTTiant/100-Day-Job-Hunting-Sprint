import java.util.ArrayList;
import java.util.List;

public class Combination {
    public List<List<Integer>> combination(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(1, n, k, new ArrayList<>(), res);
        return res;

    }

    private void dfs(int start, int n, int k, List<Integer> list, List<List<Integer>> res) {
        if (k == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= n; i++) {
            list.add(i);
            dfs(i + 1, n, k - 1, list, res);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        Combination combination = new Combination();
        System.out.println(combination.combination(4, 2));
    }
}
