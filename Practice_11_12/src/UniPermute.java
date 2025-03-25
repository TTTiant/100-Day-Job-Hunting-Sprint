
import java.util.ArrayList;
import java.util.Arrays;

public class UniPermute{

    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    ArrayList<Integer> list = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> uniquePermute(int[] num) {
        if (num.length == 0||num == null) return res;
        Arrays.sort(num);
        boolean[] used = new boolean[num.length];
        Arrays.fill(used, false);

        backTrack(num,used,list,res);
        return res;
    }

    public void backTrack(int[] num,boolean[] used,ArrayList<Integer> list,ArrayList<ArrayList<Integer>> res){
        if (list.size() == num.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < num.length; i++) {
            if (used[i]) continue;
            if(i>0 && num[i]==num[i-1]&& !used[i-1] ) continue;
            list.add(num[i]);
            used[i] = true;
            backTrack(num,used,list,res);
            list.remove(list.size()-1);
            used[i] = false;
        }
    }
    public static void main(String[] args) {
        UniPermute uniPermute = new UniPermute();
        System.out.println(uniPermute.uniquePermute(new int[]{1,1,2}));
    }
}