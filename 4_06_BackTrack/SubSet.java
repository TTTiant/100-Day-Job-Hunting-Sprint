import java.util.ArrayList;
import java.util.List;

public class SubSet {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> list = new ArrayList<>();
        backTrack(nums, 0, list);
        return res;


    }

    private void backTrack(int[] nums, int index, List<Integer> list) {
        res.add(new ArrayList<>(list)); //  添加子集到res
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            backTrack(nums, i + 1, list);
            list.remove(list.size() - 1);  //移除list最后一个元素
        }
    }
    public static void main(String[] args) {
        SubSet subSet = new SubSet();
        System.out.println(subSet.subsets(new int[]{1, 2, 3}));
    }

}
