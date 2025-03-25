import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class Permute {


    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    LinkedList<Integer> list = new LinkedList<>();

    public ArrayList<ArrayList<Integer>> permute (int[] num ){

        if(num == null || num.length == 0) return res;
        boolean[] used = new boolean[num.length];
        backTrack(num,used, list);

        return res;
    }

    public void backTrack(int[] num, boolean[] used, LinkedList<Integer> list){
        if(list.size() == num.length){
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = 0; i < num.length; i++){
            if(used[i]) continue;  //使用boolean 降低复杂度
            used[i] = true;
            list.add(num[i]);
            backTrack(num, used,list);

            //回溯
            list.removeLast();    //撤销选择
            used[i] = false;  //恢复状态
        }

    }
    public static  void main(String[] args){
        Permute permute = new Permute();
        System.out.println(permute.permute(new int[]{1,2,3}));
    }
}
